package com.dxc.service;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.ParseException;
import org.json.JSONException;

import com.dxc.entity.DailyReport;
import com.dxc.model.DailyData;

public interface DailyReportService {

	public void saveOrUpdate(String linkAPI) throws ParseException, IOException, JSONException;

	public DailyReport getValues(String key) throws ParseException, IOException, JSONException;

	public ArrayList<DailyReport> getAll();
	
	public DailyData getDailyData(int idModule);
}
