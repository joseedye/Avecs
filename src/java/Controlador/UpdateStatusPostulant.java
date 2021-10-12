/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.Conexion;
import DAO.EstatusPostulanteJpaController;
import DAO.PostulanteJpaController;
import DAO.SolicitudJpaController;
import DTO.EstatusPostulante;
import DTO.Postulante;
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
public class UpdateStatusPostulant extends HttpServlet {

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
            int idPostulante = Integer.valueOf(req.getParameterValues("idPostulante")[0]);
            int estatus = Integer.valueOf(req.getParameterValues("estatus")[0]);
            String observacion = req.getParameterValues("observacion")[0];

            EntityManagerFactory emf = Conexion.getConexion().getBd();
            PostulanteJpaController postulanteDao = new PostulanteJpaController(emf);
            EstatusPostulanteJpaController estausDao = new EstatusPostulanteJpaController(emf);
            SolicitudJpaController solicitudDao = new SolicitudJpaController(emf);
            
            Postulante postulante = postulanteDao.findPostulante(idPostulante);
            EstatusPostulante estatusDto = estausDao.findEstatusPostulante(estatus);            
            
            postulante.setEstatus(estatusDto);              
            postulante.setObservacion(observacion);
            postulanteDao.edit(postulante); 
            
            
            Solicitud solicitud = postulante.getIdSolicitud();
            int cupos = solicitud.getCuposDisponibles();
            if(estatus==2)
                solicitud.setCuposDisponibles(cupos-1);
            if(estatus==3)
                solicitud.setCuposDisponibles(cupos+1);
            
            solicitudDao.edit(solicitud);
                        
            res.sendRedirect("QueryPostulantes.do");            
            
        } catch (Exception e) {
            req.getSession().setAttribute("msg", "Error al actualizar el estatus del postulante.");
            res.sendRedirect("QueryPostulantes.do");
            
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
