package DTO;

import DTO.Solicitud;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-05T23:07:06")
@StaticMetamodel(EstatusSolicitud.class)
public class EstatusSolicitud_ { 

    public static volatile SingularAttribute<EstatusSolicitud, String> descripcion;
    public static volatile SingularAttribute<EstatusSolicitud, String> desDetallada;
    public static volatile SingularAttribute<EstatusSolicitud, Integer> id;
    public static volatile ListAttribute<EstatusSolicitud, Solicitud> solicitudList;

}