package com.dxc.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dxc.model.DailyData;
import com.dxc.service.APIService;
import com.dxc.service.DailyReportService;
import com.dxc.service.ModuleService;
import com.dxc.service.ProjectService;

@RestController
@RequestMapping("/api")
public class RESTfulController {

	@Autowired
	ProjectService projectService;

	@Autowired
	ModuleService moduleSErvice;

	@Autowired
	DailyReportService dailyReportService;

	@Autowired
	APIService apiService;

	@RequestMapping(value = "/showall", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public ArrayList<DailyData> showAllUser() {
		return apiService.getDailyData();
	}
}
