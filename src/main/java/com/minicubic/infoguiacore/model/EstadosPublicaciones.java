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
@Table(name = "estados_publicaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EstadosPublicaciones.findAll", query = "SELECT e FROM EstadosPublicaciones e"),
    @NamedQuery(name = "EstadosPublicaciones.findById", query = "SELECT e FROM EstadosPublicaciones e WHERE e.id = :id"),
    @NamedQuery(name = "EstadosPublicaciones.findByDescripcion", query = "SELECT e FROM EstadosPublicaciones e WHERE e.descripcion = :descripcion")})
public class EstadosPublicaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstadoPublicacion", fetch = FetchType.LAZY)
    private List<ClientePublicaciones> clientePublicacionesList;

    public EstadosPublicaciones() {
    }

    public EstadosPublicaciones(Integer id) {
        this.id = id;
    }

    public EstadosPublicaciones(Integer id, String descripcion) {
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
    public List<ClientePublicaciones> getClientePublicacionesList() {
        return clientePublicacionesList;
    }

    public void setClientePublicacionesList(List<ClientePublicaciones> clientePublicacionesList) {
        this.clientePublicacionesList = clientePublicacionesList;
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
        if (!(object instanceof EstadosPublicaciones)) {
            return false;
        }
        EstadosPublicaciones other = (EstadosPublicaciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.minicubic.infoguiaserver.model.EstadosPublicaciones[ id=" + id + " ]";
    }
    
}
