/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.NonexistentEntityException;
import DTO.Documento;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.Persona;
import DTO.TipoDocumento;
import DTO.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Leonardo
 */
public class DocumentoJpaController implements Serializable {

    public DocumentoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Documento documento) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Persona idPersona = documento.getIdPersona();
            if (idPersona != null) {
                idPersona = em.getReference(idPersona.getClass(), idPersona.getIdPersona());
                documento.setIdPersona(idPersona);
            }
            TipoDocumento idTipoDoc = documento.getIdTipoDoc();
            if (idTipoDoc != null) {
                idTipoDoc = em.getReference(idTipoDoc.getClass(), idTipoDoc.getIdTipoDoc());
                documento.setIdTipoDoc(idTipoDoc);
            }
            Usuario idUser = documento.getIdUser();
            if (idUser != null) {
                idUser = em.getReference(idUser.getClass(), idUser.getIdUsuario());
                documento.setIdUser(idUser);
            }
            em.persist(documento);
            if (idPersona != null) {
                idPersona.getDocumentoList().add(documento);
                idPersona = em.merge(idPersona);
            }
            if (idTipoDoc != null) {
                idTipoDoc.getDocumentoList().add(documento);
                idTipoDoc = em.merge(idTipoDoc);
            }
            if (idUser != null) {
                idUser.getDocumentoList().add(documento);
                idUser = em.merge(idUser);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Documento documento) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Documento persistentDocumento = em.find(Documento.class, documento.getIdDoc());
            Persona idPersonaOld = persistentDocumento.getIdPersona();
            Persona idPersonaNew = documento.getIdPersona();
            TipoDocumento idTipoDocOld = persistentDocumento.getIdTipoDoc();
            TipoDocumento idTipoDocNew = documento.getIdTipoDoc();
            Usuario idUserOld = persistentDocumento.getIdUser();
            Usuario idUserNew = documento.getIdUser();
            if (idPersonaNew != null) {
                idPersonaNew = em.getReference(idPersonaNew.getClass(), idPersonaNew.getIdPersona());
                documento.setIdPersona(idPersonaNew);
            }
            if (idTipoDocNew != null) {
                idTipoDocNew = em.getReference(idTipoDocNew.getClass(), idTipoDocNew.getIdTipoDoc());
                documento.setIdTipoDoc(idTipoDocNew);
            }
            if (idUserNew != null) {
                idUserNew = em.getReference(idUserNew.getClass(), idUserNew.getIdUsuario());
                documento.setIdUser(idUserNew);
            }
            documento = em.merge(documento);
            if (idPersonaOld != null && !idPersonaOld.equals(idPersonaNew)) {
                idPersonaOld.getDocumentoList().remove(documento);
                idPersonaOld = em.merge(idPersonaOld);
            }
            if (idPersonaNew != null && !idPersonaNew.equals(idPersonaOld)) {
                idPersonaNew.getDocumentoList().add(documento);
                idPersonaNew = em.merge(idPersonaNew);
            }
            if (idTipoDocOld != null && !idTipoDocOld.equals(idTipoDocNew)) {
                idTipoDocOld.getDocumentoList().remove(documento);
                idTipoDocOld = em.merge(idTipoDocOld);
            }
            if (idTipoDocNew != null && !idTipoDocNew.equals(idTipoDocOld)) {
                idTipoDocNew.getDocumentoList().add(documento);
                idTipoDocNew = em.merge(idTipoDocNew);
            }
            if (idUserOld != null && !idUserOld.equals(idUserNew)) {
                idUserOld.getDocumentoList().remove(documento);
                idUserOld = em.merge(idUserOld);
            }
            if (idUserNew != null && !idUserNew.equals(idUserOld)) {
                idUserNew.getDocumentoList().add(documento);
                idUserNew = em.merge(idUserNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = documento.getIdDoc();
                if (findDocumento(id) == null) {
                    throw new NonexistentEntityException("The documento with id " + id + " no longer exists.");
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
            Documento documento;
            try {
                documento = em.getReference(Documento.class, id);
                documento.getIdDoc();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The documento with id " + id + " no longer exists.", enfe);
            }
            Persona idPersona = documento.getIdPersona();
            if (idPersona != null) {
                idPersona.getDocumentoList().remove(documento);
                idPersona = em.merge(idPersona);
            }
            TipoDocumento idTipoDoc = documento.getIdTipoDoc();
            if (idTipoDoc != null) {
                idTipoDoc.getDocumentoList().remove(documento);
                idTipoDoc = em.merge(idTipoDoc);
            }
            Usuario idUser = documento.getIdUser();
            if (idUser != null) {
                idUser.getDocumentoList().remove(documento);
                idUser = em.merge(idUser);
            }
            em.remove(documento);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Documento> findDocumentoEntities() {
        return findDocumentoEntities(true, -1, -1);
    }

    public List<Documento> findDocumentoEntities(int maxResults, int firstResult) {
        return findDocumentoEntities(false, maxResults, firstResult);
    }

    private List<Documento> findDocumentoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Documento.class));
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

    public Documento findDocumento(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Documento.class, id);
        } finally {
            em.close();
        }
    }

    public int getDocumentoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Documento> rt = cq.from(Documento.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
