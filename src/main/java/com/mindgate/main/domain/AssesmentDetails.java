package com.mindgate.main.domain;

import java.sql.Date;
import java.time.LocalDate;

public class AssesmentDetails 
{
	private String assesmentId;
    private String techSkill;
    private String communication;
    private String softSkills;
    private Candidate candidate;
    private String status;
    private LocalDate interviewDate;
    

    public AssesmentDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

    public AssesmentDetails(String assesmentId, String techSkill, String communication, String softSkills, Candidate candidate,
            String status, LocalDate interviewDate) {
        super();
        this.assesmentId = assesmentId;
        this.techSkill = techSkill;
        this.communication = communication;
        this.softSkills = softSkills;
        this.candidate = candidate;
        this.status = status;
        this.interviewDate = interviewDate;
    }

    public String getAssesmentId() {
        return assesmentId;
    }

    public void setAssesmentId(String assesmentId) {
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

    public String getSoftSkills() {
        return softSkills;
    }

    public void setSoftSkills(String softSkills) {
        this.softSkills = softSkills;
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

    public LocalDate getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(LocalDate interviewDate) {
        this.interviewDate = interviewDate;
    }

    @Override
    public String toString() {
        return "AssesmentDetails [assesmentId=" + assesmentId + ", techSkill=" + techSkill + ", communication=" + communication
                + ", softSkills=" + softSkills + ", candidateId=" + candidate + ", status=" + status
                + ", interviewDate=" + interviewDate + "]";
    }
    

}
