package Controlador;

import DAO.Conexion;
import DAO.CronogramaJpaController;
import DAO.SolicitudJpaController;
import DTO.Cronograma;
import DTO.Solicitud;
import java.io.IOException;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Leonardo
 */
public class UpdateStatusCronograma extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param req servlet request
     * @param res servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            int idCronograma = 0;
            int idSolicitud = Integer.valueOf(req.getParameter("idSolicitud"));
            EntityManagerFactory emf = Conexion.getConexion().getBd();
            SolicitudJpaController solicitudDao = new SolicitudJpaController(emf);
            Solicitud solicitud = solicitudDao.findSolicitud(idSolicitud);
            CronogramaJpaController cronogramaDao = new CronogramaJpaController(emf);
            Cronograma cronograma = null;            

            String estatus = req.getParameter("estatus");
            String idObservacion = req.getParameter("idObservacion");

            if (estatus != null) {
                idCronograma = Integer.valueOf(req.getParameter("idCronograma"));
                cronograma = cronogramaDao.findCronograma(idCronograma);
                boolean isDone = Boolean.valueOf(estatus);
                cronograma.setIsDone(!isDone);
                cronogramaDao.edit(cronograma);
                String idCheck = req.getParameter("idCheck");
                res.sendRedirect("QueryRequest.do?isVisita=true&idventana=1&idSolicitud=" + idSolicitud + "#done" + idCheck);
                
            } else if(idObservacion != null) {     
                idCronograma = Integer.valueOf(req.getParameter("idCronograma"));                
                cronograma = cronogramaDao.findCronograma(idCronograma);
                String observacion = req.getParameter("observacion");
                cronograma.setObservacion(observacion);                
                cronogramaDao.edit(cronograma);
                res.sendRedirect("QueryRequest.do?isVisita=true&idventana=1&idSolicitud=" + idSolicitud + "#activ" + idObservacion);                
            }
            else{
                String observacionValue = req.getParameter("observacionValue");
                solicitud.setObservacion(observacionValue);
                solicitudDao.edit(solicitud);
                res.sendRedirect("QueryRequest.do?isVisita=true&idventana=1&idSolicitud=" + idSolicitud + "#observaciones");
            }


        } catch (Exception e) {
            req.getSession().setAttribute("msg", "Error al actualizar la actividad.");
            res.sendRedirect("Docente/visita_ver");

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
