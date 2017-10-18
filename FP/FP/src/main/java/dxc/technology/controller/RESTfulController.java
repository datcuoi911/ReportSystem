package dxc.technology.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dxc.technology.model.DailyData;
import dxc.technology.model.ShortData;
import dxc.technology.service.APIService;
import dxc.technology.service.DailyReportService;
import dxc.technology.service.ModuleService;
import dxc.technology.service.ProjectService;

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

	@CrossOrigin
	@RequestMapping(value = "/allprojects", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public ArrayList<DailyData> showAllProjects() throws ParseException {
		Date date = new SimpleDateFormat("yyyy-MM-dd").parse("2017-10-17");
		return apiService.getDailyData(date);
	}

	@CrossOrigin
	@RequestMapping(value = "/{columnName}/{projectId}", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
	public ArrayList<ShortData> showAllTectDebtinMonth(@PathVariable("projectId") int projectId,
			@PathVariable("columnName") String columnName) throws ParseException {
		return dailyReportService.getMonthlyData(projectId, columnName);
	}

}
