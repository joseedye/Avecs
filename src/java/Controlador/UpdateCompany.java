package Controlador;

import DAO.Conexion;
import DAO.EmpresaJpaController;
import DTO.Empresa;
import java.io.IOException;
import Util.Utileria;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateCompany extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        Map<String, String> user = (Map<String, String>) req.getSession().getAttribute("user");
        String idEmpresaQuery = req.getParameter("idEmpresaQuery");
        try {
            EntityManagerFactory emf = Conexion.getConexion().getBd();
            EmpresaJpaController empresaDao = new EmpresaJpaController(emf);
            
            //Dirige a la vista modificar 
            if (idEmpresaQuery != null) {
                Empresa empresa = empresaDao.findEmpresa(Integer.valueOf(idEmpresaQuery));
                Map<String, String> mapEmpresa = Utileria.empresaToMap(empresa);                
                req.getSession().setAttribute("empresa", mapEmpresa);
                res.sendRedirect(user.get("TipoUsuario")+"/empresa_modificar?idEmpresa=" + idEmpresaQuery);
            } else {
                //Desde la vista modificar y hace edit de la empresa
                int idEmpresa = Integer.valueOf(req.getParameter("idEmpresa"));
                String razon = req.getParameter("razon");
                String nit = req.getParameter("nit");
                String departamento = req.getParameter("departamento");
                String ciudad = req.getParameter("ciudad");
                String email = req.getParameter("email");
                String telefono1 = req.getParameter("tl1");
                String telefono2 = req.getParameter("tl2");
                String direccion = req.getParameter("direccion");
                String persona = req.getParameter("persona");
                String telefono = req.getParameter("telefono");
                String email2 = req.getParameter("email2");

                Empresa empresa = empresaDao.findEmpresa(idEmpresa);
                empresa.setNombreEmpresa(razon);
                empresa.setNitEmpresa(nit);
                empresa.setDptoEmpresa(persona);
                empresa.setCiudadEmpresa(ciudad);
                empresa.setEmailEmpresa(email);
                empresa.setTelefono1Empresa(telefono1);
                empresa.setTelefono2Empresa(telefono2);
                empresa.setDireccionEmpresa(direccion);
                empresa.setPersonaContacto(persona);
                empresa.setTelPersonaContacto(telefono);
                empresa.setEmailPersonaContacto(email2);
                empresa.setDptoEmpresa(departamento);

                empresaDao.edit(empresa);
                
                req.getSession().setAttribute("msg", "Empresa actualizada exitosamente!");
                res.sendRedirect("UpdateCompany.do?idEmpresaQuery="+idEmpresa);
            }

        } catch (Exception e) {
            req.getSession().setAttribute("msg", "Error actualizando los datos de la empresa!");
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
