/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.IllegalOrphanException;
import DAO.exceptions.NonexistentEntityException;
import DTO.Docente;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.Persona;
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
public class DocenteJpaController implements Serializable {

    public DocenteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Docente docente) {
        if (docente.getGrupoList() == null) {
            docente.setGrupoList(new ArrayList<Grupo>());
        }
        if (docente.getSolicitudList() == null) {
            docente.setSolicitudList(new ArrayList<Solicitud>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persona idPersona = docente.getIdPersona();
            if (idPersona != null) {
                idPersona = em.getReference(idPersona.getClass(), idPersona.getIdPersona());
                docente.setIdPersona(idPersona);
            }
            List<Grupo> attachedGrupoList = new ArrayList<Grupo>();
            for (Grupo grupoListGrupoToAttach : docente.getGrupoList()) {
                grupoListGrupoToAttach = em.getReference(grupoListGrupoToAttach.getClass(), grupoListGrupoToAttach.getId());
                attachedGrupoList.add(grupoListGrupoToAttach);
            }
            docente.setGrupoList(attachedGrupoList);
            List<Solicitud> attachedSolicitudList = new ArrayList<Solicitud>();
            for (Solicitud solicitudListSolicitudToAttach : docente.getSolicitudList()) {
                solicitudListSolicitudToAttach = em.getReference(solicitudListSolicitudToAttach.getClass(), solicitudListSolicitudToAttach.getIdSolicitud());
                attachedSolicitudList.add(solicitudListSolicitudToAttach);
            }
            docente.setSolicitudList(attachedSolicitudList);
            em.persist(docente);
            if (idPersona != null) {
                idPersona.getDocenteList().add(docente);
                idPersona = em.merge(idPersona);
            }
            for (Grupo grupoListGrupo : docente.getGrupoList()) {
                Docente oldIdDocenteOfGrupoListGrupo = grupoListGrupo.getIdDocente();
                grupoListGrupo.setIdDocente(docente);
                grupoListGrupo = em.merge(grupoListGrupo);
                if (oldIdDocenteOfGrupoListGrupo != null) {
                    oldIdDocenteOfGrupoListGrupo.getGrupoList().remove(grupoListGrupo);
                    oldIdDocenteOfGrupoListGrupo = em.merge(oldIdDocenteOfGrupoListGrupo);
                }
            }
            for (Solicitud solicitudListSolicitud : docente.getSolicitudList()) {
                Docente oldIdDocenteOfSolicitudListSolicitud = solicitudListSolicitud.getIdDocente();
                solicitudListSolicitud.setIdDocente(docente);
                solicitudListSolicitud = em.merge(solicitudListSolicitud);
                if (oldIdDocenteOfSolicitudListSolicitud != null) {
                    oldIdDocenteOfSolicitudListSolicitud.getSolicitudList().remove(solicitudListSolicitud);
                    oldIdDocenteOfSolicitudListSolicitud = em.merge(oldIdDocenteOfSolicitudListSolicitud);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Docente docente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Docente persistentDocente = em.find(Docente.class, docente.getIdDocente());
            Persona idPersonaOld = persistentDocente.getIdPersona();
            Persona idPersonaNew = docente.getIdPersona();
            List<Grupo> grupoListOld = persistentDocente.getGrupoList();
            List<Grupo> grupoListNew = docente.getGrupoList();
            List<Solicitud> solicitudListOld = persistentDocente.getSolicitudList();
            List<Solicitud> solicitudListNew = docente.getSolicitudList();
            List<String> illegalOrphanMessages = null;
            for (Grupo grupoListOldGrupo : grupoListOld) {
                if (!grupoListNew.contains(grupoListOldGrupo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Grupo " + grupoListOldGrupo + " since its idDocente field is not nullable.");
                }
            }
            for (Solicitud solicitudListOldSolicitud : solicitudListOld) {
                if (!solicitudListNew.contains(solicitudListOldSolicitud)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Solicitud " + solicitudListOldSolicitud + " since its idDocente field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idPersonaNew != null) {
                idPersonaNew = em.getReference(idPersonaNew.getClass(), idPersonaNew.getIdPersona());
                docente.setIdPersona(idPersonaNew);
            }
            List<Grupo> attachedGrupoListNew = new ArrayList<Grupo>();
            for (Grupo grupoListNewGrupoToAttach : grupoListNew) {
                grupoListNewGrupoToAttach = em.getReference(grupoListNewGrupoToAttach.getClass(), grupoListNewGrupoToAttach.getId());
                attachedGrupoListNew.add(grupoListNewGrupoToAttach);
            }
            grupoListNew = attachedGrupoListNew;
            docente.setGrupoList(grupoListNew);
            List<Solicitud> attachedSolicitudListNew = new ArrayList<Solicitud>();
            for (Solicitud solicitudListNewSolicitudToAttach : solicitudListNew) {
                solicitudListNewSolicitudToAttach = em.getReference(solicitudListNewSolicitudToAttach.getClass(), solicitudListNewSolicitudToAttach.getIdSolicitud());
                attachedSolicitudListNew.add(solicitudListNewSolicitudToAttach);
            }
            solicitudListNew = attachedSolicitudListNew;
            docente.setSolicitudList(solicitudListNew);
            docente = em.merge(docente);
            if (idPersonaOld != null && !idPersonaOld.equals(idPersonaNew)) {
                idPersonaOld.getDocenteList().remove(docente);
                idPersonaOld = em.merge(idPersonaOld);
            }
            if (idPersonaNew != null && !idPersonaNew.equals(idPersonaOld)) {
                idPersonaNew.getDocenteList().add(docente);
                idPersonaNew = em.merge(idPersonaNew);
            }
            for (Grupo grupoListNewGrupo : grupoListNew) {
                if (!grupoListOld.contains(grupoListNewGrupo)) {
                    Docente oldIdDocenteOfGrupoListNewGrupo = grupoListNewGrupo.getIdDocente();
                    grupoListNewGrupo.setIdDocente(docente);
                    grupoListNewGrupo = em.merge(grupoListNewGrupo);
                    if (oldIdDocenteOfGrupoListNewGrupo != null && !oldIdDocenteOfGrupoListNewGrupo.equals(docente)) {
                        oldIdDocenteOfGrupoListNewGrupo.getGrupoList().remove(grupoListNewGrupo);
                        oldIdDocenteOfGrupoListNewGrupo = em.merge(oldIdDocenteOfGrupoListNewGrupo);
                    }
                }
            }
            for (Solicitud solicitudListNewSolicitud : solicitudListNew) {
                if (!solicitudListOld.contains(solicitudListNewSolicitud)) {
                    Docente oldIdDocenteOfSolicitudListNewSolicitud = solicitudListNewSolicitud.getIdDocente();
                    solicitudListNewSolicitud.setIdDocente(docente);
                    solicitudListNewSolicitud = em.merge(solicitudListNewSolicitud);
                    if (oldIdDocenteOfSolicitudListNewSolicitud != null && !oldIdDocenteOfSolicitudListNewSolicitud.equals(docente)) {
                        oldIdDocenteOfSolicitudListNewSolicitud.getSolicitudList().remove(solicitudListNewSolicitud);
                        oldIdDocenteOfSolicitudListNewSolicitud = em.merge(oldIdDocenteOfSolicitudListNewSolicitud);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = docente.getIdDocente();
                if (findDocente(id) == null) {
                    throw new NonexistentEntityException("The docente with id " + id + " no longer exists.");
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
            Docente docente;
            try {
                docente = em.getReference(Docente.class, id);
                docente.getIdDocente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The docente with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Grupo> grupoListOrphanCheck = docente.getGrupoList();
            for (Grupo grupoListOrphanCheckGrupo : grupoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Docente (" + docente + ") cannot be destroyed since the Grupo " + grupoListOrphanCheckGrupo + " in its grupoList field has a non-nullable idDocente field.");
            }
            List<Solicitud> solicitudListOrphanCheck = docente.getSolicitudList();
            for (Solicitud solicitudListOrphanCheckSolicitud : solicitudListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Docente (" + docente + ") cannot be destroyed since the Solicitud " + solicitudListOrphanCheckSolicitud + " in its solicitudList field has a non-nullable idDocente field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Persona idPersona = docente.getIdPersona();
            if (idPersona != null) {
                idPersona.getDocenteList().remove(docente);
                idPersona = em.merge(idPersona);
            }
            em.remove(docente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Docente> findDocenteEntities() {
        return findDocenteEntities(true, -1, -1);
    }

    public List<Docente> findDocenteEntities(int maxResults, int firstResult) {
        return findDocenteEntities(false, maxResults, firstResult);
    }

    private List<Docente> findDocenteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Docente.class));
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

    public Docente findDocente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Docente.class, id);
        } finally {
            em.close();
        }
    }

    public int getDocenteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Docente> rt = cq.from(Docente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public Docente findDocenteByPersona(int idPersona) {
        EntityManager em = getEntityManager();
        try {
            return (Docente) em.createNativeQuery("SELECT * FROM docente WHERE id_persona = "+idPersona, Docente.class).getResultList().get(0);
        } finally {
            em.close();
        }
    }
    
    public Docente findDocenteByCodigo(int codigoDocente) {
        EntityManager em = getEntityManager();
        try {
            return (Docente) em.createNamedQuery("Docente.findByCodigoDocente", Docente.class).setParameter("codigoDocente", codigoDocente).getResultList().get(0);
        } finally {
            em.close();
        }
    }
    
}
