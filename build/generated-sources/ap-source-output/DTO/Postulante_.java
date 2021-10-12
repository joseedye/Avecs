package DTO;

import DTO.EstatusPostulante;
import DTO.Estudiante;
import DTO.Solicitud;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-05T23:07:06")
@StaticMetamodel(Postulante.class)
public class Postulante_ { 

    public static volatile SingularAttribute<Postulante, EstatusPostulante> estatus;
    public static volatile SingularAttribute<Postulante, Date> fecPostulacion;
    public static volatile SingularAttribute<Postulante, Estudiante> idEstudiante;
    public static volatile SingularAttribute<Postulante, Integer> id;
    public static volatile SingularAttribute<Postulante, Solicitud> idSolicitud;
    public static volatile SingularAttribute<Postulante, String> observacion;

}