package dxc.technology.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.ParseException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dxc.technology.dao.DailyReportDAO;
import dxc.technology.entity.DailyReport;
import dxc.technology.model.DailyData;

@Service
public class DailyReportServiceImpl implements DailyReportService {

	@Autowired
	DailyReportDAO dailyReportDAO;

	@Autowired
	ModuleService moduleService;

	@Autowired
	APIService apiService;

	@Override
	public void saveOrUpdate(String linkAPI, Date date) throws ParseException, IOException, JSONException {

		// initializer
		List<String> listKey = getApiService().getKeyAPI(linkAPI);

		int check = checkDate(date);

		if (check == 0) {
			for (int i = 0; i < listKey.size(); i++) {
				DailyReport dailyReport = getValues(listKey.get(i));
				getDailyReportDAO().insert(dailyReport);
				System.out.println(" just Insert ");
			}
		} else {
			for (int i = 0; i < listKey.size(); i++) {
				DailyReport dailyReport = getValues(listKey.get(i));
				getDailyReportDAO().update(dailyReport);
				System.out.println(" just Update ");
			}
		}
	}

	@Override
	public DailyReport getValues(String key) throws ParseException, IOException, JSONException {

		DailyReport dailyReport = new DailyReport();
		JSONArray arrMeasures = getApiService().getValueAPI(key);

		dailyReport.setModuleId(getModuleService().findId(key)); // set module
																	// id

		dailyReport.setDate(new Date()); // set module date

		// set module values
		for (int j = 0; j < arrMeasures.length(); j++) {
			JSONObject temp = (JSONObject) arrMeasures.get(j);
			if (temp.get("metric").toString().equals("coverage")) {
				dailyReport.setCoverage(Double.parseDouble(temp.get("value").toString()));
			}
			if (temp.get("metric").toString().equals("code_smells")) {
				dailyReport.setCodeSmell(Integer.parseInt(temp.get("value").toString()));
			}
			if (temp.get("metric").toString().equals("vulnerabilities")) {
				dailyReport.setVulnerability(Integer.parseInt(temp.get("value").toString()));
			}
			if (temp.get("metric").toString().equals("tests")) {
				dailyReport.setTestcase(Integer.parseInt(temp.get("value").toString()));
			}
			if (temp.get("metric").toString().equals("sqale_index")) {
				dailyReport.setTechDebt(Integer.parseInt(temp.get("value").toString()));
			}
		}
		return dailyReport;
	}

	@Override
	public ArrayList<DailyReport> getAll() {
		return getDailyReportDAO().getAll();
	}

	@Override
	public DailyData getDailyData(int idProject, Date date) {
		return dailyReportDAO.getDailyData(idProject, date);
	}

	@Override
	public int checkDate(Date date) {
		ArrayList<Date> arrDate = getDailyReportDAO().getDateReport(new Date());
		return arrDate.size();
	}

	public APIService getApiService() {
		return apiService;
	}

	public void setApiService(APIService apiService) {
		this.apiService = apiService;
	}

	public DailyReportDAO getDailyReportDAO() {
		return dailyReportDAO;
	}

	public void setDailyReportDAO(DailyReportDAO dailyReportDAO) {
		this.dailyReportDAO = dailyReportDAO;
	}

	public ModuleService getModuleService() {
		return moduleService;
	}

	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}

}
