package com.minicubic.infoguiacore.util;

import com.minicubic.infoguiacore.dto.ClienteDto;
import com.minicubic.infoguiacore.dto.Response;
import com.minicubic.infoguiacore.dto.UsuarioDto;
import com.minicubic.infoguiacore.dto.ValidatorResponse;

/**
 *
 * @author xergio
 * @version 1
 */
public class Validator {
    private static Validator INSTANCE = null;
    
    private Validator(){}
    
    public static Validator getInstance() {
        if ( Util.isEmpty(INSTANCE) ) {
            INSTANCE = new Validator();
        }
        return INSTANCE;
    }

    /**
     * 
     * @param usuarioDto
     * @return 
     */
    public ValidatorResponse<Boolean> validateAddUsuario(UsuarioDto usuarioDto) {
        ValidatorResponse<Boolean> response = new ValidatorResponse<>();
        response.setData(new Boolean(true));
        
        if ( Util.isEmpty(usuarioDto.getUsername()) ) {
            response.setData(new Boolean(false));
            response.setMensaje(response.getMensaje().concat(Constants.VALIDATION_USUARIO_USERNAME_REQUIRED));
        }
        
        if ( Util.isEmpty(usuarioDto.getPassword()) ) {
            response.setData(new Boolean(false));
            response.setMensaje(response.getMensaje().concat(Constants.VALIDATION_USUARIO_PASSWORD_REQUIRED));
        }
        
        if ( Util.isEmpty(usuarioDto.getUsuarioEstadoDto())) {
            response.setData(new Boolean(false));
            response.setMensaje(response.getMensaje().concat(Constants.VALIDATION_USUARIO_ESTADOUSUARIO_REQUIRED));
        }
        
        if ( Util.isEmpty(usuarioDto.getTipoUsuarioDto()) ) {
            response.setData(new Boolean(false));
            response.setMensaje(response.getMensaje().concat(Constants.VALIDATION_USUARIO_ESTADOUSUARIO_REQUIRED));
        }
        
        return response;
    }
    
    /**
     * 
     * @param clienteDto
     * @return 
     */
    public ValidatorResponse<Boolean> validateAddCliente(ClienteDto clienteDto) {
        ValidatorResponse<Boolean> response = new ValidatorResponse<>();
        response.setData(new Boolean(true));
        
        if ( Util.isEmpty(clienteDto.getNombreCompleto()) ) {
            response.setData(new Boolean(false));
            response.setMensaje(response.getMensaje().concat(Constants.VALIDATION_CLIENTE_NOMBRECOMPLETO_REQUIRED));
        }
        
        return response;
    }    
}
