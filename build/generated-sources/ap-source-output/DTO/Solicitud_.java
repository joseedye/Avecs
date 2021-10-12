package DTO;

import DTO.Asignatura;
import DTO.Cronograma;
import DTO.Docente;
import DTO.Empresa;
import DTO.EstatusSolicitud;
import DTO.Postulante;
import DTO.Usuario;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-05T23:07:06")
@StaticMetamodel(Solicitud.class)
public class Solicitud_ { 

    public static volatile ListAttribute<Solicitud, Cronograma> cronogramaList;
    public static volatile SingularAttribute<Solicitud, Date> fecSolicitudAprob;
    public static volatile SingularAttribute<Solicitud, String> periodo;
    public static volatile SingularAttribute<Solicitud, Usuario> idAprobador;
    public static volatile ListAttribute<Solicitud, Postulante> postulanteList;
    public static volatile SingularAttribute<Solicitud, Integer> cuposDisponibles;
    public static volatile SingularAttribute<Solicitud, Integer> idSolicitud;
    public static volatile SingularAttribute<Solicitud, Integer> cantidadParticipantes;
    public static volatile SingularAttribute<Solicitud, Date> fecFin;
    public static volatile SingularAttribute<Solicitud, Docente> idDocente;
    public static volatile SingularAttribute<Solicitud, String> tematica;
    public static volatile SingularAttribute<Solicitud, EstatusSolicitud> estatus;
    public static volatile SingularAttribute<Solicitud, Date> fecCreacion;
    public static volatile SingularAttribute<Solicitud, Date> fecAprobacion;
    public static volatile SingularAttribute<Solicitud, Empresa> idEmpresa;
    public static volatile SingularAttribute<Solicitud, Asignatura> idAsignatura;
    public static volatile SingularAttribute<Solicitud, Date> fecInicio;
    public static volatile SingularAttribute<Solicitud, String> observacion;

}