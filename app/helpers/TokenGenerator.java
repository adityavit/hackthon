/**
 * 
 */
package helpers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * @author Aditya
 *
 */
public class TokenGenerator {
	
	 private String[] tokens = null;
	 
	 /**
	  * Api to extract information about the tokens.
	  */
	 private String url = "http://search.yahooapis.com/ContentAnalysisService/V1/termExtraction?appid=YC2.9p_V34GTd1y6vHvTETcuyDPNATf4SOR_ZhuPFsvIMj__qwtY7CMVacvBwgXb1l5LUaWyDto2AUwKsfHgaJX1trHfNmE-&output=json&context=";
	 
	 public String[] getTokens(String context) throws UnsupportedEncodingException{
		 url = url + URLEncoder.encode(context,"UTF-8");
		 tokens = null;
		 JsonElement jse;
		 try{
			   URL source = new URL(url);
			   BufferedReader in = new BufferedReader(new InputStreamReader(source.openStream(), "UTF-8"));
			   jse = new JsonParser().parse(in);
			   if(jse.isJsonObject()){
				   JsonObject jseObj = jse.getAsJsonObject();
				   JsonElement jseResultSet = jseObj.get("ResultSet");
				   JsonObject jseResultSetObj = jseResultSet.getAsJsonObject();
				   JsonElement jseResultObj = jseResultSetObj.get("Result");
				   JsonArray jseResultArr = jseResultObj.getAsJsonArray();
				   tokens = new String[jseResultArr.size()];
				   for(int i =0;i<jseResultArr.size();i++){
					   tokens[i] = jseResultArr.get(i).getAsString();
				   }
				   System.out.println(tokens.toString());
			   }
		 }catch(Exception e){
			 System.out.println("The Yahoo Api not working."+e);
		 }
		return tokens;	   
	 }
}
