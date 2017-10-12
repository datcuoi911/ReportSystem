package com.dxc.exam.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.ParseException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.exam.constain.AppConst;
import com.dxc.exam.entity.Project;
import com.dxc.exam.service.ProjectService;

@RestController
@RequestMapping("/api")
public class RESTfullController {

	@Autowired
	ProjectService projectService;

	@RequestMapping("/create4test")
	public String index() throws ParseException, IOException, JSONException {
		projectService.getProject(AppConst.API_PROJECT);
		return "register";
	}

	@CrossOrigin(origins = "*")
	@RequestMapping(value = "/showall", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public ArrayList<Project> showAllUser() {
		return projectService.getAll();
	}
}