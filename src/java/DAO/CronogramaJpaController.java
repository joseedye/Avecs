/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.NonexistentEntityException;
import DTO.Cronograma;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.Solicitud;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author rozo-
 */
public class CronogramaJpaController implements Serializable {

    public CronogramaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cronograma cronograma) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Solicitud idSolicitud = cronograma.getIdSolicitud();
            if (idSolicitud != null) {
                idSolicitud = em.getReference(idSolicitud.getClass(), idSolicitud.getIdSolicitud());
                cronograma.setIdSolicitud(idSolicitud);
            }
            em.persist(cronograma);
            if (idSolicitud != null) {
                idSolicitud.getCronogramaList().add(cronograma);
                idSolicitud = em.merge(idSolicitud);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cronograma cronograma) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cronograma persistentCronograma = em.find(Cronograma.class, cronograma.getId());
            Solicitud idSolicitudOld = persistentCronograma.getIdSolicitud();
            Solicitud idSolicitudNew = cronograma.getIdSolicitud();
            if (idSolicitudNew != null) {
                idSolicitudNew = em.getReference(idSolicitudNew.getClass(), idSolicitudNew.getIdSolicitud());
                cronograma.setIdSolicitud(idSolicitudNew);
            }
            cronograma = em.merge(cronograma);
            if (idSolicitudOld != null && !idSolicitudOld.equals(idSolicitudNew)) {
                idSolicitudOld.getCronogramaList().remove(cronograma);
                idSolicitudOld = em.merge(idSolicitudOld);
            }
            if (idSolicitudNew != null && !idSolicitudNew.equals(idSolicitudOld)) {
                idSolicitudNew.getCronogramaList().add(cronograma);
                idSolicitudNew = em.merge(idSolicitudNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cronograma.getId();
                if (findCronograma(id) == null) {
                    throw new NonexistentEntityException("The cronograma with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cronograma cronograma;
            try {
                cronograma = em.getReference(Cronograma.class, id);
                cronograma.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cronograma with id " + id + " no longer exists.", enfe);
            }
            Solicitud idSolicitud = cronograma.getIdSolicitud();
            if (idSolicitud != null) {
                idSolicitud.getCronogramaList().remove(cronograma);
                idSolicitud = em.merge(idSolicitud);
            }
            em.remove(cronograma);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cronograma> findCronogramaEntities() {
        return findCronogramaEntities(true, -1, -1);
    }

    public List<Cronograma> findCronogramaEntities(int maxResults, int firstResult) {
        return findCronogramaEntities(false, maxResults, firstResult);
    }

    private List<Cronograma> findCronogramaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cronograma.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Cronograma findCronograma(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cronograma.class, id);
        } finally {
            em.close();
        }
    }

    public int getCronogramaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cronograma> rt = cq.from(Cronograma.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
