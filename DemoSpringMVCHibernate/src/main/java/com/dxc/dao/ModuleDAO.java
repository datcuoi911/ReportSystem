package com.dxc.dao;

import java.util.ArrayList;

import com.dxc.entity.Module;

public interface ModuleDAO {

	public void insert(Module module);

	public ArrayList<Integer> getIdModule();

	public ArrayList<Integer> getIdModule(String key);
	
	public String findNameModule(int idModule);
	
	public int findIdProject(int idModule);
}
