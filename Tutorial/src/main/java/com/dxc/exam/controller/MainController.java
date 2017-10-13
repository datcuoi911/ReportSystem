package com.dxc.exam.controller;

import java.io.IOException;

import org.apache.http.ParseException;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.dxc.exam.service.DailyReportService;

@Controller
@Transactional
@EnableWebMvc
public class MainController {

	@Autowired
	DailyReportService dailyReportService;

	@RequestMapping(value = { "/", "/welcome" }, method = RequestMethod.GET)
	public String welcomePage() throws ParseException, IOException, JSONException {
		return "welcome";
	}

	@RequestMapping("/register")
	public String index() throws ParseException, IOException, JSONException {
		return "register";
	}

	@RequestMapping({ "/userlist" })
	public String deptList(Model model) {
		return "userlist";
	}
}
