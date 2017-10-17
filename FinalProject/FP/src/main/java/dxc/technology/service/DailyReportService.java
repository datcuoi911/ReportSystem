package dxc.technology.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.http.ParseException;
import org.json.JSONException;

import dxc.technology.entity.DailyReport;
import dxc.technology.model.DailyData;

public interface DailyReportService {

	public void saveOrUpdate(String linkAPI, Date date) throws ParseException, IOException, JSONException;

	public DailyReport getValues(String key) throws ParseException, IOException, JSONException;

	public ArrayList<DailyReport> getAll();

	public DailyData getDailyData(int idModule, Date date);

	public int checkDate(Date date);
}
