package com.mindgate.main.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long employeeId;
	private String firstname;
	private String lastname;
	private int age;
	private String role;
	
	@ManyToOne()
	private Project project;
	private String is_interviewer;
	private String password;
	private String designation;
	private String primarySkill;
	private String secondarySkill;
	private String ternarySkill;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Candidate> getCandidate() {
		return Candidate;
	}

	public void setCandidate(List<Candidate> candidate) {
		Candidate = candidate;
	}

	
	private String username;

	@OneToMany(mappedBy = "candidateId", cascade = CascadeType.ALL)
	private List<Candidate> Candidate;

	public Long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getIs_interviewer() {
		return is_interviewer;
	}

	public void setIs_interviewer(String is_interviewer) {
		this.is_interviewer = is_interviewer;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
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

	
	public Employee(Long employeeId, String firstname, String lastname, int age, String role, Project project,
			String is_interviewer, String password, String designation, String primarySkill, String secondarySkill,
			String ternarySkill, String username, List<com.mindgate.main.domain.Candidate> candidate) {
		super();
		this.employeeId = employeeId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.age = age;
		this.role = role;
		this.project = project;
		this.is_interviewer = is_interviewer;
		this.password = password;
		this.designation = designation;
		this.primarySkill = primarySkill;
		this.secondarySkill = secondarySkill;
		this.ternarySkill = ternarySkill;
		this.username = username;
		Candidate = candidate;
	}



	public Employee() {
	}

}