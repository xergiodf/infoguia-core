package com.minicubic.infoguiacore.dao;

import com.minicubic.infoguiacore.dto.ClientePublicacionDto;
import com.minicubic.infoguiacore.model.ClientePublicacion;
import com.minicubic.infoguiacore.util.PersistenceManager;
import com.minicubic.infoguiacore.util.converter.ClientePublicacionConverter;
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
public class ClientePublicacionDao {
    
    private final ClientePublicacionConverter converter = new ClientePublicacionConverter();
    private final EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
    private static final Logger LOG = Logger.getLogger("ClientePublicacionDao");
    
    /**
     *
     * @param id
     * @return
     */
    public ClientePublicacionDto getClientePublicacion(Integer id) {
        try {
            ClientePublicacion clientePublicacion = (ClientePublicacion) em.createNamedQuery("ClientePublicacion.findById")
                    .setParameter("id", id)
                    .getSingleResult();

            return converter.getClientePublicacionDto(clientePublicacion);
        } catch (NoResultException nre) {
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * 
     * @return 
     */
    public List<ClientePublicacionDto> getClientePublicaciones() {
        try {
            List<ClientePublicacion> clientePublicaciones = em.createNamedQuery("ClientePublicacion.findAll")
                    .getResultList();

            return converter.getClientePublicacionesDto(clientePublicaciones);
        } catch (NoResultException nre) {
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * 
     * @param tipoPublicacionId
     * @return 
     */
    public List<ClientePublicacionDto> getClientePublicacionesByTipoPublicacion(Integer tipoPublicacionId) {
        try {
            List<ClientePublicacion> clientePublicaciones = em.createNamedQuery("ClientePublicacion.findByTipoPublicacion")
                    .setParameter("tipoPublicacionId", tipoPublicacionId)
                    .getResultList();

            return converter.getClientePublicacionesDto(clientePublicaciones);
        } catch (NoResultException nre) {
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * 
     * @param clienteId
     * @return 
     */
    public List<ClientePublicacionDto> getClientePublicacionesByCliente(Long clienteId) {
        try {
            List<ClientePublicacion> clientePublicaciones = em.createNamedQuery("ClientePublicacion.findByCliente")
                    .setParameter("clienteId", clienteId)
                    .getResultList();

            return converter.getClientePublicacionesDto(clientePublicaciones);
        } catch (NoResultException nre) {
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param clientePublicacionDto
     * @return
     */
    public ClientePublicacionDto saveClientePublicacion(ClientePublicacionDto clientePublicacionDto) {
        try {
            ClientePublicacion clientePublicacion = converter.getClientePublicacion(clientePublicacionDto);

            em.getTransaction().begin();
            clientePublicacion = em.merge(clientePublicacion);
            em.flush();
            em.getTransaction().commit();

            return converter.getClientePublicacionDto(clientePublicacion);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            LOG.log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void deleteClientePublicacion(Integer id) {
        try {
            ClientePublicacion clientePublicacion = (ClientePublicacion) em.createNamedQuery("ClientePublicacion.findById")
                    .setParameter("id", id)
                    .getSingleResult();

            em.getTransaction().begin();
            em.remove(clientePublicacion);
            em.flush();
            em.getTransaction().commit();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

}