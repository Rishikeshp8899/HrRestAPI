package com.mindgate.main.domain;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long projectId;
	private String projectname;
	private String describsion;
	private double budget;
	private String status;
	private double available_fund;

	@OneToOne
	private JobDescription jobDescription;
	

	@OneToMany(mappedBy="employeeId")
	private List<Employee> employee;
	
	public Long getProjectId() {
		return projectId;
	}

	public JobDescription getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(JobDescription jobDescription) {
		this.jobDescription = jobDescription;
	}

	public List<Employee> getEmployee() {
		return employee;
	}

	public void setEmployee(List<Employee> employee) {
		this.employee = employee;
	}

	public void setProjectId(Long projectId) {
		this.projectId = projectId;
	}

	public String getProjectname() {
		return projectname;
	}

	public void setProjectname(String projectname) {
		this.projectname = projectname;
	}

	public String getDescribsion() {
		return describsion;
	}

	public void setDescribsion(String describsion) {
		this.describsion = describsion;
	}

	public double getBudget() {
		return budget;
	}

	public void setBudget(double d) {
		this.budget = d;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getAvailable_fund() {
		return available_fund;
	}

	public void setAvailable_fund(double d) {
		this.available_fund = d;
	}

	public Project(Long projectId, String projectname, String describsion, double budget, String status,
			double available_fund) {

		this.projectId = projectId;
		this.projectname = projectname;
		this.describsion = describsion;
		this.budget = budget;
		this.status = status;
		this.available_fund = available_fund;
	}

	public Project() {
	}

}