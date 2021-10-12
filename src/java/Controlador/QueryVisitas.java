/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DAO.Conexion;
import DAO.DocenteJpaController;
import DAO.EmpresaJpaController;
import DAO.EstudianteJpaController;
import DAO.SolicitudJpaController;
import DAO.UsuarioJpaController;
import DTO.Docente;
import DTO.Empresa;
import DTO.Estudiante;
import DTO.Persona;
import DTO.Postulante;
import DTO.Solicitud;
import DTO.Usuario;
import Util.Utileria;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
 * @author rozo-
 */
public class QueryVisitas extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *  
     * @param req servlet request
     * @param res servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        Map<String, String> user = (Map<String, String>) req.getSession().getAttribute("user");

        try {
            EntityManagerFactory emf = Conexion.getConexion().getBd();
            SolicitudJpaController solicitudDao = new SolicitudJpaController(emf);
            EmpresaJpaController empresaDao = new EmpresaJpaController(emf);
            List<Solicitud> listSolicitud = new ArrayList<>();
            Map<String, Object> mapSolicitudes = new HashMap<>();

            String tipoUser = user.get("TipoUsuario");
            String fecha1 = req.getParameter("anio");
            String empresa = req.getParameter("empresa");

            //Lista de empresas
            List<Empresa> listEmpresa = empresaDao.findEmpresaEntities();
            Map<String, Object> mapEmpresa = new HashMap<>();

            for (int i = 0; i < listEmpresa.size(); i++) {
                mapEmpresa.put(i + "", Utileria.empresaToMap(listEmpresa.get(i)));
            }
            req.getSession().setAttribute("empresas", mapEmpresa);

            switch (tipoUser) {
                case "Administrador":
                    listSolicitud = solicitudDao.findSolicitudEntities();
                    
                    //Filtro por Año y por empresa
                    if (empresa != null && fecha1 != null) {
                        int idempresa = Integer.valueOf(empresa);
                        Empresa empresabuscar = empresaDao.findEmpresa(idempresa);

                        Date d = new Date();
                        for (int i = 0; i < listSolicitud.size(); i++) {

                            d = listSolicitud.get(i).getFecInicio();
                            String anioSolicitud = (d.getYear() + 1900) + "";

                            if (listSolicitud.get(i).getEstatus().getDescripcion().equals("Aprobada") && listSolicitud.get(i).getIdEmpresa().equals(empresabuscar) && anioSolicitud.equals(fecha1)) {
                                mapSolicitudes.put(i + "", Utileria.solicitudToMap(listSolicitud.get(i)));
                            }
                        }
                    //Filtro por Año en todas las empresas
                    } else if (empresa == null && fecha1 != null) {
                        Date d = new Date();
                        for (int i = 0; i < listSolicitud.size(); i++) {

                            d = listSolicitud.get(i).getFecInicio();
                            String anioSolicitud = (d.getYear() + 1900) + "";

                            if (listSolicitud.get(i).getEstatus().getDescripcion().equals("Aprobada") && anioSolicitud.equals(fecha1)) {
                                mapSolicitudes.put(i + "", Utileria.solicitudToMap(listSolicitud.get(i)));
                            }
                        }
                    //Filtro por Empresa todos los años
                    } else if (empresa != null && fecha1 == null) {
                        int idempresa = Integer.valueOf(empresa);
                        Empresa empresabuscar = empresaDao.findEmpresa(idempresa);

                        for (int i = 0; i < listSolicitud.size(); i++) {

                            if (listSolicitud.get(i).getEstatus().getDescripcion().equals("Aprobada") && listSolicitud.get(i).getIdEmpresa().equals(empresabuscar)) {
                                mapSolicitudes.put(i + "", Utileria.solicitudToMap(listSolicitud.get(i)));
                            }
                        }

                    }
                    //Todos los años y todas las empresas
                    else {
                        for (int i = 0; i < listSolicitud.size(); i++) {
                            if (listSolicitud.get(i).getEstatus().getDescripcion().equals("Aprobada")) {
                                mapSolicitudes.put(i + "", Utileria.solicitudToMap(listSolicitud.get(i)));
                            }
                        }
                    }                    
                    break;

                case "Docente":
                    DocenteJpaController docenteDao = new DocenteJpaController(emf);
                    UsuarioJpaController usuariojpa = new UsuarioJpaController(emf);
                    int iduser = Integer.valueOf(user.get("idUsuario"));
                    Usuario usuario = usuariojpa.findUsuario(iduser);
                    Docente docente = docenteDao.findDocenteByPersona(usuario.getIdPersona().getIdPersona());
                    listSolicitud = docente.getSolicitudList();

                    //Filtro por Año y por empresa
                    if (empresa != null && fecha1 != null) {
                        int idempresa = Integer.valueOf(empresa);
                        Empresa empresabuscar = empresaDao.findEmpresa(idempresa);

                        Date d = new Date();
                        for (int i = 0; i < listSolicitud.size(); i++) {

                            d = listSolicitud.get(i).getFecInicio();
                            String anioSolicitud = (d.getYear() + 1900) + "";

                            if (listSolicitud.get(i).getEstatus().getDescripcion().equals("Aprobada") && listSolicitud.get(i).getIdEmpresa().equals(empresabuscar) && anioSolicitud.equals(fecha1)) {
                                mapSolicitudes.put(i + "", Utileria.solicitudToMap(listSolicitud.get(i)));
                            }
                        }
                    //Filtro por Año en todas las empresas
                    } else if (empresa == null && fecha1 != null) {
                        Date d = new Date();
                        for (int i = 0; i < listSolicitud.size(); i++) {

                            d = listSolicitud.get(i).getFecInicio();
                            String anioSolicitud = (d.getYear() + 1900) + "";

                            if (listSolicitud.get(i).getEstatus().getDescripcion().equals("Aprobada") && anioSolicitud.equals(fecha1)) {
                                mapSolicitudes.put(i + "", Utileria.solicitudToMap(listSolicitud.get(i)));
                            }
                        }
                    //Filtro por Empresa todos los años
                    } else if (empresa != null && fecha1 == null) {
                        int idempresa = Integer.valueOf(empresa);
                        Empresa empresabuscar = empresaDao.findEmpresa(idempresa);

                        for (int i = 0; i < listSolicitud.size(); i++) {

                            if (listSolicitud.get(i).getEstatus().getDescripcion().equals("Aprobada") && listSolicitud.get(i).getIdEmpresa().equals(empresabuscar)) {
                                mapSolicitudes.put(i + "", Utileria.solicitudToMap(listSolicitud.get(i)));
                            }
                        }

                    }
                    //Todos los años y todas las empresas
                    else {
                        for (int i = 0; i < listSolicitud.size(); i++) {
                            if (listSolicitud.get(i).getEstatus().getDescripcion().equals("Aprobada")) {
                                mapSolicitudes.put(i + "", Utileria.solicitudToMap(listSolicitud.get(i)));
                            }
                        }
                    }
                    break;

                case "Estudiante":
                    EstudianteJpaController estudianteDao = new EstudianteJpaController(emf);
                    UsuarioJpaController usuarioDao = new UsuarioJpaController(emf);

                    int iduser1 = Integer.valueOf(user.get("idUsuario"));
                    Usuario usuario1 = usuarioDao.findUsuario(iduser1);
                    Persona persona = usuario1.getIdPersona();
                    Estudiante estudiante = estudianteDao.findEstudianteByPersona(persona.getIdPersona());

                    List<Postulante> listPostulante = estudiante.getPostulanteList();

                    for (int i = 0; i < listPostulante.size(); i++) {
                        if (listPostulante.get(i).getEstatus().getDescripcion().equals("Aprobado")) {
                            mapSolicitudes.put(i + "", Utileria.solicitudToMap(listPostulante.get(i).getIdSolicitud()));
                        }
                    }
                    break;
            }

            req.getSession().setAttribute("solicitudes", mapSolicitudes);
            res.sendRedirect(tipoUser + "/visitas");

        } catch (Exception e) {
            req.getSession().setAttribute("msg", "Error en la consulta de visitas empresariales.");
            res.sendRedirect(user.get("TipoUsuario") + "/perfil");

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
