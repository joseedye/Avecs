
package Controlador;

import DAO.Conexion;
import DAO.DocenteJpaController;
import DAO.PersonaJpaController;
import DAO.UsuarioJpaController;
import java.io.IOException;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUser extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       try {
            EntityManagerFactory emf = Conexion.getConexion().getBd();
            UsuarioJpaController usuarioDao = new UsuarioJpaController(emf);
            PersonaJpaController personaDao = new PersonaJpaController(emf);    
            DocenteJpaController docenteDao = new DocenteJpaController(emf);
                      
            int idUsuario = Integer.valueOf(request.getParameter("idUsuario")); 
            int idPersona = usuarioDao.findUsuario(idUsuario).getIdPersona().getIdPersona();            
            int tipoUser = usuarioDao.findUsuario(idUsuario).getIdTipoUsuario().getIdTipoUsuario();
            usuarioDao.destroy(idUsuario);
            if(tipoUser == 2){
                int idDocente = docenteDao.findDocenteByPersona(idPersona).getIdDocente();
                docenteDao.destroy(idDocente);
            }            
            
            personaDao.destroy(idPersona);
            response.sendRedirect("QueryUsers.do");
            
        } catch (Exception e) {         
            request.getSession().setAttribute("msg", "Error al eliminar Usuario!");
            response.sendRedirect("QueryUsers.do");
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
