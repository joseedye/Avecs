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
@Table(name = "tipo_documento", catalog = "avecsdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TipoDocumento.findAll", query = "SELECT t FROM TipoDocumento t")
    , @NamedQuery(name = "TipoDocumento.findByIdTipoDoc", query = "SELECT t FROM TipoDocumento t WHERE t.idTipoDoc = :idTipoDoc")
    , @NamedQuery(name = "TipoDocumento.findByDescripcionTipoDoc", query = "SELECT t FROM TipoDocumento t WHERE t.descripcionTipoDoc = :descripcionTipoDoc")})
public class TipoDocumento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_tipo_doc", nullable = false)
    private Integer idTipoDoc;
    @Basic(optional = false)
    @Column(name = "descripcion_tipo_doc", nullable = false, length = 70)
    private String descripcionTipoDoc;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoDoc")
    private List<Documento> documentoList;

    public TipoDocumento() {
    }

    public TipoDocumento(Integer idTipoDoc) {
        this.idTipoDoc = idTipoDoc;
    }

    public TipoDocumento(Integer idTipoDoc, String descripcionTipoDoc) {
        this.idTipoDoc = idTipoDoc;
        this.descripcionTipoDoc = descripcionTipoDoc;
    }

    public Integer getIdTipoDoc() {
        return idTipoDoc;
    }

    public void setIdTipoDoc(Integer idTipoDoc) {
        this.idTipoDoc = idTipoDoc;
    }

    public String getDescripcionTipoDoc() {
        return descripcionTipoDoc;
    }

    public void setDescripcionTipoDoc(String descripcionTipoDoc) {
        this.descripcionTipoDoc = descripcionTipoDoc;
    }

    @XmlTransient
    public List<Documento> getDocumentoList() {
        return documentoList;
    }

    public void setDocumentoList(List<Documento> documentoList) {
        this.documentoList = documentoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTipoDoc != null ? idTipoDoc.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TipoDocumento)) {
            return false;
        }
        TipoDocumento other = (TipoDocumento) object;
        if ((this.idTipoDoc == null && other.idTipoDoc != null) || (this.idTipoDoc != null && !this.idTipoDoc.equals(other.idTipoDoc))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.TipoDocumento[ idTipoDoc=" + idTipoDoc + " ]";
    }
    
}
