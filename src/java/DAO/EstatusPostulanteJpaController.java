/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.IllegalOrphanException;
import DAO.exceptions.NonexistentEntityException;
import DTO.EstatusPostulante;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.Postulante;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Leonardo
 */
public class EstatusPostulanteJpaController implements Serializable {

    public EstatusPostulanteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(EstatusPostulante estatusPostulante) {
        if (estatusPostulante.getPostulanteList() == null) {
            estatusPostulante.setPostulanteList(new ArrayList<Postulante>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Postulante> attachedPostulanteList = new ArrayList<Postulante>();
            for (Postulante postulanteListPostulanteToAttach : estatusPostulante.getPostulanteList()) {
                postulanteListPostulanteToAttach = em.getReference(postulanteListPostulanteToAttach.getClass(), postulanteListPostulanteToAttach.getId());
                attachedPostulanteList.add(postulanteListPostulanteToAttach);
            }
            estatusPostulante.setPostulanteList(attachedPostulanteList);
            em.persist(estatusPostulante);
            for (Postulante postulanteListPostulante : estatusPostulante.getPostulanteList()) {
                EstatusPostulante oldEstatusOfPostulanteListPostulante = postulanteListPostulante.getEstatus();
                postulanteListPostulante.setEstatus(estatusPostulante);
                postulanteListPostulante = em.merge(postulanteListPostulante);
                if (oldEstatusOfPostulanteListPostulante != null) {
                    oldEstatusOfPostulanteListPostulante.getPostulanteList().remove(postulanteListPostulante);
                    oldEstatusOfPostulanteListPostulante = em.merge(oldEstatusOfPostulanteListPostulante);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(EstatusPostulante estatusPostulante) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EstatusPostulante persistentEstatusPostulante = em.find(EstatusPostulante.class, estatusPostulante.getId());
            List<Postulante> postulanteListOld = persistentEstatusPostulante.getPostulanteList();
            List<Postulante> postulanteListNew = estatusPostulante.getPostulanteList();
            List<String> illegalOrphanMessages = null;
            for (Postulante postulanteListOldPostulante : postulanteListOld) {
                if (!postulanteListNew.contains(postulanteListOldPostulante)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Postulante " + postulanteListOldPostulante + " since its estatus field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Postulante> attachedPostulanteListNew = new ArrayList<Postulante>();
            for (Postulante postulanteListNewPostulanteToAttach : postulanteListNew) {
                postulanteListNewPostulanteToAttach = em.getReference(postulanteListNewPostulanteToAttach.getClass(), postulanteListNewPostulanteToAttach.getId());
                attachedPostulanteListNew.add(postulanteListNewPostulanteToAttach);
            }
            postulanteListNew = attachedPostulanteListNew;
            estatusPostulante.setPostulanteList(postulanteListNew);
            estatusPostulante = em.merge(estatusPostulante);
            for (Postulante postulanteListNewPostulante : postulanteListNew) {
                if (!postulanteListOld.contains(postulanteListNewPostulante)) {
                    EstatusPostulante oldEstatusOfPostulanteListNewPostulante = postulanteListNewPostulante.getEstatus();
                    postulanteListNewPostulante.setEstatus(estatusPostulante);
                    postulanteListNewPostulante = em.merge(postulanteListNewPostulante);
                    if (oldEstatusOfPostulanteListNewPostulante != null && !oldEstatusOfPostulanteListNewPostulante.equals(estatusPostulante)) {
                        oldEstatusOfPostulanteListNewPostulante.getPostulanteList().remove(postulanteListNewPostulante);
                        oldEstatusOfPostulanteListNewPostulante = em.merge(oldEstatusOfPostulanteListNewPostulante);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = estatusPostulante.getId();
                if (findEstatusPostulante(id) == null) {
                    throw new NonexistentEntityException("The estatusPostulante with id " + id + " no longer exists.");
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
            EstatusPostulante estatusPostulante;
            try {
                estatusPostulante = em.getReference(EstatusPostulante.class, id);
                estatusPostulante.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estatusPostulante with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Postulante> postulanteListOrphanCheck = estatusPostulante.getPostulanteList();
            for (Postulante postulanteListOrphanCheckPostulante : postulanteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This EstatusPostulante (" + estatusPostulante + ") cannot be destroyed since the Postulante " + postulanteListOrphanCheckPostulante + " in its postulanteList field has a non-nullable estatus field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(estatusPostulante);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<EstatusPostulante> findEstatusPostulanteEntities() {
        return findEstatusPostulanteEntities(true, -1, -1);
    }

    public List<EstatusPostulante> findEstatusPostulanteEntities(int maxResults, int firstResult) {
        return findEstatusPostulanteEntities(false, maxResults, firstResult);
    }

    private List<EstatusPostulante> findEstatusPostulanteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(EstatusPostulante.class));
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

    public EstatusPostulante findEstatusPostulante(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(EstatusPostulante.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstatusPostulanteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<EstatusPostulante> rt = cq.from(EstatusPostulante.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
