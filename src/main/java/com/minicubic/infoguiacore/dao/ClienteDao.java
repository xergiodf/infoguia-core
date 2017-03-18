package com.minicubic.infoguiacore.dao;

<<<<<<< HEAD
<<<<<<< HEAD
import com.minicubic.infoguiacore.dto.ClientesDTO;
import com.minicubic.infoguiacore.dto.NovedadesDTO;
=======
import com.minicubic.infoguiacore.dto.ClienteDto;
import com.minicubic.infoguiacore.dto.NovedadesDto;
>>>>>>> origin/master
import com.minicubic.infoguiacore.model.Cliente;
import com.minicubic.infoguiacore.util.PersistenceManager;
<<<<<<< HEAD
import java.io.Serializable;
=======
import com.minicubic.infoguiacore.util.converter.ClienteConverter;
import java.lang.reflect.InvocationTargetException;
>>>>>>> origin/master
=======
import com.minicubic.infoguiacore.dto.ClienteDto;
import com.minicubic.infoguiacore.dto.NovedadesDto;
import com.minicubic.infoguiacore.model.Cliente;
import com.minicubic.infoguiacore.util.PersistenceManager;
import com.minicubic.infoguiacore.util.converter.ClienteConverter;
import java.lang.reflect.InvocationTargetException;
>>>>>>> 574076d7cdd0fbe62358add52be2e9b562e838f4
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author xergio
 */
public class ClienteDao implements Serializable{

    private final ClienteConverter converter = new ClienteConverter();
    private final EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
    private static final Logger LOG = Logger.getLogger("ClienteService");
<<<<<<< HEAD
    
    public List<Cliente> getClientes(Cliente params) {
        try {
            LOG.info("getClientes... ");
            Query query = em.createNativeQuery("select * from gndb.clientes c where c.nombre_corto  like '" + params.getNombreCorto() + "%'", Cliente.class);
            LOG.log(Level.INFO, "Se encontraron {0} registros", query.getResultList().size());
            return query.getResultList();
        } catch (Exception e) {
            LOG.log(Level.SEVERE, "Error ", e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    
    public List<ClientesDTO> getClientesPorSucursal(ClientesDTO params) {
=======

    /**
     *
     * @param id
     * @return
     */
    public ClienteDto getCliente(Long id) {
        try {
            Cliente cliente = (Cliente) em.createNamedQuery("Cliente.findById")
                    .setParameter("id", id)
                    .getSingleResult();

            return converter.getClienteDto(cliente);
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
    public List<ClienteDto> getClientes() {
        try {
            List<Cliente> clientes = em.createNamedQuery("Cliente.findAll")
                    .getResultList();

            return converter.getClientesDto(clientes);
        } catch (NoResultException nre) {
        } catch (Exception ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @param clienteDto
     * @return
     */
    public List<ClienteDto> getClientes(ClienteDto clienteDto) {
<<<<<<< HEAD
>>>>>>> origin/master
=======
>>>>>>> 574076d7cdd0fbe62358add52be2e9b562e838f4
        try {
            LOG.info("getClientesPorSucursal... ");
            Query query = em.createNativeQuery("SELECT com.minicubic.infoguiacore.dto.ClientesDTO(c.id, c.nombreCompleto, "
                    + "c.nombreCorto, cs.nombreSucursal, cs.direccionFisica, cs.coordenadas, cs.horarios, cs.telefono "
                    + ") FROM ClienteSucursales cs join Cliente c where c.nombre_corto like '" + clienteDto.getNombreCorto() + "%'", ClienteDto.class);
            LOG.log(Level.INFO, "Se encontraron {0} registros", query.getResultList().size());
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     *
     * @param clienteDto
     * @return
     */
    public ClienteDto saveCliente(ClienteDto clienteDto) {
        try {
            Cliente cliente = converter.getCliente(clienteDto);

            em.getTransaction().begin();
            em.merge(cliente);
            em.flush();
            em.getTransaction().commit();

            return converter.getClienteDto(cliente);
        } catch (IllegalAccessException | InvocationTargetException ex) {
            LOG.log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public void deleteCliente(ClienteDto clienteDto) {
        try {
            Cliente cliente = converter.getCliente(clienteDto);

            em.getTransaction().begin();
            em.remove(cliente);
            em.flush();
            em.getTransaction().commit();
        } catch (IllegalAccessException | InvocationTargetException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    public List<NovedadesDto> getNovedades() {
        try {
            LOG.info("getNovedades...");
<<<<<<< HEAD
<<<<<<< HEAD
            return em.createQuery("SELECT new com.minicubic.infoguiacore.dto.NovedadesDTO(cp.id, cp.titulo, cp.descripcionCorta, cp.dirImagen, c.id as idcliente, c.nombreCompleto, "
                                                + "tp.descripcion as tipo_publicacion) FROM ClientePublicaciones \n" +
                                                "cp join cp.idCliente c join cp.tipoPublicacionesId tp ").getResultList();
=======
=======
>>>>>>> 574076d7cdd0fbe62358add52be2e9b562e838f4
            Query query = em.createQuery("SELECT new com.minicubic.infoguiacore.dto.NovedadesDTO(cp.id, cp.titulo, cp.descripcionCorta, cp.dirImagen, c.id as idcliente, c.nombreCompleto, "
                    + "tp.descripcion as tipo_publicacion) FROM ClientePublicaciones \n"
                    + "cp join cp.idCliente c join cp.tipoPublicacionesId tp ", NovedadesDto.class);
            return query.getResultList();
>>>>>>> origin/master
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
