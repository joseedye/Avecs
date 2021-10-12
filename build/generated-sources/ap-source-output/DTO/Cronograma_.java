package DTO;

import DTO.Solicitud;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-05T23:07:06")
@StaticMetamodel(Cronograma.class)
public class Cronograma_ { 

    public static volatile SingularAttribute<Cronograma, String> descripcion;
    public static volatile SingularAttribute<Cronograma, Integer> id;
    public static volatile SingularAttribute<Cronograma, Solicitud> idSolicitud;
    public static volatile SingularAttribute<Cronograma, Date> fecFin;
    public static volatile SingularAttribute<Cronograma, Date> fecInicio;
    public static volatile SingularAttribute<Cronograma, Boolean> isDone;
    public static volatile SingularAttribute<Cronograma, String> observacion;
    public static volatile SingularAttribute<Cronograma, String> actividad;

}