package com.dxc.exam.dao;

import java.util.ArrayList;

import com.dxc.exam.entity.Module;

public interface ModuleDAO {

	public void insert(Module module);

	public ArrayList<Integer> getIdModule();
	
	public ArrayList<Integer> getIdModule(String key);
}
