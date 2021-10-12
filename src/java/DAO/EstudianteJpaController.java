/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.IllegalOrphanException;
import DAO.exceptions.NonexistentEntityException;
import DTO.Estudiante;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.Persona;
import DTO.Grupo;
import java.util.ArrayList;
import java.util.List;
import DTO.Postulante;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Leonardo
 */
public class EstudianteJpaController implements Serializable {

    public EstudianteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Estudiante estudiante) {
        if (estudiante.getGrupoList() == null) {
            estudiante.setGrupoList(new ArrayList<Grupo>());
        }
        if (estudiante.getPostulanteList() == null) {
            estudiante.setPostulanteList(new ArrayList<Postulante>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persona idPersona = estudiante.getIdPersona();
            if (idPersona != null) {
                idPersona = em.getReference(idPersona.getClass(), idPersona.getIdPersona());
                estudiante.setIdPersona(idPersona);
            }
            List<Grupo> attachedGrupoList = new ArrayList<Grupo>();
            for (Grupo grupoListGrupoToAttach : estudiante.getGrupoList()) {
                grupoListGrupoToAttach = em.getReference(grupoListGrupoToAttach.getClass(), grupoListGrupoToAttach.getId());
                attachedGrupoList.add(grupoListGrupoToAttach);
            }
            estudiante.setGrupoList(attachedGrupoList);
            List<Postulante> attachedPostulanteList = new ArrayList<Postulante>();
            for (Postulante postulanteListPostulanteToAttach : estudiante.getPostulanteList()) {
                postulanteListPostulanteToAttach = em.getReference(postulanteListPostulanteToAttach.getClass(), postulanteListPostulanteToAttach.getId());
                attachedPostulanteList.add(postulanteListPostulanteToAttach);
            }
            estudiante.setPostulanteList(attachedPostulanteList);
            em.persist(estudiante);
            if (idPersona != null) {
                idPersona.getEstudianteList().add(estudiante);
                idPersona = em.merge(idPersona);
            }
            for (Grupo grupoListGrupo : estudiante.getGrupoList()) {
                Estudiante oldIdEstudianteOfGrupoListGrupo = grupoListGrupo.getIdEstudiante();
                grupoListGrupo.setIdEstudiante(estudiante);
                grupoListGrupo = em.merge(grupoListGrupo);
                if (oldIdEstudianteOfGrupoListGrupo != null) {
                    oldIdEstudianteOfGrupoListGrupo.getGrupoList().remove(grupoListGrupo);
                    oldIdEstudianteOfGrupoListGrupo = em.merge(oldIdEstudianteOfGrupoListGrupo);
                }
            }
            for (Postulante postulanteListPostulante : estudiante.getPostulanteList()) {
                Estudiante oldIdEstudianteOfPostulanteListPostulante = postulanteListPostulante.getIdEstudiante();
                postulanteListPostulante.setIdEstudiante(estudiante);
                postulanteListPostulante = em.merge(postulanteListPostulante);
                if (oldIdEstudianteOfPostulanteListPostulante != null) {
                    oldIdEstudianteOfPostulanteListPostulante.getPostulanteList().remove(postulanteListPostulante);
                    oldIdEstudianteOfPostulanteListPostulante = em.merge(oldIdEstudianteOfPostulanteListPostulante);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Estudiante estudiante) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Estudiante persistentEstudiante = em.find(Estudiante.class, estudiante.getIdEstudiante());
            Persona idPersonaOld = persistentEstudiante.getIdPersona();
            Persona idPersonaNew = estudiante.getIdPersona();
            List<Grupo> grupoListOld = persistentEstudiante.getGrupoList();
            List<Grupo> grupoListNew = estudiante.getGrupoList();
            List<Postulante> postulanteListOld = persistentEstudiante.getPostulanteList();
            List<Postulante> postulanteListNew = estudiante.getPostulanteList();
            List<String> illegalOrphanMessages = null;
            for (Grupo grupoListOldGrupo : grupoListOld) {
                if (!grupoListNew.contains(grupoListOldGrupo)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Grupo " + grupoListOldGrupo + " since its idEstudiante field is not nullable.");
                }
            }
            for (Postulante postulanteListOldPostulante : postulanteListOld) {
                if (!postulanteListNew.contains(postulanteListOldPostulante)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Postulante " + postulanteListOldPostulante + " since its idEstudiante field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idPersonaNew != null) {
                idPersonaNew = em.getReference(idPersonaNew.getClass(), idPersonaNew.getIdPersona());
                estudiante.setIdPersona(idPersonaNew);
            }
            List<Grupo> attachedGrupoListNew = new ArrayList<Grupo>();
            for (Grupo grupoListNewGrupoToAttach : grupoListNew) {
                grupoListNewGrupoToAttach = em.getReference(grupoListNewGrupoToAttach.getClass(), grupoListNewGrupoToAttach.getId());
                attachedGrupoListNew.add(grupoListNewGrupoToAttach);
            }
            grupoListNew = attachedGrupoListNew;
            estudiante.setGrupoList(grupoListNew);
            List<Postulante> attachedPostulanteListNew = new ArrayList<Postulante>();
            for (Postulante postulanteListNewPostulanteToAttach : postulanteListNew) {
                postulanteListNewPostulanteToAttach = em.getReference(postulanteListNewPostulanteToAttach.getClass(), postulanteListNewPostulanteToAttach.getId());
                attachedPostulanteListNew.add(postulanteListNewPostulanteToAttach);
            }
            postulanteListNew = attachedPostulanteListNew;
            estudiante.setPostulanteList(postulanteListNew);
            estudiante = em.merge(estudiante);
            if (idPersonaOld != null && !idPersonaOld.equals(idPersonaNew)) {
                idPersonaOld.getEstudianteList().remove(estudiante);
                idPersonaOld = em.merge(idPersonaOld);
            }
            if (idPersonaNew != null && !idPersonaNew.equals(idPersonaOld)) {
                idPersonaNew.getEstudianteList().add(estudiante);
                idPersonaNew = em.merge(idPersonaNew);
            }
            for (Grupo grupoListNewGrupo : grupoListNew) {
                if (!grupoListOld.contains(grupoListNewGrupo)) {
                    Estudiante oldIdEstudianteOfGrupoListNewGrupo = grupoListNewGrupo.getIdEstudiante();
                    grupoListNewGrupo.setIdEstudiante(estudiante);
                    grupoListNewGrupo = em.merge(grupoListNewGrupo);
                    if (oldIdEstudianteOfGrupoListNewGrupo != null && !oldIdEstudianteOfGrupoListNewGrupo.equals(estudiante)) {
                        oldIdEstudianteOfGrupoListNewGrupo.getGrupoList().remove(grupoListNewGrupo);
                        oldIdEstudianteOfGrupoListNewGrupo = em.merge(oldIdEstudianteOfGrupoListNewGrupo);
                    }
                }
            }
            for (Postulante postulanteListNewPostulante : postulanteListNew) {
                if (!postulanteListOld.contains(postulanteListNewPostulante)) {
                    Estudiante oldIdEstudianteOfPostulanteListNewPostulante = postulanteListNewPostulante.getIdEstudiante();
                    postulanteListNewPostulante.setIdEstudiante(estudiante);
                    postulanteListNewPostulante = em.merge(postulanteListNewPostulante);
                    if (oldIdEstudianteOfPostulanteListNewPostulante != null && !oldIdEstudianteOfPostulanteListNewPostulante.equals(estudiante)) {
                        oldIdEstudianteOfPostulanteListNewPostulante.getPostulanteList().remove(postulanteListNewPostulante);
                        oldIdEstudianteOfPostulanteListNewPostulante = em.merge(oldIdEstudianteOfPostulanteListNewPostulante);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = estudiante.getIdEstudiante();
                if (findEstudiante(id) == null) {
                    throw new NonexistentEntityException("The estudiante with id " + id + " no longer exists.");
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
            Estudiante estudiante;
            try {
                estudiante = em.getReference(Estudiante.class, id);
                estudiante.getIdEstudiante();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The estudiante with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Grupo> grupoListOrphanCheck = estudiante.getGrupoList();
            for (Grupo grupoListOrphanCheckGrupo : grupoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Estudiante (" + estudiante + ") cannot be destroyed since the Grupo " + grupoListOrphanCheckGrupo + " in its grupoList field has a non-nullable idEstudiante field.");
            }
            List<Postulante> postulanteListOrphanCheck = estudiante.getPostulanteList();
            for (Postulante postulanteListOrphanCheckPostulante : postulanteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Estudiante (" + estudiante + ") cannot be destroyed since the Postulante " + postulanteListOrphanCheckPostulante + " in its postulanteList field has a non-nullable idEstudiante field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Persona idPersona = estudiante.getIdPersona();
            if (idPersona != null) {
                idPersona.getEstudianteList().remove(estudiante);
                idPersona = em.merge(idPersona);
            }
            em.remove(estudiante);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Estudiante> findEstudianteEntities() {
        return findEstudianteEntities(true, -1, -1);
    }

    public List<Estudiante> findEstudianteEntities(int maxResults, int firstResult) {
        return findEstudianteEntities(false, maxResults, firstResult);
    }

    private List<Estudiante> findEstudianteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Estudiante.class));
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

    public Estudiante findEstudiante(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Estudiante.class, id);
        } finally {
            em.close();
        }
    }

    public int getEstudianteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Estudiante> rt = cq.from(Estudiante.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public Estudiante findEstudianteByPersona(int idPersona) {
        EntityManager em = getEntityManager();
        try {
            return (Estudiante) em.createNativeQuery("SELECT * FROM estudiante WHERE id_persona = "+idPersona, Estudiante.class).getResultList().get(0);
        } finally {
            em.close();
        }
    }    
    
}
