package com.dxc.dao;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dxc.entity.Project;

@Repository
@Transactional
public class ProjectDAOImpl implements ProjectDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void insert(Project project) {
		getSessionFactory().getCurrentSession().saveOrUpdate(project);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Project> getAll() {
		return (ArrayList<Project>) getSessionFactory().getCurrentSession().createQuery("from project").list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public String findNameProject(int idProject) {
		Query q = sessionFactory.getCurrentSession().createQuery("select name from project where id = :id");
		q.setParameter("id", idProject);
		ArrayList<String> arrList = (ArrayList<String>) q.list();
		return arrList.get(0);
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
