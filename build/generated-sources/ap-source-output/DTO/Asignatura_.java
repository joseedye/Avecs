package DTO;

import DTO.Grupo;
import DTO.Solicitud;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-05T23:07:06")
@StaticMetamodel(Asignatura.class)
public class Asignatura_ { 

    public static volatile ListAttribute<Asignatura, Grupo> grupoList;
    public static volatile SingularAttribute<Asignatura, String> descripcionAsignatura;
    public static volatile SingularAttribute<Asignatura, Integer> idAsignatura;
    public static volatile SingularAttribute<Asignatura, Integer> semestre;
    public static volatile ListAttribute<Asignatura, Solicitud> solicitudList;
    public static volatile SingularAttribute<Asignatura, Integer> codigoAsignatura;

}