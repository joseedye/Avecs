/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Leonardo
 */
@Entity
@Table(name = "postulante", catalog = "avecsdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Postulante.findAll", query = "SELECT p FROM Postulante p")
    , @NamedQuery(name = "Postulante.findById", query = "SELECT p FROM Postulante p WHERE p.id = :id")
    , @NamedQuery(name = "Postulante.findByFecPostulacion", query = "SELECT p FROM Postulante p WHERE p.fecPostulacion = :fecPostulacion")})
public class Postulante implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "fec_postulacion", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecPostulacion;
    @Basic(optional = false)
    @Lob
    @Column(name = "observacion", nullable = false, length = 65535)
    private String observacion;
    @JoinColumn(name = "estatus", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private EstatusPostulante estatus;
    @JoinColumn(name = "id_estudiante", referencedColumnName = "id_estudiante", nullable = false)
    @ManyToOne(optional = false)
    private Estudiante idEstudiante;
    @JoinColumn(name = "id_solicitud", referencedColumnName = "id_solicitud", nullable = false)
    @ManyToOne(optional = false)
    private Solicitud idSolicitud;

    public Postulante() {
    }

    public Postulante(Integer id) {
        this.id = id;
    }

    public Postulante(Integer id, Date fecPostulacion, String observacion) {
        this.id = id;
        this.fecPostulacion = fecPostulacion;
        this.observacion = observacion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecPostulacion() {
        return fecPostulacion;
    }

    public void setFecPostulacion(Date fecPostulacion) {
        this.fecPostulacion = fecPostulacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public EstatusPostulante getEstatus() {
        return estatus;
    }

    public void setEstatus(EstatusPostulante estatus) {
        this.estatus = estatus;
    }

    public Estudiante getIdEstudiante() {
        return idEstudiante;
    }

    public void setIdEstudiante(Estudiante idEstudiante) {
        this.idEstudiante = idEstudiante;
    }

    public Solicitud getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Solicitud idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Postulante)) {
            return false;
        }
        Postulante other = (Postulante) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Postulante[ id=" + id + " ]";
    }
    
}
