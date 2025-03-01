package com.mindgate.main.domain;

import java.util.Date;

public class Candidate {
	private String candidateId;
	private JobDescription jobDescription;
	private String name;
	private int age;
	private int experience;
	private String email;
	private int contactNumber;
	private String primarySkill;
	private String secondarySkill;
	private String ternarySkill;
	private Employee employee;
	private Date interviewDate;
	private String sendOfferLetter;

	public Candidate() {
		// TODO Auto-generated constructor stub
	}

	public String getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}

	public JobDescription getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(JobDescription jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(int contactNumber) {
		this.contactNumber = contactNumber;
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

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Date getInterviewDate() {
		return interviewDate;
	}

	public void setInterviewDate(Date interviewDate) {
		this.interviewDate = interviewDate;
	}

	public String getSendOfferLetter() {
		return sendOfferLetter;
	}

	public void setSendOfferLetter(String sendOfferLetter) {
		this.sendOfferLetter = sendOfferLetter;
	}

	public Candidate(String candidateId, JobDescription jobDescription, String name, int age, int experience,
			String email, int contactNumber, String primarySkill, String secondarySkill, String ternarySkill,
			Employee employee, Date interviewDate, String sendOfferLetter) {
		super();
		this.candidateId = candidateId;
		this.jobDescription = jobDescription;
		this.name = name;
		this.age = age;
		this.experience = experience;
		this.email = email;
		this.contactNumber = contactNumber;
		this.primarySkill = primarySkill;
		this.secondarySkill = secondarySkill;
		this.ternarySkill = ternarySkill;
		this.employee = employee;
		this.interviewDate = interviewDate;
		this.sendOfferLetter = sendOfferLetter;
	}

}