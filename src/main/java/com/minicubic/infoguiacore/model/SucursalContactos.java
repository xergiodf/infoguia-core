/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minicubic.infoguiacore.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hectorvillalba
 */
@Entity
@Table(name = "sucursal_contactos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SucursalContactos.findAll", query = "SELECT s FROM SucursalContactos s"),
    @NamedQuery(name = "SucursalContactos.findById", query = "SELECT s FROM SucursalContactos s WHERE s.id = :id"),
    @NamedQuery(name = "SucursalContactos.findByContacto", query = "SELECT s FROM SucursalContactos s WHERE s.contacto = :contacto")})
public class SucursalContactos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "contacto")
    private String contacto;
    @JoinColumn(name = "id_tipo_contactos", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TiposContactos idTipoContactos;
    @JoinColumn(name = "id_cliente_sucursal", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ClienteSucursales idClienteSucursal;

    public SucursalContactos() {
    }

    public SucursalContactos(Integer id) {
        this.id = id;
    }

    public SucursalContactos(Integer id, String contacto) {
        this.id = id;
        this.contacto = contacto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public TiposContactos getIdTipoContactos() {
        return idTipoContactos;
    }

    public void setIdTipoContactos(TiposContactos idTipoContactos) {
        this.idTipoContactos = idTipoContactos;
    }

    public ClienteSucursales getIdClienteSucursal() {
        return idClienteSucursal;
    }

    public void setIdClienteSucursal(ClienteSucursales idClienteSucursal) {
        this.idClienteSucursal = idClienteSucursal;
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
        if (!(object instanceof SucursalContactos)) {
            return false;
        }
        SucursalContactos other = (SucursalContactos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.minicubic.infoguiaserver.model.SucursalContactos[ id=" + id + " ]";
    }
    
}
