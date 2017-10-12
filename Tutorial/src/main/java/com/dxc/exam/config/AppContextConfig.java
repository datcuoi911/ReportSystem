package com.dxc.exam.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.dxc.exam.dao.DailyReportDAO;
import com.dxc.exam.dao.ModuleDAO;
import com.dxc.exam.dao.ProjectDAO;
import com.dxc.exam.dao.impl.DailyReportDAOImpl;
import com.dxc.exam.dao.impl.ModuleDAOImpl;
import com.dxc.exam.dao.impl.ProjectDAOImpl;
import com.dxc.exam.service.APIService;
import com.dxc.exam.service.DailyReportService;
import com.dxc.exam.service.ModuleService;
import com.dxc.exam.service.ProjectService;
import com.dxc.exam.service.impl.APIServiceImpl;
import com.dxc.exam.service.impl.DailyReportServiceImpl;
import com.dxc.exam.service.impl.ModuleServiceImpl;
import com.dxc.exam.service.impl.ProjectServiceImpl;

@Configuration
@ComponentScan("com.dxc.exam.*")
@EnableTransactionManagement
@PropertySource("classpath:ds-hibernate-cfg.properties")
public class AppContextConfig {

	@Autowired
	private Environment env;

	@Bean(name = "viewResolver")
	public InternalResourceViewResolver getViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setPrefix("/WEB-INF/pages/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Bean(name = "dataSource")
	public DataSource getDataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();

		// ds-hibernate-cfg.properties
		dataSource.setDriverClassName(env.getProperty("ds.database-driver"));
		dataSource.setUrl(env.getProperty("ds.url"));
		dataSource.setUsername(env.getProperty("ds.username"));
		dataSource.setPassword(env.getProperty("ds.password"));

		System.out.println("## getDataSource: " + dataSource);

		return dataSource;
	}

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) throws Exception {
		Properties properties = new Properties();

		properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		properties.put("current_session_context_class", env.getProperty("current_session_context_class"));
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));

		// Package chua entity class
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		factoryBean.setPackagesToScan(new String[] { "com.dxc.exam.entity" });
		factoryBean.setDataSource(dataSource);
		factoryBean.setHibernateProperties(properties);
		factoryBean.afterPropertiesSet();

		SessionFactory sf = factoryBean.getObject();
		System.out.println("## getSessionFactory: " + sf);

		return sf;
	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);

		return transactionManager;
	}

	@Bean(name = "projectDAO")
	public ProjectDAO getProjectDAO() {
		return new ProjectDAOImpl();
	}

	@Bean(name = "projectService")
	public ProjectService getProjectService() {
		return new ProjectServiceImpl();
	}

	@Bean(name = "moduleDAO")
	public ModuleDAO getModuleDAO() {
		return new ModuleDAOImpl();
	}

	@Bean(name = "moduleService")
	public ModuleService getModuleService() {
		return new ModuleServiceImpl();
	}

	@Bean(name = "dailyReportDAO")
	public DailyReportDAO getDailyReportDAO() {
		return new DailyReportDAOImpl();
	}

	@Bean(name = "dailyReportService")
	public DailyReportService getDailyReportService() {
		return new DailyReportServiceImpl();
	}

	@Bean(name = "APIService")
	public APIService getAPIService() {
		return new APIServiceImpl();
	}
}
