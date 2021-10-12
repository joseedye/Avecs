/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.IllegalOrphanException;
import DAO.exceptions.NonexistentEntityException;
import DTO.EstatusSolicitud;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.Solicitud;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Leonardo
 */
public class EstatusSolicitudJpaController implements Serializable {

    public EstatusSolicitudJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EstatusSolicitud estatusSolicitud) {
        if (estatusSolicitud.getSolicitudList() == null) {
            estatusSolicitud.setSolicitudList(new ArrayList<Solicitud>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Solicitud> attachedSolicitudList = new ArrayList<Solicitud>();
            for (Solicitud solicitudListSolicitudToAttach : estatusSolicitud.getSolicitudList()) {
                solicitudListSolicitudToAttach = em.getReference(solicitudListSolicitudToAttach.getClass(), solicitudListSolicitudToAttach.getIdSolicitud());
                attachedSolicitudList.add(solicitudListSolicitudToAttach);
            }
            estatusSolicitud.setSolicitudList(attachedSolicitudList);
            em.persist(estatusSolicitud);
            for (Solicitud solicitudListSolicitud : estatusSolicitud.getSolicitudList()) {
                EstatusSolicitud oldEstatusOfSolicitudListSolicitud = solicitudListSolicitud.getEstatus();
                solicitudListSolicitud.setEstatus(estatusSolicitud);
                solicitudListSolicitud = em.merge(solicitudListSolicitud);
                if (oldEstatusOfSolicitudListSolicitud != null) {
                    oldEstatusOfSolicitudListSolicitud.getSolicitudList().remove(solicitudListSolicitud);
                    oldEstatusOfSolicitudListSolicitud = em.merge(oldEstatusOfSolicitudListSolicitud);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EstatusSolicitud estatusSolicitud) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EstatusSolicitud persistentEstatusSolicitud = em.find(EstatusSolicitud.class, estatusSolicitud.getId());
            List<Solicitud> solicitudListOld = persistentEstatusSolicitud.getSolicitudList();
            List<Solicitud> solicitudListNew = estatusSolicitud.getSolicitudList();
            List<String> illegalOrphanMessages = null;
            for (Solicitud solicitudListOldSolicitud : solicitudListOld) {
                if (!solicitudListNew.contains(solicitudListOldSolicitud)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Solicitud " + solicitudListOldSolicitud + " since its estatus field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Solicitud> attachedSolicitudListNew = new ArrayList<Solicitud>();
            for (Solicitud solicitudListNewSolicitudToAttach : solicitudListNew) {
                solicitudListNewSolicitudToAttach = em.getReference(solicitudListNewSolicitudToAttach.getClass(), solicitudListNewSolicitudToAttach.getIdSolicitud());
                attachedSolicitudListNew.add(solicitudListNewSolicitudToAttach);
            }
            solicitudListNew = attachedSolicitudListNew;
            estatusSolicitud.setSolicitudList(solicitudListNew);
            estatusSolicitud = em.merge(estatusSolicitud);
            for (Solicitud solicitudListNewSolicitud : solicitudListNew) {
                if (!solicitudListOld.contains(solicitudListNewSolicitud)) {
                    EstatusSolicitud oldEstatusOfSolicitudListNewSolicitud = solicitudListNewSolicitud.getEstatus();
                    solicitudListNewSolicitud.setEstatus(estatusSolicitud);
                    solicitudListNewSolicitud = em.merge(solicitudListNewSolicitud);
                    if (oldEstatusOfSolicitudListNewSolicitud != null && !oldEstatusOfSolicitudListNewSolicitud.equals(estatusSolicitud)) {
                        oldEstatusOfSolicitudListNewSolicitud.getSolicitudList().remove(solicitudListNewSolicitud);
                        oldEstatusOfSolicitudListNewSolicitud = em.merge(oldEstatusOfSolicitudListNewSolicitud);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = estatusSolicitud.getId();
                if (findEstatusSolicitud(id) == null) {
                    throw new NonexistentEntityException("The estatusSolicitud with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EstatusSolicitud estatusSolicitud;
            try {
                estatusSolicitud = em.getReference(EstatusSolicitud.class, id);
                estatusSolicitud.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estatusSolicitud with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Solicitud> solicitudListOrphanCheck = estatusSolicitud.getSolicitudList();
            for (Solicitud solicitudListOrphanCheckSolicitud : solicitudListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This EstatusSolicitud (" + estatusSolicitud + ") cannot be destroyed since the Solicitud " + solicitudListOrphanCheckSolicitud + " in its solicitudList field has a non-nullable estatus field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(estatusSolicitud);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EstatusSolicitud> findEstatusSolicitudEntities() {
        return findEstatusSolicitudEntities(true, -1, -1);
    }

    public List<EstatusSolicitud> findEstatusSolicitudEntities(int maxResults, int firstResult) {
        return findEstatusSolicitudEntities(false, maxResults, firstResult);
    }

    private List<EstatusSolicitud> findEstatusSolicitudEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EstatusSolicitud.class));
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

    public EstatusSolicitud findEstatusSolicitud(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EstatusSolicitud.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstatusSolicitudCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EstatusSolicitud> rt = cq.from(EstatusSolicitud.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
