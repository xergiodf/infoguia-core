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
import java.util.List;
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
        List<ClienteDTO> listaCliente = new ArrayList<>();
        JSONParser parser = new JSONParser();
           String locations = "|";
        for (int i = 0; i < lista.size(); i++) {
            ClienteDTO cdto = (ClienteDTO) lista.get(i);
            if ((i+1)==lista.size()) {
                locations ="";
            }
            if(i>40){
                System.err.println("Sucu: " + cdto.getCoordenadas() + ": " + cdto.getNombre_sucursal());
            }
           String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + 
                   cdto.getCoordenadas().split("\\|")[0] + "," +
                   cdto.getCoordenadas().split("\\|")[1] + 
                   locations +"&destinations="
                    + lat.toString() +"," + lon.toString() + "&key=AIzaSyBMXKKpQIh0lsXE6iuMVY1iPuiQd5fnR4A";
            HttpRequest request2 = HttpRequest.get(url );
            if (request2.code() == 200){
                try {
                    Object obj = parser.parse(request2.body());
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
                                cdto.setDuracion(duration.get("text").toString());
                                cdto.setDistancia(distance.get("text").toString());
                                cdto.setValorDistancia((Long) distance.get("value"));
                            }
                        }
                        System.out.println(locations);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            Long valueDist = (cdto.getValorDistancia()/1000);
            if (valueDist < 5) {
                listaCliente.add(cdto);
            }
        }

        return listaCliente;
    }
    
        public List<SucursalClientesDTO> obtenerDistanciaSucursales(Double lat, Double lon, List<SucursalClientesDTO> lista){
        List<SucursalClientesDTO> listaCliente = new ArrayList<>();
        JSONParser parser = new JSONParser();
           String locations = "|";
        for (int i = 0; i < lista.size(); i++) {

            SucursalClientesDTO cdto = (SucursalClientesDTO) lista.get(i);
            if ((i+1)==lista.size()) {
                locations ="";
            }
           String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + 
                   cdto.getCoordenadas().split("\\|")[0] + "," +
                   cdto.getCoordenadas().split("\\|")[1] + 
                   locations +"&destinations="
                    + lat.toString() +"," + lon.toString() + "&key=AIzaSyBMXKKpQIh0lsXE6iuMVY1iPuiQd5fnR4A";
            HttpRequest request2 = HttpRequest.get(url );
            if (request2.code() == 200){
                try {
                    Object obj = parser.parse(request2.body());
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
                                cdto.setDuracion(duration.get("text").toString());
                                cdto.setDistancia(distance.get("text").toString());
                                cdto.setValorDistancia((Long) distance.get("value"));
                            }
                        }
                        System.out.println(locations);
                } catch (Exception e) {
                    System.out.println(cdto.getNombre_sucursal());
                    e.printStackTrace();
                }
            }
            
            Long valueDist = (cdto.getValorDistancia()/1000);
            if (valueDist < 5) {
                listaCliente.add(cdto);
            }
        }

        return listaCliente;
    }
    
    
}
