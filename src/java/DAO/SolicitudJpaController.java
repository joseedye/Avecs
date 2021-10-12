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
import DTO.Asignatura;
import DTO.Docente;
import DTO.Empresa;
import DTO.EstatusSolicitud;
import DTO.Usuario;
import DTO.Cronograma;
import java.util.ArrayList;
import java.util.List;
import DTO.Postulante;
import DTO.Solicitud;
import java.util.Vector;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Leonardo
 */
public class SolicitudJpaController implements Serializable {

    public SolicitudJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Solicitud solicitud) {
        if (solicitud.getCronogramaList() == null) {
            solicitud.setCronogramaList(new ArrayList<Cronograma>());
        }
        if (solicitud.getPostulanteList() == null) {
            solicitud.setPostulanteList(new ArrayList<Postulante>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asignatura idAsignatura = solicitud.getIdAsignatura();
            if (idAsignatura != null) {
                idAsignatura = em.getReference(idAsignatura.getClass(), idAsignatura.getIdAsignatura());
                solicitud.setIdAsignatura(idAsignatura);
            }
            Docente idDocente = solicitud.getIdDocente();
            if (idDocente != null) {
                idDocente = em.getReference(idDocente.getClass(), idDocente.getIdDocente());
                solicitud.setIdDocente(idDocente);
            }
            Empresa idEmpresa = solicitud.getIdEmpresa();
            if (idEmpresa != null) {
                idEmpresa = em.getReference(idEmpresa.getClass(), idEmpresa.getIdEmpresa());
                solicitud.setIdEmpresa(idEmpresa);
            }
            EstatusSolicitud estatus = solicitud.getEstatus();
            if (estatus != null) {
                estatus = em.getReference(estatus.getClass(), estatus.getId());
                solicitud.setEstatus(estatus);
            }
            Usuario idAprobador = solicitud.getIdAprobador();
            if (idAprobador != null) {
                idAprobador = em.getReference(idAprobador.getClass(), idAprobador.getIdUsuario());
                solicitud.setIdAprobador(idAprobador);
            }
            List<Cronograma> attachedCronogramaList = new ArrayList<Cronograma>();
            for (Cronograma cronogramaListCronogramaToAttach : solicitud.getCronogramaList()) {
                cronogramaListCronogramaToAttach = em.getReference(cronogramaListCronogramaToAttach.getClass(), cronogramaListCronogramaToAttach.getId());
                attachedCronogramaList.add(cronogramaListCronogramaToAttach);
            }
            solicitud.setCronogramaList(attachedCronogramaList);
            List<Postulante> attachedPostulanteList = new ArrayList<Postulante>();
            for (Postulante postulanteListPostulanteToAttach : solicitud.getPostulanteList()) {
                postulanteListPostulanteToAttach = em.getReference(postulanteListPostulanteToAttach.getClass(), postulanteListPostulanteToAttach.getId());
                attachedPostulanteList.add(postulanteListPostulanteToAttach);
            }
            solicitud.setPostulanteList(attachedPostulanteList);
            em.persist(solicitud);
            if (idAsignatura != null) {
                idAsignatura.getSolicitudList().add(solicitud);
                idAsignatura = em.merge(idAsignatura);
            }
            if (idDocente != null) {
                idDocente.getSolicitudList().add(solicitud);
                idDocente = em.merge(idDocente);
            }
            if (idEmpresa != null) {
                idEmpresa.getSolicitudList().add(solicitud);
                idEmpresa = em.merge(idEmpresa);
            }
            if (estatus != null) {
                estatus.getSolicitudList().add(solicitud);
                estatus = em.merge(estatus);
            }
            if (idAprobador != null) {
                idAprobador.getSolicitudList().add(solicitud);
                idAprobador = em.merge(idAprobador);
            }
            for (Cronograma cronogramaListCronograma : solicitud.getCronogramaList()) {
                Solicitud oldIdSolicitudOfCronogramaListCronograma = cronogramaListCronograma.getIdSolicitud();
                cronogramaListCronograma.setIdSolicitud(solicitud);
                cronogramaListCronograma = em.merge(cronogramaListCronograma);
                if (oldIdSolicitudOfCronogramaListCronograma != null) {
                    oldIdSolicitudOfCronogramaListCronograma.getCronogramaList().remove(cronogramaListCronograma);
                    oldIdSolicitudOfCronogramaListCronograma = em.merge(oldIdSolicitudOfCronogramaListCronograma);
                }
            }
            for (Postulante postulanteListPostulante : solicitud.getPostulanteList()) {
                Solicitud oldIdSolicitudOfPostulanteListPostulante = postulanteListPostulante.getIdSolicitud();
                postulanteListPostulante.setIdSolicitud(solicitud);
                postulanteListPostulante = em.merge(postulanteListPostulante);
                if (oldIdSolicitudOfPostulanteListPostulante != null) {
                    oldIdSolicitudOfPostulanteListPostulante.getPostulanteList().remove(postulanteListPostulante);
                    oldIdSolicitudOfPostulanteListPostulante = em.merge(oldIdSolicitudOfPostulanteListPostulante);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Solicitud solicitud) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Solicitud persistentSolicitud = em.find(Solicitud.class, solicitud.getIdSolicitud());
            Asignatura idAsignaturaOld = persistentSolicitud.getIdAsignatura();
            Asignatura idAsignaturaNew = solicitud.getIdAsignatura();
            Docente idDocenteOld = persistentSolicitud.getIdDocente();
            Docente idDocenteNew = solicitud.getIdDocente();
            Empresa idEmpresaOld = persistentSolicitud.getIdEmpresa();
            Empresa idEmpresaNew = solicitud.getIdEmpresa();
            EstatusSolicitud estatusOld = persistentSolicitud.getEstatus();
            EstatusSolicitud estatusNew = solicitud.getEstatus();
            Usuario idAprobadorOld = persistentSolicitud.getIdAprobador();
            Usuario idAprobadorNew = solicitud.getIdAprobador();
            List<Cronograma> cronogramaListOld = persistentSolicitud.getCronogramaList();
            List<Cronograma> cronogramaListNew = solicitud.getCronogramaList();
            List<Postulante> postulanteListOld = persistentSolicitud.getPostulanteList();
            List<Postulante> postulanteListNew = solicitud.getPostulanteList();
            List<String> illegalOrphanMessages = null;
            for (Cronograma cronogramaListOldCronograma : cronogramaListOld) {
                if (!cronogramaListNew.contains(cronogramaListOldCronograma)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Cronograma " + cronogramaListOldCronograma + " since its idSolicitud field is not nullable.");
                }
            }
            for (Postulante postulanteListOldPostulante : postulanteListOld) {
                if (!postulanteListNew.contains(postulanteListOldPostulante)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Postulante " + postulanteListOldPostulante + " since its idSolicitud field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (idAsignaturaNew != null) {
                idAsignaturaNew = em.getReference(idAsignaturaNew.getClass(), idAsignaturaNew.getIdAsignatura());
                solicitud.setIdAsignatura(idAsignaturaNew);
            }
            if (idDocenteNew != null) {
                idDocenteNew = em.getReference(idDocenteNew.getClass(), idDocenteNew.getIdDocente());
                solicitud.setIdDocente(idDocenteNew);
            }
            if (idEmpresaNew != null) {
                idEmpresaNew = em.getReference(idEmpresaNew.getClass(), idEmpresaNew.getIdEmpresa());
                solicitud.setIdEmpresa(idEmpresaNew);
            }
            if (estatusNew != null) {
                estatusNew = em.getReference(estatusNew.getClass(), estatusNew.getId());
                solicitud.setEstatus(estatusNew);
            }
            if (idAprobadorNew != null) {
                idAprobadorNew = em.getReference(idAprobadorNew.getClass(), idAprobadorNew.getIdUsuario());
                solicitud.setIdAprobador(idAprobadorNew);
            }
            List<Cronograma> attachedCronogramaListNew = new ArrayList<Cronograma>();
            for (Cronograma cronogramaListNewCronogramaToAttach : cronogramaListNew) {
                cronogramaListNewCronogramaToAttach = em.getReference(cronogramaListNewCronogramaToAttach.getClass(), cronogramaListNewCronogramaToAttach.getId());
                attachedCronogramaListNew.add(cronogramaListNewCronogramaToAttach);
            }
            cronogramaListNew = attachedCronogramaListNew;
            solicitud.setCronogramaList(cronogramaListNew);
            List<Postulante> attachedPostulanteListNew = new ArrayList<Postulante>();
            for (Postulante postulanteListNewPostulanteToAttach : postulanteListNew) {
                postulanteListNewPostulanteToAttach = em.getReference(postulanteListNewPostulanteToAttach.getClass(), postulanteListNewPostulanteToAttach.getId());
                attachedPostulanteListNew.add(postulanteListNewPostulanteToAttach);
            }
            postulanteListNew = attachedPostulanteListNew;
            solicitud.setPostulanteList(postulanteListNew);
            solicitud = em.merge(solicitud);
            if (idAsignaturaOld != null && !idAsignaturaOld.equals(idAsignaturaNew)) {
                idAsignaturaOld.getSolicitudList().remove(solicitud);
                idAsignaturaOld = em.merge(idAsignaturaOld);
            }
            if (idAsignaturaNew != null && !idAsignaturaNew.equals(idAsignaturaOld)) {
                idAsignaturaNew.getSolicitudList().add(solicitud);
                idAsignaturaNew = em.merge(idAsignaturaNew);
            }
            if (idDocenteOld != null && !idDocenteOld.equals(idDocenteNew)) {
                idDocenteOld.getSolicitudList().remove(solicitud);
                idDocenteOld = em.merge(idDocenteOld);
            }
            if (idDocenteNew != null && !idDocenteNew.equals(idDocenteOld)) {
                idDocenteNew.getSolicitudList().add(solicitud);
                idDocenteNew = em.merge(idDocenteNew);
            }
            if (idEmpresaOld != null && !idEmpresaOld.equals(idEmpresaNew)) {
                idEmpresaOld.getSolicitudList().remove(solicitud);
                idEmpresaOld = em.merge(idEmpresaOld);
            }
            if (idEmpresaNew != null && !idEmpresaNew.equals(idEmpresaOld)) {
                idEmpresaNew.getSolicitudList().add(solicitud);
                idEmpresaNew = em.merge(idEmpresaNew);
            }
            if (estatusOld != null && !estatusOld.equals(estatusNew)) {
                estatusOld.getSolicitudList().remove(solicitud);
                estatusOld = em.merge(estatusOld);
            }
            if (estatusNew != null && !estatusNew.equals(estatusOld)) {
                estatusNew.getSolicitudList().add(solicitud);
                estatusNew = em.merge(estatusNew);
            }
            if (idAprobadorOld != null && !idAprobadorOld.equals(idAprobadorNew)) {
                idAprobadorOld.getSolicitudList().remove(solicitud);
                idAprobadorOld = em.merge(idAprobadorOld);
            }
            if (idAprobadorNew != null && !idAprobadorNew.equals(idAprobadorOld)) {
                idAprobadorNew.getSolicitudList().add(solicitud);
                idAprobadorNew = em.merge(idAprobadorNew);
            }
            for (Cronograma cronogramaListNewCronograma : cronogramaListNew) {
                if (!cronogramaListOld.contains(cronogramaListNewCronograma)) {
                    Solicitud oldIdSolicitudOfCronogramaListNewCronograma = cronogramaListNewCronograma.getIdSolicitud();
                    cronogramaListNewCronograma.setIdSolicitud(solicitud);
                    cronogramaListNewCronograma = em.merge(cronogramaListNewCronograma);
                    if (oldIdSolicitudOfCronogramaListNewCronograma != null && !oldIdSolicitudOfCronogramaListNewCronograma.equals(solicitud)) {
                        oldIdSolicitudOfCronogramaListNewCronograma.getCronogramaList().remove(cronogramaListNewCronograma);
                        oldIdSolicitudOfCronogramaListNewCronograma = em.merge(oldIdSolicitudOfCronogramaListNewCronograma);
                    }
                }
            }
            for (Postulante postulanteListNewPostulante : postulanteListNew) {
                if (!postulanteListOld.contains(postulanteListNewPostulante)) {
                    Solicitud oldIdSolicitudOfPostulanteListNewPostulante = postulanteListNewPostulante.getIdSolicitud();
                    postulanteListNewPostulante.setIdSolicitud(solicitud);
                    postulanteListNewPostulante = em.merge(postulanteListNewPostulante);
                    if (oldIdSolicitudOfPostulanteListNewPostulante != null && !oldIdSolicitudOfPostulanteListNewPostulante.equals(solicitud)) {
                        oldIdSolicitudOfPostulanteListNewPostulante.getPostulanteList().remove(postulanteListNewPostulante);
                        oldIdSolicitudOfPostulanteListNewPostulante = em.merge(oldIdSolicitudOfPostulanteListNewPostulante);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = solicitud.getIdSolicitud();
                if (findSolicitud(id) == null) {
                    throw new NonexistentEntityException("The solicitud with id " + id + " no longer exists.");
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
            Solicitud solicitud;
            try {
                solicitud = em.getReference(Solicitud.class, id);
                solicitud.getIdSolicitud();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The solicitud with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Cronograma> cronogramaListOrphanCheck = solicitud.getCronogramaList();
            for (Cronograma cronogramaListOrphanCheckCronograma : cronogramaListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Solicitud (" + solicitud + ") cannot be destroyed since the Cronograma " + cronogramaListOrphanCheckCronograma + " in its cronogramaList field has a non-nullable idSolicitud field.");
            }
            List<Postulante> postulanteListOrphanCheck = solicitud.getPostulanteList();
            for (Postulante postulanteListOrphanCheckPostulante : postulanteListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Solicitud (" + solicitud + ") cannot be destroyed since the Postulante " + postulanteListOrphanCheckPostulante + " in its postulanteList field has a non-nullable idSolicitud field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Asignatura idAsignatura = solicitud.getIdAsignatura();
            if (idAsignatura != null) {
                idAsignatura.getSolicitudList().remove(solicitud);
                idAsignatura = em.merge(idAsignatura);
            }
            Docente idDocente = solicitud.getIdDocente();
            if (idDocente != null) {
                idDocente.getSolicitudList().remove(solicitud);
                idDocente = em.merge(idDocente);
            }
            Empresa idEmpresa = solicitud.getIdEmpresa();
            if (idEmpresa != null) {
                idEmpresa.getSolicitudList().remove(solicitud);
                idEmpresa = em.merge(idEmpresa);
            }
            EstatusSolicitud estatus = solicitud.getEstatus();
            if (estatus != null) {
                estatus.getSolicitudList().remove(solicitud);
                estatus = em.merge(estatus);
            }
            Usuario idAprobador = solicitud.getIdAprobador();
            if (idAprobador != null) {
                idAprobador.getSolicitudList().remove(solicitud);
                idAprobador = em.merge(idAprobador);
            }
            em.remove(solicitud);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Solicitud> findSolicitudEntities() {
        return findSolicitudEntities(true, -1, -1);
    }

    public List<Solicitud> findSolicitudEntities(int maxResults, int firstResult) {
        return findSolicitudEntities(false, maxResults, firstResult);
    }

    private List<Solicitud> findSolicitudEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Solicitud.class));
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

    public Solicitud findSolicitud(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Solicitud.class, id);
        } finally {
            em.close();
        }
    }

    public int getSolicitudCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Solicitud> rt = cq.from(Solicitud.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public Solicitud getSolicitudLast() {
        EntityManager em = getEntityManager();
        try {
            return (Solicitud) em.createNativeQuery("Select * from solicitud order by id_solicitud desc limit 1", Solicitud.class).getResultList().get(0);
        } finally {
            em.close();
        }
    }
    
}
