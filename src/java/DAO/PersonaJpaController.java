/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.IllegalOrphanException;
import DAO.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.Estudiante;
import java.util.ArrayList;
import java.util.List;
import DTO.Docente;
import DTO.Documento;
import DTO.Persona;
import DTO.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Leonardo
 */
public class PersonaJpaController implements Serializable {

    public PersonaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Persona persona) {
        if (persona.getEstudianteList() == null) {
            persona.setEstudianteList(new ArrayList<Estudiante>());
        }
        if (persona.getDocenteList() == null) {
            persona.setDocenteList(new ArrayList<Docente>());
        }
        if (persona.getDocumentoList() == null) {
            persona.setDocumentoList(new ArrayList<Documento>());
        }
        if (persona.getUsuarioList() == null) {
            persona.setUsuarioList(new ArrayList<Usuario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Estudiante> attachedEstudianteList = new ArrayList<Estudiante>();
            for (Estudiante estudianteListEstudianteToAttach : persona.getEstudianteList()) {
                estudianteListEstudianteToAttach = em.getReference(estudianteListEstudianteToAttach.getClass(), estudianteListEstudianteToAttach.getIdEstudiante());
                attachedEstudianteList.add(estudianteListEstudianteToAttach);
            }
            persona.setEstudianteList(attachedEstudianteList);
            List<Docente> attachedDocenteList = new ArrayList<Docente>();
            for (Docente docenteListDocenteToAttach : persona.getDocenteList()) {
                docenteListDocenteToAttach = em.getReference(docenteListDocenteToAttach.getClass(), docenteListDocenteToAttach.getIdDocente());
                attachedDocenteList.add(docenteListDocenteToAttach);
            }
            persona.setDocenteList(attachedDocenteList);
            List<Documento> attachedDocumentoList = new ArrayList<Documento>();
            for (Documento documentoListDocumentoToAttach : persona.getDocumentoList()) {
                documentoListDocumentoToAttach = em.getReference(documentoListDocumentoToAttach.getClass(), documentoListDocumentoToAttach.getIdDoc());
                attachedDocumentoList.add(documentoListDocumentoToAttach);
            }
            persona.setDocumentoList(attachedDocumentoList);
            List<Usuario> attachedUsuarioList = new ArrayList<Usuario>();
            for (Usuario usuarioListUsuarioToAttach : persona.getUsuarioList()) {
                usuarioListUsuarioToAttach = em.getReference(usuarioListUsuarioToAttach.getClass(), usuarioListUsuarioToAttach.getIdUsuario());
                attachedUsuarioList.add(usuarioListUsuarioToAttach);
            }
            persona.setUsuarioList(attachedUsuarioList);
            em.persist(persona);
            for (Estudiante estudianteListEstudiante : persona.getEstudianteList()) {
                Persona oldIdPersonaOfEstudianteListEstudiante = estudianteListEstudiante.getIdPersona();
                estudianteListEstudiante.setIdPersona(persona);
                estudianteListEstudiante = em.merge(estudianteListEstudiante);
                if (oldIdPersonaOfEstudianteListEstudiante != null) {
                    oldIdPersonaOfEstudianteListEstudiante.getEstudianteList().remove(estudianteListEstudiante);
                    oldIdPersonaOfEstudianteListEstudiante = em.merge(oldIdPersonaOfEstudianteListEstudiante);
                }
            }
            for (Docente docenteListDocente : persona.getDocenteList()) {
                Persona oldIdPersonaOfDocenteListDocente = docenteListDocente.getIdPersona();
                docenteListDocente.setIdPersona(persona);
                docenteListDocente = em.merge(docenteListDocente);
                if (oldIdPersonaOfDocenteListDocente != null) {
                    oldIdPersonaOfDocenteListDocente.getDocenteList().remove(docenteListDocente);
                    oldIdPersonaOfDocenteListDocente = em.merge(oldIdPersonaOfDocenteListDocente);
                }
            }
            for (Documento documentoListDocumento : persona.getDocumentoList()) {
                Persona oldIdPersonaOfDocumentoListDocumento = documentoListDocumento.getIdPersona();
                documentoListDocumento.setIdPersona(persona);
                documentoListDocumento = em.merge(documentoListDocumento);
                if (oldIdPersonaOfDocumentoListDocumento != null) {
                    oldIdPersonaOfDocumentoListDocumento.getDocumentoList().remove(documentoListDocumento);
                    oldIdPersonaOfDocumentoListDocumento = em.merge(oldIdPersonaOfDocumentoListDocumento);
                }
            }
            for (Usuario usuarioListUsuario : persona.getUsuarioList()) {
                Persona oldIdPersonaOfUsuarioListUsuario = usuarioListUsuario.getIdPersona();
                usuarioListUsuario.setIdPersona(persona);
                usuarioListUsuario = em.merge(usuarioListUsuario);
                if (oldIdPersonaOfUsuarioListUsuario != null) {
                    oldIdPersonaOfUsuarioListUsuario.getUsuarioList().remove(usuarioListUsuario);
                    oldIdPersonaOfUsuarioListUsuario = em.merge(oldIdPersonaOfUsuarioListUsuario);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Persona persona) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persona persistentPersona = em.find(Persona.class, persona.getIdPersona());
            List<Estudiante> estudianteListOld = persistentPersona.getEstudianteList();
            List<Estudiante> estudianteListNew = persona.getEstudianteList();
            List<Docente> docenteListOld = persistentPersona.getDocenteList();
            List<Docente> docenteListNew = persona.getDocenteList();
            List<Documento> documentoListOld = persistentPersona.getDocumentoList();
            List<Documento> documentoListNew = persona.getDocumentoList();
            List<Usuario> usuarioListOld = persistentPersona.getUsuarioList();
            List<Usuario> usuarioListNew = persona.getUsuarioList();
            List<String> illegalOrphanMessages = null;
            for (Estudiante estudianteListOldEstudiante : estudianteListOld) {
                if (!estudianteListNew.contains(estudianteListOldEstudiante)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Estudiante " + estudianteListOldEstudiante + " since its idPersona field is not nullable.");
                }
            }
            for (Docente docenteListOldDocente : docenteListOld) {
                if (!docenteListNew.contains(docenteListOldDocente)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Docente " + docenteListOldDocente + " since its idPersona field is not nullable.");
                }
            }
            for (Usuario usuarioListOldUsuario : usuarioListOld) {
                if (!usuarioListNew.contains(usuarioListOldUsuario)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Usuario " + usuarioListOldUsuario + " since its idPersona field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Estudiante> attachedEstudianteListNew = new ArrayList<Estudiante>();
            for (Estudiante estudianteListNewEstudianteToAttach : estudianteListNew) {
                estudianteListNewEstudianteToAttach = em.getReference(estudianteListNewEstudianteToAttach.getClass(), estudianteListNewEstudianteToAttach.getIdEstudiante());
                attachedEstudianteListNew.add(estudianteListNewEstudianteToAttach);
            }
            estudianteListNew = attachedEstudianteListNew;
            persona.setEstudianteList(estudianteListNew);
            List<Docente> attachedDocenteListNew = new ArrayList<Docente>();
            for (Docente docenteListNewDocenteToAttach : docenteListNew) {
                docenteListNewDocenteToAttach = em.getReference(docenteListNewDocenteToAttach.getClass(), docenteListNewDocenteToAttach.getIdDocente());
                attachedDocenteListNew.add(docenteListNewDocenteToAttach);
            }
            docenteListNew = attachedDocenteListNew;
            persona.setDocenteList(docenteListNew);
            List<Documento> attachedDocumentoListNew = new ArrayList<Documento>();
            for (Documento documentoListNewDocumentoToAttach : documentoListNew) {
                documentoListNewDocumentoToAttach = em.getReference(documentoListNewDocumentoToAttach.getClass(), documentoListNewDocumentoToAttach.getIdDoc());
                attachedDocumentoListNew.add(documentoListNewDocumentoToAttach);
            }
            documentoListNew = attachedDocumentoListNew;
            persona.setDocumentoList(documentoListNew);
            List<Usuario> attachedUsuarioListNew = new ArrayList<Usuario>();
            for (Usuario usuarioListNewUsuarioToAttach : usuarioListNew) {
                usuarioListNewUsuarioToAttach = em.getReference(usuarioListNewUsuarioToAttach.getClass(), usuarioListNewUsuarioToAttach.getIdUsuario());
                attachedUsuarioListNew.add(usuarioListNewUsuarioToAttach);
            }
            usuarioListNew = attachedUsuarioListNew;
            persona.setUsuarioList(usuarioListNew);
            persona = em.merge(persona);
            for (Estudiante estudianteListNewEstudiante : estudianteListNew) {
                if (!estudianteListOld.contains(estudianteListNewEstudiante)) {
                    Persona oldIdPersonaOfEstudianteListNewEstudiante = estudianteListNewEstudiante.getIdPersona();
                    estudianteListNewEstudiante.setIdPersona(persona);
                    estudianteListNewEstudiante = em.merge(estudianteListNewEstudiante);
                    if (oldIdPersonaOfEstudianteListNewEstudiante != null && !oldIdPersonaOfEstudianteListNewEstudiante.equals(persona)) {
                        oldIdPersonaOfEstudianteListNewEstudiante.getEstudianteList().remove(estudianteListNewEstudiante);
                        oldIdPersonaOfEstudianteListNewEstudiante = em.merge(oldIdPersonaOfEstudianteListNewEstudiante);
                    }
                }
            }
            for (Docente docenteListNewDocente : docenteListNew) {
                if (!docenteListOld.contains(docenteListNewDocente)) {
                    Persona oldIdPersonaOfDocenteListNewDocente = docenteListNewDocente.getIdPersona();
                    docenteListNewDocente.setIdPersona(persona);
                    docenteListNewDocente = em.merge(docenteListNewDocente);
                    if (oldIdPersonaOfDocenteListNewDocente != null && !oldIdPersonaOfDocenteListNewDocente.equals(persona)) {
                        oldIdPersonaOfDocenteListNewDocente.getDocenteList().remove(docenteListNewDocente);
                        oldIdPersonaOfDocenteListNewDocente = em.merge(oldIdPersonaOfDocenteListNewDocente);
                    }
                }
            }
            for (Documento documentoListOldDocumento : documentoListOld) {
                if (!documentoListNew.contains(documentoListOldDocumento)) {
                    documentoListOldDocumento.setIdPersona(null);
                    documentoListOldDocumento = em.merge(documentoListOldDocumento);
                }
            }
            for (Documento documentoListNewDocumento : documentoListNew) {
                if (!documentoListOld.contains(documentoListNewDocumento)) {
                    Persona oldIdPersonaOfDocumentoListNewDocumento = documentoListNewDocumento.getIdPersona();
                    documentoListNewDocumento.setIdPersona(persona);
                    documentoListNewDocumento = em.merge(documentoListNewDocumento);
                    if (oldIdPersonaOfDocumentoListNewDocumento != null && !oldIdPersonaOfDocumentoListNewDocumento.equals(persona)) {
                        oldIdPersonaOfDocumentoListNewDocumento.getDocumentoList().remove(documentoListNewDocumento);
                        oldIdPersonaOfDocumentoListNewDocumento = em.merge(oldIdPersonaOfDocumentoListNewDocumento);
                    }
                }
            }
            for (Usuario usuarioListNewUsuario : usuarioListNew) {
                if (!usuarioListOld.contains(usuarioListNewUsuario)) {
                    Persona oldIdPersonaOfUsuarioListNewUsuario = usuarioListNewUsuario.getIdPersona();
                    usuarioListNewUsuario.setIdPersona(persona);
                    usuarioListNewUsuario = em.merge(usuarioListNewUsuario);
                    if (oldIdPersonaOfUsuarioListNewUsuario != null && !oldIdPersonaOfUsuarioListNewUsuario.equals(persona)) {
                        oldIdPersonaOfUsuarioListNewUsuario.getUsuarioList().remove(usuarioListNewUsuario);
                        oldIdPersonaOfUsuarioListNewUsuario = em.merge(oldIdPersonaOfUsuarioListNewUsuario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = persona.getIdPersona();
                if (findPersona(id) == null) {
                    throw new NonexistentEntityException("The persona with id " + id + " no longer exists.");
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
            Persona persona;
            try {
                persona = em.getReference(Persona.class, id);
                persona.getIdPersona();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The persona with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Estudiante> estudianteListOrphanCheck = persona.getEstudianteList();
            for (Estudiante estudianteListOrphanCheckEstudiante : estudianteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Persona (" + persona + ") cannot be destroyed since the Estudiante " + estudianteListOrphanCheckEstudiante + " in its estudianteList field has a non-nullable idPersona field.");
            }
            List<Docente> docenteListOrphanCheck = persona.getDocenteList();
            for (Docente docenteListOrphanCheckDocente : docenteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Persona (" + persona + ") cannot be destroyed since the Docente " + docenteListOrphanCheckDocente + " in its docenteList field has a non-nullable idPersona field.");
            }
            List<Usuario> usuarioListOrphanCheck = persona.getUsuarioList();
            for (Usuario usuarioListOrphanCheckUsuario : usuarioListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Persona (" + persona + ") cannot be destroyed since the Usuario " + usuarioListOrphanCheckUsuario + " in its usuarioList field has a non-nullable idPersona field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Documento> documentoList = persona.getDocumentoList();
            for (Documento documentoListDocumento : documentoList) {
                documentoListDocumento.setIdPersona(null);
                documentoListDocumento = em.merge(documentoListDocumento);
            }
            em.remove(persona);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Persona> findPersonaEntities() {
        return findPersonaEntities(true, -1, -1);
    }

    public List<Persona> findPersonaEntities(int maxResults, int firstResult) {
        return findPersonaEntities(false, maxResults, firstResult);
    }

    private List<Persona> findPersonaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Persona.class));
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

    public Persona findPersona(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Persona.class, id);
        } finally {
            em.close();
        }
    }

    public int getPersonaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Persona> rt = cq.from(Persona.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public Persona findPersona(int numDocumento) {
        EntityManager em = getEntityManager();
        try {
            return (Persona) em.createNamedQuery("Persona.findByNumDocumento", Persona.class).setParameter("numDocumento", numDocumento).getResultList().get(0);
        } finally {
            em.close();
        }
    }
    
    public Persona getPersonaLast() {
        EntityManager em = getEntityManager();
        try {
            return (Persona) em.createNativeQuery("Select * from persona order by id_persona desc limit 1", Persona.class).getResultList().get(0);
        } finally {
            em.close();
        }
    }
    
     public Persona findPersonaByEmail(String email) {
        EntityManager em = getEntityManager();
        try {
            return (Persona) em.createNamedQuery("Persona.findByEmail", Persona.class).setParameter("email", email).getResultList().get(0);
        } finally {
            em.close();
        }
    }
    
}
