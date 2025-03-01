package com.mindgate.main.restdomain;

public class RequestBodyForInterviewer {

	private String emplpoyeeId;
	private String candidateId;
	private String tectSkill;
	private String communication;
	private String softSkills;
	private String status;
	
	public RequestBodyForInterviewer() {
		// TODO Auto-generated constructor stub
	}

	public RequestBodyForInterviewer(String emplpoyeeId, String candidateId, String tectSkill, String communication,
			String softSkills, String status) {
		super();
		this.emplpoyeeId = emplpoyeeId;
		this.candidateId = candidateId;
		this.tectSkill = tectSkill;
		this.communication = communication;
		this.softSkills = softSkills;
		this.status = status;
	}

	public String getEmplpoyeeId() {
		return emplpoyeeId;
	}

	public void setEmplpoyeeId(String emplpoyeeId) {
		this.emplpoyeeId = emplpoyeeId;
	}

	public String getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}

	public String getTectSkill() {
		return tectSkill;
	}

	public void setTectSkill(String tectSkill) {
		this.tectSkill = tectSkill;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "RequestBodyForInterviewer [emplpoyeeId=" + emplpoyeeId + ", candidateId=" + candidateId + ", tectSkill="
				+ tectSkill + ", communication=" + communication + ", softSkills=" + softSkills + ", status=" + status
				+ "]";
	}
	
	
	
}
