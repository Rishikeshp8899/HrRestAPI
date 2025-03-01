package com.mindgate.main.domain;

public class Project {
	private String projectId;
	private String projectname;
	private String describsion;
	private long budget;
	private String status;
	private double availableFund;

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
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

	public long getBudget() {
		return budget;
	}

	public void setBudget(long budget) {
		this.budget = budget;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getAvailableFund() {
		return availableFund;
	}

	public void setAvailable_fund(double availableFund) {
		this.availableFund = availableFund;
	}

	public Project(String projectId, String projectname, String describsion, long budget, String status,
			double availableFund) {

		this.projectId = projectId;
		this.projectname = projectname;
		this.describsion = describsion;
		this.budget = budget;
		this.status = status;
		this.availableFund = availableFund;
	}

	public Project() {
	}

	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", projectname=" + projectname + ", describsion=" + describsion
				+ ", budget=" + budget + ", status=" + status + ", availableFund=" + availableFund + "]";
	}

}
