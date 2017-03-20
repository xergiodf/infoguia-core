package com.minicubic.infoguiacore.dao;

import java.io.Serializable;
import com.minicubic.infoguiacore.dto.ClienteSucursalDto;
import com.minicubic.infoguiacore.model.ClienteSucursal;
import com.minicubic.infoguiacore.util.PersistenceManager;
import com.minicubic.infoguiacore.util.converter.ClienteSucursalConverter;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

/**
 *
 * @author xergio
 */
public class ClienteSucursalDao implements Serializable{

    private final ClienteSucursalConverter converter = new ClienteSucursalConverter();
    private final EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
    private static final Logger LOG = Logger.getLogger("ClienteSucursalService");
    
    /**
     *
     * @param id
     * @return
     */
    public ClienteSucursalDto getClienteSucursal(Integer id) {
        try {
            ClienteSucursal cliente = (ClienteSucursal) em.createNamedQuery("ClienteSucursal.findById")
                    .setParameter("id", id)
                    .getSingleResult();

            return converter.getClienteSucursalDto(cliente);
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
    public List<ClienteSucursalDto> getClienteSucursales() {
        try {
            List<ClienteSucursal> clientes = em.createNamedQuery("ClienteSucursal.findAll")
                    .getResultList();

            return converter.getClienteSucursalesDto(clientes);
        } catch (NoResultException nre) {
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param clienteSucursalDto
     * @return
     */
    public ClienteSucursalDto saveClienteSucursal(ClienteSucursalDto clienteSucursalDto) {
        try {
            ClienteSucursal cliente = converter.getClienteSucursal(clienteSucursalDto);

            em.getTransaction().begin();
            cliente = em.merge(cliente);
            em.flush();
            em.getTransaction().commit();

            return converter.getClienteSucursalDto(cliente);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            LOG.log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void deleteClienteSucursal(Integer id) {
        try {
            ClienteSucursal cliente = (ClienteSucursal) em.createNamedQuery("ClienteSucursal.findById")
                    .setParameter("id", id)
                    .getSingleResult();

            em.getTransaction().begin();
            em.remove(cliente);
            em.flush();
            em.getTransaction().commit();
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }
}
