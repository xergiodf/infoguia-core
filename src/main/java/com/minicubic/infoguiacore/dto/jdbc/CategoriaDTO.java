/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minicubic.infoguiacore.dto.jdbc;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author hectorvillalba
 */
public class CategoriaDTO {
    
    @Getter
    @Setter
    private Long id;
    
    @Getter
    @Setter
    private String descripcion;
    
}
