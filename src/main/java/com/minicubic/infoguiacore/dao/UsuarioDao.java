package com.minicubic.infoguiacore.dao;

import com.minicubic.infoguiacore.model.TipoUsuario;
import com.minicubic.infoguiacore.model.Usuario;
import com.minicubic.infoguiacore.util.PersistenceManager;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 *
 * @author xergio
 * @version 1
 */
public class UsuarioDao {

    private final EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
    private static final Logger LOG = Logger.getLogger("UsuarioService");
    
    /**
     * Obtiene un usuario en base al ID
     * @param id
     * @return Registro especifico de usuario
     */
    public Usuario getUsuario(Long id) {
        try {
            return em.find(Usuario.class, id);
        } catch (NoResultException nre) {
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Obtiene una Lista de Tipos de Usuario
     * @return Lista de Tipos de Usuarios
     */
    public List<TipoUsuario> getTiposUsuarios() {
        try {
            return em.createNamedQuery("TipoUsuario.findAll").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
