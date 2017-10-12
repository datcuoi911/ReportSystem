package com.dxc.exam.dao;

import java.util.ArrayList;

import com.dxc.exam.entity.DailyReport;

public interface DailyReportDAO {

	public void insert(DailyReport dailyReport);
	
	public ArrayList<DailyReport> getAll();
}
