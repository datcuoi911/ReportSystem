package com.dxc.exam.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.ParseException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import com.dxc.exam.dao.ModuleDAO;
import com.dxc.exam.entity.Module;
import com.dxc.exam.entity.Project;
import com.dxc.exam.service.APIService;
import com.dxc.exam.service.ModuleService;
import com.dxc.exam.service.ProjectService;

public class ModuleServiceImpl implements ModuleService {

	@Autowired
	ModuleDAO moduleDAO;

	@Autowired
	APIService apiService;

	@Autowired
	ProjectService projectService;

	@Override
	public void getModule(String linkAPI) throws ParseException, IOException, JSONException {

		// initializer
		List<String> listKey = apiService.getKeyAPI(linkAPI);

		// get name module
		ArrayList<Project> listProject = projectService.getAll();

		for (int i = 0; i < listKey.size(); i++) {
			Module module = new Module();
			module.setKey(listKey.get(i)); // set key

			for (int j = 0; j < listProject.size(); j++) {
				String temp = listProject.get(j).getName().replace(" ", "-");
				if (module.getKey().contains(temp)) {
					module.setProjectId(listProject.get(j).getId()); // set id project
				}
			}

			if (listKey.get(i).contains("%3A%2F")) {
				String[] strList = listKey.get(i).split("%3A%2F");
				String[] strList2 = strList[0].toString().split("%3A");
				strList2[1] = strList2[1].replace("-", " ");
				module.setName(strList2[1]); // set name
			} else {
				String[] strList = listKey.get(i).split("%3A");
				strList[1] = strList[1].replace("-", " ");
				module.setName(strList[1]); // set name
			}

			moduleDAO.insert(module);
		}
	}

	@Override
	public int findId(String key) {
		return moduleDAO.findIdModule(key);
	}

	@Override
	public ArrayList<Integer> getId() {
		return getModuleDAO().getIdModule();
	}

	public ModuleDAO getModuleDAO() {
		return moduleDAO;
	}

	public void setModuleDAO(ModuleDAO moduleDAO) {
		this.moduleDAO = moduleDAO;
	}

	public APIService getApiService() {
		return apiService;
	}

	public void setApiService(APIService apiService) {
		this.apiService = apiService;
	}

	public ProjectService getProjectService() {
		return projectService;
	}

	public void setProjectService(ProjectService projectService) {
		this.projectService = projectService;
	}
}
