
package Controlador;

import DAO.Conexion;
import DAO.UsuarioJpaController;
import DTO.Usuario;
import java.io.IOException;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.digest.DigestUtils;

public class UpdatePassword extends HttpServlet {


    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            EntityManagerFactory emf = Conexion.getConexion().getBd();
            Map<String, String> user = (Map<String, String>) req.getSession().getAttribute("user");
            
            String anterior = req.getParameter("ContraAnt");
            String nueva = req.getParameter("ContraNueva");
            
            String nueva2 = DigestUtils.sha1Hex(nueva);
            String anterior2 = DigestUtils.sha1Hex(anterior);
            
            String contraguardada = user.get("contra");
            String usuarioo = user.get("user");

            UsuarioJpaController usuarioDao = new UsuarioJpaController(emf);

            if (anterior2.equals(contraguardada)) {
                Usuario usuario = usuarioDao.findUsuario(usuarioo);
                usuario.setPassword(nueva2);
                usuarioDao.edit(usuario);
                req.getSession().setAttribute("msg", "se ha cambiado exitosamente su contraseña");
                res.sendRedirect("Docente/perfil");
            } else {
                req.getSession().setAttribute("msg", "Error,no se ha podido cambiar la contraseña");
                res.sendRedirect("Docente/perfil");
            }

        } catch (Exception e) {
            req.getSession().setAttribute("msg", "Error,no se ha podido cambiar la contraseña");
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
