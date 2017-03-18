package com.minicubic.infoguiacore.util;

/**
 *
 * @author xergio
 */
public class Constants {
    
    public static final String SECRET_KEY = "INFOGUIA-VILO-SA";
    
    public static final String HOST_NAME_SMTP = "server1.hostipy.com";
    public static final String PUERT0_SMTP = "465";
    public static final String USER_NAME_SMTP = "noreply@enemedu.com";
    public static final String PASS_SMTP = ".NoReply.*";
    
    public static final String VALIDATION_USUARIO_USERNAME_REQUIRED = "Nombre de Usuario es un campo requerido \n";
    public static final String VALIDATION_USUARIO_PASSWORD_REQUIRED = "Contrase\u00f1a es un campo requerido \n";
    public static final String VALIDATION_USUARIO_ESTADOUSUARIO_REQUIRED = "Estado de Usuario es un campo requerido \n";
    public static final String VALIDATION_USUARIO_TIPOUSUARIO_REQUIRED = "Tipo de Usuario es un campo requerido \n";
    
    public static final Integer CODE_ERROR_DEFAULT = 409;
    public static final String STR_ERROR_DEFAULT = "Ocurrio un error al procesar la peticion. Revise el log.";
    
    public static final Integer CODE_SUCCESS_DEFAULT = 200;
    public static final String STR_SUCCESS_USUARIO_REGISTRO = "Usuario creado correctamente";
    
}
