/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minicubic.infoguiacore.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
 * @author hectorvillalba
 */
@Entity
@Table(name = "tipos_contactos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TiposContactos.findAll", query = "SELECT t FROM TiposContactos t"),
    @NamedQuery(name = "TiposContactos.findById", query = "SELECT t FROM TiposContactos t WHERE t.id = :id"),
    @NamedQuery(name = "TiposContactos.findByDescripcion", query = "SELECT t FROM TiposContactos t WHERE t.descripcion = :descripcion")})
public class TiposContactos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idTipoContactos", fetch = FetchType.LAZY)
    private List<SucursalContactos> sucursalContactosList;

    public TiposContactos() {
    }

    public TiposContactos(Integer id) {
        this.id = id;
    }

    public TiposContactos(Integer id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
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

    @XmlTransient
    public List<SucursalContactos> getSucursalContactosList() {
        return sucursalContactosList;
    }

    public void setSucursalContactosList(List<SucursalContactos> sucursalContactosList) {
        this.sucursalContactosList = sucursalContactosList;
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
        if (!(object instanceof TiposContactos)) {
            return false;
        }
        TiposContactos other = (TiposContactos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.minicubic.infoguiaserver.model.TiposContactos[ id=" + id + " ]";
    }
    
}
