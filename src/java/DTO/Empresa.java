/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Leonardo
 */
@Entity
@Table(name = "empresa", catalog = "avecsdb", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"nombre_empresa"})
    , @UniqueConstraint(columnNames = {"nit_empresa"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Empresa.findAll", query = "SELECT e FROM Empresa e")
    , @NamedQuery(name = "Empresa.findByIdEmpresa", query = "SELECT e FROM Empresa e WHERE e.idEmpresa = :idEmpresa")
    , @NamedQuery(name = "Empresa.findByNitEmpresa", query = "SELECT e FROM Empresa e WHERE e.nitEmpresa = :nitEmpresa")
    , @NamedQuery(name = "Empresa.findByNombreEmpresa", query = "SELECT e FROM Empresa e WHERE e.nombreEmpresa = :nombreEmpresa")
    , @NamedQuery(name = "Empresa.findByDptoEmpresa", query = "SELECT e FROM Empresa e WHERE e.dptoEmpresa = :dptoEmpresa")
    , @NamedQuery(name = "Empresa.findByCiudadEmpresa", query = "SELECT e FROM Empresa e WHERE e.ciudadEmpresa = :ciudadEmpresa")
    , @NamedQuery(name = "Empresa.findByDireccionEmpresa", query = "SELECT e FROM Empresa e WHERE e.direccionEmpresa = :direccionEmpresa")
    , @NamedQuery(name = "Empresa.findByTelefono1Empresa", query = "SELECT e FROM Empresa e WHERE e.telefono1Empresa = :telefono1Empresa")
    , @NamedQuery(name = "Empresa.findByTelefono2Empresa", query = "SELECT e FROM Empresa e WHERE e.telefono2Empresa = :telefono2Empresa")
    , @NamedQuery(name = "Empresa.findByEmailEmpresa", query = "SELECT e FROM Empresa e WHERE e.emailEmpresa = :emailEmpresa")
    , @NamedQuery(name = "Empresa.findByPersonaContacto", query = "SELECT e FROM Empresa e WHERE e.personaContacto = :personaContacto")
    , @NamedQuery(name = "Empresa.findByTelPersonaContacto", query = "SELECT e FROM Empresa e WHERE e.telPersonaContacto = :telPersonaContacto")
    , @NamedQuery(name = "Empresa.findByEmailPersonaContacto", query = "SELECT e FROM Empresa e WHERE e.emailPersonaContacto = :emailPersonaContacto")
    , @NamedQuery(name = "Empresa.findByFecCreacion", query = "SELECT e FROM Empresa e WHERE e.fecCreacion = :fecCreacion")})
