package com.minicubic.infoguiacore.dao;

import com.minicubic.infoguiacore.util.converter.UsuarioConverter;
import com.minicubic.infoguiacore.dto.UsuarioDto;
import com.minicubic.infoguiacore.model.TipoUsuario;
import com.minicubic.infoguiacore.model.Usuario;
import com.minicubic.infoguiacore.util.PersistenceManager;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 *
 * @author xergio
 * @version 1
 */
public class UsuarioDao {

    private final UsuarioConverter converter = new UsuarioConverter();
    private final EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
    private static final Logger LOG = Logger.getLogger("UsuarioService");

    /**
     * Obtiene un usuario en base al ID
     *
     * @param id
     * @return Registro especifico de usuario
     */
    public UsuarioDto getUsuario(Long id) {
        try {
            Usuario usuario = (Usuario) em.find(Usuario.class, id);

            return converter.getUsuarioDto(usuario);
        } catch (NoResultException nre) {
        } catch (Exception ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param usuarioDto
     * @return
     */
    public UsuarioDto saveUsuario(UsuarioDto usuarioDto) {
        try {
            Usuario usuario = converter.getUsuario(usuarioDto);

            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();

            return converter.getUsuarioDto(usuario);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    /**
     * Obtiene una Lista de Tipos de Usuario
     *
     * @return Lista de Tipos de Usuarios
     */
    public List<TipoUsuario> getTiposUsuarios() {
        try {
            return em.createNamedQuery("TipoUsuario.findAll").getResultList();
        } catch (Exception ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param user
     * @param pass
     * @return
     */
    public UsuarioDto getUsuarioByCredentials(String user, String pass) {
        try {

            Usuario usuario = (Usuario) em.createNamedQuery("Usuario.findByUsernameAndPassword")
                    .setParameter("username", user)
                    .setParameter("password", pass)
                    .getSingleResult();

            return converter.getUsuarioDto(usuario);

        } catch (NoResultException nre) {
        } catch (Exception ex) {
            Logger.getLogger(UsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
