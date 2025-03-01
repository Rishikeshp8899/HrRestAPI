package com.mindgate.main.domain;

public class JobDescription {
    
    private String jobDescriptionId;
    private String role;
    private double salary;
    private String location;
    private String primarySkill;
    private String secondarySkill;
    private String ternarySkill;
    private int experience;
    private String about;
    private Project projectId;
    private String status;
    private int requiredCandidate;
    private String sendToHr;
    private String  postJobDescription;

    

    public JobDescription() {
        // TODO Auto-generated constructor stub
    }



	public String getJobDescriptionId() {
		return jobDescriptionId;
	}



	public void setJobDescriptionId(String jobDescriptionId) {
		this.jobDescriptionId = jobDescriptionId;
	}



	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}



	public double getSalary() {
		return salary;
	}



	public void setSalary(double salary) {
		this.salary = salary;
	}



	public String getLocation() {
		return location;
	}



	public void setLocation(String location) {
		this.location = location;
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



	public int getExperience() {
		return experience;
	}



	public void setExperience(int experience) {
		this.experience = experience;
	}



	public String getAbout() {
		return about;
	}



	public void setAbout(String about) {
		this.about = about;
	}



	public Project getProjectId() {
		return projectId;
	}



	public void setProjectId(Project projectId) {
		this.projectId = projectId;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public int getRequiredCandidate() {
		return requiredCandidate;
	}



	public void setRequiredCandidate(int requiredCandidate) {
		this.requiredCandidate = requiredCandidate;
	}



	public String getSendToHr() {
		return sendToHr;
	}



	public void setSendToHr(String sendToHr) {
		this.sendToHr = sendToHr;
	}



	public String getPostJobDescription() {
		return postJobDescription;
	}



	public void setPostJobDescription(String postJobDescription) {
		this.postJobDescription = postJobDescription;
	}



	public JobDescription(String jobDescriptionId, String role, double salary, String location, String primarySkill,
			String secondarySkill, String ternarySkill, int experience, String about, Project projectId, String status,
			int requiredCandidate, String sendToHr, String postJobDescription) {
		super();
		this.jobDescriptionId = jobDescriptionId;
		this.role = role;
		this.salary = salary;
		this.location = location;
		this.primarySkill = primarySkill;
		this.secondarySkill = secondarySkill;
		this.ternarySkill = ternarySkill;
		this.experience = experience;
		this.about = about;
		this.projectId = projectId;
		this.status = status;
		this.requiredCandidate = requiredCandidate;
		this.sendToHr = sendToHr;
		this.postJobDescription = postJobDescription;
	}

   




    
}
