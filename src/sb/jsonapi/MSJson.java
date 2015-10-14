/*
 * @author MATYSIAK Herve
 * @version 1.0
 * Last Update : 2015/10/09
 */
package sb.jsonapi;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * The Class MSJson.
 */
public class MSJson {
	
	/** The _service url. */
	private static String _serviceURL = "http://localhost:8000/";
	
	/**
	 * Get URL content in string.
	 *
	 * @param URL the url
	 * @return the url content
	 */
	private static String getUrlContent(String URL){
		StringBuilder sb = new StringBuilder();
		URLConnection urlConn = null;
		InputStreamReader in = null;
		try {
			URL url = new URL(URL);
			urlConn = url.openConnection();
			if (urlConn != null)
				urlConn.setReadTimeout(60 * 1000);
			if (urlConn != null && urlConn.getInputStream() != null) {
				in = new InputStreamReader(urlConn.getInputStream(),
						Charset.defaultCharset());
				BufferedReader bufferedReader = new BufferedReader(in);
				if (bufferedReader != null) {
					int cp;
					while ((cp = bufferedReader.read()) != -1) {
						sb.append((char) cp);
					}
					bufferedReader.close();
				}
			}
		in.close();
		} catch (Exception e) {
			throw new RuntimeException("Exception while calling URL:"+ URL, e);
		} 
	 
		return sb.toString();
	}
	
	/**
	 * Return a JSEquipement from name.
	 *
	 * @param Name the name
	 * @return the equipment
	 */
	public static JSEquipement getEquipment(String Name){
		String url = _serviceURL + "Get_Equipment/" + Name;
		String json = getUrlContent(url);
		
		JSEquipement jsEquipement = null;
		ObjectMapper mapper = new ObjectMapper();
		try
		{
			jsEquipement =  mapper.readValue(json, JSEquipement.class);
		} catch (IOException e)
		{
		   e.printStackTrace();
		}
		
		return jsEquipement;
	}
	
	/**
	 * Update a Equipment with Name (identifier).
	 *
	 * @param Name the name
	 * @param value the value
	 * @return true, if successful
	 */
	public static boolean updateEquipment(String Name, String value){
		String url = _serviceURL + "Update_EquipmentState/" + Name + "/";
		url += value;
		
		HttpURLConnection httpConnection = null;
		try {
			URL uri = new URL(url);
			httpConnection = (HttpURLConnection) uri.openConnection();
			if (httpConnection != null)
				httpConnection.setReadTimeout(60 * 1000);
			if(httpConnection.getResponseCode() != 200)
				return false;
			else
				return true;
		} catch (Exception e) {
			throw new RuntimeException("Exception while calling URL:"+ url, e);
		}
	}
	
	/**
	 * Add a equipment.
	 *
	 * @param Name the name
	 * @param type the type
	 * @param value the value
	 * @return true, if successful
	 */
	public static boolean addEquipment(String Name, ENetType type, String value){
		String url = _serviceURL + "Add_Equipement/" + Name + "/";
		url += type.toCode() + "/";
		url += value;
		
		HttpURLConnection httpConnection = null;
		try {
			URL uri = new URL(url);
			httpConnection = (HttpURLConnection) uri.openConnection();
			if (httpConnection != null)
				httpConnection.setReadTimeout(60 * 1000);
			if(httpConnection.getResponseCode() != 200)
				return false;
			else
				return true;
		} catch (Exception e) {
			throw new RuntimeException("Exception while calling URL:"+ url, e);
		}
	}
	
	/**
	 * Get Temperature datas from a specified room.
	 * @param roomId
	 * @return
	 */
	public static JSTemperature getTemperatureByRoom(int roomId){
		String url = _serviceURL + "Get_Temperatures/" + roomId;
		String json = getUrlContent(url);
		
		JSTemperature jsTemperature = null;
		ObjectMapper mapper = new ObjectMapper();
		try
		{
			jsTemperature =  mapper.readValue(json, JSTemperature.class);
		} catch (IOException e)
		{
		   e.printStackTrace();
		}
		
		return jsTemperature;
	}
	
	/**
	 * Get SMA State : Auto or Manual
	 * @param roomId
	 * @return
	 */
	public static JSSMAState getSMAStateByRoom(int roomId){
		String url = _serviceURL + "Get_Temperatures/" + roomId;
		String json = getUrlContent(url);
		
		JSSMAState jsSMAState = null;
		ObjectMapper mapper = new ObjectMapper();
		try
		{
			jsSMAState =  mapper.readValue(json, JSSMAState.class);
		} catch (IOException e)
		{
		   e.printStackTrace();
		}
		
		return jsSMAState;
	}
}
