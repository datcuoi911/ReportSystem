package com.dxc.exam.controller;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.ParseException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.dxc.exam.constain.AppConst;
import com.dxc.exam.entity.DailyReport;
import com.dxc.exam.service.DailyReportService;
import com.dxc.exam.service.ModuleService;
import com.dxc.exam.service.ProjectService;

@Controller
@Transactional
@EnableWebMvc
public class MainController {

	@Autowired
	ProjectService projectService;

	@Autowired
	ModuleService moduleService;

	@Autowired
	DailyReportService dailyReportService;

	@RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
	public String welcomePage() {
		return "welcome";
	}

	@RequestMapping("/register")
	public String index() throws ParseException, IOException, JSONException {
		projectService.getProject(AppConst.API_PROJECT);
		moduleService.getModule(AppConst.API_PROJECT);
		dailyReportService.getValues(AppConst.API_PROJECT);
		return "register";
	}

	@RequestMapping({ "/userlist" })
	public String deptList(Model model) {
		
		return "userlist";
	}
}
