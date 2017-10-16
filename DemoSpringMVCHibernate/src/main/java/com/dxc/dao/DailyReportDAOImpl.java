package com.dxc.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dxc.entity.DailyReport;
import com.dxc.model.DailyData;

@Repository
@Transactional
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

	@SuppressWarnings("unchecked")
	@Override
	public DailyData getDailyData(int idModule) {

		Query q = sessionFactory.getCurrentSession().createSQLQuery(
				"select sum(test_case) from daily_report d, project p, module m where p.id = :id and d.date_report = :date_report and d.id_module = m.id and p.id = m.project_id");
		q.setParameter("id", 1);
		q.setParameter("date_report", "2017-10-16");
		ArrayList<DailyData> listData = (ArrayList<DailyData>) q.list();
		System.out.println(listData);
		System.out.println("CC Quang ! ");

		DailyData dailyData = new DailyData();
		Query qCover = sessionFactory.getCurrentSession().createSQLQuery(
				"select avg(coverage) from daily_report d, project p, module m where p.id = :id and d.date_report = :date_report and d.id_module = m.id and p.id = m.project_id");
		qCover.setParameter("id", 1);
		qCover.setParameter("date_report", "2017-10-16");

		Query qCodeSmell = sessionFactory.getCurrentSession().createSQLQuery(
				"select sum(code_smell) from daily_report d, project p, module m where p.id = :id and d.date_report = :date_report and d.id_module = m.id and p.id = m.project_id");
		qCodeSmell.setParameter("id", 1);
		qCodeSmell.setParameter("date_report", "2017-10-16");

		Query qTechDebt = sessionFactory.getCurrentSession().createSQLQuery(
				"select sum(technical_debt) from daily_report d, project p, module m where p.id = :id and d.date_report = :date_report and d.id_module = m.id and p.id = m.project_id");
		qTechDebt.setParameter("id", 1);
		qTechDebt.setParameter("date_report", "2017-10-16");

		Query qTestCase = sessionFactory.getCurrentSession().createSQLQuery(
				"select sum(test_case) from daily_report d, project p, module m where p.id = :id and d.date_report = :date_report and d.id_module = m.id and p.id = m.project_id");
		qTestCase.setParameter("id", 1);
		qTestCase.setParameter("date_report", "2017-10-16");

		Query qVul = sessionFactory.getCurrentSession().createSQLQuery(
				"select sum(vulnerability) from daily_report d, project p, module m where p.id = :id and d.date_report = :date_report and d.id_module = m.id and p.id = m.project_id");
		qVul.setParameter("id", 1);
		qVul.setParameter("date_report", "2017-10-16");

		ArrayList<Integer> listObjectCS = (ArrayList<Integer>) qCodeSmell.list();
		ArrayList<Integer> listObjectTD = (ArrayList<Integer>) qTechDebt.list();
		ArrayList<Integer> listObjectTC = (ArrayList<Integer>) qTestCase.list();
		ArrayList<Integer> listObjectV = (ArrayList<Integer>) qVul.list();
		ArrayList<Double> listObjectC = (ArrayList<Double>) qCover.list();
		
		dailyData.setSumCodeSmell(listObjectCS.get(0));
		dailyData.setSumTechDebt(listObjectTD.get(0));
		dailyData.setSumTestcase(listObjectTC.get(0));
		dailyData.setSumVulnerability(listObjectV.get(0));
		dailyData.setSumCoverage(listObjectC.get(0));
		
		System.out.println(dailyData.getSumCodeSmell());
		
		return dailyData;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
