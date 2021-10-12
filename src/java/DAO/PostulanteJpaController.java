/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.EstatusPostulante;
import DTO.Estudiante;
import DTO.Postulante;
import DTO.Solicitud;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Leonardo
 */
public class PostulanteJpaController implements Serializable {

    public PostulanteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Postulante postulante) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            EstatusPostulante estatus = postulante.getEstatus();
            if (estatus != null) {
                estatus = em.getReference(estatus.getClass(), estatus.getId());
                postulante.setEstatus(estatus);
            }
            Estudiante idEstudiante = postulante.getIdEstudiante();
            if (idEstudiante != null) {
                idEstudiante = em.getReference(idEstudiante.getClass(), idEstudiante.getIdEstudiante());
                postulante.setIdEstudiante(idEstudiante);
            }
            Solicitud idSolicitud = postulante.getIdSolicitud();
            if (idSolicitud != null) {
                idSolicitud = em.getReference(idSolicitud.getClass(), idSolicitud.getIdSolicitud());
                postulante.setIdSolicitud(idSolicitud);
            }
            em.persist(postulante);
            if (estatus != null) {
                estatus.getPostulanteList().add(postulante);
                estatus = em.merge(estatus);
            }
            if (idEstudiante != null) {
                idEstudiante.getPostulanteList().add(postulante);
                idEstudiante = em.merge(idEstudiante);
            }
            if (idSolicitud != null) {
                idSolicitud.getPostulanteList().add(postulante);
                idSolicitud = em.merge(idSolicitud);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Postulante postulante) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Postulante persistentPostulante = em.find(Postulante.class, postulante.getId());
            EstatusPostulante estatusOld = persistentPostulante.getEstatus();
            EstatusPostulante estatusNew = postulante.getEstatus();
            Estudiante idEstudianteOld = persistentPostulante.getIdEstudiante();
            Estudiante idEstudianteNew = postulante.getIdEstudiante();
            Solicitud idSolicitudOld = persistentPostulante.getIdSolicitud();
            Solicitud idSolicitudNew = postulante.getIdSolicitud();
            if (estatusNew != null) {
                estatusNew = em.getReference(estatusNew.getClass(), estatusNew.getId());
                postulante.setEstatus(estatusNew);
            }
            if (idEstudianteNew != null) {
                idEstudianteNew = em.getReference(idEstudianteNew.getClass(), idEstudianteNew.getIdEstudiante());
                postulante.setIdEstudiante(idEstudianteNew);
            }
            if (idSolicitudNew != null) {
                idSolicitudNew = em.getReference(idSolicitudNew.getClass(), idSolicitudNew.getIdSolicitud());
                postulante.setIdSolicitud(idSolicitudNew);
            }
            postulante = em.merge(postulante);
            if (estatusOld != null && !estatusOld.equals(estatusNew)) {
                estatusOld.getPostulanteList().remove(postulante);
                estatusOld = em.merge(estatusOld);
            }
            if (estatusNew != null && !estatusNew.equals(estatusOld)) {
                estatusNew.getPostulanteList().add(postulante);
                estatusNew = em.merge(estatusNew);
            }
            if (idEstudianteOld != null && !idEstudianteOld.equals(idEstudianteNew)) {
                idEstudianteOld.getPostulanteList().remove(postulante);
                idEstudianteOld = em.merge(idEstudianteOld);
            }
            if (idEstudianteNew != null && !idEstudianteNew.equals(idEstudianteOld)) {
                idEstudianteNew.getPostulanteList().add(postulante);
                idEstudianteNew = em.merge(idEstudianteNew);
            }
            if (idSolicitudOld != null && !idSolicitudOld.equals(idSolicitudNew)) {
                idSolicitudOld.getPostulanteList().remove(postulante);
                idSolicitudOld = em.merge(idSolicitudOld);
            }
            if (idSolicitudNew != null && !idSolicitudNew.equals(idSolicitudOld)) {
                idSolicitudNew.getPostulanteList().add(postulante);
                idSolicitudNew = em.merge(idSolicitudNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = postulante.getId();
                if (findPostulante(id) == null) {
                    throw new NonexistentEntityException("The postulante with id " + id + " no longer exists.");
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
            Postulante postulante;
            try {
                postulante = em.getReference(Postulante.class, id);
                postulante.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The postulante with id " + id + " no longer exists.", enfe);
            }
            EstatusPostulante estatus = postulante.getEstatus();
            if (estatus != null) {
                estatus.getPostulanteList().remove(postulante);
                estatus = em.merge(estatus);
            }
            Estudiante idEstudiante = postulante.getIdEstudiante();
            if (idEstudiante != null) {
                idEstudiante.getPostulanteList().remove(postulante);
                idEstudiante = em.merge(idEstudiante);
            }
            Solicitud idSolicitud = postulante.getIdSolicitud();
            if (idSolicitud != null) {
                idSolicitud.getPostulanteList().remove(postulante);
                idSolicitud = em.merge(idSolicitud);
            }
            em.remove(postulante);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Postulante> findPostulanteEntities() {
        return findPostulanteEntities(true, -1, -1);
    }

    public List<Postulante> findPostulanteEntities(int maxResults, int firstResult) {
        return findPostulanteEntities(false, maxResults, firstResult);
    }

    private List<Postulante> findPostulanteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Postulante.class));
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

    public Postulante findPostulante(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Postulante.class, id);
        } finally {
            em.close();
        }
    }

    public int getPostulanteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Postulante> rt = cq.from(Postulante.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public Postulante findPostulanteByEstudianteAndSolicitud(int idEstudiante, int idSolicitud) {
        EntityManager em = getEntityManager();
        try {
            return (Postulante) em.createNativeQuery("SELECT * FROM postulante WHERE id_estudiante = "+idEstudiante +" AND id_solicitud="+idSolicitud, Postulante.class).getResultList().get(0);
        } finally {
            em.close();
        }
    }
    
}
