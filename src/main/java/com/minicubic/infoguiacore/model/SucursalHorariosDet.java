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
import javax.persistence.Lob;
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
@Table(name = "sucursal_horarios_det")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SucursalHorariosDet.findAll", query = "SELECT s FROM SucursalHorariosDet s"),
    @NamedQuery(name = "SucursalHorariosDet.findById", query = "SELECT s FROM SucursalHorariosDet s WHERE s.id = :id"),
    @NamedQuery(name = "SucursalHorariosDet.findByHoraDesde", query = "SELECT s FROM SucursalHorariosDet s WHERE s.horaDesde = :horaDesde"),
    @NamedQuery(name = "SucursalHorariosDet.findByHoraHasta", query = "SELECT s FROM SucursalHorariosDet s WHERE s.horaHasta = :horaHasta")})
public class SucursalHorariosDet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Lob
    @Column(name = "dias")
    private String dias;
    @Basic(optional = false)
    @Column(name = "hora_desde")
    private String horaDesde;
    @Basic(optional = false)
    @Column(name = "hora_hasta")
    private String horaHasta;
    @JoinColumn(name = "id_cabecera", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SucursalHorariosCab idCabecera;

    public SucursalHorariosDet() {
    }

    public SucursalHorariosDet(Integer id) {
        this.id = id;
    }

    public SucursalHorariosDet(Integer id, String dias, String horaDesde, String horaHasta) {
        this.id = id;
        this.dias = dias;
        this.horaDesde = horaDesde;
        this.horaHasta = horaHasta;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDias() {
        return dias;
    }

    public void setDias(String dias) {
        this.dias = dias;
    }

    public String getHoraDesde() {
        return horaDesde;
    }

    public void setHoraDesde(String horaDesde) {
        this.horaDesde = horaDesde;
    }

    public String getHoraHasta() {
        return horaHasta;
    }

    public void setHoraHasta(String horaHasta) {
        this.horaHasta = horaHasta;
    }

    public SucursalHorariosCab getIdCabecera() {
        return idCabecera;
    }

    public void setIdCabecera(SucursalHorariosCab idCabecera) {
        this.idCabecera = idCabecera;
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
        if (!(object instanceof SucursalHorariosDet)) {
            return false;
        }
        SucursalHorariosDet other = (SucursalHorariosDet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.minicubic.infoguiaserver.model.SucursalHorariosDet[ id=" + id + " ]";
    }
    
}
