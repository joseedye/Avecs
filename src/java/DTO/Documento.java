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
@Table(name = "documento", catalog = "avecsdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Documento.findAll", query = "SELECT d FROM Documento d")
    , @NamedQuery(name = "Documento.findByIdDoc", query = "SELECT d FROM Documento d WHERE d.idDoc = :idDoc")
    , @NamedQuery(name = "Documento.findByDescripcionDoc", query = "SELECT d FROM Documento d WHERE d.descripcionDoc = :descripcionDoc")
    , @NamedQuery(name = "Documento.findByRutaDoc", query = "SELECT d FROM Documento d WHERE d.rutaDoc = :rutaDoc")
    , @NamedQuery(name = "Documento.findByFechaDoc", query = "SELECT d FROM Documento d WHERE d.fechaDoc = :fechaDoc")
    , @NamedQuery(name = "Documento.findByFechaSubidaDoc", query = "SELECT d FROM Documento d WHERE d.fechaSubidaDoc = :fechaSubidaDoc")
    , @NamedQuery(name = "Documento.findByIsPublic", query = "SELECT d FROM Documento d WHERE d.isPublic = :isPublic")})
public class Documento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_doc", nullable = false)
    private Integer idDoc;
    @Basic(optional = false)
    @Column(name = "descripcion_doc", nullable = false, length = 100)
    private String descripcionDoc;
    @Basic(optional = false)
    @Column(name = "ruta_doc", nullable = false, length = 200)
    private String rutaDoc;
    @Basic(optional = false)
    @Column(name = "fecha_doc", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaDoc;
    @Basic(optional = false)
    @Column(name = "fecha_subida_doc", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaSubidaDoc;
    @Basic(optional = false)
    @Column(name = "is_public", nullable = false)
    private boolean isPublic;
    @JoinColumn(name = "id_persona", referencedColumnName = "id_persona")
    @ManyToOne
    private Persona idPersona;
    @JoinColumn(name = "id_tipo_doc", referencedColumnName = "id_tipo_doc", nullable = false)
    @ManyToOne(optional = false)
    private TipoDocumento idTipoDoc;
    @JoinColumn(name = "id_user", referencedColumnName = "id_usuario", nullable = false)
    @ManyToOne(optional = false)
    private Usuario idUser;

    public Documento() {
    }

    public Documento(Integer idDoc) {
        this.idDoc = idDoc;
    }

    public Documento(Integer idDoc, String descripcionDoc, String rutaDoc, Date fechaDoc, Date fechaSubidaDoc, boolean isPublic) {
        this.idDoc = idDoc;
        this.descripcionDoc = descripcionDoc;
        this.rutaDoc = rutaDoc;
        this.fechaDoc = fechaDoc;
        this.fechaSubidaDoc = fechaSubidaDoc;
        this.isPublic = isPublic;
    }

    public Integer getIdDoc() {
        return idDoc;
    }

    public void setIdDoc(Integer idDoc) {
        this.idDoc = idDoc;
    }

    public String getDescripcionDoc() {
        return descripcionDoc;
    }

    public void setDescripcionDoc(String descripcionDoc) {
        this.descripcionDoc = descripcionDoc;
    }

    public String getRutaDoc() {
        return rutaDoc;
    }

    public void setRutaDoc(String rutaDoc) {
        this.rutaDoc = rutaDoc;
    }

    public Date getFechaDoc() {
        return fechaDoc;
    }

    public void setFechaDoc(Date fechaDoc) {
        this.fechaDoc = fechaDoc;
    }

    public Date getFechaSubidaDoc() {
        return fechaSubidaDoc;
    }

    public void setFechaSubidaDoc(Date fechaSubidaDoc) {
        this.fechaSubidaDoc = fechaSubidaDoc;
    }

    public boolean getIsPublic() {
        return isPublic;
    }

    public void setIsPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public Persona getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Persona idPersona) {
        this.idPersona = idPersona;
    }

    public TipoDocumento getIdTipoDoc() {
        return idTipoDoc;
    }

    public void setIdTipoDoc(TipoDocumento idTipoDoc) {
        this.idTipoDoc = idTipoDoc;
    }

    public Usuario getIdUser() {
        return idUser;
    }

    public void setIdUser(Usuario idUser) {
        this.idUser = idUser;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDoc != null ? idDoc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Documento)) {
            return false;
        }
        Documento other = (Documento) object;
        if ((this.idDoc == null && other.idDoc != null) || (this.idDoc != null && !this.idDoc.equals(other.idDoc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Documento[ idDoc=" + idDoc + " ]";
    }
    
}
