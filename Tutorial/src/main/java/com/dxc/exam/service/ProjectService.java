package com.dxc.exam.service;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.ParseException;
import org.json.JSONException;

import com.dxc.exam.entity.Project;

public interface ProjectService {

	public void getProject(String linkAPI) throws ParseException, IOException, JSONException;

	public ArrayList<Project> getAll();
}
