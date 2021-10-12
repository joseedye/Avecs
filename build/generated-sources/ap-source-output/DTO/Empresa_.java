package DTO;

import DTO.Solicitud;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2021-10-05T23:07:06")
@StaticMetamodel(Empresa.class)
public class Empresa_ { 

    public static volatile SingularAttribute<Empresa, String> emailEmpresa;
    public static volatile SingularAttribute<Empresa, String> nitEmpresa;
    public static volatile SingularAttribute<Empresa, String> personaContacto;
    public static volatile SingularAttribute<Empresa, String> dptoEmpresa;
    public static volatile SingularAttribute<Empresa, String> telefono2Empresa;
    public static volatile ListAttribute<Empresa, Solicitud> solicitudList;
    public static volatile SingularAttribute<Empresa, String> nombreEmpresa;
    public static volatile SingularAttribute<Empresa, String> telPersonaContacto;
    public static volatile SingularAttribute<Empresa, String> telefono1Empresa;
    public static volatile SingularAttribute<Empresa, String> direccionEmpresa;
    public static volatile SingularAttribute<Empresa, Date> fecCreacion;
    public static volatile SingularAttribute<Empresa, String> emailPersonaContacto;
    public static volatile SingularAttribute<Empresa, Integer> idEmpresa;
    public static volatile SingularAttribute<Empresa, String> ciudadEmpresa;

}