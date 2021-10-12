package Controlador;

import DAO.Conexion;
import DAO.UsuarioJpaController;
import DTO.Usuario;
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

public class QueryUsers extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        Map<String, String> user = (Map<String, String>) req.getSession().getAttribute("user");
        try {
            EntityManagerFactory emf = Conexion.getConexion().getBd();
            UsuarioJpaController usuarioDAO = new UsuarioJpaController(emf);

            List<Usuario> listUsuarios = usuarioDAO.findUsuarioEntities();
            Map<String, Object> mapUsuarios = new HashMap<>();

            for (int i = 0; i < listUsuarios.size(); i++) {
                mapUsuarios.put(i + "", Utileria.usuarioToMap(listUsuarios.get(i)));
            }

            req.getSession().setAttribute("usuarios", mapUsuarios);            
            res.sendRedirect(user.get("TipoUsuario")+"/usuario_consulta");
            
        } catch (Exception e) {
            req.getSession().setAttribute("msg", "Error, al consultar usuarios");
            res.sendRedirect(user.get("TipoUsuario")+"/usuario_registrar");
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
