/**
 * 
 */
package models;

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
	
	private String[] tokens = null;
	
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
}
