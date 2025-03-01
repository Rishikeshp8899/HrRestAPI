package com.mindgate.main.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.mindgate.main.domain.Employee;


public interface EmployeeDetailsRepositoryInterface 
{
	public Employee validateEmployee(String username ,String password);
	
	public boolean checkEmployeeExist(String username);
	
	public List<Employee> getEmployeeOnWorkbench();
	
	public boolean assignEmployeeToProject(String projectId,String employeeId);
	
	public boolean removeProjectId(String employeeId);
	
	public List<Employee> getInterviewerDetails();
	
	public boolean addEmplyee(Employee employee);
	
	public boolean deleteEployee(String fristName,String DESIGNATION,String PRIMARY_SKILL,String SECONDARY_SKILL,String TERNARY_SKILL);
	
	
	
}
