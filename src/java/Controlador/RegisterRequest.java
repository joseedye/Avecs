package Controlador;

import DAO.AsignaturaJpaController;
import DAO.Conexion;
import DAO.CronogramaJpaController;
import DAO.DocenteJpaController;
import DAO.EmpresaJpaController;
import DAO.EstatusSolicitudJpaController;
import DAO.SolicitudJpaController;
import DAO.UsuarioJpaController;
import DTO.Asignatura;
import DTO.Cronograma;
import DTO.Docente;
import DTO.Empresa;
import DTO.EstatusSolicitud;
import DTO.Solicitud;
import DTO.Usuario;
import Util.Utileria;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegisterRequest extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            EntityManagerFactory emf = Conexion.getConexion().getBd();
            Map<String, String> user = (Map<String, String>) request.getSession().getAttribute("user");

            int idsolicitud = Integer.valueOf(request.getParameter("idsolicitud"));
            int NumMax = Integer.valueOf(request.getParameter("NumMax"));
            int idUsuario = Integer.valueOf(user.get("idUsuario"));
            int asignatura = Integer.valueOf(request.getParameter("Asignatura"));
            int empresa = Integer.valueOf(request.getParameter("empresa"));
            int estatus = Integer.valueOf(request.getParameter("estatus"));
            String Tematica = request.getParameter("Tematica");
            String observacion = request.getParameter("observacion");
            String Fecha = request.getParameter("Fecha");
            String FechaF = request.getParameter("FechaF");

            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date fecInicio = formato.parse(Fecha);
            Date fecFin = formato.parse(FechaF);
            Date fecCreacion = new Date();
            String periodo = Utileria.getPeriodoYearCurrent(fecCreacion);

            UsuarioJpaController usuariojpa = new UsuarioJpaController(emf);
            Usuario usuario = usuariojpa.findUsuario(idUsuario);

            DocenteJpaController docentejpa = new DocenteJpaController(emf);
            Docente docenteDTO = docentejpa.findDocenteByPersona(usuario.getIdPersona().getIdPersona());

            AsignaturaJpaController asignaturajpa = new AsignaturaJpaController(emf);
            Asignatura asignaturaDTO = asignaturajpa.findAsignatura(asignatura);

            EmpresaJpaController empresajpa = new EmpresaJpaController(emf);
            Empresa empresaDTO = empresajpa.findEmpresa(empresa);

            EstatusSolicitudJpaController estatusjpa = new EstatusSolicitudJpaController(emf);
            EstatusSolicitud estatusDTO = estatusjpa.findEstatusSolicitud(estatus);

            SolicitudJpaController solicitudjpa = new SolicitudJpaController(emf);
            Solicitud solicitudDTO = solicitudjpa.findSolicitud(idsolicitud);
            
            //Obtiene el ultimo registro de la tabla solicitudes
            int auto_increment = 1;
            try {
                auto_increment = solicitudjpa.getSolicitudLast().getIdSolicitud() + 1;
            } catch (Exception e) { }        
            
            boolean isNewSolicitud = solicitudDTO == null;

            //crea una nueva
            if (isNewSolicitud) {
                solicitudDTO = new Solicitud();
                solicitudDTO.setIdSolicitud(auto_increment);
                solicitudDTO.setFecCreacion(fecCreacion);
                solicitudDTO.setFecSolicitudAprob(fecCreacion);
                solicitudDTO.setFecAprobacion(fecCreacion);
                solicitudDTO.setPeriodo(periodo);
                solicitudDTO.setIdDocente(docenteDTO);
                solicitudDTO.setIdAprobador(usuario);

            }
            //edita una existente
            solicitudDTO.setCantidadParticipantes(NumMax);
            solicitudDTO.setCuposDisponibles(NumMax);
            solicitudDTO.setTematica(Tematica);
            solicitudDTO.setFecInicio(fecInicio);
            solicitudDTO.setFecFin(fecFin);
            solicitudDTO.setObservacion(observacion);
            solicitudDTO.setIdAsignatura(asignaturaDTO);
            solicitudDTO.setIdEmpresa(empresaDTO);
            solicitudDTO.setEstatus(estatusDTO);

            if (isNewSolicitud) {
                solicitudjpa.create(solicitudDTO);
            } else {
                solicitudjpa.edit(solicitudDTO);
            }

            switch (estatus) {
                case 1:
                    request.getSession().setAttribute("msg", "Solicitud guardada Temporalmente!");
                    break;
                case 2:
                    request.getSession().setAttribute("msg", "Solicitud enviada a Dirección exitosamente!");
                    break;
            }

            //Cronograma
            CronogramaJpaController cronogramaDao = new CronogramaJpaController(emf);
            if (solicitudDTO.getCronogramaList() == null) {//borrar siempre viene size 0
                solicitudDTO.setCronogramaList(new ArrayList<>());
            }

            List<Cronograma> listCronograma = solicitudDTO.getCronogramaList();
            for (Cronograma c : listCronograma) {
                cronogramaDao.destroy(c.getId());
            }
            solicitudDTO.setCronogramaList(new ArrayList<>());

            int lastRow = Integer.valueOf(request.getParameter("lastRow"));

            for (int i = 1; i <= lastRow; i++) {
                if (request.getParameter("actividad" + i) != null) {
                    String actividad = request.getParameter("actividad" + i);
                    String descripcion = request.getParameter("descripcion" + i);
                    String fecI = request.getParameter("FechaInicio" + i);
                    String fecF = request.getParameter("FechaFin" + i);

                    Date fecInici = formato.parse(fecI);
                    Date fecFi = formato.parse(fecF);

                    Cronograma c = new Cronograma(actividad, descripcion, "", fecInici, fecFi,false);
                    c.setIdSolicitud(solicitudDTO);
                    cronogramaDao.create(c);
                }
            }
            //Cronograma

            response.sendRedirect("QuerySolicitudes.do");
        } catch (Exception e) {
            request.getSession().setAttribute("msg", "No se ha procesado la Solicitud!");
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
