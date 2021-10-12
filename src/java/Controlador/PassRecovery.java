package Controlador;

import DAO.Conexion;
import DAO.RecoveryJpaController;
import DAO.UsuarioJpaController;
import DTO.Recovery;
import DTO.Usuario;
import Util.Utileria;
import java.io.IOException;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PassRecovery extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            EntityManagerFactory emf = Conexion.getConexion().getBd();
            RecoveryJpaController recoveryjpa = new RecoveryJpaController(emf);
            UsuarioJpaController usuariojpa = new UsuarioJpaController(emf);

            String user = req.getParameter("user");
            Usuario usuario = usuariojpa.findUsuario(user);

            if (usuario != null) {
                int token = Utileria.token();
                Recovery recoveyDTO = new Recovery(token, user);
                recoveryjpa.create(recoveyDTO);

                String titulo = "Soporte AVECS";
                String dominio = "https://avecs.azurewebsites.net/";
                String enlace = dominio + "Sesion/cambio_clave?token=" + token;
                String cuerpo = "Este mensaje se ha generado automáticamente "
                        + "para realizar el cambio de su contraseña al sistema Avecs. "
                        + "Si fué usted haz clic en siguiente enlace " + enlace
                        + " de lo contrario omita este mensaje.";

                Utileria.enviarCorreo(user, titulo, cuerpo);
                req.getSession().setAttribute("msg", "Se ha enviado al correo electrónico "
                        + "los pasos para recuperar su contraseña.");
                res.sendRedirect("./index");

            } else {
                req.getSession().setAttribute("msg", "Error, no existe registrado el correo!");
                res.sendRedirect("Sesion/recuperacion_clave");
            }

        } catch (Exception e) {
            req.getSession().setAttribute("msg", "Error, intentar de nuevo!");
            res.sendRedirect("Sesion/recuperacion_clave");
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
