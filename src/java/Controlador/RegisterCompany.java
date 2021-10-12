
package Controlador;

import DAO.Conexion;
import DAO.EmpresaJpaController;
import DTO.Empresa;
import Util.Utileria;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterCompany extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Map<String, String> user = (Map<String, String>) request.getSession().getAttribute("user");
        try {
            String razon = request.getParameter("razon");
            String nit = request.getParameter("Nit");
            String departamento = request.getParameter("Departamento");//mirar
            String ciudad = request.getParameter("Ciudad");
            String email = request.getParameter("Email");
            String telefono1 = request.getParameter("Tel1");
            String telefono2 = request.getParameter("Tel2");
            String direccion = request.getParameter("Direccion");
            String persona = request.getParameter("PerCont");
            String telefono = request.getParameter("TelCont");
            String email2 = request.getParameter("EmailCont");
            
            Date fecCreacion = new Date();

            EntityManagerFactory emf = Conexion.getConexion().getBd();
            EmpresaJpaController empresajpa = new EmpresaJpaController(emf);
            
            Empresa empresaDTO = new Empresa(nit, razon, departamento, ciudad, direccion, telefono1, telefono2, email, persona, telefono, email2, fecCreacion);
            empresajpa.create(empresaDTO);
            request.getSession().setAttribute("msg", "Empresa registrada exitosamente!");
            response.sendRedirect(user.get("TipoUsuario")+"/empresa_registrar");
            
        } catch (Exception e) {
            String cause = e.getCause().getCause().getMessage();            
            request.getSession().setAttribute("msg", "Error, el dato: "+Utileria.msgExPersistence(cause)+" ya existe!");
            response.sendRedirect("/Error/errorRedir");
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