public class Empresa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_empresa", nullable = false)
    private Integer idEmpresa;
    @Basic(optional = false)
    @Column(name = "nit_empresa", nullable = false, length = 15)
    private String nitEmpresa;
    @Basic(optional = false)
    @Column(name = "nombre_empresa", nullable = false, length = 70)
    private String nombreEmpresa;
    @Basic(optional = false)
    @Column(name = "dpto_empresa", nullable = false, length = 100)
    private String dptoEmpresa;
    @Basic(optional = false)
    @Column(name = "ciudad_empresa", nullable = false, length = 50)
    private String ciudadEmpresa;
    @Basic(optional = false)
    @Column(name = "direccion_empresa", nullable = false, length = 100)
    private String direccionEmpresa;
    @Basic(optional = false)
    @Column(name = "telefono1_empresa", nullable = false, length = 10)
    private String telefono1Empresa;
    @Basic(optional = false)
    @Column(name = "telefono2_empresa", nullable = false, length = 10)
    private String telefono2Empresa;
    @Basic(optional = false)
    @Column(name = "email_empresa", nullable = false, length = 70)
    private String emailEmpresa;
    @Basic(optional = false)
    @Column(name = "persona_contacto", nullable = false, length = 50)
    private String personaContacto;
    @Basic(optional = false)
    @Column(name = "tel_persona_contacto", nullable = false, length = 10)
    private String telPersonaContacto;
    @Basic(optional = false)
    @Column(name = "email_persona_contacto", nullable = false, length = 70)
    private String emailPersonaContacto;
    @Basic(optional = false)
    @Column(name = "fec_creacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecCreacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEmpresa")
    private List<Solicitud> solicitudList;

    public Empresa() {
    }

    public Empresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public Empresa(Integer idEmpresa, String nitEmpresa, String nombreEmpresa, String dptoEmpresa, String ciudadEmpresa, String direccionEmpresa, String telefono1Empresa, String telefono2Empresa, String emailEmpresa, String personaContacto, String telPersonaContacto, String emailPersonaContacto, Date fecCreacion) {
        this.idEmpresa = idEmpresa;
        this.nitEmpresa = nitEmpresa;
        this.nombreEmpresa = nombreEmpresa;
        this.dptoEmpresa = dptoEmpresa;
        this.ciudadEmpresa = ciudadEmpresa;
        this.direccionEmpresa = direccionEmpresa;
        this.telefono1Empresa = telefono1Empresa;
        this.telefono2Empresa = telefono2Empresa;
        this.emailEmpresa = emailEmpresa;
        this.personaContacto = personaContacto;
        this.telPersonaContacto = telPersonaContacto;
        this.emailPersonaContacto = emailPersonaContacto;
        this.fecCreacion = fecCreacion;
    }
    
    public Empresa(String nitEmpresa, String nombreEmpresa, String dptoEmpresa, String ciudadEmpresa, String direccionEmpresa, String telefono1Empresa, String telefono2Empresa, String emailEmpresa, String personaContacto, String telPersonaContacto, String emailPersonaContacto, Date fecCreacion) {
        this(null,nitEmpresa,nombreEmpresa,dptoEmpresa,ciudadEmpresa,direccionEmpresa,telefono1Empresa,telefono2Empresa,emailEmpresa,personaContacto,telPersonaContacto,emailPersonaContacto,fecCreacion);
    }

    public Integer getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Integer idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNitEmpresa() {
        return nitEmpresa;
    }

    public void setNitEmpresa(String nitEmpresa) {
        this.nitEmpresa = nitEmpresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public String getDptoEmpresa() {
        return dptoEmpresa;
    }

    public void setDptoEmpresa(String dptoEmpresa) {
        this.dptoEmpresa = dptoEmpresa;
    }

    public String getCiudadEmpresa() {
        return ciudadEmpresa;
    }

    public void setCiudadEmpresa(String ciudadEmpresa) {
        this.ciudadEmpresa = ciudadEmpresa;
    }

    public String getDireccionEmpresa() {
        return direccionEmpresa;
    }

    public void setDireccionEmpresa(String direccionEmpresa) {
        this.direccionEmpresa = direccionEmpresa;
    }

    public String getTelefono1Empresa() {
        return telefono1Empresa;
    }

    public void setTelefono1Empresa(String telefono1Empresa) {
        this.telefono1Empresa = telefono1Empresa;
    }

    public String getTelefono2Empresa() {
        return telefono2Empresa;
    }

    public void setTelefono2Empresa(String telefono2Empresa) {
        this.telefono2Empresa = telefono2Empresa;
    }

    public String getEmailEmpresa() {
        return emailEmpresa;
    }

    public void setEmailEmpresa(String emailEmpresa) {
        this.emailEmpresa = emailEmpresa;
    }

    public String getPersonaContacto() {
        return personaContacto;
    }

    public void setPersonaContacto(String personaContacto) {
        this.personaContacto = personaContacto;
    }

    public String getTelPersonaContacto() {
        return telPersonaContacto;
    }

    public void setTelPersonaContacto(String telPersonaContacto) {
        this.telPersonaContacto = telPersonaContacto;
    }

    public String getEmailPersonaContacto() {
        return emailPersonaContacto;
    }

    public void setEmailPersonaContacto(String emailPersonaContacto) {
        this.emailPersonaContacto = emailPersonaContacto;
    }

    public Date getFecCreacion() {
        return fecCreacion;
    }

    public void setFecCreacion(Date fecCreacion) {
        this.fecCreacion = fecCreacion;
    }

    @XmlTransient
    public List<Solicitud> getSolicitudList() {
        return solicitudList;
    }

    public void setSolicitudList(List<Solicitud> solicitudList) {
        this.solicitudList = solicitudList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEmpresa != null ? idEmpresa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Empresa)) {
            return false;
        }
        Empresa other = (Empresa) object;
        if ((this.idEmpresa == null && other.idEmpresa != null) || (this.idEmpresa != null && !this.idEmpresa.equals(other.idEmpresa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Empresa[ idEmpresa=" + idEmpresa + " ]";
    }
    
}
