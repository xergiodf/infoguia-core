/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minicubic.infoguiacore.dao.jdbc;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.minicubic.infoguiacore.dto.ClienteDto;
import com.minicubic.infoguiacore.dto.NovedadesDto;
import com.minicubic.infoguiacore.dto.Request;
import com.minicubic.infoguiacore.dto.Response;
import com.minicubic.infoguiacore.dto.jdbc.CategoriaDTO;
import com.minicubic.infoguiacore.dto.jdbc.ClienteDTO;
import com.minicubic.infoguiacore.dto.jdbc.HorariosDTO;
import com.minicubic.infoguiacore.dto.jdbc.PublicacionClienteDTO;
import com.minicubic.infoguiacore.dto.jdbc.SucursalClientesDTO;
import com.minicubic.infoguiacore.jdbc.service.LocationServices;
import com.minicubic.infoguiacore.jdbc.util.DBUtil;
import com.minicubic.infoguiacore.jdbc.util.Query;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author hectorvillalba
 */
public class ClienteDAOJDBC implements Serializable {
    private final DBUtil dbUtil;
    Gson gson = new GsonBuilder().create();
    LocationServices location = LocationServices.getInstance();
    static final Logger LOG = Logger.getLogger("UserService");
    Type listType = new TypeToken<Request<ClienteDTO>>() {
                }.getType();
                
    public ClienteDAOJDBC(){
        dbUtil = new DBUtil();
    }
    
    public List<ClienteDTO> getClientePorNombreCorto(String  nombre) {
        JSONParser parser = new JSONParser();
        Request<ClienteDTO> request = gson.fromJson(nombre, listType);
        List<ClienteDTO> lista = new ArrayList<>();
        Double lat = null;
        Double lon = null;
        
        try {
            lat = Double.parseDouble(request.getData().getCoordenadas().split("\\|")[0]);
            lon = Double.parseDouble(request.getData().getCoordenadas().split("\\|")[1]);
            String json = dbUtil.resultSetToJson(Query.getListClientesByNombreCorto(request.getData().getNombre_corto(), request.getData().getId()), null);
            lista = gson.fromJson(json, new TypeToken<List<ClienteDTO>>() {}.getType());
            lista = location.obtenerDistancia(lat, lon, lista);
            
            LOG.info("success...");
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(ClienteDAOJDBC.class.getName()).log(Level.SEVERE, null, ex);
            return lista;
        }
    }
    
    public List<PublicacionClienteDTO> getPublicacionCliente(String tipoPublicacionId){
            Type listType = new TypeToken<Request<PublicacionClienteDTO>>() {}.getType();
            Request<PublicacionClienteDTO> request = gson.fromJson(tipoPublicacionId, listType);
        List<PublicacionClienteDTO> lista = new ArrayList<>();
        try {
            String json = dbUtil.resultSetToJson(Query.getPublicacionCliente(request.getData().getTipo_publicaciones_id(), 
                    request.getData().getId_cliente()), null);
            lista = gson.fromJson(json, new TypeToken<List<PublicacionClienteDTO>>() {}.getType());
            LOG.info("success...");
            return lista;
        } catch (Exception ex) {
            Logger.getLogger(ClienteDAOJDBC.class.getName()).log(Level.SEVERE, null, ex);
            return lista;
        }
    }
    
        
    public Response<List<NovedadesDto>> getNovedades() {
        Response<List<NovedadesDto>> response = new Response<>();
        try {
            String json = dbUtil.resultSetToJson(Query.getNovedades(), null);

            List<NovedadesDto> lista = gson.fromJson(json, new TypeToken<List<NovedadesDto>>() {
            }.getType());
            response.setCodigo(200);
            response.setData(lista);
            response.setMensaje("Success");
            LOG.info("success...");
            return response;
        } catch (Exception ex) {
            Logger.getLogger(ClienteDAOJDBC.class.getName()).log(Level.SEVERE, null, ex);
            response.setCodigo(600);
            response.setMensaje(ex.getMessage());
            return response;
        }
    }
    
    
    public List<SucursalClientesDTO> getSucursalMatriz(Long idCliente){
        try {
            String json = dbUtil.resultSetToJson(Query.getSucursalMatriz(idCliente), null);
            List<SucursalClientesDTO> lista = gson.fromJson(json, new TypeToken<List<SucursalClientesDTO>>() {
            }.getType());
            List<SucursalClientesDTO> lista2 = new ArrayList<>();
            for(SucursalClientesDTO listaSucu : lista){
                double lat = Double.parseDouble(listaSucu.getCoordenadas().split("\\|")[0]);
                double lon = Double.parseDouble(listaSucu.getCoordenadas().split("\\|")[1]);
                listaSucu.setLat(lat);
                listaSucu.setLon(lon);
                lista2.add(listaSucu);
            }
            return lista2;
        } catch (Exception e) {
            Logger.getLogger(ClienteDAOJDBC.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }

    public List<SucursalClientesDTO> getSucursales(String idCliente){
        try {
            Type listType2 = new TypeToken<Request<SucursalClientesDTO>>() {}.getType();
            Request<SucursalClientesDTO> request = gson.fromJson(idCliente, listType2);
            String json = dbUtil.resultSetToJson(Query.getSucursales(request.getData().getId_cliente()), null);
            List<SucursalClientesDTO> lista = gson.fromJson(json, new TypeToken<List<SucursalClientesDTO>>() {}.getType());
                        List<SucursalClientesDTO> lista2 = new ArrayList<>();
            if(request.getData().getCoordenadas().equals("")){
                lista2 = lista;     
            }else{
                Double latitud = Double.parseDouble(request.getData().getCoordenadas().split("\\|")[0]);
                Double longitud = Double.parseDouble(request.getData().getCoordenadas().split("\\|")[1]);
                try {
                    lista2 = location.obtenerDistanciaSucursales(latitud, longitud, lista); 
                } catch (Exception e) {
                    Logger.getLogger(ClienteDAOJDBC.class.getName()).log(Level.SEVERE, null, e);
                    return lista;
                }
            }
                               
            
            return lista2;
        } catch (Exception e) {
            Logger.getLogger(ClienteDAOJDBC.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
    
    public List<CategoriaDTO> getCategorias(String parametro){
        try {
            Type listTypeCategoria = new TypeToken<Request<CategoriaDTO>>() {}.getType();
            Request<CategoriaDTO> request = gson.fromJson(parametro, listTypeCategoria);
            String json = dbUtil.resultSetToJson(Query.getCategorias(request.getData().getDescripcion()), null);
            List<CategoriaDTO> lista = gson.fromJson(json, new TypeToken<List<CategoriaDTO>>() {}.getType());
            return lista;
        } catch (Exception e) {
            Logger.getLogger(ClienteDAOJDBC.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
}
