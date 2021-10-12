package Controlador;

import DAO.Conexion;
import DAO.DocenteJpaController;
import DAO.EstudianteJpaController;
import DAO.PostulanteJpaController;
import DAO.SolicitudJpaController;
import DAO.UsuarioJpaController;
import DTO.Cronograma;
import DTO.Docente;
import DTO.Estudiante;
import DTO.Persona;
import DTO.Postulante;
import DTO.Solicitud;
import DTO.Usuario;
import Util.Utileria;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QuerySolicitudes extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        Map<String, String> user = (Map<String, String>) req.getSession().getAttribute("user");
        String idSolicitud = req.getParameter("idSolicitud");
        try {
            EntityManagerFactory emf = Conexion.getConexion().getBd();
            SolicitudJpaController solicitudDao = new SolicitudJpaController(emf);
            EstudianteJpaController estudianteDao = new EstudianteJpaController(emf);
            UsuarioJpaController usuarioDao = new UsuarioJpaController(emf);
            PostulanteJpaController postulanteDao = new PostulanteJpaController(emf);

            //Obtiene el detalle de una solicitud
            if (idSolicitud != null) {
                Solicitud solicitud = solicitudDao.findSolicitud(Integer.valueOf(idSolicitud));
                Map<String, String> mapSolicitud = Utileria.solicitudToMap(solicitud);
                req.getSession().setAttribute("solicitud", mapSolicitud);

                if (user.get("TipoUsuario").equals("Estudiante")) {

                    int iduser = Integer.valueOf(user.get("idUsuario"));
                    Usuario usuario = usuarioDao.findUsuario(iduser);
                    Persona persona = usuario.getIdPersona();
                    Estudiante estudiante = estudianteDao.findEstudianteByPersona(persona.getIdPersona());

                    req.getSession().setAttribute("idEstudiante", estudiante.getIdEstudiante());
                    req.getSession().removeAttribute("estatusPostulante");

                    int idSolicit = Integer.valueOf(idSolicitud);
                    Map<String, Object> mapCroonograma = new HashMap<>();

                    //si el estudiante ha postulado a esta visita.
                    try {
                        Postulante postulante = postulanteDao.findPostulanteByEstudianteAndSolicitud(estudiante.getIdEstudiante(), idSolicit);
                        req.getSession().setAttribute("estatusPostulante", postulante.getEstatus().getDescripcion());
                        req.getSession().setAttribute("idPostulante", postulante.getId());
                        req.getSession().setAttribute("observacionPostulante", postulante.getObservacion());
                    } catch (Exception ex) {
                        req.getSession().setAttribute("estatusPostulante", "");
                        req.getSession().setAttribute("idPostulante", 0);
                        req.getSession().setAttribute("observacionPostulante", "");
                    }

                    //obtiene el cronograma de la solicitud.
                    List<Cronograma> listcronograma = solicitud.getCronogramaList();

                    for (int i = 0; i < listcronograma.size(); i++) {
                        mapCroonograma.put(i + "", Utileria.cronogramaToMap(listcronograma.get(i)));
                    }
                    req.getSession().setAttribute("cronograma", mapCroonograma);
                }

                String ventana = req.getParameter("idventana");
                req.getSession().setAttribute("idventana", ventana);
                res.sendRedirect(user.get("TipoUsuario") + "/solicitud_ver?idSolicitud=" + idSolicitud);
                

            //Obtiene list de solicitudes
            } else {
                List<Solicitud> listSolicitud = new ArrayList<>();
                Map<String, Object> mapSolicitudes = new HashMap<>();
                String tipoUser = user.get("TipoUsuario");

                switch (tipoUser) {
                    case "Administrador":
                        listSolicitud = solicitudDao.findSolicitudEntities();
                        for (int i = 0; i < listSolicitud.size(); i++) {
                            if (listSolicitud.get(i).getEstatus().getDescripcion().equals("Pendiente") || listSolicitud.get(i).getEstatus().getDescripcion().equals("No Aprobada")) {
                                mapSolicitudes.put(i + "", Utileria.solicitudToMap(listSolicitud.get(i)));
                            }
                        }
                        req.getSession().setAttribute("solicitudes", mapSolicitudes);
                        res.sendRedirect("Administrador/solicitudes");
                        break;

                    case "Docente":
                        DocenteJpaController docenteDao = new DocenteJpaController(emf);
                        UsuarioJpaController usuariojpa = new UsuarioJpaController(emf);
                        int iduser = Integer.valueOf(user.get("idUsuario"));
                        Usuario usuario = usuariojpa.findUsuario(iduser);
                        Docente docente = docenteDao.findDocenteByPersona(usuario.getIdPersona().getIdPersona());
                        listSolicitud = docente.getSolicitudList();

                        for (int i = 0; i < listSolicitud.size(); i++) {
                            mapSolicitudes.put(i + "", Utileria.solicitudToMap(listSolicitud.get(i)));
                        }
                        req.getSession().setAttribute("solicitudes", mapSolicitudes);
                        res.sendRedirect("Docente/Solicitudes");
                        break;

                    case "Estudiante":
                        boolean filterMyPostulation = Boolean.valueOf(req.getParameter("filterMyPostulation"));
                        int idUsuari = Integer.valueOf(user.get("idUsuario"));
                        Usuario usuari = usuarioDao.findUsuario(idUsuari);
                        Persona persona = usuari.getIdPersona();
                        Estudiante estudiante = estudianteDao.findEstudianteByPersona(persona.getIdPersona());

                        //Obtengo las postulaciones del estudiante excepto las aprobadas (ya son visitas).
                        if (filterMyPostulation) {

                            for (Postulante postulante : estudiante.getPostulanteList()) {
                                if (postulante.getEstatus().getId() != 2) {
                                    int idSol = postulante.getIdSolicitud().getIdSolicitud();
                                    listSolicitud.add(solicitudDao.findSolicitud(idSol));
                                }
                            }
                            req.getSession().setAttribute("filterMyPostulation", "true");

                        } //Obtengo todas las solicitudes excepto las aprobadas por el estudiante.
                        else {
                            listSolicitud = solicitudDao.findSolicitudEntities();
                            for (Postulante postulante : estudiante.getPostulanteList()) {
                                if (postulante.getEstatus().getId() == 2) {
                                    int idSol = postulante.getIdSolicitud().getIdSolicitud();
                                    listSolicitud.remove(solicitudDao.findSolicitud(idSol));
                                }
                            }
                            req.getSession().setAttribute("filterMyPostulation", "false");
                        }

                        //Crea el mapa de solicitudes para la vista
                        for (int i = 0; i < listSolicitud.size(); i++) {
                            if (listSolicitud.get(i).getEstatus().getDescripcion().equals("Aprobada")) {
                                mapSolicitudes.put(i + "", Utileria.solicitudToMap(listSolicitud.get(i)));
                            }
                        }
                        req.getSession().setAttribute("solicitudes", mapSolicitudes);
                        res.sendRedirect("Estudiante/solicitudes");
                        break;

                }
            }

        } catch (Exception e) {
            req.getSession().setAttribute("msg", "Error en la consulta de visitas empresariales.");
            res.sendRedirect(user.get("TipoUsuario") + "/perfil");
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
