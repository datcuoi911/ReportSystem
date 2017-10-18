package dxc.technology.controller;

import java.io.IOException;
import java.util.Date;

import org.apache.http.ParseException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import dxc.technology.constain.AppConst;
import dxc.technology.service.DailyReportService;
import dxc.technology.service.ModuleService;
import dxc.technology.service.ProjectService;

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
			dailyReportService.saveOrUpdate(AppConst.API_PROJECT, new Date());
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
