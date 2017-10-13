package com.dxc.exam.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.ParseException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.exam.constain.AppConst;
import com.dxc.exam.entity.Project;
import com.dxc.exam.service.DailyReportService;
import com.dxc.exam.service.ModuleService;
import com.dxc.exam.service.ProjectService;

@RestController
@Transactional
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class RESTfullController {

	@Autowired
	ProjectService projectService;

	@Autowired
	ModuleService moduleService;

	@Autowired
	DailyReportService dailyReportService;

	@RequestMapping("/create4test")
	public String index() throws ParseException, IOException, JSONException {
		// projectService.getProject(AppConst.API_PROJECT);
		// moduleService.getModule(AppConst.API_PROJECT);
		dailyReportService.saveOrUpdate(AppConst.API_PROJECT);
		return "register";
	}

	@RequestMapping(value = "/showall", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public ResponseEntity<ArrayList<Project>> showAllUser() {
		System.out.println("abc");
		return new ResponseEntity<ArrayList<Project>>(projectService.getAll(), HttpStatus.OK);
	}
}