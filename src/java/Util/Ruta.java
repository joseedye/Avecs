package Util;


import java.util.ArrayList;

public final class Ruta {
  
    static ArrayList<String> rutasAdmin = new ArrayList<>();
    static ArrayList<String> rutasDocente = new ArrayList<>();
    static ArrayList<String> rutasEstudiante = new ArrayList<>();
    static ArrayList<String> rutasGeneral = new ArrayList<>();
    
    

    public static void createRutas() {       
        //Administrator pages
        
        rutasAdmin.add("/Administrador");
        rutasAdmin.add("/Administrador/");
        rutasAdmin.add("/Administrador/documento_consulta");
        rutasAdmin.add("/Administrador/documento_nuevo");        
        rutasAdmin.add("/Administrador/empresa_consulta");
        rutasAdmin.add("/Administrador/empresa_modificar");
        rutasAdmin.add("/Administrador/empresa_registrar");
        rutasAdmin.add("/Administrador/perfil");
        rutasAdmin.add("/Administrador/solicitud_ver");
        rutasAdmin.add("/Administrador/solicitudes");
        rutasAdmin.add("/Administrador/usuario_consulta");
        rutasAdmin.add("/Administrador/usuario_modificar");
        rutasAdmin.add("/Administrador/usuario_registrar");
        rutasAdmin.add("/Administrador/estudiantes_registrar");
        rutasAdmin.add("/Administrador/visitas");
        rutasAdmin.add("/Error/errorRedir");
        rutasAdmin.add("/Error/page404");
        
        
        //Docente pages        
        rutasDocente.add("/Docente/");
        rutasDocente.add("/Docente");
        rutasDocente.add("/Docente/Solicitudes");
        rutasDocente.add("/Docente/doc_estudiante");
        rutasDocente.add("/Docente/documento_consulta");
        rutasDocente.add("/Docente/empresa_consulta");
        rutasDocente.add("/Docente/empresa_modificar");
        rutasDocente.add("/Docente/empresa_registrar");
        rutasDocente.add("/Docente/perfil");
        rutasDocente.add("/Docente/postulante_consulta");
        rutasDocente.add("/Docente/solicitud_ver");
        rutasDocente.add("/Docente/visitas");
        rutasDocente.add("/Docente/visita_ver");
        rutasDocente.add("/Error/errorRedir");
        rutasDocente.add("/Error/page404");
        
        //Estudiante pages
        rutasEstudiante.add("/Estudiante/");
        rutasEstudiante.add("/Estudiante");
        rutasEstudiante.add("/Estudiante/perfil");        
        rutasEstudiante.add("/Estudiante/solicitudes");
        rutasEstudiante.add("/Estudiante/solicitud_ver");
        rutasEstudiante.add("/Estudiante/visitas");
        rutasEstudiante.add("/Error/errorRedir");
        rutasEstudiante.add("/Error/page404");
        
        //General pages
        rutasGeneral.add("/Sesion/cambio_clave");
        rutasGeneral.add("/Sesion/recuperacion_clave");
        rutasGeneral.add("/Error/page404");
        rutasGeneral.add("/Error/errorRedir");
        rutasGeneral.add("/index");
        rutasGeneral.add("/logout");
        rutasGeneral.add("/");
    }
    
    public static boolean containRutasGeneral(String uri){  
        createRutas();
        return (rutasGeneral.stream().anyMatch((ruta) -> (ruta.equalsIgnoreCase(uri)))) || isFileSystem(uri);
    }
    
    public static boolean containRutasAdmin(String uri){  
        createRutas();        
        return (rutasAdmin.stream().anyMatch((ruta) -> (ruta.equalsIgnoreCase(uri)))) || isFileSystem(uri);
    }
    
    public static boolean containRutasDocente(String uri){  
        createRutas();        
        return (rutasDocente.stream().anyMatch((ruta) -> (ruta.equalsIgnoreCase(uri)))) || isFileSystem(uri);
    }
    
    public static boolean containRutasEstudiante(String uri){  
        createRutas();        
        return (rutasEstudiante.stream().anyMatch((ruta) -> (ruta.equalsIgnoreCase(uri)))) || isFileSystem(uri);
    }

    private static boolean isFileSystem(String uri) {
        return uri.contains(".css")  || uri.contains(".jpg") ||
               uri.contains(".jpeg") || uri.contains(".png") || uri.contains(".do")  ||
               uri.contains(".do")   || isJS(uri) || uri.contains("/Files/");
    }
    
    private static boolean isJS(String uri){
        int len = uri.length();
        String ext = "";
        if(len > 10)        
            ext = uri.substring(len-3,len);
        
        return ext.equals(".js");
    }
}
