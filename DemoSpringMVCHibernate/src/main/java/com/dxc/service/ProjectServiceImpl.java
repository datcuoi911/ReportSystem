package com.dxc.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.http.ParseException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxc.dao.ProjectDAO;
import com.dxc.entity.Project;

@Service
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	ProjectDAO projectDAO;

	@Autowired
	APIService apiService;

	@Override
	public void getProject(String linkAPI) throws ParseException, IOException, JSONException {
		List<String> listKey = apiService.getKeyAPI(linkAPI);
		Set<String> setKeyName = new HashSet<>();
		for (int i = 0; i < listKey.size(); i++) {
			if (listKey.get(i).contains("%3A%2F")) {
				String[] strList = listKey.get(i).split("%3A%2F");
				strList[1] = strList[1].replace("-", " ");
				setKeyName.add(strList[1]);
			} else {
				String[] strList = listKey.get(i).split("%3A");
				strList[2] = strList[2].replace("-", " ");
				setKeyName.add(strList[2]);
			}
		}

		for (String str : setKeyName) {
			projectDAO.insert(new Project(str));
		}
	}

	@Override
	public ArrayList<Project> getAll() {
		return getProjectDAO().getAll();
	}

	@Override
	public String findNameProject(int idProject) {
		return projectDAO.findNameProject(idProject);
	}

	public ProjectDAO getProjectDAO() {
		return projectDAO;
	}

	public void setProjectDAO(ProjectDAO projectDAO) {
		this.projectDAO = projectDAO;
	}
}
