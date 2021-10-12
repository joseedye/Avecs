
package Controlador;

import DAO.Conexion;
import DAO.PostulanteJpaController;
import DTO.Postulante;
import Util.Utileria;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Leonardo
 */
public class QueryPostulantes extends HttpServlet {

    
    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        try {
//            EntityManagerFactory emf = null;
            EntityManagerFactory emf = Conexion.getConexion().getBd();
            PostulanteJpaController postulanteDao = new PostulanteJpaController(emf);

            List<Postulante> listPostulantes = postulanteDao.findPostulanteEntities();
            Map<String, Object> mapPostulantes = new HashMap<>();

            for (int i = 0; i < listPostulantes.size(); i++) {
                mapPostulantes.put(i + "", Utileria.postulanteToMap(listPostulantes.get(i)));
            }
            
            req.getSession().setAttribute("postulantes", mapPostulantes);            
            res.sendRedirect("Docente/postulante_consulta");
            
        } catch (Exception e) {
            
            req.getSession().setAttribute("msg", "Error, al consultar postulantes");
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
