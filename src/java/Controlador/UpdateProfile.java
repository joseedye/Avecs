
package Controlador;

import DAO.Conexion;
import DAO.EstudianteJpaController;
import DAO.PersonaJpaController;
import DAO.TipoUsuarioJpaController;
import DAO.UsuarioJpaController;
import DTO.Estudiante;
import DTO.Persona;
import DTO.TipoUsuario;
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

public class UpdateProfile extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        
        Map<String, String> user = (Map<String, String>) req.getSession().getAttribute("user");
        try {
            EntityManagerFactory emf = Conexion.getConexion().getBd();
            UsuarioJpaController usuarioDao = new UsuarioJpaController(emf);
            PersonaJpaController personaDao = new PersonaJpaController(emf);
            TipoUsuarioJpaController tipoUsuarioDao = new TipoUsuarioJpaController(emf);
            EstudianteJpaController estudianteDao = new EstudianteJpaController(emf);
            
            String nombres = req.getParameter("nombres");
            String ap1 = req.getParameter("ap1");
            String ap2 = req.getParameter("ap2");
            String tl1 = req.getParameter("tl1");
            String tl2 = req.getParameter("tl2");
            String fecha = req.getParameter("FechaNac");
            String genero = req.getParameter("genero");
            String direccion = req.getParameter("direccion");
            int numDocumento = Integer.valueOf(req.getParameter("numDocumento"));
                      
            int idUsuario = Integer.valueOf(user.get("idUsuario"));
            
            Usuario usuario = usuarioDao.findUsuario(idUsuario);
            Persona persona = personaDao.findPersona(usuario.getIdPersona().getIdPersona());
            persona.setNombres(nombres);
            persona.setApellido1(ap1);
            persona.setApellido2(ap2);
            persona.setTelefono1(tl1);
            persona.setTelefono2(tl2);
            persona.setGenero(genero);
            persona.setDireccion(direccion);
            persona.setNumDocumento(numDocumento);
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date fecNacimiento = formato.parse(fecha);
            persona.setFecNacimiento(fecNacimiento);
            
            TipoUsuario studentUser = tipoUsuarioDao.findTipoUsuario(3);
            if(usuario.getIdTipoUsuario().equals(studentUser)){
                String representante = req.getParameter("representante");
                String reptelefono = req.getParameter("reptelefono");
                String arl = req.getParameter("arl");
                
                Estudiante estudiante = estudianteDao.findEstudianteByPersona(persona.getIdPersona());
                estudiante.setNombreContacto(representante);
                estudiante.setNumeroContacto(reptelefono);
                estudiante.setArl(arl);
                estudianteDao.edit(estudiante);       
                
                Map<String, String> student = (Map<String, String>) Utileria.estudianteToMap(estudiante);
                req.getSession().setAttribute("student", student);
            }
            
            personaDao.edit(persona); 
            usuario.setIdPersona(persona);
            user = Utileria.usuarioToMap(usuario); 
            req.getSession().setAttribute("user", user);
            req.getSession().setAttribute("msg", "Perfil actualizado exitosamente!");
            res.sendRedirect(user.get("TipoUsuario") + "/perfil");
            
        } catch (Exception e) {
            req.getSession().setAttribute("msg", "Error, al actualizar usuario!");
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
