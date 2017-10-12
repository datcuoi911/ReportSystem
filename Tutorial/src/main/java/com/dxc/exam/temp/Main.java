package com.dxc.exam.temp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dxc.exam.constain.AppConst;

public class Main {

	public static void main(String[] args) throws ClientProtocolException, IOException, ParseException, JSONException {
		getAPI(AppConst.API_PROJECT);
	}

	public static void getAPI(String name) throws ClientProtocolException, IOException, ParseException, JSONException {

		// initializer
		List<String> listKey = new ArrayList<String>();

		// get key
		HttpGet httpGetKeyAndId = new HttpGet(name);
		try (CloseableHttpClient httpClient = HttpClients.createDefault();
				CloseableHttpResponse response = httpClient.execute(httpGetKeyAndId);) {
			HttpEntity entity = response.getEntity();

			// do something here
			JSONObject decoded = new JSONObject(EntityUtils.toString(entity));

			JSONArray arrComponents = (JSONArray) decoded.get("components");

			for (int i = 0; i < arrComponents.length(); i++) {
				JSONObject listComponent = (JSONObject) arrComponents.get(i);
				String tempKey = listComponent.get("key").toString();

				System.out.println(tempKey);

				tempKey = tempKey.replace(":", "%3A");
				tempKey = tempKey.replace("/", "%2F");
				listKey.add(tempKey);
			}
		}

		// get values
		for (int i = 0; i < listKey.size(); i++) {
			HttpGet httpGetValues = new HttpGet(AppConst.API_HEAD + listKey.get(i) + AppConst.API_FOOT);
			try (CloseableHttpClient httpClient = HttpClients.createDefault();
					CloseableHttpResponse response = httpClient.execute(httpGetValues);) {
				HttpEntity entity = response.getEntity();
				JSONObject jsonObject = new JSONObject(EntityUtils.toString(entity));

				JSONObject jsonComponent = (JSONObject) jsonObject.get("component");
				JSONArray arr = (JSONArray) jsonComponent.get("measures");
				
				System.out.println(arr);

				System.out.println("\n project No" + i);

				for (int j = 0; j < arr.length(); j++) {
					JSONObject temp = (JSONObject) arr.get(j);
					if (temp.get("metric").toString().equals("coverage")) {
						System.out.println("coverage : " + temp.get("value") + "%");
					}
					if (temp.get("metric").toString().equals("code_smells")) {
						System.out.println("code smell : " + temp.get("value"));
					}
					if (temp.get("metric").toString().equals("vulnerabilities")) {
						System.out.println("vulnerabilitie : " + temp.get("value"));
					}
					if (temp.get("metric").toString().equals("tests")) {
						System.out.println("test : " + temp.get("value"));
					}
					if (temp.get("metric").toString().equals("sqale_index")) {
						System.out.println("tech debt : " + temp.get("value") + "min");
					}
				}
			}
		}
	}
}
