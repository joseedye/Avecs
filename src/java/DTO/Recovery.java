/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Leonardo
 */
@Entity
@Table(name = "recovery", catalog = "avecsdb", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Recovery.findAll", query = "SELECT r FROM Recovery r")
    , @NamedQuery(name = "Recovery.findByUser", query = "SELECT r FROM Recovery r WHERE r.user = :user")
    , @NamedQuery(name = "Recovery.findByToken", query = "SELECT r FROM Recovery r WHERE r.token = :token")})
public class Recovery implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "user", nullable = false, length = 70)
    private String user;
    @Id
    @Basic(optional = false)
    @Column(name = "token", nullable = false)
    private Integer token;

    public Recovery() {
    }

    public Recovery(Integer token) {
        this.token = token;
    }

    public Recovery(Integer token, String user) {
        this.token = token;
        this.user = user;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Integer getToken() {
        return token;
    }

    public void setToken(Integer token) {
        this.token = token;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (token != null ? token.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Recovery)) {
            return false;
        }
        Recovery other = (Recovery) object;
        if ((this.token == null && other.token != null) || (this.token != null && !this.token.equals(other.token))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Recovery[ token=" + token + " ]";
    }
    
}
