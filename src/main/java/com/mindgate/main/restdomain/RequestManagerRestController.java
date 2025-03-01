package com.mindgate.main.restdomain;

public class RequestManagerRestController {

	private String jobDescriptionId;
	private String employeeId;
	private String employeeIdPm;
	private String projectId;
	private double salary;
	private String value;
	public RequestManagerRestController() {}
	public RequestManagerRestController(String jobDescriptionId, String employeeId, String employeeIdPm,
			String projectId, double salary, String value) {
		super();
		this.jobDescriptionId = jobDescriptionId;
		this.employeeId = employeeId;
		this.employeeIdPm = employeeIdPm;
		this.projectId = projectId;
		this.salary = salary;
		this.value = value;
	}
	public String getJobDescriptionId() {
		return jobDescriptionId;
	}
	public void setJobDescriptionId(String jobDescriptionId) {
		this.jobDescriptionId = jobDescriptionId;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeeIdPm() {
		return employeeIdPm;
	}
	public void setEmployeeIdPm(String employeeIdPm) {
		this.employeeIdPm = employeeIdPm;
	}
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
