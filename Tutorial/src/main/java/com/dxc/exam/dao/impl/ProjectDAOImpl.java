package com.dxc.exam.dao.impl;

import java.util.ArrayList;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dxc.exam.dao.ProjectDAO;
import com.dxc.exam.entity.Project;

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

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
