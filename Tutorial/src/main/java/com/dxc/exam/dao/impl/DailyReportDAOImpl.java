package com.dxc.exam.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.hibernate.Query;
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

	@Override
	public void update(DailyReport dailyReport) {
		Query q = sessionFactory.getCurrentSession().createSQLQuery(
				"update daily_report set code_smell = :code_smell , coverage = :coverage , technical_debt = :technical_debt , test_case = :test_case , vulnerability = :vulnerability where id_module = :id_module and date_report = :date_report");
		q.setParameter("code_smell", dailyReport.getCodeSmell());
		q.setParameter("coverage", dailyReport.getCoverage());
		q.setParameter("technical_debt", dailyReport.getTechDebt());
		q.setParameter("test_case", dailyReport.getTestcase());
		q.setParameter("vulnerability", dailyReport.getVulnerability());
		q.setParameter("id_module", dailyReport.getModuleId());
		q.setParameter("date_report", new SimpleDateFormat("yyyy-MM-dd ").format(dailyReport.getDate()));
		q.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<DailyReport> getAll() {
		return (ArrayList<DailyReport>) getSessionFactory().getCurrentSession().createQuery("from daily_report").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Date> getDateReport(Date key) {
		Query q = sessionFactory.getCurrentSession()
				.createSQLQuery("select date_report from daily_report where date_report = :date_report");
		q.setParameter("date_report", new SimpleDateFormat("yyyy-MM-dd ").format(key));
		ArrayList<Date> arrList = (ArrayList<Date>) q.list();
		return arrList;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}
