package DTO;

import DTO.Grupo;
import DTO.Persona;
import DTO.Postulante;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-05T23:07:06")
@StaticMetamodel(Estudiante.class)
public class Estudiante_ { 

    public static volatile SingularAttribute<Estudiante, String> nombreContacto;
    public static volatile ListAttribute<Estudiante, Grupo> grupoList;
    public static volatile ListAttribute<Estudiante, Postulante> postulanteList;
    public static volatile SingularAttribute<Estudiante, Integer> idEstudiante;
    public static volatile SingularAttribute<Estudiante, String> arl;
    public static volatile SingularAttribute<Estudiante, Persona> idPersona;
    public static volatile SingularAttribute<Estudiante, Integer> codigoEstudiante;
    public static volatile SingularAttribute<Estudiante, String> numeroContacto;

}