/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minicubic.infoguiacore.model;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author hectorvillalba
 */
@Entity
@Table(name = "cliente_publicaciones")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClientePublicaciones.findAll", query = "SELECT c FROM ClientePublicaciones c"),
    @NamedQuery(name = "ClientePublicaciones.findById", query = "SELECT c FROM ClientePublicaciones c WHERE c.id = :id"),
    @NamedQuery(name = "ClientePublicaciones.findByTitulo", query = "SELECT c FROM ClientePublicaciones c WHERE c.titulo = :titulo"),
    @NamedQuery(name = "ClientePublicaciones.findByDescripcionCorta", query = "SELECT c FROM ClientePublicaciones c WHERE c.descripcionCorta = :descripcionCorta"),
    @NamedQuery(name = "ClientePublicaciones.findByDirImagen", query = "SELECT c FROM ClientePublicaciones c WHERE c.dirImagen = :dirImagen"),
    @NamedQuery(name = "ClientePublicaciones.findByBotonAccion", query = "SELECT c FROM ClientePublicaciones c WHERE c.botonAccion = :botonAccion"),
    @NamedQuery(name = "ClientePublicaciones.findByFechaCreacion", query = "SELECT c FROM ClientePublicaciones c WHERE c.fechaCreacion = :fechaCreacion"),
    @NamedQuery(name = "ClientePublicaciones.findByFechaDesde", query = "SELECT c FROM ClientePublicaciones c WHERE c.fechaDesde = :fechaDesde"),
    @NamedQuery(name = "ClientePublicaciones.findByFechaHasta", query = "SELECT c FROM ClientePublicaciones c WHERE c.fechaHasta = :fechaHasta")})
public class ClientePublicaciones implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "titulo")
    private String titulo;
    @Column(name = "descripcion_corta")
    private String descripcionCorta;
    @Column(name = "dir_imagen")
    private String dirImagen;
    @Column(name = "boton_accion")
    private String botonAccion;
    @Column(name = "fecha_creacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;
    @Basic(optional = false)
    @Column(name = "fecha_desde")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaDesde;
    @Column(name = "fecha_hasta")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHasta;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cliente idCliente;
    @JoinColumn(name = "tipo_publicaciones_id", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private TiposPublicaciones tipoPublicacionesId;
    @JoinColumn(name = "id_estado_publicacion", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private EstadosPublicaciones idEstadoPublicacion;

    public ClientePublicaciones() {
    }

    public ClientePublicaciones(Integer id) {
        this.id = id;
    }

    public ClientePublicaciones(Integer id, Date fechaDesde) {
        this.id = id;
        this.fechaDesde = fechaDesde;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcionCorta() {
        return descripcionCorta;
    }

    public void setDescripcionCorta(String descripcionCorta) {
        this.descripcionCorta = descripcionCorta;
    }

    public String getDirImagen() {
        return dirImagen;
    }

    public void setDirImagen(String dirImagen) {
        this.dirImagen = dirImagen;
    }

    public String getBotonAccion() {
        return botonAccion;
    }

    public void setBotonAccion(String botonAccion) {
        this.botonAccion = botonAccion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    public Date getFechaHasta() {
        return fechaHasta;
    }

    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    public TiposPublicaciones getTipoPublicacionesId() {
        return tipoPublicacionesId;
    }

    public void setTipoPublicacionesId(TiposPublicaciones tipoPublicacionesId) {
        this.tipoPublicacionesId = tipoPublicacionesId;
    }

    public EstadosPublicaciones getIdEstadoPublicacion() {
        return idEstadoPublicacion;
    }

    public void setIdEstadoPublicacion(EstadosPublicaciones idEstadoPublicacion) {
        this.idEstadoPublicacion = idEstadoPublicacion;
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
        if (!(object instanceof ClientePublicaciones)) {
            return false;
        }
        ClientePublicaciones other = (ClientePublicaciones) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.minicubic.infoguiaserver.model.ClientePublicaciones[ id=" + id + " ]";
    }
    
}
