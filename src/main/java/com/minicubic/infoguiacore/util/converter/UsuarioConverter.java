package com.minicubic.infoguiacore.util.converter;

import com.minicubic.infoguiacore.dto.UsuarioDto;
import com.minicubic.infoguiacore.model.Usuario;
import java.lang.reflect.InvocationTargetException;
import org.apache.commons.beanutils.PropertyUtilsBean;

/**
 *
 * @author xergio
 * @version 1
 */
public class UsuarioConverter {

    private final PropertyUtilsBean beanUtil = new PropertyUtilsBean();

    public Usuario getUsuario(UsuarioDto usuarioDto) throws IllegalAccessException, InvocationTargetException {
        Usuario usuario = new Usuario();

        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(usuario, usuarioDto);
        } catch (NoSuchMethodException ex) {
            // No importa
        }

        // Cargamos las propiedas compuestas
        if (usuarioDto.getClienteDto() != null)       
            usuario.setCliente(new ClienteConverter().getCliente(usuarioDto.getClienteDto()));
        
        if (usuarioDto.getTipoUsuarioDto() != null)
            usuario.setTipoUsuario(new TipoUsuarioConverter().getTipoUsuario(usuarioDto.getTipoUsuarioDto()));
        
        if ( usuarioDto.getUsuarioEstadoDto() != null ) {
            usuario.setUsuarioEstado(new EstadoUsuarioConverter().getEstadoUsuario(usuarioDto.getUsuarioEstadoDto()));
        }

        return usuario;
    }
    
    public UsuarioDto getUsuarioDto(Usuario usuario) throws IllegalAccessException, InvocationTargetException {
        UsuarioDto usuarioDto = new UsuarioDto();

        // Copiamos las propiedades simples
        try {
            beanUtil.copyProperties(usuarioDto, usuario);
        } catch (NoSuchMethodException ex) {
            // No importa
        }

        // Cargamos las propiedas compuestas
        if (usuario.getCliente() != null)       
            usuarioDto.setClienteDto(new ClienteConverter().getClienteDto(usuario.getCliente()));
        
        if (usuario.getTipoUsuario() != null)
            usuarioDto.setTipoUsuarioDto(new TipoUsuarioConverter().getTipoUsuarioDto(usuario.getTipoUsuario()));
        
        if ( usuario.getUsuarioEstado() != null ) {
            usuarioDto.setUsuarioEstadoDto(new EstadoUsuarioConverter().getEstadoUsuarioDto(usuario.getUsuarioEstado()));
        }
        
        // Por cuestion de seguridad, no enviamos el password
        usuarioDto.setPassword(null);

        return usuarioDto;
    }
}
