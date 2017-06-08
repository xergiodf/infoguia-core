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
public class HorariosDTO {
    
    @Getter
    @Setter
    private Long id;
    
    @Getter
    @Setter
    private String dias;
    
    @Getter
    @Setter
    private String hora_desde;
    
    @Getter
    @Setter
    private String hora_hasta;
    
}
