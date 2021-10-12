package Controlador;

import DAO.Conexion;
import DAO.UsuarioJpaController;
import DTO.Usuario;
import java.io.IOException;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateActivUser extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            int idUsuario = Integer.valueOf(req.getParameterValues("idUsuario")[0]);
            boolean isActivo = Boolean.valueOf(req.getParameterValues("activo")[0]);

            EntityManagerFactory emf = Conexion.getConexion().getBd();
            UsuarioJpaController usuarioDao = new UsuarioJpaController(emf);
            
            Usuario usuario = usuarioDao.findUsuario(idUsuario);
            
            usuario.setActivo(!isActivo);
            usuarioDao.edit(usuario);
            
            String msgInactiv = "Usuario se ha inactivado! Esta opción le prohíbe el acceso al sistema.";
            String msgActiv = "Usuario se ha activado! Esta opción le permite el acceso al sistema.";
            
            if(usuario.getActivo())
                req.getSession().setAttribute("msg", msgActiv);
            else
                req.getSession().setAttribute("msg", msgInactiv);
                
            res.sendRedirect("QueryUsers.do");            
            
        } catch (Exception e) {
            req.getSession().setAttribute("msg", "Error al actualizar el usuario.");
            res.sendRedirect("QueryUsers.do");
            
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
