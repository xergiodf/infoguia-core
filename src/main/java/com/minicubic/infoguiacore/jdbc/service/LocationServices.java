/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minicubic.infoguiacore.jdbc.service;

import com.minicubic.infoguiacore.dto.jdbc.ClienteDTO;
import com.minicubic.infoguiacore.dto.jdbc.SucursalClientesDTO;
import com.minicubic.infoguiacore.jdbc.util.HttpRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import lombok.extern.log4j.Log4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author hectorvillalba
 */
public class LocationServices {
    
    
    private static LocationServices instance = new LocationServices();
    private LocationServices(){

    }
    public static LocationServices getInstance(){
        return instance;
    }
    
    public List<ClienteDTO> obtenerDistancia(Double lat, Double lon, List<ClienteDTO> lista){
        String coordenadas = "";
        return lista;
    }
    
    private List<ClienteDTO> calcularDistancia(List<ClienteDTO> listaClientes, int pisicion ){
            String locations = "|";
            String url = "";
            String coordenadas = "";
            JSONParser parser = new JSONParser();
                for (int i = 0; i < listaClientes.size(); i++) {
            ClienteDTO cdto = (ClienteDTO) listaClientes.get(i);
            try {
                if(i ==0){
                    coordenadas += cdto.getCoordenadas().split("\\|")[0] + "," +cdto.getCoordenadas().split("\\|")[1];
                }else {                      
                    coordenadas += locations + cdto.getCoordenadas().split("\\|")[0] + "," +cdto.getCoordenadas().split("\\|")[1];
                    if(i== 50)
                        break;
                }
            } catch (Exception e) {
                System.err.println("Error coordenadas " + cdto.getCoordenadas());
                System.err.println("Error " + e.getMessage());
            }

            
        }
        HttpRequest request2 = null;
        try {
                   url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + lat.toString() +"," + lon.toString() + "&destinations="
                    + coordenadas + "&key=AIzaSyBMXKKpQIh0lsXE6iuMVY1iPuiQd5fnR4A";
            ClienteDTO cdto  = null; 
            Thread.sleep(1000);
            request2 = HttpRequest.get(url );
        } catch (Exception e) {
            e.printStackTrace();
        }

            if (request2.code() == 200){
                try {
                    Object obj = parser.parse(request2.body());
                    System.out.println(obj);
                    if (obj.toString().contains("OVER_QUERY_LIMIT") || obj.toString().contains("NOT_FOUND") ||
                          obj.toString().contains("ZERO_RESULTS")  ) {
                        System.out.println("ERROR: " + "OVER_QUERY_LIMIT | ZERO_RESULTS | NOT_FOUND");
                        return listaClientes;
                    }
                    JSONObject array = (JSONObject)obj;  
                   
                        JSONArray array2 = (JSONArray) array.get("rows");
                        for (int j = 0;j<array2.size(); j++){
                            try {
                                JSONObject element = (JSONObject)array2.get(j); 
                                JSONArray filas = (JSONArray)element.get("elements");
                                int k = 0;
                                for (Object fila : filas) {
                                    JSONObject item = (JSONObject) fila;
                                    Object loca = item.get("duration");
                                    JSONObject duration  = (JSONObject) loca;
                                    Object dist = item.get("distance");
                                    JSONObject distance  = (JSONObject) dist;
                                    listaClientes.get(k).setDuracion(duration.get("text").toString());
                                    listaClientes.get(k).setDistancia(distance.get("text").toString());
                                    listaClientes.get(k).setValorDistancia((Long) distance.get("value"));
                                    k++;
                                }
                                //Long valueDist = (cdto.getValorDistancia()/1000);
                                //    listaCliente.add(cdto); 
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        Collections.sort(listaClientes);
    }
    
    public List<SucursalClientesDTO> obtenerDistanciaSucursales(Double lat, Double lon, List<SucursalClientesDTO> lista){
        List<SucursalClientesDTO> listaCliente = new ArrayList<>();
        JSONParser parser = new JSONParser();
        String url = "";
        String coordenadas = "";
           String locations = "|";
        for (int i = 0; i < lista.size(); i++) {
            SucursalClientesDTO cdto = (SucursalClientesDTO) lista.get(i);
            if ((i+1)==lista.size()) {
                locations ="";
            }
            if(i ==0){
                coordenadas += cdto.getCoordenadas().split("\\|")[0] + "," +cdto.getCoordenadas().split("\\|")[1];
            }else {
                coordenadas += locations + cdto.getCoordenadas().split("\\|")[0] + "," +cdto.getCoordenadas().split("\\|")[1];
            }
        }
        url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + lat.toString() +"," + lon.toString() + "&destinations="
                    + coordenadas + "&key=AIzaSyBMXKKpQIh0lsXE6iuMVY1iPuiQd5fnR4A";
        HttpRequest request2 = null;
        try {
            Thread.sleep(1000);
            request2 = HttpRequest.get(url );
        } catch (Exception e) {
            e.printStackTrace();
        }

            if (request2.code() == 200){
                try {
                    Object obj = parser.parse(request2.body());
                    if (obj.toString().contains("OVER_QUERY_LIMIT") || obj.toString().contains("NOT_FOUND") ||
                          obj.toString().contains("ZERO_RESULTS")  ) { 
                        System.out.println("ERROR: " + "OVER_QUERY_LIMIT | ZERO_RESULTS | NOT_FOUND");
                        Thread.sleep(2000);
                        return lista;
                    }
                    System.out.println(obj);
                    JSONObject array = (JSONObject)obj;  
                        JSONArray array2 = (JSONArray) array.get("rows");
                        for (int j = 0;j<array2.size(); j++){
                            JSONObject element = (JSONObject)array2.get(j); 
                            JSONArray filas = (JSONArray)element.get("elements");
                            for (Object fila : filas) {
                                JSONObject item = (JSONObject) fila;
                                Object loca = item.get("duration");
                                JSONObject duration  = (JSONObject) loca;
                                Object dist = item.get("distance");
                                JSONObject distance  = (JSONObject) dist;
                                lista.get(j).setDuracion(duration.get("text").toString());
                                lista.get(j).setDistancia(distance.get("text").toString());
                                lista.get(j).setValorDistancia((Long) distance.get("value"));
                            }
                            //Long valueDist = (cdto.getValorDistancia()/1000);
                            //if (valueDist < 5) {
                            //    listaCliente.add(cdto);
                            //}
                        }
                        System.out.println(locations);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        Collections.sort(lista);
        return lista;
    }
    
    
}
