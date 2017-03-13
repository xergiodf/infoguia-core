package com.minicubic.infoguiacore.dao;

import com.minicubic.infoguiacore.dto.ClientesDTO;
import com.minicubic.infoguiacore.dto.NovedadesDTO;
import com.minicubic.infoguiacore.dto.Response;
import com.minicubic.infoguiacore.util.PersistenceManager;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author xergio
 */
public class ClienteDao {

    private final EntityManager em = PersistenceManager.INSTANCE.getEntityManager();
    private static final Logger LOG = Logger.getLogger("ClienteService");

    public List<ClientesDTO> getClientes(ClientesDTO params) {
        try {
            LOG.info("getClientesPorSucursal... ");
            Query query = em.createQuery("SELECT com.minicubic.infoguiaserver.dto.ClientesDTO(c.id, c.nombreCompleto, "
                    + "c.nombreCorto, cs.nombreSucursal, cs.direccionFisica, cs.coordenadas, cs.horarios, cs.telefono "
                    + ") FROM ClienteSucursales cs join Cliente c where c.nombre_corto like '" + params.getNombreCorto() + "%'", ClientesDTO.class);
            LOG.log(Level.INFO, "Se encontraron {0} registros", query.getResultList().size());
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public List<NovedadesDTO> getNovedades(){
        try {
            LOG.info("getNovedades...");
            Query query = em.createQuery("SELECT new com.minicubic.infoguiaserver.dto.NovedadesDTO(cp.id, cp.titulo, cp.descripcionCorta, cp.dirImagen, c.id as idcliente, c.nombreCompleto, "
                                                + "tp.descripcion as tipo_publicacion) FROM ClientePublicaciones \n" +
                                                "cp join cp.idCliente c join cp.tipoPublicacionesId tp " , NovedadesDTO.class);
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
