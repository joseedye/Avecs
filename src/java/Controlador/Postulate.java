package Controlador;

import DAO.Conexion;
import DAO.EstatusPostulanteJpaController;
import DAO.EstudianteJpaController;
import DAO.PostulanteJpaController;
import DAO.SolicitudJpaController;
import DTO.Estudiante;
import DTO.Postulante;
import DTO.Solicitud;
import java.io.IOException;
import java.util.Date;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Postulate extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            EntityManagerFactory emf = Conexion.getConexion().getBd();
            PostulanteJpaController postulanteDao = new PostulanteJpaController(emf);
            EstatusPostulanteJpaController estatusDao = new EstatusPostulanteJpaController(emf);
            SolicitudJpaController solicitudDao = new SolicitudJpaController(emf);

            String idSolicitu = req.getParameter("idSolicitud");
            int idSolicitud = Integer.valueOf(idSolicitu);
            Solicitud solicitud = solicitudDao.findSolicitud(idSolicitud);

            boolean isCancelate = Boolean.valueOf(req.getParameter("isCancelate"));

            //cancela la postulacion
            if (isCancelate) {
                String idPostulante = req.getParameter("idPostulante");
                int idPostulant = Integer.valueOf(idPostulante);
                Postulante postulante = postulanteDao.findPostulante(idPostulant);
                postulanteDao.destroy(idPostulant);
                solicitud.getPostulanteList().remove(postulante);
                solicitud.setCuposDisponibles(solicitud.getCuposDisponibles() + 1);
                solicitudDao.edit(solicitud);

                req.getSession().setAttribute("msg", "Solicitud a visita empresarial cancelada exitosamente!");
                res.sendRedirect("QuerySolicitudes.do");
            } //Crea una nueva postulacion
            else {
                int cupos = solicitud.getCuposDisponibles();

                if (cupos > 0) {
                    EstudianteJpaController estudianteDao = new EstudianteJpaController(emf);
                    String idEstudiant = req.getParameter("idEstudiante");
                    int idEstudiante = Integer.valueOf(idEstudiant);
                    Estudiante estudiante = estudianteDao.findEstudiante(idEstudiante);

                    Postulante postulante = new Postulante();
                    postulante.setEstatus(estatusDao.findEstatusPostulanteEntities().get(0));
                    postulante.setFecPostulacion(new Date());
                    postulante.setIdEstudiante(estudiante);
                    postulante.setIdSolicitud(solicitud);
                    postulante.setObservacion("");
                    postulanteDao.create(postulante);   

                    solicitud.getPostulanteList().add(postulante);
                    solicitud.setCuposDisponibles(solicitud.getCuposDisponibles() - 1);
                    solicitudDao.edit(solicitud);

                    req.getSession().setAttribute("msg", "Solicitud enviada exitosamente!");
                    res.sendRedirect("QuerySolicitudes.do");
                }else{
                    req.getSession().setAttribute("msg", "No hay cupos disponibles!");
                    res.sendRedirect("QuerySolicitudes.do?idSolicitud="+idSolicitud);
                }
            }

        } catch (Exception e) {
            req.getSession().setAttribute("msg", "Error durante la solicitud. Intentar nuevamente");
            res.sendRedirect("QuerySolicitudes.do");
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
