/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minicubic.infoguiacore.dto.jdbc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author hectorvillalba
 */

@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class SucursalClientesDTO implements Comparable<SucursalClientesDTO>{
    
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String nombre_sucursal;
    
    @Getter
    @Setter
    private String direccion_fisica;
    
    @Getter
    @Setter
    private String coordenadas;
    
    @Getter
    @Setter
    private Long id_cliente;
    
    @Getter
    @Setter
    private String horarios;
    
    @Getter
    @Setter
    private String telefonos;
    @Getter
    @Setter
    private String telefono2;
    @Getter
    @Setter
    private String telefono3;
    
    @Getter
    @Setter
    private Double lat;
    
    @Getter
    @Setter 
    private Double lon;
    
    @Getter
    @Setter
    private String duracion;
   
    @Getter
    @Setter
    private String distancia;
    
    
    @Getter
    @Setter
    private Long valorDistancia;
    
    @Getter
    @Setter
    private String photo_url;

    @Override
    public int compareTo(SucursalClientesDTO o) {
        int comparable = ((SucursalClientesDTO)o).getValorDistancia().intValue();
        return  this.valorDistancia.intValue() - comparable;
   }
    
    
}
