/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minicubic.infoguiacore.dto.jdbc;

import java.util.Date;
import java.util.List;
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
public class ClienteDTO  implements Comparable<ClienteDTO>{
    
    @Getter
    @Setter
    private Long id;
    
    @Getter
    @Setter
    private String nombre_completo;
    
    @Getter
    @Setter
    private String nombre_corto;
    
    @Getter
    @Setter
    private String descripcion_completa;
    
    @Getter
    @Setter
    private String descripcion_corta;
    
    @Getter
    @Setter
    private String fecha_alta;
    
    @Getter
    @Setter
    private String fecha_inicio;
    
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
    private String telefonos;
    
    @Getter
    @Setter
    private String horario_atencion;
    @Getter
    @Setter
    private List<SucursalClientesDTO> sucursalClientes;
    
    @Getter
    @Setter
    private String distancia;
    
    @Getter
    @Setter 
   private String duracion;
    
   @Getter
   @Setter 
   private Long valorDistancia;
   
   @Getter
   @Setter
   private String photo_url;
   
   @Getter
   @Setter
   private Long idsucursal;
   
   @Getter
   @Setter
   private String sitio_web;
   
   @Getter
   @Setter 
   private String emails;
   
   @Getter
   @Setter
   private Integer cant_sucursales;
    
    

    @Override
    public int compareTo(ClienteDTO o) {
       int compareDistance = ((ClienteDTO)o).getValorDistancia().intValue();
       return this.valorDistancia.intValue() - compareDistance;
    }
   
}