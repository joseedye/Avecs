
package Controlador;

import DAO.Conexion;
import DAO.DocumentoJpaController;
import DTO.Documento;
import java.io.IOException;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateActivDoc extends HttpServlet {

    
    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        
        try {
            int idDocumento = Integer.valueOf(req.getParameterValues("idDocumento")[0]);
            boolean isActivo = Boolean.valueOf(req.getParameterValues("publico")[0]);

            EntityManagerFactory emf = Conexion.getConexion().getBd();
            DocumentoJpaController documentoDao = new DocumentoJpaController(emf);
            
            Documento documento = documentoDao.findDocumento(idDocumento);
            documento.setIsPublic(!isActivo);
            documentoDao.edit(documento);
            
            String msgNoPublic = "El Documento ya no es público! No será visible para los Docentes.";
            String msgPublic = "El Documento ahora es público! Será visible para los Docentes.";
            
            if(documento.getIsPublic())
                req.getSession().setAttribute("msg", msgPublic);
            else
                req.getSession().setAttribute("msg", msgNoPublic);
                
            res.sendRedirect("QueryDocuments.do");            
            
        } catch (Exception e) {
            req.getSession().setAttribute("msg", "Error al actualizar el documento.");
            res.sendRedirect("QueryDocuments.do");
            
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
