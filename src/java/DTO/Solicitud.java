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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Leonardo
 */
@Entity
@Table(name = "solicitud", catalog = "avecsdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Solicitud.findAll", query = "SELECT s FROM Solicitud s")
    , @NamedQuery(name = "Solicitud.findByIdSolicitud", query = "SELECT s FROM Solicitud s WHERE s.idSolicitud = :idSolicitud")
    , @NamedQuery(name = "Solicitud.findByCantidadParticipantes", query = "SELECT s FROM Solicitud s WHERE s.cantidadParticipantes = :cantidadParticipantes")
    , @NamedQuery(name = "Solicitud.findByCuposDisponibles", query = "SELECT s FROM Solicitud s WHERE s.cuposDisponibles = :cuposDisponibles")
    , @NamedQuery(name = "Solicitud.findByTematica", query = "SELECT s FROM Solicitud s WHERE s.tematica = :tematica")
    , @NamedQuery(name = "Solicitud.findByFecInicio", query = "SELECT s FROM Solicitud s WHERE s.fecInicio = :fecInicio")
    , @NamedQuery(name = "Solicitud.findByFecFin", query = "SELECT s FROM Solicitud s WHERE s.fecFin = :fecFin")
    , @NamedQuery(name = "Solicitud.findByFecCreacion", query = "SELECT s FROM Solicitud s WHERE s.fecCreacion = :fecCreacion")
    , @NamedQuery(name = "Solicitud.findByFecSolicitudAprob", query = "SELECT s FROM Solicitud s WHERE s.fecSolicitudAprob = :fecSolicitudAprob")
    , @NamedQuery(name = "Solicitud.findByFecAprobacion", query = "SELECT s FROM Solicitud s WHERE s.fecAprobacion = :fecAprobacion")
    , @NamedQuery(name = "Solicitud.findByPeriodo", query = "SELECT s FROM Solicitud s WHERE s.periodo = :periodo")})
public class Solicitud implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_solicitud", nullable = false)
    private Integer idSolicitud;
    @Basic(optional = false)
    @Column(name = "cantidad_participantes", nullable = false)
    private int cantidadParticipantes;
    @Basic(optional = false)
    @Column(name = "cupos_disponibles", nullable = false)
    private int cuposDisponibles;
    @Basic(optional = false)
    @Column(name = "tematica", nullable = false, length = 100)
    private String tematica;
    @Basic(optional = false)
    @Column(name = "fec_inicio", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecInicio;
    @Basic(optional = false)
    @Column(name = "fec_fin", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecFin;
    @Basic(optional = false)
    @Lob
    @Column(name = "observacion", nullable = false, length = 65535)
    private String observacion;
    @Basic(optional = false)
    @Column(name = "fec_creacion", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecCreacion;
    @Basic(optional = false)
    @Column(name = "fec_solicitud_aprob", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecSolicitudAprob;
    @Basic(optional = false)
    @Column(name = "fec_aprobacion", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fecAprobacion;
    @Basic(optional = false)
    @Column(name = "periodo", nullable = false, length = 10)
    private String periodo;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSolicitud")
    private List<Cronograma> cronogramaList;
    @JoinColumn(name = "id_asignatura", referencedColumnName = "id_asignatura", nullable = false)
    @ManyToOne(optional = false)
    private Asignatura idAsignatura;
    @JoinColumn(name = "id_docente", referencedColumnName = "id_docente", nullable = false)
    @ManyToOne(optional = false)
    private Docente idDocente;
    @JoinColumn(name = "id_empresa", referencedColumnName = "id_empresa", nullable = false)
    @ManyToOne(optional = false)
    private Empresa idEmpresa;
    @JoinColumn(name = "estatus", referencedColumnName = "id", nullable = false)
    @ManyToOne(optional = false)
    private EstatusSolicitud estatus;
    @JoinColumn(name = "id_aprobador", referencedColumnName = "id_usuario")
    @ManyToOne
    private Usuario idAprobador;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idSolicitud")
    private List<Postulante> postulanteList;

    public Solicitud() {
    }

    public Solicitud(Integer idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public Solicitud(Integer idSolicitud, int cantidadParticipantes, int cuposDisponibles, String tematica, Date fecInicio, Date fecFin, String observacion, Date fecCreacion, Date fecSolicitudAprob, Date fecAprobacion, String periodo) {
        this.idSolicitud = idSolicitud;
        this.cantidadParticipantes = cantidadParticipantes;
        this.cuposDisponibles = cuposDisponibles;
        this.tematica = tematica;
        this.fecInicio = fecInicio;
        this.fecFin = fecFin;
        this.observacion = observacion;
        this.fecCreacion = fecCreacion;
        this.fecSolicitudAprob = fecSolicitudAprob;
        this.fecAprobacion = fecAprobacion;
        this.periodo = periodo;
    }

    public Integer getIdSolicitud() {
        return idSolicitud;
    }

    public void setIdSolicitud(Integer idSolicitud) {
        this.idSolicitud = idSolicitud;
    }

    public int getCantidadParticipantes() {
        return cantidadParticipantes;
    }

    public void setCantidadParticipantes(int cantidadParticipantes) {
        this.cantidadParticipantes = cantidadParticipantes;
    }

    public int getCuposDisponibles() {
        return cuposDisponibles;
    }

    public void setCuposDisponibles(int cuposDisponibles) {
        this.cuposDisponibles = cuposDisponibles;
    }

    public String getTematica() {
        return tematica;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
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

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getFecCreacion() {
        return fecCreacion;
    }

    public void setFecCreacion(Date fecCreacion) {
        this.fecCreacion = fecCreacion;
    }

    public Date getFecSolicitudAprob() {
        return fecSolicitudAprob;
    }

    public void setFecSolicitudAprob(Date fecSolicitudAprob) {
        this.fecSolicitudAprob = fecSolicitudAprob;
    }

    public Date getFecAprobacion() {
        return fecAprobacion;
    }

    public void setFecAprobacion(Date fecAprobacion) {
        this.fecAprobacion = fecAprobacion;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    @XmlTransient
    public List<Cronograma> getCronogramaList() {
        return cronogramaList;
    }

    public void setCronogramaList(List<Cronograma> cronogramaList) {
        this.cronogramaList = cronogramaList;
    }

    public Asignatura getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(Asignatura idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public Docente getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(Docente idDocente) {
        this.idDocente = idDocente;
    }

    public Empresa getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(Empresa idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public EstatusSolicitud getEstatus() {
        return estatus;
    }

    public void setEstatus(EstatusSolicitud estatus) {
        this.estatus = estatus;
    }

    public Usuario getIdAprobador() {
        return idAprobador;
    }

    public void setIdAprobador(Usuario idAprobador) {
        this.idAprobador = idAprobador;
    }

    @XmlTransient
    public List<Postulante> getPostulanteList() {
        return postulanteList;
    }

    public void setPostulanteList(List<Postulante> postulanteList) {
        this.postulanteList = postulanteList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSolicitud != null ? idSolicitud.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Solicitud)) {
            return false;
        }
        Solicitud other = (Solicitud) object;
        if ((this.idSolicitud == null && other.idSolicitud != null) || (this.idSolicitud != null && !this.idSolicitud.equals(other.idSolicitud))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Solicitud[ idSolicitud=" + idSolicitud + " ]";
    }
    
}
