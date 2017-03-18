package com.minicubic.infoguiacore.dto;

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
public class ClienteDto {
    
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String nombreCompleto;
    @Getter
    @Setter
    private String nombreCorto;
    @Getter
    @Setter
    private String nombreSucursal;
    @Getter
    @Setter
    private String direccionFisica;
    @Getter
    @Setter
    private String coordenadas;
    @Getter
    @Setter
    private String horarios;
    @Getter
    @Setter
    private String telefono;
}
