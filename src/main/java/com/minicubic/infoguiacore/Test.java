package com.minicubic.infoguiacore;

import com.minicubic.infoguiacore.dao.UsuarioDao;
import com.minicubic.infoguiacore.dto.UsuarioDto;
import com.minicubic.infoguiacore.util.PasswordService;

/**
 *
 * @author sedf
 * @version 1
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        PasswordService ps = new PasswordService();
        String encryptedPassword = ps.encrypt("password");
        
        UsuarioDao service = new UsuarioDao();

        UsuarioDto usuarioDto = service.getUsuarioByCredentials("sfernandez", encryptedPassword);
        
        System.out.println(usuarioDto);
    }
}
