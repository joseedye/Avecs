package DTO;

import DTO.Persona;
import DTO.TipoDocumento;
import DTO.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-05T23:07:06")
@StaticMetamodel(Documento.class)
public class Documento_ { 

    public static volatile SingularAttribute<Documento, Usuario> idUser;
    public static volatile SingularAttribute<Documento, Date> fechaSubidaDoc;
    public static volatile SingularAttribute<Documento, TipoDocumento> idTipoDoc;
    public static volatile SingularAttribute<Documento, String> rutaDoc;
    public static volatile SingularAttribute<Documento, Date> fechaDoc;
    public static volatile SingularAttribute<Documento, Boolean> isPublic;
    public static volatile SingularAttribute<Documento, String> descripcionDoc;
    public static volatile SingularAttribute<Documento, Integer> idDoc;
    public static volatile SingularAttribute<Documento, Persona> idPersona;

}