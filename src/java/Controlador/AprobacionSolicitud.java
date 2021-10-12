
package Controlador;

import DAO.Conexion;
import DAO.EstatusSolicitudJpaController;
import DAO.SolicitudJpaController;
import DAO.UsuarioJpaController;
import DTO.EstatusSolicitud;
import DTO.Solicitud;
import DTO.Usuario;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AprobacionSolicitud extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        
        try {
            String idSolicitud = req.getParameter("idSolicitud");
            String observaciones = req.getParameter("observaciones");
            int status = Integer.valueOf(req.getParameter("status"));
            
            
            Map<String, String> user = (Map<String, String>) req.getSession().getAttribute("user");
            
            EntityManagerFactory emf = Conexion.getConexion().getBd();
            SolicitudJpaController solicitudDao = new SolicitudJpaController(emf);
            UsuarioJpaController usuarioDao = new UsuarioJpaController(emf);
            EstatusSolicitudJpaController estatusDao = new EstatusSolicitudJpaController(emf);
            
            int idUser = Integer.valueOf(user.get("idUsuario"));            
            Usuario usuario = usuarioDao.findUsuario(idUser);
            List<EstatusSolicitud> listEstatus = estatusDao.findEstatusSolicitudEntities();
            
            Date fecAprobacion = new Date();
                        
            Solicitud solicitud = solicitudDao.findSolicitud(Integer.valueOf(idSolicitud));
            solicitud.setIdAprobador(usuario);
            solicitud.setObservacion(observaciones);
            solicitud.setFecAprobacion(fecAprobacion);
            solicitud.setEstatus(listEstatus.get(status));
            
            solicitudDao.edit(solicitud);            
            res.sendRedirect("QuerySolicitudes.do");
            
        } catch (Exception e) {
            res.sendRedirect("/Error/errorRedir");
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

