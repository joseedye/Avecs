package Controlador;

import DAO.Conexion;
import DAO.PersonaJpaController;
import DAO.UsuarioJpaController;
import DTO.Persona;
import DTO.Usuario;
import Util.Utileria;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateUser extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        
        String idUserQuery = req.getParameter("idUserQuery");
        try {
            EntityManagerFactory emf = Conexion.getConexion().getBd();
            UsuarioJpaController usuarioDao = new UsuarioJpaController(emf);
            
            if (idUserQuery != null) {
                Usuario usuario = usuarioDao.findUsuario(Integer.valueOf(idUserQuery));
                Map<String, String> mapUsuario = Utileria.usuarioToMap(usuario);
                req.getSession().setAttribute("usuario", mapUsuario);                
                res.sendRedirect("Administrador/usuario_modificar?idUsuario=" + idUserQuery);
            }
            else
            {
                int idUsuario = Integer.valueOf(req.getParameter("idUsuario"));
                PersonaJpaController personaDao = new PersonaJpaController(emf);

                String nombres = req.getParameter("nombres");
                String ap1 = req.getParameter("ap1");
                String ap2 = req.getParameter("ap2");
                String tl1 = req.getParameter("tl1");
                String tl2 = req.getParameter("tl2");
                String fecha = req.getParameter("fecNacimiento");
                String genero = req.getParameter("genero");
                String email = req.getParameter("email");
                String direccion = req.getParameter("direccion");
                int numDocumento = Integer.valueOf(req.getParameter("numDocumento"));

                Usuario usuario = usuarioDao.findUsuario(idUsuario);
                Persona persona = personaDao.findPersona(usuario.getIdPersona().getIdPersona());
                persona.setNombres(nombres);
                persona.setApellido1(ap1);
                persona.setApellido2(ap2);
                persona.setTelefono1(tl1);
                persona.setTelefono2(tl2);
                persona.setGenero(genero);
                persona.setEmail(email);
                persona.setDireccion(direccion);
                persona.setNumDocumento(numDocumento);
                SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
                Date fecNacimiento = formato.parse(fecha);
                persona.setFecNacimiento(fecNacimiento);
                personaDao.edit(persona);
                
                req.getSession().setAttribute("msg", "Usuario actualizado exitosamente!");
                res.sendRedirect("UpdateUser.do?idUserQuery=" + usuario.getIdUsuario());
            }
        } catch (Exception e) {
            req.getSession().setAttribute("msg", "Error actualizando los datos del usuario!");
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
