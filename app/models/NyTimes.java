package models;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class NyTimes {
	private static String url = "http://api.nytimes.com/svc/mostpopular/v2/mostviewed/all-sections/7.json?api-key=db76a9121152ad686b5d997b712c7400:6:64948807";
	
	private static String apiKey="";
	
	private String responseData = null;
	/**
	 * Fetches the Json Data of the NyTimes.
	 * @return
	 */
	public void fetchNyTimesData(){
		JsonElement jse = null;
		//url = url + apiKey;
		try{
		   URL source = new URL(url);
		   BufferedReader in = new BufferedReader(new InputStreamReader(source.openStream(), "UTF-8"));
		   jse = new JsonParser().parse(in);
		   in.close();
		}catch(Exception e){
			System.out.println("Exception thrown "+e);
		}
		responseData = jse.toString();
	}
	
	public String getResponseData(){
		return responseData;
	}
}
