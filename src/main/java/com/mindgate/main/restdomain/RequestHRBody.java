package com.mindgate.main.restdomain;

import java.util.Date;

public class RequestHRBody {
	private String employeeId;
	private String jobDescriptionId;
	private String primarySkill;
	private String secondarySkill;
	private String ternarySkill;
	private String candidateId;
	private String interviewer;
	private Date interviewDate;

	public RequestHRBody() {
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getJobDescriptionId() {
		return jobDescriptionId;
	}

	public void setJobDescriptionId(String jobDescriptionId) {
		this.jobDescriptionId = jobDescriptionId;
	}

	public String getPrimarySkill() {
		return primarySkill;
	}

	public void setPrimarySkill(String primarySkill) {
		this.primarySkill = primarySkill;
	}

	public String getSecondarySkill() {
		return secondarySkill;
	}

	public void setSecondarySkill(String secondarySkill) {
		this.secondarySkill = secondarySkill;
	}

	public String getTernarySkill() {
		return ternarySkill;
	}

	public void setTernarySkill(String ternarySkill) {
		this.ternarySkill = ternarySkill;
	}

	public String getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}

	public String getInterviewer() {
		return interviewer;
	}

	public void setInterviewer(String interviewer) {
		this.interviewer = interviewer;
	}
	

	public Date getInterviewDate() {
		return interviewDate;
	}

	public void setInterviewDate(Date interviewDate) {
		this.interviewDate = interviewDate;
	}

	public RequestHRBody(String employeeId, String jobDescriptionId, String primarySkill, String secondarySkill,
			String ternarySkill, String candidateId, String interviewer, Date interviewDate) {
		super();
		this.employeeId = employeeId;
		this.jobDescriptionId = jobDescriptionId;
		this.primarySkill = primarySkill;
		this.secondarySkill = secondarySkill;
		this.ternarySkill = ternarySkill;
		this.candidateId = candidateId;
		this.interviewer = interviewer;
		this.interviewDate = interviewDate;
	}

	


}
