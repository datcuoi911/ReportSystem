package com.dxc.exam.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.ParseException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.dxc.exam.dao.DailyReportDAO;
import com.dxc.exam.entity.DailyReport;
import com.dxc.exam.service.APIService;
import com.dxc.exam.service.DailyReportService;
import com.dxc.exam.service.ModuleService;

public class DailyReportServiceImpl implements DailyReportService {

	@Autowired
	DailyReportDAO dailyReportDAO;

	@Autowired
	ModuleService moduleService;

	@Autowired
	APIService apiService;

	@Override
	public void saveOrUpdate(String linkAPI) throws ParseException, IOException, JSONException {

		// initializer
		List<String> listKey = apiService.getKeyAPI(linkAPI);

		ArrayList<Date> arrDate = dailyReportDAO.getDateReport(new Date());

		int check = arrDate.size();

		if (check == 0) {
			for (int i = 0; i < listKey.size(); i++) {
				DailyReport dailyReport = getValues(listKey.get(i));
				dailyReportDAO.insert(dailyReport);
				System.out.println(" just Insert ");
			}
		} else {
			for (int i = 0; i < listKey.size(); i++) {
				DailyReport dailyReport = getValues(listKey.get(i));
				dailyReportDAO.update(dailyReport);
				System.out.println(" just Update ");
			}
		}
	}

	@Override
	public DailyReport getValues(String key) throws ParseException, IOException, JSONException {

		DailyReport dailyReport = new DailyReport();
		JSONArray arrMeasures = apiService.getValueAPI(key);

		dailyReport.setModuleId(moduleService.findId(key)); // set module id

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
