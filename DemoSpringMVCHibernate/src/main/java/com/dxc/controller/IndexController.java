package com.dxc.controller;

import java.io.IOException;

import org.apache.http.ParseException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dxc.constain.AppConst;
import com.dxc.service.DailyReportService;
import com.dxc.service.ModuleService;
import com.dxc.service.ProjectService;

@Controller
public class IndexController {

	@Autowired
	ProjectService projectService;

	@Autowired
	ModuleService moduleService;

	@Autowired
	DailyReportService dailyReportService;

	@RequestMapping("/create4test")
	public void index() {
		try {
			projectService.getProject(AppConst.API_PROJECT);
			moduleService.getModule(AppConst.API_PROJECT);
			dailyReportService.saveOrUpdate(AppConst.API_PROJECT);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
