package com.minicubic.infoguiacore.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author hectorvillalba
 */

@Data
public class Request<T> {
    
    @Getter
    @Setter
    private String type;
    
    @Getter
    @Setter
    private T data;
}
