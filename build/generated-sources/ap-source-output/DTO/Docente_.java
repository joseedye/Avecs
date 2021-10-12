package DTO;

import DTO.Grupo;
import DTO.Persona;
import DTO.Solicitud;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-05T23:07:06")
@StaticMetamodel(Docente.class)
public class Docente_ { 

    public static volatile ListAttribute<Docente, Grupo> grupoList;
    public static volatile SingularAttribute<Docente, Integer> idDocente;
    public static volatile ListAttribute<Docente, Solicitud> solicitudList;
    public static volatile SingularAttribute<Docente, Persona> idPersona;
    public static volatile SingularAttribute<Docente, Integer> codigoDocente;

}