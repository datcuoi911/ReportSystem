package com.dxc.exam.dao;

import java.util.ArrayList;
import java.util.Date;

import com.dxc.exam.entity.DailyReport;

public interface DailyReportDAO {

	public void insert(DailyReport dailyReport);

	public void update(DailyReport dailyReport);

	public ArrayList<DailyReport> getAll();

	public ArrayList<Date> getDateReport(Date key);
}
