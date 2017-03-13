package com.minicubic.infoguiacore.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author hectorvillalba
 */

@Data
public class ClientesDTO {
    
    @Getter
    @Setter
    private Integer id;
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
