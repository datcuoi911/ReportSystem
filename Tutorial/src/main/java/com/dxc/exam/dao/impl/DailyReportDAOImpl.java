package com.dxc.exam.dao.impl;

import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dxc.exam.dao.DailyReportDAO;
import com.dxc.exam.entity.DailyReport;

public class DailyReportDAOImpl implements DailyReportDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void insert(DailyReport dailyReport) {
		getSessionFactory().getCurrentSession().saveOrUpdate(dailyReport);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<DailyReport> getAll() {
		return (ArrayList<DailyReport>) getSessionFactory().getCurrentSession().createQuery("from daily_report").list();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
