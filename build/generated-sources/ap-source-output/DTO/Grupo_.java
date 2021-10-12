package DTO;

import DTO.Asignatura;
import DTO.Docente;
import DTO.Estudiante;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-05T23:07:06")
@StaticMetamodel(Grupo.class)
public class Grupo_ { 

    public static volatile SingularAttribute<Grupo, String> periodo;
    public static volatile SingularAttribute<Grupo, Estudiante> idEstudiante;
    public static volatile SingularAttribute<Grupo, Integer> id;
    public static volatile SingularAttribute<Grupo, String> letra;
    public static volatile SingularAttribute<Grupo, Asignatura> idAsignatura;
    public static volatile SingularAttribute<Grupo, Docente> idDocente;

}