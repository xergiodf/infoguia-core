/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minicubic.infoguiacore.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author hectorvillalba
 */
@Data
public class NovedadesDTO {
   
    @Getter
    @Setter
    private Integer id;
    @Getter
    @Setter
    private String titulo;
    @Getter
    @Setter
    private String descripconCorta;
    @Getter
    @Setter
    private String dirImagen;
    @Getter
    @Setter
    private Long idCliente;
    @Getter
    @Setter
    private String nombreCompleto;
    @Getter
    @Setter
    private String tipoPublicacion;

    public NovedadesDTO(Integer id, String titulo, String descripconCorta, String dirImagen, Long idCliente, String nombreCompleto, String tipoPublicacion) {
        this.id = id;
        this.titulo = titulo;
        this.descripconCorta = descripconCorta;
        this.dirImagen = dirImagen;
        this.idCliente = idCliente;
        this.nombreCompleto = nombreCompleto;
        this.tipoPublicacion = tipoPublicacion;
    }
    
    
    
}
