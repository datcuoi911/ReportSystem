package com.dxc.exam.dao;

import java.util.ArrayList;

import com.dxc.exam.entity.Project;

public interface ProjectDAO {

	public void insert(Project project);

	public ArrayList<Project> getAll();
}
