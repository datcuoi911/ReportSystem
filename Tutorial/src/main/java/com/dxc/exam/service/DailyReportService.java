package com.dxc.exam.service;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.ParseException;
import org.json.JSONException;

import com.dxc.exam.entity.DailyReport;

public interface DailyReportService {

	public void getValues(String linkAPI) throws ParseException, IOException, JSONException;

	public ArrayList<DailyReport> getAll();
}
