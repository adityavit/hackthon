/**
 * 
 */
package models;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

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
	
	
	public NyTimesModel(JsonElement responseObject){
		JsonObject resultObj = responseObject.getAsJsonObject();
		recordUrl = resultObj.get("url").getAsString();
		section = resultObj.get("section").getAsString();
		title = resultObj.get("title").getAsString();
		postAbstract = resultObj.get("abstract").getAsString();
		publishedDate = resultObj.get("published_date").getAsString();
		
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
