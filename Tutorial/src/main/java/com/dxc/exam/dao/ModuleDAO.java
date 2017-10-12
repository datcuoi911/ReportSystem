package com.dxc.exam.dao;

import java.util.ArrayList;

import com.dxc.exam.entity.Module;

public interface ModuleDAO {

	public void insert(Module module);

	public ArrayList<Integer> getIdModule();
	
	public int findIdModule(String key);
}
