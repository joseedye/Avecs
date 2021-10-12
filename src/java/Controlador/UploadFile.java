package Controlador;

import DAO.Conexion;
import DAO.DocumentoJpaController;
import DAO.TipoDocumentoJpaController;
import DAO.UsuarioJpaController;
import DTO.Documento;
import DTO.TipoDocumento;
import DTO.Usuario;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@MultipartConfig
public class UploadFile extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
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
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String msgFail = "No se subieron estos archivos: ";
        boolean fail = false;
        EntityManagerFactory emf = Conexion.getConexion().getBd();
        Map<String, String> user = (Map<String, String>) req.getSession().getAttribute("user");
        int idUser = Integer.valueOf(user.get("idUsuario"));

        String ruta = getServletContext().getRealPath("/Files"); //Ruta donde se guardará el archivo.

        for (int i = 1; i < 4; i++) {
            Part filePart = req.getPart("file" + i); // Archivo input
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // Nombre Archivo con extension.

            if (!fileName.equals("")) {
                String idTipo = req.getParameter("tipo" + i); //input tipo file
                String publico = req.getParameter("publico" + i); //public file
                String desc = req.getParameter("desc" + i); //Description file

                File uploads = new File(ruta); //Objeto File con la ruta
                File file = new File(uploads, fileName); //Objeto File con la ruta y el nombre del archivo.

                try {
                    InputStream fileContent = filePart.getInputStream(); //Archivo ahora es InputStream
                    Files.copy(fileContent, file.toPath()); //Copia el archivo a la ruta

                    //Guardar en base de datos
                    String rutaDoc = "Files/" + fileName;
                    DocumentoJpaController documentoDao = new DocumentoJpaController(emf);
                    UsuarioJpaController usuarioDao = new UsuarioJpaController(emf);
                    Usuario usuario = usuarioDao.findUsuario(idUser);
                    TipoDocumentoJpaController tipoDao = new TipoDocumentoJpaController(emf);
                    TipoDocumento tipoDoc = tipoDao.findTipoDocumento(Integer.valueOf(idTipo));

                    Documento d = new Documento();
                    d.setDescripcionDoc(desc);
                    d.setIdTipoDoc(tipoDoc);
                    d.setRutaDoc(rutaDoc);
                    d.setFechaDoc(new Date());
                    d.setFechaSubidaDoc(new Date());
                    d.setIdUser(usuario);
                    d.setIsPublic(publico != null);
                    documentoDao.create(d);

                } catch (Exception ex) {
                    msgFail += fileName + " ";
                    fail = true;
                }
            }
        }

        if (fail) {
            req.getSession().setAttribute("msg", msgFail + " .Es posible que ya existan.");
            res.sendRedirect("/Error/errorRedir");
        } else {
            req.getSession().setAttribute("msg", "Archivos Subidos con Exito!");
            res.sendRedirect("QueryTypeDocuments.do");
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
