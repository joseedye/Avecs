package Controlador;

import DAO.AsignaturaJpaController;
import DAO.Conexion;
import DAO.EmpresaJpaController;
import DAO.SolicitudJpaController;
import DTO.Asignatura;
import DTO.Cronograma;
import DTO.Empresa;
import DTO.Postulante;
import DTO.Solicitud;
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

/**
 *
 * @author Usuario
 */
public class QueryRequest extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        Map<String, String> user = (Map<String, String>) req.getSession().getAttribute("user");

        try {
            EntityManagerFactory emf = Conexion.getConexion().getBd();
            AsignaturaJpaController asignaturaDao = new AsignaturaJpaController(emf);
            EmpresaJpaController empresaDao = new EmpresaJpaController(emf);

            List<Asignatura> listAsignatura = asignaturaDao.findAsignaturaEntities();
            Map<String, Object> mapAsignatura = new HashMap<>();

            for (int i = 0; i < listAsignatura.size(); i++) {
                mapAsignatura.put(i + "", Utileria.asignaturaToMap(listAsignatura.get(i)));
            }

            List<Empresa> listEmpresa = empresaDao.findEmpresaEntities();
            Map<String, Object> mapEmpresa = new HashMap<>();

            for (int i = 0; i < listEmpresa.size(); i++) {
                mapEmpresa.put(i + "", Utileria.empresaToMap(listEmpresa.get(i)));
            }

            String idSolicitud = req.getParameter("idSolicitud");
            SolicitudJpaController solicitudDao = new SolicitudJpaController(emf);
            Map<String, String> mapSolicitud = null;
            Map<String, Object> mapCronograma = new HashMap<>();
            Map<String, Object> mapPostulante = new HashMap<>();

            //solicitud creada
            if (idSolicitud != null) {
                Solicitud solicitud = solicitudDao.findSolicitud(Integer.valueOf(idSolicitud));
                mapSolicitud = Utileria.solicitudToMap(solicitud);

                //busca los postulantes
                List<Postulante> listPostulante = solicitud.getPostulanteList();
                for (int i = 0; i < listPostulante.size(); i++) {
                    mapPostulante.put(i + "", Utileria.postulanteToMap(listPostulante.get(i)));
                }
                req.getSession().setAttribute("postulantes", mapPostulante);

                //busca cronograma
                List<Cronograma> listCronograma = solicitud.getCronogramaList();
                for (int i = 0; i < listCronograma.size(); i++) {
                    mapCronograma.put(i + "", Utileria.cronogramaToMap(listCronograma.get(i)));
                }
                req.getSession().setAttribute("cronograma", mapCronograma);
            }
            String ventana = req.getParameter("idventana");
            req.getSession().setAttribute("solicitud", mapSolicitud);
            req.getSession().setAttribute("asignaturas", mapAsignatura);
            req.getSession().setAttribute("empresas", mapEmpresa);
            req.getSession().setAttribute("cronograma", mapCronograma);
            req.getSession().setAttribute("idventana", ventana);

            String isVisita = req.getParameter("isVisita");
            if (isVisita != null) {
                res.sendRedirect("Docente/visita_ver");
            } else {
                res.sendRedirect(user.get("TipoUsuario") + "/solicitud_ver");
            }

        } catch (Exception e) {
            req.getSession().setAttribute("msg", "Error, al cargar datos de empresa o asignatura");
            res.sendRedirect("QuerySolicitudes.do");
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
