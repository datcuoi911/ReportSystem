package com.dxc.exam.temp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.dxc.exam.entity.DailyReport;

public class Test {

	public static Date getDateInPreviousWeek() {
		Date today = new Date();
		Calendar cal = Calendar.getInstance();
		cal.setTime(today);
		cal.add(Calendar.DATE, -7);
		return cal.getTime();
	}
	
	public void temp() throws ParseException {
		Date datetimetoday = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy");
		String todaystr = sf.format(datetimetoday);
		Date today = sf.parse(todaystr);
	}

	public static void main(String[] args) throws ParseException {
		
		
		
		
		
		
	}

}
