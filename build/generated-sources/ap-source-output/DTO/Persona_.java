package DTO;

import DTO.Docente;
import DTO.Documento;
import DTO.Estudiante;
import DTO.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-05T23:07:06")
@StaticMetamodel(Persona.class)
public class Persona_ { 

    public static volatile SingularAttribute<Persona, String> apellido2;
    public static volatile SingularAttribute<Persona, Integer> numDocumento;
    public static volatile ListAttribute<Persona, Documento> documentoList;
    public static volatile ListAttribute<Persona, Usuario> usuarioList;
    public static volatile SingularAttribute<Persona, String> apellido1;
    public static volatile SingularAttribute<Persona, String> direccion;
    public static volatile SingularAttribute<Persona, String> nombres;
    public static volatile SingularAttribute<Persona, String> tipoDocumento;
    public static volatile SingularAttribute<Persona, String> genero;
    public static volatile ListAttribute<Persona, Estudiante> estudianteList;
    public static volatile SingularAttribute<Persona, Date> fecNacimiento;
    public static volatile SingularAttribute<Persona, String> telefono1;
    public static volatile SingularAttribute<Persona, String> telefono2;
    public static volatile SingularAttribute<Persona, Integer> idPersona;
    public static volatile SingularAttribute<Persona, String> email;
    public static volatile ListAttribute<Persona, Docente> docenteList;

}