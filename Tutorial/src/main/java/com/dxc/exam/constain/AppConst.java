package com.dxc.exam.constain;

public class AppConst {

	public static final String API_PROJECT = "http://ec2-54-169-150-141.ap-southeast-1.compute.amazonaws.com/sonarqube/api/components/search_projects?ps=50&facets=reliability_rating%2Csecurity_rating%2Csqale_rating%2Ccoverage%2Cduplicated_lines_density%2Cncloc%2Calert_status";

	public static final String API_HEAD = "http://ec2-54-169-150-141.ap-southeast-1.compute.amazonaws.com/sonarqube/api/measures/component?additionalFields=metrics%2Cperiods&componentKey=";

	public static final String API_FOOT = "&metricKeys=alert_status%2Cquality_gate_details%2Cbugs%2Cnew_bugs%2Creliability_rating%2Cnew_reliability_rating%2Cvulnerabilities%2Cnew_vulnerabilities%2Csecurity_rating%2Cnew_security_rating%2Ccode_smells%2Cnew_code_smells%2Csqale_rating%2Cnew_maintainability_rating%2Csqale_index%2Cnew_technical_debt%2Ccoverage%2Cnew_coverage%2Cnew_lines_to_cover%2Ctests%2Cduplicated_lines_density%2Cnew_duplicated_lines_density%2Cduplicated_blocks%2Cncloc%2Cncloc_language_distribution%2Cnew_lines";

}
