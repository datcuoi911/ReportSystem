package com.dxc.exam.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.dxc.exam.constain.AppConst;
import com.dxc.exam.service.APIService;

public class APIServiceImpl implements APIService {

	@Override
	public List<String> getKeyAPI(String linkAPI) throws IOException, ParseException, JSONException {

		// initialier
		List<String> listKey = new ArrayList<String>();

		// get keys from API
		HttpGet httpGetKeyAndId = new HttpGet(linkAPI);
		try (CloseableHttpClient httpClient = HttpClients.createDefault();
				CloseableHttpResponse response = httpClient.execute(httpGetKeyAndId);) {
			HttpEntity entity = response.getEntity();

			JSONObject decoded = new JSONObject(EntityUtils.toString(entity));

			JSONArray arrComponents = (JSONArray) decoded.get("components");

			for (int i = 0; i < arrComponents.length(); i++) {
				JSONObject listComponent = (JSONObject) arrComponents.get(i);
				String tempKey = listComponent.get("key").toString();

				tempKey = tempKey.replace(":", "%3A");
				tempKey = tempKey.replace("/", "%2F");
				listKey.add(tempKey);
			}
		}
		return listKey;
	}

	@Override
	public JSONArray getValueAPI(String key) throws IOException, ParseException, JSONException {

		// initialier
		JSONArray arrMeasures = null;

		// get values from API
		HttpGet httpGetValues = new HttpGet(AppConst.API_HEAD + key + AppConst.API_FOOT);
		try (CloseableHttpClient httpClient = HttpClients.createDefault();
				CloseableHttpResponse response = httpClient.execute(httpGetValues);) {
			HttpEntity entity = response.getEntity();
			JSONObject jsonObject = new JSONObject(EntityUtils.toString(entity));

			JSONObject jsonComponent = (JSONObject) jsonObject.get("component");
			arrMeasures = (JSONArray) jsonComponent.get("measures");
		}

		return arrMeasures;
	}

}
