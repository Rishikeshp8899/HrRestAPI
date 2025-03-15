package com.mindgate.main.domain;


import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;


@Entity
public class Assesment {
	
	 @Id 
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long assesmentId;
private String techSkill;
private String communication;
private String softSkill;

@OneToOne
private Candidate candidate;
private String status;
private Date interviewDate;

public Assesment() {}

public Long getAssesmentId() {
	return assesmentId;
}

public void setAssesmentId(Long assesmentId) {
	this.assesmentId = assesmentId;
}

public String getTechSkill() {
	return techSkill;
}

public void setTechSkill(String techSkill) {
	this.techSkill = techSkill;
}

public String getCommunication() {
	return communication;
}

public void setCommunication(String communication) {
	this.communication = communication;
}

public String getSoftSkill() {
	return softSkill;
}

public void setSoftSkill(String softSkill) {
	this.softSkill = softSkill;
}

public Candidate getCandidate() {
	return candidate;
}

public void setCandidate(Candidate candidate) {
	this.candidate = candidate;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public Date getInterviewDate() {
	return interviewDate;
}

public void setInterviewDate(Date interviewDate) {
	this.interviewDate = interviewDate;
}

public Assesment(Long assesmentId, String techSkill, String communication, String softSkill, Candidate candidate,
		String status, Date interviewDate) {
	super();
	this.assesmentId = assesmentId;
	this.techSkill = techSkill;
	this.communication = communication;
	this.softSkill = softSkill;
	this.candidate = candidate;
	this.status = status;
	this.interviewDate = interviewDate;
}

@Override
public String toString() {
	return "Assesment [assesmentId=" + assesmentId + ", techSkill=" + techSkill + ", communication=" + communication
			+ ", softSkill=" + softSkill + ", candidate=" + candidate + ", status=" + status + ", interviewDate="
			+ interviewDate + "]";
}



}
