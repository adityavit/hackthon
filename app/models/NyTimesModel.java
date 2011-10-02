/**
 * 
 */
package models;

import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import helpers.TokenGenerator;

/**
 * @author Aditya
 *
 */
public class NyTimesModel {
	
	private String recordUrl = null;
	
	private  String section= null;
	
	private String title = null;
	
	private String publishedDate = null;
	
	private String postAbstract = null;
	
	private String thumbUrl = null; 
	private String thumbHeight = null;
	private String thumbWidth = null;
	
	private String[] tokens = null;
	
	
	private HashMap<String,ArrayList<ParselyModel>> parselyObjects = null;

	public NyTimesModel(JsonElement responseObject){
		JsonObject resultObj = responseObject.getAsJsonObject();
		recordUrl = resultObj.get("url").getAsString();
		section = resultObj.get("section").getAsString();
		title = resultObj.get("title").getAsString();
		postAbstract = resultObj.get("abstract").getAsString();
		publishedDate = resultObj.get("published_date").getAsString();
		try{
		TokenGenerator tg = new TokenGenerator();
		tokens = tg.getTokens(postAbstract);
		}catch(Exception e){
			System.out.println("Exception Caught");
		}
		JsonElement mediaObj = resultObj.get("media");
		if(mediaObj != null && mediaObj.isJsonArray()){
			JsonArray mediaArry = mediaObj.getAsJsonArray();
			if(mediaArry.size() != 0){
				JsonElement mediaElement =  mediaArry.get(0);
				JsonObject mediaObject = mediaElement.getAsJsonObject();
				JsonElement mediaMetadata = mediaObject.get("media-metadata");
				if(mediaMetadata != null){
					JsonObject mediaMetaDataObj = mediaMetadata.getAsJsonArray().get(0).getAsJsonObject();
					thumbUrl = mediaMetaDataObj.get("url").getAsString();
					thumbHeight = mediaMetaDataObj.get("height").getAsString();
					thumbWidth = mediaMetaDataObj.get("width").getAsString();
				}
					
			}
		}
	}

	/**
	 * @return the tokens
	 */
	public String[] getTokens() {
		return tokens;
	}

	/**
	 * @return the recordUrl
	 */
	public String getRecordUrl() {
		return recordUrl;
	}

	/**
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the publishedDate
	 */
	public String getPublishedDate() {
		return publishedDate;
	}

	/**
	 * @return the postAbstract
	 */
	public String getPostAbstract() {
		return postAbstract;
	}
	
	
	/**
	 * @return the parselyUrls
	 */
	public HashMap<String,ArrayList<ParselyModel>> getParselyObjects() {
		return parselyObjects;
	}

	/**
	 * @param parselyUrls the parselyUrls to set
	 */
	public void setParselyObjects(HashMap<String,ArrayList<ParselyModel>> parselyObjects) {
		this.parselyObjects = parselyObjects;
	}
}
