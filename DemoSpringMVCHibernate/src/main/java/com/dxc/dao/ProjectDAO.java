package com.dxc.dao;

import java.util.ArrayList;

import com.dxc.entity.Project;

public interface ProjectDAO {

	public void insert(Project project);

	public ArrayList<Project> getAll();
	
	public ArrayList<Integer> getIdProject();
	
	public String findNameProject(int idProject);
}
