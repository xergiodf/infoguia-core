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
public class PublicacionClienteDTO {
    
    @Getter
    @Setter
    private Long id;
    
    @Getter
    @Setter
    private Long id_cliente;
    
    @Getter
    @Setter
    private Integer tipo_publicaciones_id;
    
    @Getter
    @Setter
    private Integer id_estado_publicacion;
    
    @Getter
    @Setter
    private String titulo;
    @Getter
    @Setter
    private String descripcion_corta;
    
    @Getter
    @Setter
    private String dir_imagen;
    
    @Getter
    @Setter
    private String boton_accion;
   
    @Getter
    @Setter
    private String url;
}
