package com.mindgate.main.domain;

public class Employee {
	private String employeeId;
	private String firstname;
	private String lastname;
	private String email;
	private int age;
	private String role;
	private Project project;
	private String is_interviewer;
	private String password;
	private String designation;
	private String primarySkill;
	private String secondarySkill;
	private String ternarySkill;

	public Employee() {
		// TODO Auto-generated constructor stub
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Employee(String employeeId, String firstname, String lastname, String email, int age, String role,
			Project project, String is_interviewer, String password, String designation, String primarySkill,
			String secondarySkill, String ternarySkill) {
		this.employeeId = employeeId;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.age = age;
		this.role = role;
		this.project = project;
		this.is_interviewer = is_interviewer;
		this.password = password;
		this.designation = designation;
		this.primarySkill = primarySkill;
		this.secondarySkill = secondarySkill;
		this.ternarySkill = ternarySkill;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", firstname=" + firstname + ", lastname=" + lastname + ", email="
				+ email + ", age=" + age + ", role=" + role + ", project=" + project + ", is_interviewer="
				+ is_interviewer + ", password=" + password + ", designation=" + designation + ", primarySkill="
				+ primarySkill + ", secondarySkill=" + secondarySkill + ", ternarySkill=" + ternarySkill + "]";
	}

}