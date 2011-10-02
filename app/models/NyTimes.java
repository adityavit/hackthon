package models;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class NyTimes {
	private static String url = "http://api.nytimes.com/svc/mostpopular/v2/mostviewed/all-sections/7.json?api-key=db76a9121152ad686b5d997b712c7400:6:64948807";
	
	private static String apiKey="";
	
	//private String responseData = null;
	
	private ArrayList<NyTimesModel> nyTimesModelObjects = new ArrayList<NyTimesModel>();
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
		   JsonObject responseJsonObj = jse.getAsJsonObject();
		   if(responseJsonObj.get("status").getAsString().equalsIgnoreCase("OK")){
			   if(responseJsonObj.get("results").isJsonArray()){
				   JsonArray jsonArr = responseJsonObj.get("results").getAsJsonArray();
				   for(int i = 0 ; i< jsonArr.size();i++){
					   NyTimesModel nyTimesModelObj = new NyTimesModel(jsonArr.get(i));
					   nyTimesModelObjects.add(nyTimesModelObj);
				   }
			   }else{
				   NyTimesModel nyTimesModelObj = new NyTimesModel(responseJsonObj);
				   nyTimesModelObjects.add(nyTimesModelObj);
			   }
		   }

		   /*System.out.println(responseJsonArr.size());
		   for(int i = 0;i<responseJsonArr.size();i++){
			   
			   nyTimesModelObjects.add(new NyTimesModel(responseJsonArr.get(i)));	
		   }*/
		}catch(Exception e){
			System.out.println("Exception thrown "+e);
		}
	}
	
	public ArrayList<NyTimesModel> getNyTimesModelObjects(){
		return nyTimesModelObjects;
	}
	
}
