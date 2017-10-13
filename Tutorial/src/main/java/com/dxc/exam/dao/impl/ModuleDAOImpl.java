package com.dxc.exam.dao.impl;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dxc.exam.dao.ModuleDAO;
import com.dxc.exam.entity.Module;

public class ModuleDAOImpl implements ModuleDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void insert(Module module) {
		getSessionFactory().getCurrentSession().saveOrUpdate(module);
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Integer> getIdModule() {
		Query q = sessionFactory.getCurrentSession().createQuery("select id from module");
		ArrayList<Integer> arrList = (ArrayList<Integer>) q.list();
		return arrList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ArrayList<Integer> getIdModule(String key) {
		Query q = sessionFactory.getCurrentSession().createQuery("select id from module where api_key = :api_key");
		q.setParameter("api_key", key);
		ArrayList<Integer> arrList = (ArrayList<Integer>) q.list();
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
