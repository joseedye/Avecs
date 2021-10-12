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
@Table(name = "cronograma", catalog = "avecsdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cronograma.findAll", query = "SELECT c FROM Cronograma c")
    , @NamedQuery(name = "Cronograma.findById", query = "SELECT c FROM Cronograma c WHERE c.id = :id")
    , @NamedQuery(name = "Cronograma.findByActividad", query = "SELECT c FROM Cronograma c WHERE c.actividad = :actividad")
    , @NamedQuery(name = "Cronograma.findByFecInicio", query = "SELECT c FROM Cronograma c WHERE c.fecInicio = :fecInicio")
    , @NamedQuery(name = "Cronograma.findByFecFin", query = "SELECT c FROM Cronograma c WHERE c.fecFin = :fecFin")
    , @NamedQuery(name = "Cronograma.findByIsDone", query = "SELECT c FROM Cronograma c WHERE c.isDone = :isDone")})
public class Cronograma implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "actividad", nullable = false, length = 100)
    private String actividad;
    @Basic(optional = false)
    @Lob
    @Column(name = "descripcion", nullable = false, length = 65535)
    private String descripcion;
    @Basic(optional = false)
    @Lob
    @Column(name = "observacion", nullable = false, length = 65535)
    private String observacion;
    @Basic(optional = false)
    @Column(name = "fec_inicio", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecInicio;
    @Basic(optional = false)
    @Column(name = "fec_fin", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecFin;
    @Basic(optional = false)
    @Column(name = "isDone", nullable = false)
    private boolean isDone;
    @JoinColumn(name = "id_solicitud", referencedColumnName = "id_solicitud", nullable = false)
    @ManyToOne(optional = false)
    private Solicitud idSolicitud;

    public Cronograma() {
    }

    public Cronograma(Integer id) {
        this.id = id;
    }

    public Cronograma(Integer id, String actividad, String descripcion, String observacion, Date fecInicio, Date fecFin, boolean isDone) {
        this.id = id;
        this.actividad = actividad;
        this.descripcion = descripcion;
        this.observacion = observacion;
        this.fecInicio = fecInicio;
        this.fecFin = fecFin;
        this.isDone = isDone;
    }
    
    public Cronograma(String actividad, String descripcion, String observacion, Date fecInicio, Date fecFin, boolean isDone) {
        this(null,actividad,descripcion,observacion,fecInicio,fecFin,isDone);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getFecInicio() {
        return fecInicio;
    }

    public void setFecInicio(Date fecInicio) {
        this.fecInicio = fecInicio;
    }

    public Date getFecFin() {
        return fecFin;
    }

    public void setFecFin(Date fecFin) {
        this.fecFin = fecFin;
    }

    public boolean getIsDone() {
        return isDone;
    }

    public void setIsDone(boolean isDone) {
        this.isDone = isDone;
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
        if (!(object instanceof Cronograma)) {
            return false;
        }
        Cronograma other = (Cronograma) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Cronograma[ id=" + id + " ]";
    }
    
}
