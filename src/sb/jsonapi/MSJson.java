package sb.jsonapi;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MSJson {
	
	private static String _serviceURL = "http://localhost:8000/";
	
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
	
	
	public static JSEquipement getEquipement(String Name){
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
	
	public static boolean updateEquipement(String Name, boolean bool, String value){
		String url = _serviceURL + "Update_EquipmentState/" + Name + "/";
		url += bool ? "System.Boolean/" : "System.Int/"; //TODO
		url += value;
		
		HttpURLConnection httpConnection = null;
		InputStreamReader in = null;
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
}
