package dxc.technology.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dxc.technology.constain.AppConst;
import dxc.technology.entity.Project;
import dxc.technology.model.DailyData;

@Service
public class APIServiceImpl implements APIService {

	@Autowired
	ProjectService projectService;

	@Autowired
	ModuleService moduleService;

	@Autowired
	DailyReportService dailyReportService;

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

	@Override
	public ArrayList<DailyData> getDailyData(Date date) {
		ArrayList<DailyData> listDailyData = new ArrayList<DailyData>();
		ArrayList<Project> listProject = getProjectService().getAll();
		for (int i = 0; i < listProject.size(); i++) {
			DailyData dailyData = getDailyReportService().getDailyData(listProject.get(i).getId(), date);
			dailyData.setId(listProject.get(i).getId());
			dailyData.setName(listProject.get(i).getName());
			listDailyData.add(dailyData);
		}
		return listDailyData;
	}
	
//	@Override
//	public ArrayList<DailyData> getMonthlyDate(Date date) {
//		ArrayList<Date> month = getDailyReportService()
//		ArrayList<DailyData> listMonthly = new ArrayList<DailyData>();
//		for(int i=1;i<28;i++){
//			
//			
//		}
//	}
	

	public ProjectService getProjectService() {
		return projectService;
	}

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}

	public ModuleService getModuleService() {
		return moduleService;
	}

	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}

	public DailyReportService getDailyReportService() {
		return dailyReportService;
	}

	public void setDailyReportService(DailyReportService dailyReportService) {
		this.dailyReportService = dailyReportService;
	}

	
}
