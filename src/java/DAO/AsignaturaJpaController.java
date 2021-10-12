/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.IllegalOrphanException;
import DAO.exceptions.NonexistentEntityException;
import DTO.Asignatura;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.Grupo;
import java.util.ArrayList;
import java.util.List;
import DTO.Solicitud;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Leonardo
 */
public class AsignaturaJpaController implements Serializable {

    public AsignaturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Asignatura asignatura) {
        if (asignatura.getGrupoList() == null) {
            asignatura.setGrupoList(new ArrayList<Grupo>());
        }
        if (asignatura.getSolicitudList() == null) {
            asignatura.setSolicitudList(new ArrayList<Solicitud>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Grupo> attachedGrupoList = new ArrayList<Grupo>();
            for (Grupo grupoListGrupoToAttach : asignatura.getGrupoList()) {
                grupoListGrupoToAttach = em.getReference(grupoListGrupoToAttach.getClass(), grupoListGrupoToAttach.getId());
                attachedGrupoList.add(grupoListGrupoToAttach);
            }
            asignatura.setGrupoList(attachedGrupoList);
            List<Solicitud> attachedSolicitudList = new ArrayList<Solicitud>();
            for (Solicitud solicitudListSolicitudToAttach : asignatura.getSolicitudList()) {
                solicitudListSolicitudToAttach = em.getReference(solicitudListSolicitudToAttach.getClass(), solicitudListSolicitudToAttach.getIdSolicitud());
                attachedSolicitudList.add(solicitudListSolicitudToAttach);
            }
            asignatura.setSolicitudList(attachedSolicitudList);
            em.persist(asignatura);
            for (Grupo grupoListGrupo : asignatura.getGrupoList()) {
                Asignatura oldIdAsignaturaOfGrupoListGrupo = grupoListGrupo.getIdAsignatura();
                grupoListGrupo.setIdAsignatura(asignatura);
                grupoListGrupo = em.merge(grupoListGrupo);
                if (oldIdAsignaturaOfGrupoListGrupo != null) {
                    oldIdAsignaturaOfGrupoListGrupo.getGrupoList().remove(grupoListGrupo);
                    oldIdAsignaturaOfGrupoListGrupo = em.merge(oldIdAsignaturaOfGrupoListGrupo);
                }
            }
            for (Solicitud solicitudListSolicitud : asignatura.getSolicitudList()) {
                Asignatura oldIdAsignaturaOfSolicitudListSolicitud = solicitudListSolicitud.getIdAsignatura();
                solicitudListSolicitud.setIdAsignatura(asignatura);
                solicitudListSolicitud = em.merge(solicitudListSolicitud);
                if (oldIdAsignaturaOfSolicitudListSolicitud != null) {
                    oldIdAsignaturaOfSolicitudListSolicitud.getSolicitudList().remove(solicitudListSolicitud);
                    oldIdAsignaturaOfSolicitudListSolicitud = em.merge(oldIdAsignaturaOfSolicitudListSolicitud);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Asignatura asignatura) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asignatura persistentAsignatura = em.find(Asignatura.class, asignatura.getIdAsignatura());
            List<Grupo> grupoListOld = persistentAsignatura.getGrupoList();
            List<Grupo> grupoListNew = asignatura.getGrupoList();
            List<Solicitud> solicitudListOld = persistentAsignatura.getSolicitudList();
            List<Solicitud> solicitudListNew = asignatura.getSolicitudList();
            List<String> illegalOrphanMessages = null;
            for (Grupo grupoListOldGrupo : grupoListOld) {
                if (!grupoListNew.contains(grupoListOldGrupo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Grupo " + grupoListOldGrupo + " since its idAsignatura field is not nullable.");
                }
            }
            for (Solicitud solicitudListOldSolicitud : solicitudListOld) {
                if (!solicitudListNew.contains(solicitudListOldSolicitud)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Solicitud " + solicitudListOldSolicitud + " since its idAsignatura field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Grupo> attachedGrupoListNew = new ArrayList<Grupo>();
            for (Grupo grupoListNewGrupoToAttach : grupoListNew) {
                grupoListNewGrupoToAttach = em.getReference(grupoListNewGrupoToAttach.getClass(), grupoListNewGrupoToAttach.getId());
                attachedGrupoListNew.add(grupoListNewGrupoToAttach);
            }
            grupoListNew = attachedGrupoListNew;
            asignatura.setGrupoList(grupoListNew);
            List<Solicitud> attachedSolicitudListNew = new ArrayList<Solicitud>();
            for (Solicitud solicitudListNewSolicitudToAttach : solicitudListNew) {
                solicitudListNewSolicitudToAttach = em.getReference(solicitudListNewSolicitudToAttach.getClass(), solicitudListNewSolicitudToAttach.getIdSolicitud());
                attachedSolicitudListNew.add(solicitudListNewSolicitudToAttach);
            }
            solicitudListNew = attachedSolicitudListNew;
            asignatura.setSolicitudList(solicitudListNew);
            asignatura = em.merge(asignatura);
            for (Grupo grupoListNewGrupo : grupoListNew) {
                if (!grupoListOld.contains(grupoListNewGrupo)) {
                    Asignatura oldIdAsignaturaOfGrupoListNewGrupo = grupoListNewGrupo.getIdAsignatura();
                    grupoListNewGrupo.setIdAsignatura(asignatura);
                    grupoListNewGrupo = em.merge(grupoListNewGrupo);
                    if (oldIdAsignaturaOfGrupoListNewGrupo != null && !oldIdAsignaturaOfGrupoListNewGrupo.equals(asignatura)) {
                        oldIdAsignaturaOfGrupoListNewGrupo.getGrupoList().remove(grupoListNewGrupo);
                        oldIdAsignaturaOfGrupoListNewGrupo = em.merge(oldIdAsignaturaOfGrupoListNewGrupo);
                    }
                }
            }
            for (Solicitud solicitudListNewSolicitud : solicitudListNew) {
                if (!solicitudListOld.contains(solicitudListNewSolicitud)) {
                    Asignatura oldIdAsignaturaOfSolicitudListNewSolicitud = solicitudListNewSolicitud.getIdAsignatura();
                    solicitudListNewSolicitud.setIdAsignatura(asignatura);
                    solicitudListNewSolicitud = em.merge(solicitudListNewSolicitud);
                    if (oldIdAsignaturaOfSolicitudListNewSolicitud != null && !oldIdAsignaturaOfSolicitudListNewSolicitud.equals(asignatura)) {
                        oldIdAsignaturaOfSolicitudListNewSolicitud.getSolicitudList().remove(solicitudListNewSolicitud);
                        oldIdAsignaturaOfSolicitudListNewSolicitud = em.merge(oldIdAsignaturaOfSolicitudListNewSolicitud);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = asignatura.getIdAsignatura();
                if (findAsignatura(id) == null) {
                    throw new NonexistentEntityException("The asignatura with id " + id + " no longer exists.");
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
            Asignatura asignatura;
            try {
                asignatura = em.getReference(Asignatura.class, id);
                asignatura.getIdAsignatura();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignatura with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Grupo> grupoListOrphanCheck = asignatura.getGrupoList();
            for (Grupo grupoListOrphanCheckGrupo : grupoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Asignatura (" + asignatura + ") cannot be destroyed since the Grupo " + grupoListOrphanCheckGrupo + " in its grupoList field has a non-nullable idAsignatura field.");
            }
            List<Solicitud> solicitudListOrphanCheck = asignatura.getSolicitudList();
            for (Solicitud solicitudListOrphanCheckSolicitud : solicitudListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Asignatura (" + asignatura + ") cannot be destroyed since the Solicitud " + solicitudListOrphanCheckSolicitud + " in its solicitudList field has a non-nullable idAsignatura field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(asignatura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Asignatura> findAsignaturaEntities() {
        return findAsignaturaEntities(true, -1, -1);
    }

    public List<Asignatura> findAsignaturaEntities(int maxResults, int firstResult) {
        return findAsignaturaEntities(false, maxResults, firstResult);
    }

    private List<Asignatura> findAsignaturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Asignatura.class));
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

    public Asignatura findAsignatura(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Asignatura.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignaturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Asignatura> rt = cq.from(Asignatura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public Asignatura findAsignaturaByCodigo(String codigoAsignatura) {
        EntityManager em = getEntityManager();
        try {
            return (Asignatura) em.createNativeQuery("SELECT * FROM asignatura WHERE codigo_asignatura = "+codigoAsignatura, Asignatura.class).getResultList().get(0);
        } finally {
            em.close();
        }
    }
    
}
