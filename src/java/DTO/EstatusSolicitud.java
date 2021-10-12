/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Leonardo
 */
@Entity
@Table(name = "estatus_solicitud", catalog = "avecsdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstatusSolicitud.findAll", query = "SELECT e FROM EstatusSolicitud e")
    , @NamedQuery(name = "EstatusSolicitud.findById", query = "SELECT e FROM EstatusSolicitud e WHERE e.id = :id")
    , @NamedQuery(name = "EstatusSolicitud.findByDescripcion", query = "SELECT e FROM EstatusSolicitud e WHERE e.descripcion = :descripcion")
    , @NamedQuery(name = "EstatusSolicitud.findByDesDetallada", query = "SELECT e FROM EstatusSolicitud e WHERE e.desDetallada = :desDetallada")})
public class EstatusSolicitud implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Basic(optional = false)
    @Column(name = "descripcion", nullable = false, length = 20)
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "des_detallada", nullable = false, length = 100)
    private String desDetallada;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "estatus")
    private List<Solicitud> solicitudList;

    public EstatusSolicitud() {
    }

    public EstatusSolicitud(Integer id) {
        this.id = id;
    }

    public EstatusSolicitud(Integer id, String descripcion, String desDetallada) {
        this.id = id;
        this.descripcion = descripcion;
        this.desDetallada = desDetallada;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDesDetallada() {
        return desDetallada;
    }

    public void setDesDetallada(String desDetallada) {
        this.desDetallada = desDetallada;
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
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EstatusSolicitud)) {
            return false;
        }
        EstatusSolicitud other = (EstatusSolicitud) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.EstatusSolicitud[ id=" + id + " ]";
    }
    
}
