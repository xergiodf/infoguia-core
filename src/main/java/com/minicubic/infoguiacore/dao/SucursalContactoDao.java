package com.minicubic.infoguiacore.dao;

import java.io.Serializable;
import com.minicubic.infoguiacore.dto.SucursalContactoDto;
import com.minicubic.infoguiacore.model.SucursalContacto;
import com.minicubic.infoguiacore.util.PersistenceManager;
import com.minicubic.infoguiacore.util.converter.SucursalContactoConverter;
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
public class SucursalContactoDao implements Serializable{

    private final SucursalContactoConverter converter = new SucursalContactoConverter();
    private final EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
    private static final Logger LOG = Logger.getLogger("SucursalContactoService");
    
    /**
     *
     * @param id
     * @return
     */
    public SucursalContactoDto getSucursalContacto(Integer id) {
        try {
            SucursalContacto cliente = (SucursalContacto) em.createNamedQuery("SucursalContacto.findById")
                    .setParameter("id", id)
                    .getSingleResult();

            return converter.getSucursalContactoDto(cliente);
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
    public List<SucursalContactoDto> getSucursalContactoes() {
        try {
            List<SucursalContacto> clientes = em.createNamedQuery("SucursalContacto.findAll")
                    .getResultList();

            return converter.getSucursalContactosDto(clientes);
        } catch (NoResultException nre) {
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param sucursalContactoDto
     * @return
     */
    public SucursalContactoDto saveSucursalContacto(SucursalContactoDto sucursalContactoDto) {
        try {
            SucursalContacto cliente = converter.getSucursalContacto(sucursalContactoDto);

            em.getTransaction().begin();
            cliente = em.merge(cliente);
            em.flush();
            em.getTransaction().commit();

            return converter.getSucursalContactoDto(cliente);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            LOG.log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void deleteSucursalContacto(Integer id) {
        try {
            SucursalContacto cliente = (SucursalContacto) em.createNamedQuery("SucursalContacto.findById")
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
