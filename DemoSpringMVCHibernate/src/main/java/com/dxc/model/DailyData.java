package com.dxc.model;

import java.util.Date;

public class DailyData {

	private int id;
	private String nameProject;
	private String nameModule;
	private double sumCoverage;
	private int sumTechDebt;
	private int sumVulnerability;
	private int sumCodeSmell;
	private int sumTestcase;
	private Date date;

	public DailyData() {

	}

	public DailyData(int id, String nameProject, String nameModule, double sumCoverage, int sumTechDebt,
			int sumVulnerability, int sumCodeSmell, int sumTestcase, Date date) {
		super();
		this.id = id;
		this.nameProject = nameProject;
		this.nameModule = nameModule;
		this.sumCoverage = sumCoverage;
		this.sumTechDebt = sumTechDebt;
		this.sumVulnerability = sumVulnerability;
		this.sumCodeSmell = sumCodeSmell;
		this.sumTestcase = sumTestcase;
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNameProject() {
		return nameProject;
	}

	public void setNameProject(String nameProject) {
		this.nameProject = nameProject;
	}

	public String getNameModule() {
		return nameModule;
	}

	public void setNameModule(String nameModule) {
		this.nameModule = nameModule;
	}

	public double getSumCoverage() {
		return sumCoverage;
	}

	public void setSumCoverage(double sumCoverage) {
		this.sumCoverage = sumCoverage;
	}

	public int getSumTechDebt() {
		return sumTechDebt;
	}

	public void setSumTechDebt(int sumTechDebt) {
		this.sumTechDebt = sumTechDebt;
	}

	public int getSumVulnerability() {
		return sumVulnerability;
	}

	public void setSumVulnerability(int sumVulnerability) {
		this.sumVulnerability = sumVulnerability;
	}

	public int getSumCodeSmell() {
		return sumCodeSmell;
	}

	public void setSumCodeSmell(int sumCodeSmell) {
		this.sumCodeSmell = sumCodeSmell;
	}

	public int getSumTestcase() {
		return sumTestcase;
	}

	public void setSumTestcase(int sumTestcase) {
		this.sumTestcase = sumTestcase;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "DailyData [id=" + id + ", nameProject=" + nameProject + ", nameModule=" + nameModule + ", sumCoverage="
				+ sumCoverage + ", sumTechDebt=" + sumTechDebt + ", sumVulnerability=" + sumVulnerability
				+ ", sumCodeSmell=" + sumCodeSmell + ", sumTestcase=" + sumTestcase + ", date=" + date + "]";
	}
}
