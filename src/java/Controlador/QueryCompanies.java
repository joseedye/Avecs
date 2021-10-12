
package Controlador;

import DAO.Conexion;
import DAO.EmpresaJpaController;
import DTO.Empresa;
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

public class QueryCompanies extends HttpServlet {

   
    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
            Map<String, String> user = (Map<String, String>) req.getSession().getAttribute("user");
            try {
                EntityManagerFactory emf = Conexion.getConexion().getBd();
                EmpresaJpaController empresaDao = new EmpresaJpaController(emf);
                
                List<Empresa> listEmpresas = empresaDao.findEmpresaEntities();
                Map<String, Object> mapEmpresas = new HashMap<>();
                
                for (int i = 0; i < listEmpresas.size(); i++) {                    
                    mapEmpresas.put(i+"", Utileria.empresaToMap(listEmpresas.get(i)));                    
                }
                
                req.getSession().setAttribute("empresas", mapEmpresas);
                if(mapEmpresas.isEmpty())
                    req.getSession().setAttribute("msg", "No hay empresas registradas.");
                
                res.sendRedirect(user.get("TipoUsuario")+"/empresa_consulta");                 
                
        } catch (Exception e) {
            req.getSession().setAttribute("msg", "Error, al consultar empresas");
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
