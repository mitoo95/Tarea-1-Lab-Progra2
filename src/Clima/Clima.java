/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clima;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import java.util.Scanner;

public class Clima {
    public static String getHTML(String urlToRead) throws Exception {
	StringBuilder result = new StringBuilder();
	URL url = new URL(urlToRead);
	HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	conn.setRequestMethod("GET");
	BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	String line;
	while ((line = rd.readLine()) != null) {
	   result.append(line);
        }
	rd.close();
	return result.toString();
    }
	
    public static void main(String[] args) {
        Scanner rd = new Scanner(System.in);
        
        System.out.println("Ingrese la ciudad que desea: ");
        String ciudad = rd.nextLine();
        
	try {
            String respuesta = getHTML("http://api.openweathermap.org/data/2.5/weather?q="+ciudad+"&appid=c9ff5e589ecea8fcc7a3b2b26e91c190");
            //System.out.println(respuesta);
            
            JSONObject lon = new JSONObject(respuesta);
            double longi = lon.getJSONObject("coord").getDouble("lon");
            System.out.println("La longitud de "+ ciudad +" es: "+ longi);
            
            JSONObject lat = new JSONObject(respuesta);
            double lati = lat.getJSONObject("coord").getDouble("lat");
            System.out.println("La latitud de "+ ciudad +" es: "+ lati);
            
            JSONObject press = new JSONObject(respuesta);
            double pres = press.getJSONObject("main").getDouble("pressure");
            System.out.println("La presion del aire de "+ ciudad +" es: "+ pres);
            
            JSONObject humid = new JSONObject(respuesta);
            double hum = humid.getJSONObject("main").getDouble("humidity");
            System.out.println("La humedad actual de "+ ciudad +" es: "+ hum);
            
            JSONObject country = new JSONObject(respuesta);
            String count = country.getJSONObject("sys").getString("country");
            System.out.println("El pais de "+ ciudad +" es: "+ count);
            
            JSONObject temp = new JSONObject(respuesta);
            double temper = country.getJSONObject("main").getDouble("temp");
            System.out.println("El pais de "+ ciudad +" es: "+ temper);
	}
        catch (Exception e) {
            e.printStackTrace();
	}
    }
}
