package dxc.technology.test;

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

public class Main {

	public static void main(String[] args) throws IOException, ParseException, JSONException {

		System.out.println("end !");

		// problem 2

		List<String> listKey = new ArrayList<String>();

		String h = "http://20.203.6.208:9000/api/components/search_projects?ps=50&p=";
		String f = "&f=analysisDate";

		HttpGet httpGetFirst = new HttpGet(h + 1 + f);
		try (CloseableHttpClient httpClientFirst = HttpClients.createDefault();
				CloseableHttpResponse responseFirst = httpClientFirst.execute(httpGetFirst);) {
			HttpEntity entityFirst = responseFirst.getEntity();

			JSONObject decodedFirst = new JSONObject(EntityUtils.toString(entityFirst));

			JSONObject arrComponentsFirst = (JSONObject) decodedFirst.get("paging");
			int pageSize = Integer.parseInt(arrComponentsFirst.get("pageSize").toString());
			int total = Integer.parseInt(arrComponentsFirst.get("total").toString());

			for (int i = 1; i <= total / pageSize + 1; i++) {

				HttpGet httpGetKeyAndId = new HttpGet(h + i + f);
				try (CloseableHttpClient httpClient = HttpClients.createDefault();
						CloseableHttpResponse response = httpClient.execute(httpGetKeyAndId);) {
					HttpEntity entity = response.getEntity();

					JSONObject decoded = new JSONObject(EntityUtils.toString(entity));

					JSONArray arrComponents = (JSONArray) decoded.get("components");

					for (int j = 0; j < arrComponents.length(); j++) {
						JSONObject listComponent = (JSONObject) arrComponents.get(j);
						String tempKey = listComponent.get("key").toString();
						listKey.add(tempKey);
					}
				}
			}
		}

		int i = 1;
		for (String s : listKey) {
			String[] cut = s.split(":");
			if (cut.length == 3) {
				System.out.println(i + " " + s);
			}
			i++;
		}
		System.out.println("end");

	}
}
