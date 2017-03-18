package com.minicubic.infoguiacore.util.converter;

import com.minicubic.infoguiacore.dto.ClienteDto;
import com.minicubic.infoguiacore.model.Cliente;

/**
 *
 * @author xergio
 * @version 1
 */
public class ClienteConverter {

    public Cliente getCliente(ClienteDto clienteDto) {
        Cliente cliente = new Cliente();

        return cliente;
    }
    
    public ClienteDto getClienteDto(Cliente cliente) {
        ClienteDto clienteDto = new ClienteDto();
        
        return clienteDto;
    }
}
