/**
 * 
 */
package helpers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

import models.ParselyModel;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * @author Aditya
 * 
 */
public class ParselyExtractor {

	private String parselyApi = "http://search.parsely.com/solr/###cogtree/select?wt=json&q=";
	private String[] publishersList = { "bloomberg", "foxnews", "usnews" };
	private String parselyFoxNewsApi = "http://search.parsely.com/solr/foxnewscogtree/select?wt=json&q=";

	private HashMap<String, ArrayList<ParselyModel>> parselyModelMap = new HashMap<String, ArrayList<ParselyModel>>();

	private static HashMap<Integer, HashMap<String, ArrayList<ParselyModel>>> parselyModelCache = new HashMap<Integer, HashMap<String, ArrayList<ParselyModel>>>();

	public HashMap<String, ArrayList<ParselyModel>> fetchParselyData(
			String[] tokens) throws UnsupportedEncodingException {
		JsonElement jse = null;
		String tokenString = concat(tokens);
		String publisherString = concat(publishersList);
		Integer cacheToken = (Integer)(tokenString + publisherString).hashCode();
		if (parselyModelCache.containsKey(cacheToken)) {
			return parselyModelCache.get(cacheToken);
		} else {
			for (int j = 0; j < publishersList.length; j++) {
				String queryString = URLEncoder.encode(tokenString, "UTF-8");
				String publisherApi = parselyApi.replaceAll("###",
						publishersList[j]);
				String parselyUrl = publisherApi + queryString;
				System.out.println("queryString=" + queryString + " url="
						+ parselyUrl);
				try {
					URL source = new URL(parselyUrl);
					BufferedReader in = new BufferedReader(
							new InputStreamReader(source.openStream(), "UTF-8"));
					jse = new JsonParser().parse(in);
					in.close();
					JsonObject mainObj = jse.getAsJsonObject();
					JsonObject resHeaderElement = mainObj.get("responseHeader")
							.getAsJsonObject();
					System.out.println("Status String="
							+ resHeaderElement.get("status").getAsString());
					if (resHeaderElement.get("status").getAsString()
							.equalsIgnoreCase("0")) {
						JsonObject response = mainObj.get("response")
								.getAsJsonObject();
						JsonElement docs = response.get("docs");
						if (docs != null) {
							JsonArray docsArr = docs.getAsJsonArray();
							if (docsArr != null) {
								System.out.println("Size docsArr="
										+ docsArr.size());
								if (docsArr.size() != 0) {
									ArrayList<ParselyModel> parselyModelList = new ArrayList<ParselyModel>();
									for (int i = 0; i < docsArr.size() && i < 5; i++) {
										parselyModelList.add(new ParselyModel(
												docsArr.get(i)));
									}
									parselyModelMap.put(publishersList[j],
											parselyModelList);
								}
							}
						}
					}
				} catch (Exception e) {
					System.out.println("Parsely API gave error=" + e);
				}
			}
			System.out.println("Fetching Parsely From url cacheToken="
					+ cacheToken);
			parselyModelCache.put(cacheToken, parselyModelMap);
			return parselyModelMap;
		}
	}

	private String concat(String[] strArray) {
		String concatedStr = "";
		for (int i = 0; i < strArray.length; i++) {
			concatedStr = concatedStr + " " + strArray[i];
		}
		return concatedStr;
	}
}
