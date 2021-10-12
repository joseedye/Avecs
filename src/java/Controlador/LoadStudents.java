package Controlador;

import DAO.*;
import DTO.*;
import Util.Utileria;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import org.apache.commons.codec.digest.DigestUtils;

@MultipartConfig
public class LoadStudents extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

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
     * @param req servlet request
     * @param res servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        try {

            //Subida del archivo excel al servidor.
            String ruta = getServletContext().getRealPath("/Files");
            Part filePart = req.getPart("file");
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            File uploads = new File(ruta);
            File file = new File(uploads, fileName);
            InputStream fileContent = filePart.getInputStream();
            Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);

            EntityManagerFactory emf = Conexion.getConexion().getBd();
            PersonaJpaController personaDao = new PersonaJpaController(emf);
            EstudianteJpaController estudianteDao = new EstudianteJpaController(emf);
            UsuarioJpaController usuarioDao = new UsuarioJpaController(emf);
            TipoUsuarioJpaController tipoUserDao = new TipoUsuarioJpaController(emf);
            GrupoJpaController grupoDao = new GrupoJpaController(emf);
            AsignaturaJpaController asigDao = new AsignaturaJpaController(emf);
            DocenteJpaController docenteDao = new DocenteJpaController(emf);

            String rutaDoc = ruta + "\\" + fileName;
            ArrayList<String[]> list = Utileria.getListEstudiantes(rutaDoc);

            Map<String, Object> listEstudiantes = new HashMap<>();

            //Limpia tabla grupo
            List<Grupo> listGrupo = grupoDao.findGrupoEntities();
            for (Grupo grupo : listGrupo) {
                grupoDao.destroy(grupo.getId());
            }

            for (int i = 0; i < list.size(); i++) {
                String[] dato = list.get(i);

                //Comprueba que exista el docente
                try {
                    docenteDao.findDocenteByCodigo(Integer.valueOf(dato[7]));
                } catch (Exception ex) {
                    req.getSession().setAttribute("msg", "Algun docente de la lista no existe en el sistema. Crear el Usuario docente e intentar de nuevo!");
                    res.sendRedirect("/Error/errorRedir");
                    return;
                }

                //Creacion de persona, si ya existe por email no la crea.
                Persona p = new Persona();
                try {
                    p = personaDao.findPersonaByEmail(dato[4]);
                } catch (Exception ex) {
                    int idLastPersona = personaDao.getPersonaLast().getIdPersona();
                    p.setIdPersona(idLastPersona + 1);
                    p.setNombres(dato[0]);
                    p.setApellido1(dato[1]);
                    p.setApellido2(dato[2]);
                    p.setEmail(dato[4]);
                    p.setDireccion("");
                    p.setGenero("Masculino");
                    p.setNumDocumento(Integer.valueOf(dato[3]));
                    p.setTelefono1("300");
                    p.setTipoDocumento("CC");
                    p.setFecNacimiento(new Date());
                    personaDao.create(p);
                }

                //Creacion de estudiante, si ya existe por persona no lo crea.
                Estudiante e = new Estudiante();
                try {
                    e = estudianteDao.findEstudianteByPersona(p.getIdPersona());
                } catch (Exception ex) {
                    e.setCodigoEstudiante(Integer.valueOf(dato[3]));
                    e.setIdPersona(p);
                    e.setArl("arl");
                    e.setNombreContacto("contacto");
                    e.setNumeroContacto("300");
                    estudianteDao.create(e);
                }

                //Creacion de Usuario, si ya existe no lo crea
                TipoUsuario tipoUser = tipoUserDao.findTipoUsuario(3);
                Usuario u = new Usuario();
                try {
                    u = usuarioDao.findUsuarioByEmail(dato[4]);
                } catch (Exception ex) {
                    u.setIdPersona(p);
                    u.setActivo(true);
                    u.setFecCreacion(new Date());
                    u.setIdTipoUsuario(tipoUser);
                    u.setUser(dato[4]);
                    String passEncrip = DigestUtils.sha1Hex(dato[3]);
                    u.setPassword(passEncrip);
                    usuarioDao.create(u);
                }

                //Creacion de Grupo
                Asignatura a = asigDao.findAsignaturaByCodigo(dato[5]);
                Grupo g = new Grupo();
                g.setLetra(dato[6]);
                g.setIdAsignatura(a);
                g.setIdEstudiante(e);
                Docente d = docenteDao.findDocenteByCodigo(Integer.valueOf(dato[7]));
                g.setIdDocente(d);
                String periodo = Utileria.getPeriodoYearCurrent(new Date());
                g.setPeriodo(periodo);
                grupoDao.create(g);

                listEstudiantes.put(i + "", Utileria.estudianteToMap(e));
            }

            req.getSession().setAttribute("listEstudiantes", listEstudiantes);
            req.getSession().setAttribute("msg", "Se han registrado exitosamente!");
            res.sendRedirect("/Administrador/estudiantes_registrar");

        } catch (Exception e) {
            req.getSession().setAttribute("msg", "Error al cargar lista de estudiantes. Intentar de nuevo!");
            res.sendRedirect("/Error/errorRedir");
        }

        processRequest(req, res);
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
