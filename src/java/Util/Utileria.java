package Util;

import DTO.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public final class Utileria {

    private static String dateToString(Date date) {
        if (date == null) {
            return null;
        }

        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        return formato.format(date);
    }

    public static Map<String, String> usuarioToMap(Usuario user) {
        Map<String, String> map = new HashMap<>();
        map.put("idUsuario", user.getIdUsuario().toString());
        map.put("user", user.getUser());
        map.put("fecCreacion", dateToString(user.getFecCreacion()));
        map.put("TipoUsuario", user.getIdTipoUsuario().getDesTipoUsuario());
        map.put("nombres", user.getIdPersona().getNombres());
        map.put("apellido1", user.getIdPersona().getApellido1());
        map.put("apellido2", user.getIdPersona().getApellido2());
        map.put("fecNacimiento", dateToString(user.getIdPersona().getFecNacimiento()));
        map.put("tipoDocumento", user.getIdPersona().getTipoDocumento());
        map.put("numDocumento", user.getIdPersona().getNumDocumento() + "");
        map.put("genero", user.getIdPersona().getGenero());
        map.put("direccion", user.getIdPersona().getDireccion());
        map.put("email", user.getIdPersona().getEmail());
        map.put("telefono1", user.getIdPersona().getTelefono1());
        map.put("telefono2", user.getIdPersona().getTelefono2());
        map.put("activo", user.getActivo() + "");
        map.put("contra", user.getPassword());

        return map;
    }

    public static Map<String, String> estudianteToMap(Estudiante estudiante) {
        Map<String, String> map = new HashMap<>();
        map.put("idEstudiante", estudiante.getIdEstudiante().toString());
        map.put("nombre", estudiante.getIdPersona().getNombres());
        map.put("email", estudiante.getIdPersona().getEmail());
        map.put("codigo", estudiante.getCodigoEstudiante() + "");
        map.put("representante", estudiante.getNombreContacto());
        map.put("reptelefono", estudiante.getNumeroContacto());
        map.put("arl", estudiante.getArl());

        return map;
    }

    public static Map<String, String> asignaturaToMap(Asignatura get) {
        Map<String, String> map = new HashMap<>();
        map.put("id_asignatura", get.getIdAsignatura().toString());
        map.put("descripcion_asignatura", get.getDescripcionAsignatura());
        map.put("codigo_asignatura", get.getCodigoAsignatura() + "");
        map.put("semestre", get.getSemestre() + "");
        return map;
    }

    public static Map<String, String> personaToMap(Persona persona) {
        Map<String, String> map = new HashMap<>();
        map.put("idPersona", persona.getIdPersona().toString());
        map.put("nombres", persona.getNombres());
        map.put("apellido1", persona.getApellido1());
        map.put("apellido2", persona.getApellido2());
        map.put("fecNacimiento", dateToString(persona.getFecNacimiento()));
        map.put("tipoDocumento", persona.getTipoDocumento());
        map.put("numDocumento", persona.getNumDocumento() + "");
        map.put("genero", persona.getGenero());
        map.put("direccion", persona.getDireccion());
        map.put("email", persona.getEmail());
        map.put("telefono1", persona.getTelefono1());
        map.put("telefono2", persona.getTelefono2());
        return map;
    }

    public static Map<String, String> solicitudToMap(Solicitud solicitud) {
        Map<String, String> map = new HashMap<>();
        map.put("idSolicitud", solicitud.getIdSolicitud().toString());
        map.put("cantidadParticipantes", solicitud.getCantidadParticipantes() + "");
        map.put("tematica", solicitud.getTematica());
        map.put("fecInicio", dateToString(solicitud.getFecInicio()));
        map.put("fecFin", dateToString(solicitud.getFecFin()));
        map.put("fecCreacion", dateToString(solicitud.getFecCreacion()));
        map.put("fecSolicitudAprob", dateToString(solicitud.getFecSolicitudAprob()));
        map.put("fecAprobacion", dateToString(solicitud.getFecAprobacion()));
        map.put("asignatura", solicitud.getIdAsignatura().getIdAsignatura().toString());
        map.put("nombreasignatura", solicitud.getIdAsignatura().getDescripcionAsignatura());
        map.put("docente", solicitud.getIdDocente().getIdPersona().getNombres());
        map.put("empresa", solicitud.getIdEmpresa().getNombreEmpresa());
        map.put("estatus", solicitud.getEstatus().getDescripcion());
        map.put("estatusDetalle", solicitud.getEstatus().getDesDetallada());
        map.put("observacion", solicitud.getObservacion());
        map.put("cupos", solicitud.getCuposDisponibles() + "");
        map.put("idempresa", solicitud.getIdEmpresa().getIdEmpresa() + "");

        return map;
    }

    public static Map<String, String> cronogramaToMap(Cronograma cronograma) {
        Map<String, String> map = new HashMap<>();
        map.put("idCronograma", cronograma.getId().toString());
        map.put("actividad", cronograma.getActividad());
        map.put("descripcion", cronograma.getDescripcion());
        map.put("observacion", cronograma.getObservacion());
        map.put("fecInicio", dateToString(cronograma.getFecInicio()));
        map.put("fecFin", dateToString(cronograma.getFecFin()));
        map.put("idSolicitud", cronograma.getIdSolicitud().getIdSolicitud() + "");
        map.put("estaHecha", cronograma.getIsDone() + "");
        return map;
    }

    public static Map<String, String> empresaToMap(Empresa empresa) {
        Map<String, String> map = new HashMap<>();
        map.put("idEmpresa", empresa.getIdEmpresa().toString());
        map.put("nitEmpresa", empresa.getNitEmpresa());
        map.put("nombreEmpresa", empresa.getNombreEmpresa());
        map.put("dptoEmpresa", empresa.getDptoEmpresa());
        map.put("ciudadEmpresa", empresa.getCiudadEmpresa());
        map.put("direccionEmpresa", empresa.getDireccionEmpresa());
        map.put("telefono1Empresa", empresa.getTelefono1Empresa());
        map.put("telefono2Empresa", empresa.getTelefono2Empresa());
        map.put("emailEmpresa", empresa.getEmailEmpresa());
        map.put("personaContacto", empresa.getPersonaContacto());
        map.put("telPersonaContacto", empresa.getTelPersonaContacto());
        map.put("emailPersonaContacto", empresa.getEmailPersonaContacto());
        return map;
    }

    public static Map<String, String> documentoToMap(Documento documento) {
        Map<String, String> map = new HashMap<>();
        map.put("idDoc", documento.getIdDoc().toString());
        map.put("descripcionDoc", documento.getDescripcionDoc());
        map.put("rutaDoc", documento.getRutaDoc());
        map.put("isPublic", documento.getIsPublic() + "");
        map.put("tipoDoc", documento.getIdTipoDoc().getDescripcionTipoDoc());
        map.put("idTipoDoc", documento.getIdTipoDoc().getIdTipoDoc() + "");

        return map;
    }

    public static Map<String, String> docenteToMap(Docente docente) {
        Map<String, String> map = new HashMap<>();
        map.put("idDocente", docente.getIdDocente().toString());
        map.put("codigoDocente", docente.getCodigoDocente() + "");
        map.put("idPersona", docente.getIdPersona().getIdPersona().toString());
        return map;
    }

    public static Map<String, String> postulanteToMap(Postulante postulante) {
        Map<String, String> map = new HashMap<>();
        map.put("idPostulante", postulante.getId().toString());
        map.put("estatusPostulante", postulante.getEstatus().getId() + "");
        map.put("estatusdescripcion", postulante.getEstatus().getDescripcion());
        map.put("idEstudiante", postulante.getIdEstudiante().getIdEstudiante() + "");
        map.put("estudianteNombre", postulante.getIdEstudiante().getIdPersona().getNombres());
        map.put("estudianteCodigo", postulante.getIdEstudiante().getCodigoEstudiante() + "");
        map.put("idSolicitud", postulante.getIdSolicitud() + "");
        map.put("empresaSolicitud", postulante.getIdSolicitud().getIdEmpresa().getNombreEmpresa());
        map.put("tematicaSolicitud", postulante.getIdSolicitud().getTematica());
        map.put("fechaPostulante", dateToString(postulante.getFecPostulacion()));
        map.put("observacion", postulante.getObservacion());
        map.put("correo", postulante.getIdEstudiante().getIdPersona().getEmail());
        map.put("telefono", postulante.getIdEstudiante().getIdPersona().getTelefono1());
        return map;
    }

    public static Map<String, String> tipoDocumentoToMap(TipoDocumento tipoDocumento) {
        Map<String, String> map = new HashMap<>();
        map.put("idTipoDoc", tipoDocumento.getIdTipoDoc() + "");
        map.put("descripcionTipoDoc", tipoDocumento.getDescripcionTipoDoc());

        return map;
    }

    public static String randomString() {
        String chars = "abcdefghijklmnopqrstuvwxyz";
        Random rand = new Random();
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            buf.append(chars.charAt(rand.nextInt(chars.length())));
        }
        return buf.toString();

    }

    public static boolean enviarCorreo(String destino, String titulo, String cuerpo) {
        try {
            String emailUsuarioEmisor = "avecsoporte@gmail.com";
            String clave = "bsoahrfjazycfark";
            ServiceEmail email = new ServiceEmail(emailUsuarioEmisor, clave);
            email.enviarEmail(destino, titulo, cuerpo);

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static int token() {
        int numero = (int) (Math.random() * 999999) + 100000;
        return numero;
    }

    public static String msgExPersistence(String cause) {
        int inicio = cause.indexOf("entry") + 5;
        int fin = cause.indexOf("for");
        return cause.substring(inicio, fin);
    }

    public static String getPeriodoYearCurrent(Date date) {
        String periodo = "";

        int year = date.getYear() + 1900;
        int month = date.getMonth();

        return (month <= 7) ? year + "-1" : year + "-2";
    }

    public static ArrayList<String[]> getListEstudiantes(String rutaFile) {
        ArrayList<String[]> listEstudiantes = new ArrayList<>();

        try {
            ExcelReader doc1 = new ExcelReader();

            ArrayList<String[]> arrayDatos = doc1.guardarexcelenarray(new File(rutaFile));

            int r = 0;
            for (String[] next : arrayDatos) {
                for (int c = 0; c < next.length; c++) {
                    if (c == 5 && (next[c].equals("1330605.0") || next[c].equals("1330703.0"))) {
                        next[3] = next[3].substring(0, 7);
                        next[7] = next[7].substring(0, 7);
                        next[8] = next[8].substring(0, 1);
                        next[c] = next[c].substring(0, 7);

                        listEstudiantes.add(next);
                    }
                }
            }

        } catch (Exception e) {
        }

        return listEstudiantes;
    }

}
