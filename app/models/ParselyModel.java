/**
 * 
 */
package models;

import java.util.ArrayList;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/**
 * @author Aditya
 *
 */

public class ParselyModel {
	
	private String url = null;
	
	private String thumbUrl = null;
	
	private String title = null;
	
	private String pubDate = null;

	public ParselyModel(JsonElement parselyJsonData){
		try{
		JsonObject parselyObject = parselyJsonData.getAsJsonObject();
		url = parselyObject.get("link").getAsString();
		if(parselyObject.get("thumb_url_small") != null){
		thumbUrl = parselyObject.get("thumb_url_medium").getAsString();
		}
		title = parselyObject.get("title").getAsString();
		pubDate = parselyObject.get("pub_date").getAsString();
		}catch(Exception e){
			System.out.println("Pricely Model Exception"+ e);
		}
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @return the thumbUrl
	 */
	public String getThumbUrl() {
		return thumbUrl;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the pubDate
	 */
	public String getPubDate() {
		return pubDate;
	}
	
	
	
}
