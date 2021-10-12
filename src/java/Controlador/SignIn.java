package Controlador;

import DAO.Conexion;
import DAO.DocenteJpaController;
import DAO.EstudianteJpaController;
import DAO.UsuarioJpaController;
import DTO.Docente;
import DTO.Estudiante;
import DTO.Persona;
import DTO.TipoUsuario;
import DTO.Usuario;
import Util.Utileria;
import java.io.IOException;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignIn extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        try {
            EntityManagerFactory emf = Conexion.getConexion().getBd();
            UsuarioJpaController usuarioDao = new UsuarioJpaController(emf);
            EstudianteJpaController estudianteDao = new EstudianteJpaController(emf);

            Usuario usuario = new Usuario();

            try {
                String userGoogle = req.getParameterValues("userGoogle")[0];
                String userGoogleImg = req.getParameterValues("userGoogleImg")[0];

                usuario.setUser(userGoogle);
                usuario = usuarioDao.autenticacionGoogle(usuario);
                req.getSession().setAttribute("userImg", userGoogleImg);
                req.getSession().setAttribute("isUserGoogle", "true");
            } catch (Exception ex) {
                String user = req.getParameter("user");
                String password = req.getParameter("pass");
                req.getSession().setAttribute("userImg", "../img/perfil_none.png");
                req.getSession().setAttribute("isUserGoogle", "false");

                usuario.setUser(user);
                usuario.setPassword(password);
                usuario = usuarioDao.autenticacion(usuario);
            }

            if (usuario != null) {

                Map<String, String> usuarioMap = Utileria.usuarioToMap(usuario);
                req.getSession().setAttribute("user", usuarioMap);

                TipoUsuario tipoUsuario = usuario.getIdTipoUsuario();

                switch (tipoUsuario.getDesTipoUsuario()) {
                    case "Administrador":
                        res.sendRedirect("Administrador/perfil");
                        break;

                    case "Docente":
                        DocenteJpaController docente = new DocenteJpaController(emf);
                        Docente docenteDTO = docente.findDocenteByPersona(usuario.getIdPersona().getIdPersona());
                        String codigo = docenteDTO.getCodigoDocente() + "";
                        req.getSession().setAttribute("codigo", codigo);
                        res.sendRedirect("Docente/perfil");
                        break;

                    case "Estudiante":
                        Persona persona = usuario.getIdPersona();
                        Estudiante estudiante = estudianteDao.findEstudianteByPersona(persona.getIdPersona());
                        Map<String, String> student = (Map<String, String>) Utileria.estudianteToMap(estudiante);
                        req.getSession().setAttribute("student", student);
                        res.sendRedirect("Estudiante/perfil");

                        break;
                    default:
                        break;
                }
            } else {
                req.getSession().setAttribute("msg", "Error en el usuario o contraseña!");
                res.sendRedirect("index");
            }

        } catch (Exception e) {
            req.getSession().setAttribute("msg", "Error, intentar de nuevo!");
            res.sendRedirect("index");
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
