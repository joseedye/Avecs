package Controlador;

import DAO.Conexion;
import DAO.DocumentoJpaController;
import DAO.TipoDocumentoJpaController;
import DTO.TipoDocumento;
import Util.Utileria;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QueryTypeDocuments extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {
            EntityManagerFactory emf = Conexion.getConexion().getBd();
            TipoDocumentoJpaController tipoDao = new TipoDocumentoJpaController(emf);

            //Obtener map de tipos de documentos
            List<TipoDocumento> listTipo = tipoDao.findTipoDocumentoEntities();
            Map<String, Object> mapTipos = new HashMap<>();

            for (int i = 0; i < listTipo.size(); i++) {
                mapTipos.put(i + "", Utileria.tipoDocumentoToMap(listTipo.get(i)));
            }
            
            req.getSession().setAttribute("tipoDocumentos", mapTipos);
            res.sendRedirect("Administrador/documento_nuevo");

        } catch (Exception e) {
            res.sendRedirect("Administrador/QueryDocuments.do");
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
