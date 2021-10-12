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
import DTO.Persona;
import DTO.TipoUsuario;
import DTO.Documento;
import java.util.ArrayList;
import java.util.List;
import DTO.Solicitud;
import DTO.Usuario;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author Leonardo
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        if (usuario.getDocumentoList() == null) {
            usuario.setDocumentoList(new ArrayList<Documento>());
        }
        if (usuario.getSolicitudList() == null) {
            usuario.setSolicitudList(new ArrayList<Solicitud>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persona idPersona = usuario.getIdPersona();
            if (idPersona != null) {
                idPersona = em.getReference(idPersona.getClass(), idPersona.getIdPersona());
                usuario.setIdPersona(idPersona);
            }
            TipoUsuario idTipoUsuario = usuario.getIdTipoUsuario();
            if (idTipoUsuario != null) {
                idTipoUsuario = em.getReference(idTipoUsuario.getClass(), idTipoUsuario.getIdTipoUsuario());
                usuario.setIdTipoUsuario(idTipoUsuario);
            }
            List<Documento> attachedDocumentoList = new ArrayList<Documento>();
            for (Documento documentoListDocumentoToAttach : usuario.getDocumentoList()) {
                documentoListDocumentoToAttach = em.getReference(documentoListDocumentoToAttach.getClass(), documentoListDocumentoToAttach.getIdDoc());
                attachedDocumentoList.add(documentoListDocumentoToAttach);
            }
            usuario.setDocumentoList(attachedDocumentoList);
            List<Solicitud> attachedSolicitudList = new ArrayList<Solicitud>();
            for (Solicitud solicitudListSolicitudToAttach : usuario.getSolicitudList()) {
                solicitudListSolicitudToAttach = em.getReference(solicitudListSolicitudToAttach.getClass(), solicitudListSolicitudToAttach.getIdSolicitud());
                attachedSolicitudList.add(solicitudListSolicitudToAttach);
            }
            usuario.setSolicitudList(attachedSolicitudList);
            em.persist(usuario);
            if (idPersona != null) {
                idPersona.getUsuarioList().add(usuario);
                idPersona = em.merge(idPersona);
            }
            if (idTipoUsuario != null) {
                idTipoUsuario.getUsuarioList().add(usuario);
                idTipoUsuario = em.merge(idTipoUsuario);
            }
            for (Documento documentoListDocumento : usuario.getDocumentoList()) {
                Usuario oldIdUserOfDocumentoListDocumento = documentoListDocumento.getIdUser();
                documentoListDocumento.setIdUser(usuario);
                documentoListDocumento = em.merge(documentoListDocumento);
                if (oldIdUserOfDocumentoListDocumento != null) {
                    oldIdUserOfDocumentoListDocumento.getDocumentoList().remove(documentoListDocumento);
                    oldIdUserOfDocumentoListDocumento = em.merge(oldIdUserOfDocumentoListDocumento);
                }
            }
            for (Solicitud solicitudListSolicitud : usuario.getSolicitudList()) {
                Usuario oldIdAprobadorOfSolicitudListSolicitud = solicitudListSolicitud.getIdAprobador();
                solicitudListSolicitud.setIdAprobador(usuario);
                solicitudListSolicitud = em.merge(solicitudListSolicitud);
                if (oldIdAprobadorOfSolicitudListSolicitud != null) {
                    oldIdAprobadorOfSolicitudListSolicitud.getSolicitudList().remove(solicitudListSolicitud);
                    oldIdAprobadorOfSolicitudListSolicitud = em.merge(oldIdAprobadorOfSolicitudListSolicitud);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getIdUsuario());
            Persona idPersonaOld = persistentUsuario.getIdPersona();
            Persona idPersonaNew = usuario.getIdPersona();
            TipoUsuario idTipoUsuarioOld = persistentUsuario.getIdTipoUsuario();
            TipoUsuario idTipoUsuarioNew = usuario.getIdTipoUsuario();
            List<Documento> documentoListOld = persistentUsuario.getDocumentoList();
            List<Documento> documentoListNew = usuario.getDocumentoList();
            List<Solicitud> solicitudListOld = persistentUsuario.getSolicitudList();
            List<Solicitud> solicitudListNew = usuario.getSolicitudList();
            List<String> illegalOrphanMessages = null;
            for (Documento documentoListOldDocumento : documentoListOld) {
                if (!documentoListNew.contains(documentoListOldDocumento)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Documento " + documentoListOldDocumento + " since its idUser field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idPersonaNew != null) {
                idPersonaNew = em.getReference(idPersonaNew.getClass(), idPersonaNew.getIdPersona());
                usuario.setIdPersona(idPersonaNew);
            }
            if (idTipoUsuarioNew != null) {
                idTipoUsuarioNew = em.getReference(idTipoUsuarioNew.getClass(), idTipoUsuarioNew.getIdTipoUsuario());
                usuario.setIdTipoUsuario(idTipoUsuarioNew);
            }
            List<Documento> attachedDocumentoListNew = new ArrayList<Documento>();
            for (Documento documentoListNewDocumentoToAttach : documentoListNew) {
                documentoListNewDocumentoToAttach = em.getReference(documentoListNewDocumentoToAttach.getClass(), documentoListNewDocumentoToAttach.getIdDoc());
                attachedDocumentoListNew.add(documentoListNewDocumentoToAttach);
            }
            documentoListNew = attachedDocumentoListNew;
            usuario.setDocumentoList(documentoListNew);
            List<Solicitud> attachedSolicitudListNew = new ArrayList<Solicitud>();
            for (Solicitud solicitudListNewSolicitudToAttach : solicitudListNew) {
                solicitudListNewSolicitudToAttach = em.getReference(solicitudListNewSolicitudToAttach.getClass(), solicitudListNewSolicitudToAttach.getIdSolicitud());
                attachedSolicitudListNew.add(solicitudListNewSolicitudToAttach);
            }
            solicitudListNew = attachedSolicitudListNew;
            usuario.setSolicitudList(solicitudListNew);
            usuario = em.merge(usuario);
            if (idPersonaOld != null && !idPersonaOld.equals(idPersonaNew)) {
                idPersonaOld.getUsuarioList().remove(usuario);
                idPersonaOld = em.merge(idPersonaOld);
            }
            if (idPersonaNew != null && !idPersonaNew.equals(idPersonaOld)) {
                idPersonaNew.getUsuarioList().add(usuario);
                idPersonaNew = em.merge(idPersonaNew);
            }
            if (idTipoUsuarioOld != null && !idTipoUsuarioOld.equals(idTipoUsuarioNew)) {
                idTipoUsuarioOld.getUsuarioList().remove(usuario);
                idTipoUsuarioOld = em.merge(idTipoUsuarioOld);
            }
            if (idTipoUsuarioNew != null && !idTipoUsuarioNew.equals(idTipoUsuarioOld)) {
                idTipoUsuarioNew.getUsuarioList().add(usuario);
                idTipoUsuarioNew = em.merge(idTipoUsuarioNew);
            }
            for (Documento documentoListNewDocumento : documentoListNew) {
                if (!documentoListOld.contains(documentoListNewDocumento)) {
                    Usuario oldIdUserOfDocumentoListNewDocumento = documentoListNewDocumento.getIdUser();
                    documentoListNewDocumento.setIdUser(usuario);
                    documentoListNewDocumento = em.merge(documentoListNewDocumento);
                    if (oldIdUserOfDocumentoListNewDocumento != null && !oldIdUserOfDocumentoListNewDocumento.equals(usuario)) {
                        oldIdUserOfDocumentoListNewDocumento.getDocumentoList().remove(documentoListNewDocumento);
                        oldIdUserOfDocumentoListNewDocumento = em.merge(oldIdUserOfDocumentoListNewDocumento);
                    }
                }
            }
            for (Solicitud solicitudListOldSolicitud : solicitudListOld) {
                if (!solicitudListNew.contains(solicitudListOldSolicitud)) {
                    solicitudListOldSolicitud.setIdAprobador(null);
                    solicitudListOldSolicitud = em.merge(solicitudListOldSolicitud);
                }
            }
            for (Solicitud solicitudListNewSolicitud : solicitudListNew) {
                if (!solicitudListOld.contains(solicitudListNewSolicitud)) {
                    Usuario oldIdAprobadorOfSolicitudListNewSolicitud = solicitudListNewSolicitud.getIdAprobador();
                    solicitudListNewSolicitud.setIdAprobador(usuario);
                    solicitudListNewSolicitud = em.merge(solicitudListNewSolicitud);
                    if (oldIdAprobadorOfSolicitudListNewSolicitud != null && !oldIdAprobadorOfSolicitudListNewSolicitud.equals(usuario)) {
                        oldIdAprobadorOfSolicitudListNewSolicitud.getSolicitudList().remove(solicitudListNewSolicitud);
                        oldIdAprobadorOfSolicitudListNewSolicitud = em.merge(oldIdAprobadorOfSolicitudListNewSolicitud);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getIdUsuario();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getIdUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Documento> documentoListOrphanCheck = usuario.getDocumentoList();
            for (Documento documentoListOrphanCheckDocumento : documentoListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Documento " + documentoListOrphanCheckDocumento + " in its documentoList field has a non-nullable idUser field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Persona idPersona = usuario.getIdPersona();
            if (idPersona != null) {
                idPersona.getUsuarioList().remove(usuario);
                idPersona = em.merge(idPersona);
            }
            TipoUsuario idTipoUsuario = usuario.getIdTipoUsuario();
            if (idTipoUsuario != null) {
                idTipoUsuario.getUsuarioList().remove(usuario);
                idTipoUsuario = em.merge(idTipoUsuario);
            }
            List<Solicitud> solicitudList = usuario.getSolicitudList();
            for (Solicitud solicitudListSolicitud : solicitudList) {
                solicitudListSolicitud.setIdAprobador(null);
                solicitudListSolicitud = em.merge(solicitudListSolicitud);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public Usuario findUsuario(String user) {
        EntityManager em = getEntityManager();
        try {
            List<Usuario> listUsuarios = em.createNamedQuery("Usuario.findByUser", Usuario.class).setParameter("user", user).getResultList();

            if (listUsuarios.isEmpty()) {
                return null;
            } else {
                return listUsuarios.get(0);
            }
        } finally {
            em.close();
        }
    }

    public Usuario autenticacion(Usuario usuario) {
        Usuario usuarioBD = findUsuario(usuario.getUser());
        String passEncrip = DigestUtils.sha1Hex(usuario.getPassword());
        boolean isValido = usuarioBD != null ? (usuarioBD.getPassword().equals(passEncrip)) && usuarioBD.getActivo() : false;
        return isValido ? usuarioBD : null;
    }
    
    public Usuario autenticacionGoogle(Usuario usuario) {
        Usuario usuarioBD = findUsuario(usuario.getUser());
        boolean isValido = usuarioBD != null ? usuarioBD.getActivo() : false;        
        return isValido ? usuarioBD : null;
    }
    
    public Usuario findUsuarioByEmail(String email) {
        EntityManager em = getEntityManager();
        try {
            return (Usuario) em.createNamedQuery("Usuario.findByUser", Usuario.class).setParameter("user", email).getResultList().get(0);
        } finally {
            em.close();
        }
    }
    
}
