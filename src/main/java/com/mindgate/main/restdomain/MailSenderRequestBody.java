package com.mindgate.main.restdomain;

public class MailSenderRequestBody {
	private String to;

	private String employeeId;
	private String candidateId;
	private String jobDescriptionId;
	public MailSenderRequestBody() {
		// TODO Auto-generated constructor stub
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}
	public String getJobDescriptionId() {
		return jobDescriptionId;
	}
	public void setJobDescriptionId(String jobDescriptionId) {
		this.jobDescriptionId = jobDescriptionId;
	}
	public MailSenderRequestBody(String to, String employeeId, String candidateId, String jobDescriptionId) {
		super();
		this.to = to;
		this.employeeId = employeeId;
		this.candidateId = candidateId;
		this.jobDescriptionId = jobDescriptionId;
	}
	
	
	

}
