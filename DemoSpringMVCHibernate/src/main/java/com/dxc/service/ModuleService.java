package com.dxc.service;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.ParseException;
import org.json.JSONException;

public interface ModuleService {

	public void getModule(String linkAPI) throws ParseException, IOException, JSONException;

	public ArrayList<Integer> getId();
	
	public int findId(String key);
	
	public String findNameModule(int idModule);
	
	public int findIdProject(int idModule);
}
