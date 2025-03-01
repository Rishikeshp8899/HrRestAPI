package com.mindgate.main.domain;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;

public class Candidate {
    private String  candidateId;
    private JobDescription  jobDesignation;
    private String firstName;
    private String lastName;
    private int age;
    private int experience;
    private String email;
    private BigDecimal contactNumber;
    private String primarySkill;
    private String secondarySkill;
    private String ternarySkill;
    private Employee employee;
    private LocalDate interview_schedule;
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

	public JobDescription getJobDesignation() {
		return jobDesignation;
	}

	public void setJobDesignation(JobDescription jobDesignation) {
		this.jobDesignation = jobDesignation;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public BigDecimal getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(BigDecimal contactNumber) {
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

	public LocalDate getInterview_schedule() {
		return interview_schedule;
	}

	public void setInterview_schedule(LocalDate interview_schedule) {
		this.interview_schedule = interview_schedule;
	}

	public String getSendOfferLetter() {
		return sendOfferLetter;
	}

	public void setSendOfferLetter(String sendOfferLetter) {
		this.sendOfferLetter = sendOfferLetter;
	}

	public Candidate(String candidateId, JobDescription jobDesignation, String firstName, String lastName, int age,
			int experience, String email, BigDecimal contactNumber, String primarySkill, String secondarySkill,
			String ternarySkill, Employee employee, LocalDate interview_schedule, String sendOfferLetter) {
		this.candidateId = candidateId;
		this.jobDesignation = jobDesignation;
		this.firstName = firstName;
		this.lastName = lastName;
		this.age = age;
		this.experience = experience;
		this.email = email;
		this.contactNumber = contactNumber;
		this.primarySkill = primarySkill;
		this.secondarySkill = secondarySkill;
		this.ternarySkill = ternarySkill;
		this.employee = employee;
		this.interview_schedule = interview_schedule;
		this.sendOfferLetter = sendOfferLetter;
	}

	@Override
	public String toString() {
		return "Candidate [candidateId=" + candidateId + ", jobDesignation =" + jobDesignation + ", firstName="
				+ firstName + ", lastName=" + lastName + ", age=" + age + ", experience=" + experience + ", email="
				+ email + ", contactNumber=" + contactNumber + ", primarySkill=" + primarySkill + ", secondarySkill="
				+ secondarySkill + ", ternarySkill=" + ternarySkill + ", employee=" + employee + ", interview_schedule="
				+ interview_schedule + ", sendOfferLetter=" + sendOfferLetter + "]";
	}

		
}
