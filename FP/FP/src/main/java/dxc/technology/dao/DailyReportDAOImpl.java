package dxc.technology.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dxc.technology.entity.DailyReport;
import dxc.technology.model.DailyData;
import dxc.technology.model.ShortData;

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
		Query q = getSessionFactory().getCurrentSession().createSQLQuery(
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
		Query q = getSessionFactory().getCurrentSession()
				.createSQLQuery("select date_report from daily_report where date_report = :date_report");
		q.setParameter("date_report", new SimpleDateFormat("yyyy-MM-dd ").format(key));
		ArrayList<Date> arrList = (ArrayList<Date>) q.list();
		return arrList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public DailyData getDailyData(int idProject, Date date) {
		DailyData dailyData = new DailyData();
		Query qCover = getSessionFactory().getCurrentSession().createSQLQuery(
				"select avg(coverage) from daily_report d, project p, module m where p.id = :id and d.date_report = :date_report and d.id_module = m.id and p.id = m.project_id");
		qCover.setParameter("id", idProject);
		qCover.setParameter("date_report", new SimpleDateFormat("yyyy-MM-dd ").format(date));

		Query qCodeSmell = getSessionFactory().getCurrentSession().createSQLQuery(
				"select sum(code_smell) from daily_report d, project p, module m where p.id = :id and d.date_report = :date_report and d.id_module = m.id and p.id = m.project_id");
		qCodeSmell.setParameter("id", idProject);
		qCodeSmell.setParameter("date_report", new SimpleDateFormat("yyyy-MM-dd ").format(date));

		Query qTechDebt = getSessionFactory().getCurrentSession().createSQLQuery(
				"select sum(technical_debt) from daily_report d, project p, module m where p.id = :id and d.date_report = :date_report and d.id_module = m.id and p.id = m.project_id");
		qTechDebt.setParameter("id", idProject);
		qTechDebt.setParameter("date_report", new SimpleDateFormat("yyyy-MM-dd ").format(date));

		Query qTestCase = getSessionFactory().getCurrentSession().createSQLQuery(
				"select sum(test_case) from daily_report d, project p, module m where p.id = :id and d.date_report = :date_report and d.id_module = m.id and p.id = m.project_id");
		qTestCase.setParameter("id", idProject);
		qTestCase.setParameter("date_report", new SimpleDateFormat("yyyy-MM-dd ").format(date));

		Query qVul = getSessionFactory().getCurrentSession().createSQLQuery(
				"select sum(vulnerability) from daily_report d, project p, module m where p.id = :id and d.date_report = :date_report and d.id_module = m.id and p.id = m.project_id");
		qVul.setParameter("id", idProject);
		qVul.setParameter("date_report", new SimpleDateFormat("yyyy-MM-dd ").format(date));

		ArrayList<Double> listObjectC = (ArrayList<Double>) qCover.list();
		ArrayList<Integer> listObjectCS = (ArrayList<Integer>) qCodeSmell.list();
		ArrayList<Integer> listObjectTD = (ArrayList<Integer>) qTechDebt.list();
		ArrayList<Integer> listObjectTC = (ArrayList<Integer>) qTestCase.list();
		ArrayList<Integer> listObjectV = (ArrayList<Integer>) qVul.list();

		dailyData.setCoverage(listObjectC.get(0));
		dailyData.setCodeSmell(listObjectCS.get(0));
		dailyData.setTechDebt(listObjectTD.get(0));
		dailyData.setTestcase(listObjectTC.get(0));
		dailyData.setVulnerability(listObjectV.get(0));

		System.out.println("get Report Data");

		return dailyData;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Date getLastestDate() {
		ArrayList<Date> date = (ArrayList<Date>) getSessionFactory().getCurrentSession()
				.createSQLQuery("select top 1(date_report) from daily_report order by date_report desc").list();
		return date.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<ShortData> getMonthlyData(int projectId, Date lastestDate, String nameColumn) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(lastestDate);
		cal.add(Calendar.DATE, -28);
		Date dateBefore28Days = cal.getTime();

		Query q = getSessionFactory().getCurrentSession().createSQLQuery("select sum(" + nameColumn
				+ "), date_report from daily_report d, module m, project p where date_report between :date2 and :date1 and d.id_module = m.id and m.project_id = p.id and p.id = :id group by d.date_report");
		q.setParameter("date1", new SimpleDateFormat("yyyy-MM-dd").format(lastestDate));
		q.setParameter("date2", new SimpleDateFormat("yyyy-MM-dd").format(dateBefore28Days));
		q.setParameter("id", projectId);

		List<Object[]> datas = q.list();
		ArrayList<ShortData> listShortData = new ArrayList<>();

		for (Object[] d : datas) {
			ShortData shortData = new ShortData();
			shortData.setValue(String.valueOf(d[0]));
			shortData.setDate(new SimpleDateFormat("yyyy-MM-dd").format(d[1]));
			listShortData.add(shortData);
		}

		return listShortData;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
