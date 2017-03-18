package com.minicubic.infoguiacore.model;

import java.io.Serializable;
import java.util.Collection;
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
 * @author xergio
 */
@Entity
@Table(name = "cliente_categorias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClienteCategoria.findAll", query = "SELECT c FROM ClienteCategoria c"),
    @NamedQuery(name = "ClienteCategoria.findById", query = "SELECT c FROM ClienteCategoria c WHERE c.id = :id"),
    @NamedQuery(name = "ClienteCategoria.findByCategorias", query = "SELECT c FROM ClienteCategoria c WHERE c.categorias = :categorias")})
public class ClienteCategoria implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "categorias")
    private String categorias;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "clienteCategoria")
    private Collection<Cliente> clienteCollection;

    public ClienteCategoria() {
    }

    public ClienteCategoria(Integer id) {
        this.id = id;
    }

    public ClienteCategoria(Integer id, String categorias) {
        this.id = id;
        this.categorias = categorias;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCategorias() {
        return categorias;
    }

    public void setCategorias(String categorias) {
        this.categorias = categorias;
    }

    @XmlTransient
    public Collection<Cliente> getClienteCollection() {
        return clienteCollection;
    }

    public void setClienteCollection(Collection<Cliente> clienteCollection) {
        this.clienteCollection = clienteCollection;
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
        if (!(object instanceof ClienteCategoria)) {
            return false;
        }
        ClienteCategoria other = (ClienteCategoria) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.minicubic.infoguiaserver.model.ClienteCategoria[ id=" + id + " ]";
    }
    
}
