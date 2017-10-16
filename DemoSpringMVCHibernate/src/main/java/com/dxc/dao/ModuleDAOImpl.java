package com.dxc.dao;

import java.util.ArrayList;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dxc.entity.Module;

@Repository
@Transactional
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

	@SuppressWarnings("unchecked")
	@Override
	public String findNameModule(int idModule) {
		Query q = sessionFactory.getCurrentSession().createQuery("select name from module where id = :id_module");
		q.setParameter("id_module", idModule);
		ArrayList<String> arrList = (ArrayList<String>) q.list();
		return arrList.get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public int findIdProject(int idModule) {
		Query q = sessionFactory.getCurrentSession().createQuery("select project_id from module where id = :id");
		q.setParameter("id", idModule);
		ArrayList<Integer> arrList = (ArrayList<Integer>) q.list();
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
