package com.dxc.dao;

import java.util.ArrayList;
import java.util.Date;

import com.dxc.entity.DailyReport;
import com.dxc.model.DailyData;

public interface DailyReportDAO {

	public void insert(DailyReport dailyReport);

	public void update(DailyReport dailyReport);

	public ArrayList<DailyReport> getAll();

	public ArrayList<Date> getDateReport(Date key);

	public DailyData getDailyData(int idModule);
}
