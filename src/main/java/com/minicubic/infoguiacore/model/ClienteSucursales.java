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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "cliente_sucursales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClienteSucursales.findAll", query = "SELECT c FROM ClienteSucursales c"),
    @NamedQuery(name = "ClienteSucursales.findById", query = "SELECT c FROM ClienteSucursales c WHERE c.id = :id"),
    @NamedQuery(name = "ClienteSucursales.findByNombreSucursal", query = "SELECT c FROM ClienteSucursales c WHERE c.nombreSucursal = :nombreSucursal"),
    @NamedQuery(name = "ClienteSucursales.findByDireccionFisica", query = "SELECT c FROM ClienteSucursales c WHERE c.direccionFisica = :direccionFisica"),
    @NamedQuery(name = "ClienteSucursales.findByCoordenadas", query = "SELECT c FROM ClienteSucursales c WHERE c.coordenadas = :coordenadas"),
    @NamedQuery(name = "ClienteSucursales.findByHorarios", query = "SELECT c FROM ClienteSucursales c WHERE c.horarios = :horarios"),
    @NamedQuery(name = "ClienteSucursales.findByTelefono", query = "SELECT c FROM ClienteSucursales c WHERE c.telefono = :telefono"),
    @NamedQuery(name = "ClienteSucursales.findByTelefono2", query = "SELECT c FROM ClienteSucursales c WHERE c.telefono2 = :telefono2"),
    @NamedQuery(name = "ClienteSucursales.findByTelefono3", query = "SELECT c FROM ClienteSucursales c WHERE c.telefono3 = :telefono3")})
public class ClienteSucursales implements Serializable {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idClienteSucursal", fetch = FetchType.LAZY)
    private List<SucursalContactos> sucursalContactosList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idClienteSucursal", fetch = FetchType.LAZY)
    private List<SucursalValoracionCab> sucursalValoracionCabList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idClienteSucursal", fetch = FetchType.LAZY)
    private List<SucursalHorariosCab> sucursalHorariosCabList;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "nombre_sucursal")
    private String nombreSucursal;
    @Basic(optional = false)
    @Column(name = "direccion_fisica")
    private String direccionFisica;
    @Basic(optional = false)
    @Column(name = "coordenadas")
    private String coordenadas;
    @Column(name = "horarios")
    private String horarios;
    @Column(name = "telefono")
    private String telefono;
    @Column(name = "telefono2")
    private String telefono2;
    @Column(name = "telefono3")
    private String telefono3;
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Cliente idCliente;

    public ClienteSucursales() {
    }

    public ClienteSucursales(Integer id) {
        this.id = id;
    }

    public ClienteSucursales(Integer id, String direccionFisica, String coordenadas) {
        this.id = id;
        this.direccionFisica = direccionFisica;
        this.coordenadas = coordenadas;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombreSucursal() {
        return nombreSucursal;
    }

    public void setNombreSucursal(String nombreSucursal) {
        this.nombreSucursal = nombreSucursal;
    }

    public String getDireccionFisica() {
        return direccionFisica;
    }

    public void setDireccionFisica(String direccionFisica) {
        this.direccionFisica = direccionFisica;
    }

    public String getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(String coordenadas) {
        this.coordenadas = coordenadas;
    }

    public String getHorarios() {
        return horarios;
    }

    public void setHorarios(String horarios) {
        this.horarios = horarios;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public String getTelefono3() {
        return telefono3;
    }

    public void setTelefono3(String telefono3) {
        this.telefono3 = telefono3;
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
        if (!(object instanceof ClienteSucursales)) {
            return false;
        }
        ClienteSucursales other = (ClienteSucursales) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.minicubic.infoguiaserver.model.ClienteSucursales[ id=" + id + " ]";
    }

    @XmlTransient
    public List<SucursalContactos> getSucursalContactosList() {
        return sucursalContactosList;
    }

    public void setSucursalContactosList(List<SucursalContactos> sucursalContactosList) {
        this.sucursalContactosList = sucursalContactosList;
    }

    @XmlTransient
    public List<SucursalValoracionCab> getSucursalValoracionCabList() {
        return sucursalValoracionCabList;
    }

    public void setSucursalValoracionCabList(List<SucursalValoracionCab> sucursalValoracionCabList) {
        this.sucursalValoracionCabList = sucursalValoracionCabList;
    }

    public Cliente getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Cliente idCliente) {
        this.idCliente = idCliente;
    }

    @XmlTransient
    public List<SucursalHorariosCab> getSucursalHorariosCabList() {
        return sucursalHorariosCabList;
    }

    public void setSucursalHorariosCabList(List<SucursalHorariosCab> sucursalHorariosCabList) {
        this.sucursalHorariosCabList = sucursalHorariosCabList;
    }
    
}
