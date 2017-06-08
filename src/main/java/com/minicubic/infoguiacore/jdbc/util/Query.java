package com.minicubic.infoguiacore.jdbc.util;

/**
 *
 * @author Héctor Villalba
 */
public class Query {
    
    public static final String getListClientesByNombreCorto(String params, Long id) {
        String sql =  "select distinct c.*,  \n" +
"			cs.nombre_sucursal, \n" +
"                       cs.direccion_fisica , \n" +
"                       cs.coordenadas, \n" +
"                       cs.telefonos, \n" +
"                       cs.id as idsucursal, \n" +
"                       cs.emails, \n" +     
"                       cs.sitio_web, \n" +   
"                       cs.horario_atencion, \n" +                   
"                    (select distinct ad.URL as photo_url from  infoguiadb.cliente_sucursales  cs1 \n" +
"                    join infoguiadb.archivos_cab ac \n" +
"                    join     infoguiadb.archivos_det ad on ( ad.id_archivo_cab = ac.id ) \n" +
"                    where    ac.tabla_ref = 'cliente_sucursales' \n" +
"                    and        ac.columna_ref = 'id' \n" +
"                    and        ac.id_ref = cs.id \n" +
"                    limit 1) as photo_url,  \n" +
"                    (select count(*) from infoguiadb.cliente_sucursales css where css.id_cliente = c.id) as cant_sucursales \n" +                
"                    from  infoguiadb.clientes c \n" +
"                    left join infoguiadb.cliente_categorias cc on c.id = cc.id_cliente \n" +
"                    left join infoguiadb.categorias ccc on ccc.id = cc.id_categoria \n" +
"                    left join infoguiadb.cliente_sucursales cs on cs.id_cliente = c.id \n" +
"                    where lower(nombre_corto) like lower('"+ params +"') \n" +
"                    or lower(nombre_corto) like lower('%"+params+"') \n" +
"                    or lower(nombre_completo) like lower('%"+params+"')  \n" +
"                    or lower(nombre_completo) like lower('"+params+"') \n" +
"                    or lower(ccc.descripcion) like lower('%" +params+ "') \n" +
"                    or lower(ccc.descripcion) like lower('"+ params+ "%' ) \n";
                if(params.equals("")){
                    sql += " or c.id = " + id;
                }
        return sql;
    }
    
    public static final String getPhotoURL(Integer id){
        String sql = "select distinct ad.URL as photo_url from  infoguiadb.cliente_sucursales  cs \n" +
                    "join infoguiadb.archivos_cab ac \n" +
                    "join     infoguiadb.archivos_det ad on ( ad.id_archivo_cab = ac.id ) \n" +
                    "where    ac.tabla_ref = 'cliente_sucursales' \n" +
                    "and        ac.columna_ref = 'id' \n" +
                    "and        ac.id_ref =" +  id + " \n" + 
                    "limit 1";
        
        return sql;
    }
    
    public static final String getNovedades() {
        String sql = "select cp.id, cp.titulo, cp.descripcion_corta, cp.dir_imagen, c.id as idcliente, c.nombre_completo, \n" +
"                                                tp.descripcion as tipo_publicacion FROM infoguiadb.cliente_publicaciones \n" +
"                                                cp join infoguiadb.clientes c \n" +
"                                                on c.id = cp.id_cliente \n" +
"                                                join infoguiadb.tipos_publicaciones tp \n" +
"                                                on tp.id= cp.id_tipo_publicacion";
        return sql;
    }
    
    
    public static final String getHorarios(Long id){
        String sql = "select shd.* from infoguiadb.cliente_sucursales cs \n" +
                    "join  infoguiadb.sucursal_horarios_cab shc  \n" +
                    "on cs.id = shc.id_cliente_sucursal \n" +
                    "join infoguiadb.sucursal_horarios_det shd \n" +
                    "on shc.id = shd.id_cabecera \n" +
                    "where cs.id_cliente = " + id;
        
        return sql;
    }
    
    public static final String getPublicacionCliente(Integer tipoPublicacionId, Long idCliente){
        String sql = "";
        if(idCliente != null){
            sql =  "select cp.*, ad.URL as url from  infoguiadb.cliente_publicaciones  cp \n" +
                    " join infoguiadb.tipos_publicaciones tp \n" +
                    " on cp.id_tipo_publicacion = tp.id \n" +
                    " join infoguiadb.archivos_cab ac \n" +
                    " join     infoguiadb.archivos_det ad on ( ad.id_archivo_cab = ac.id ) \n" +
                    " where    ac.tabla_ref = 'cliente_publicaciones' \n" +
                    " and        ac.columna_ref = 'id' \n" +
                    " and        ac.id_ref = cp.id \n" +
                    " and tp.id = "  + tipoPublicacionId +  " and cp.id_cliente =  " +idCliente ;
        }else{
            sql = "select cp.*, ad.URL as url from  infoguiadb.cliente_publicaciones  cp \n" +
                    " join infoguiadb.tipos_publicaciones tp \n" +
                    " on cp.id_tipo_publicacion = tp.id \n" +
                    " join infoguiadb.archivos_cab ac \n" +
                    " join     infoguiadb.archivos_det ad on ( ad.id_archivo_cab = ac.id ) \n" +
                    " where    ac.tabla_ref = 'cliente_publicaciones' \n" +
                    " and        ac.columna_ref = 'id' \n" +
                    " and        ac.id_ref = cp.id \n" +
                    " and tp.id ="  + tipoPublicacionId;
        }                
    return sql;
    }
    
    public static final String getSucursalMatriz(Long idCliente){
        String sql = "select * from infoguiadb.cliente_sucursales "
                + " where id_cliente = "+ idCliente +" and nombre_sucursal like 'matriz%' ";
        
        return sql;
    }
    
    public static final String getSucursales(Long idCliente){
        String sql = "";
        if(idCliente != null){
                     sql = "select *, (select  ad.URL as photo_url from  infoguiadb.cliente_sucursales  cs1 \n" +
                    " join infoguiadb.archivos_cab ac \n" +
                    " join     infoguiadb.archivos_det ad on ( ad.id_archivo_cab = ac.id ) \n" +
                    " where    ac.tabla_ref = 'cliente_sucursales' \n" +
                    " and        ac.columna_ref = 'id' \n" +
                    " and        ac.id_ref = cs.id \n" +
                    " limit 1) as photo_url from infoguiadb.cliente_sucursales cs where cs.id_cliente = "+ idCliente;
        }else{
            sql = "select *, (select  ad.URL as photo_url from  infoguiadb.cliente_sucursales  cs1 \n" +
                    " join infoguiadb.archivos_cab ac \n" +
                    " join     infoguiadb.archivos_det ad on ( ad.id_archivo_cab = ac.id ) \n" +
                    " where    ac.tabla_ref = 'cliente_sucursales' \n" +
                    " and        ac.columna_ref = 'id' \n" +
                    " and        ac.id_ref = cs.id \n" +
                    " limit 1) as photo_url from infoguiadb.cliente_sucursales cs \n" +
                    " order by cs.id_cliente";
        }
        return sql;
    }
    
    public static final String getCategorias(String params){
        String sql ="select c.* from  infoguiadb.categorias c \n" +
                    "join infoguiadb.grupo_categorias gc \n" +
                    "on c.id_grupo_categoria = gc.id \n" +
                    "where lower(gc.descripcion) like lower('%"+params+"') \n" +
                    "or  lower(gc.descripcion) like lower('"+params+"%') \n" +
                    "or  lower(gc.descripcion) like lower('"+params+"') ";
        return sql;
    }
    
}
