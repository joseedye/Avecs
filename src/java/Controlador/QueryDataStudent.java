/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.Conexion;
import DAO.EstudianteJpaController;
import DAO.PostulanteJpaController;
import DTO.Estudiante;
import DTO.Postulante;
import Util.Utileria;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rozo-
 */
public class QueryDataStudent extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
            int id = Integer.valueOf(req.getParameter("idestudiante"));
            int idPostulante = Integer.valueOf(req.getParameter("idPostulante"));

            EntityManagerFactory emf = Conexion.getConexion().getBd();
            EstudianteJpaController estudianteDao = new EstudianteJpaController(emf);
            PostulanteJpaController postulanteDao = new PostulanteJpaController(emf);

            Estudiante estudianteDto = estudianteDao.findEstudiante(id);            
            Postulante postulante = postulanteDao.findPostulante(idPostulante);
            Map<String, String> mapEstudiante = Utileria.estudianteToMap(estudianteDto);

            req.getSession().setAttribute("estudiante", mapEstudiante);
            req.getSession().setAttribute("obPostulante", postulante.getObservacion());
            req.getSession().setAttribute("idPostulante", postulante.getId().toString());
            res.sendRedirect("Docente/doc_estudiante");

        } catch (Exception e) {
            req.getSession().setAttribute("msg", "Error, al consultar el estudiante");
            res.sendRedirect("Docente/postulante_consulta");
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
