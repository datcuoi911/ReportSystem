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
	public void getValues(String linkAPI) throws ParseException, IOException, JSONException {
		
		
		/*
		 * 	example: date today = 12/10/2017
			SELECT COUNT (date) FROM daily_report WHERE (datepart(yy,date)=2017) AND (datepart(mm,date)=10) AND (datepart(dd,date)=12);
			> 0 => update all where date = 12/10/2017
			= 0 => insert
		*
		*/

		// initializer
		List<String> listKey = apiService.getKeyAPI(linkAPI);

		// get values
		for (int i = 0; i < listKey.size(); i++) {
			
			
			DailyReport dailyReport = new DailyReport();
			JSONArray arrMeasures = apiService.getValueAPI(listKey.get(i));

			dailyReport.setModuleId(moduleService.findId(listKey.get(i))); // set module id

			Date today = new Date();
			
			
			
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
			dailyReportDAO.insert(dailyReport);
		}

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
