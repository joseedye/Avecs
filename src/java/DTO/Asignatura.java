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
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Leonardo
 */
@Entity
@Table(name = "asignatura", catalog = "avecsdb", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"codigo_asignatura"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Asignatura.findAll", query = "SELECT a FROM Asignatura a")
    , @NamedQuery(name = "Asignatura.findByIdAsignatura", query = "SELECT a FROM Asignatura a WHERE a.idAsignatura = :idAsignatura")
    , @NamedQuery(name = "Asignatura.findByDescripcionAsignatura", query = "SELECT a FROM Asignatura a WHERE a.descripcionAsignatura = :descripcionAsignatura")
    , @NamedQuery(name = "Asignatura.findByCodigoAsignatura", query = "SELECT a FROM Asignatura a WHERE a.codigoAsignatura = :codigoAsignatura")
    , @NamedQuery(name = "Asignatura.findBySemestre", query = "SELECT a FROM Asignatura a WHERE a.semestre = :semestre")})
public class Asignatura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_asignatura", nullable = false)
    private Integer idAsignatura;
    @Basic(optional = false)
    @Column(name = "descripcion_asignatura", nullable = false, length = 70)
    private String descripcionAsignatura;
    @Basic(optional = false)
    @Column(name = "codigo_asignatura", nullable = false)
    private int codigoAsignatura;
    @Basic(optional = false)
    @Column(name = "semestre", nullable = false)
    private int semestre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAsignatura")
    private List<Grupo> grupoList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idAsignatura")
    private List<Solicitud> solicitudList;

    public Asignatura() {
    }

    public Asignatura(Integer idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public Asignatura(Integer idAsignatura, String descripcionAsignatura, int codigoAsignatura, int semestre) {
        this.idAsignatura = idAsignatura;
        this.descripcionAsignatura = descripcionAsignatura;
        this.codigoAsignatura = codigoAsignatura;
        this.semestre = semestre;
    }

    public Integer getIdAsignatura() {
        return idAsignatura;
    }

    public void setIdAsignatura(Integer idAsignatura) {
        this.idAsignatura = idAsignatura;
    }

    public String getDescripcionAsignatura() {
        return descripcionAsignatura;
    }

    public void setDescripcionAsignatura(String descripcionAsignatura) {
        this.descripcionAsignatura = descripcionAsignatura;
    }

    public int getCodigoAsignatura() {
        return codigoAsignatura;
    }

    public void setCodigoAsignatura(int codigoAsignatura) {
        this.codigoAsignatura = codigoAsignatura;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    @XmlTransient
    public List<Grupo> getGrupoList() {
        return grupoList;
    }

    public void setGrupoList(List<Grupo> grupoList) {
        this.grupoList = grupoList;
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
        hash += (idAsignatura != null ? idAsignatura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asignatura)) {
            return false;
        }
        Asignatura other = (Asignatura) object;
        if ((this.idAsignatura == null && other.idAsignatura != null) || (this.idAsignatura != null && !this.idAsignatura.equals(other.idAsignatura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Asignatura[ idAsignatura=" + idAsignatura + " ]";
    }
    
}
