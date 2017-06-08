/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.minicubic.infoguiacore.jdbc.util;

import java.util.ArrayList;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author hectorvillalba
 */
public class Test {
    
    
    public static void main(String[] args) {
        JSONParser parser = new JSONParser();
        Double lat, lon, lat1, lon1, lat2, lon2;
        lat = -25.4059731;
        lon = -57.3639534;
        lat1 = -25.4059731;
        lon1 = -57.3639534;
        lat2 = -27.2961084;
        lon2 = -55.9672757;
        
         String locations = "";
        String url = "https://maps.googleapis.com/maps/api/distancematrix/json?origins=" + lat1.toString() +","+ lon1.toString() +"&destinations="
                    + lat.toString() +"," + lon.toString() + "&key=AIzaSyBMXKKpQIh0lsXE6iuMVY1iPuiQd5fnR4A";
            HttpRequest request2 = HttpRequest.get(url );
            if (request2.code() == 200){
                try {
                    Object obj = parser.parse(request2.body());
                    System.out.println(obj);
                    JSONObject array = (JSONObject)obj;  
                   
                        JSONArray array2 = (JSONArray) array.get("rows");
                        for (int i = 0;i<array2.size(); i++){
                            JSONObject element = (JSONObject)array2.get(i); 
                            JSONArray filas = (JSONArray)element.get("elements");
                            for (Object fila : filas) {
                                JSONObject item = (JSONObject) fila;
                                Object loca = item.get("duration");
                                JSONObject duration  = (JSONObject) loca;
                                Object dist = item.get("distance");
                                JSONObject distance  = (JSONObject) dist;
                                System.out.println("Duration: " + duration.get("text").toString()); ;
                                System.out.println("Distance: "+ distance.get("text").toString());
                            }
                        }
                        System.out.println(locations);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
    }
    
}
