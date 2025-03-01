package com.mindgate.main.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mindgate.main.domain.Employee;

public interface EmployeeDetailsServiceInterface 
{
	public Employee validateEmployee(String username ,String password);
	
	public boolean checkEmployeeExist(String username);
	
	public Employee getEmployee(String employeeId);
	public List<Employee> getEmployeeOnWorkBench();
	public boolean updateEmployeeProjectId(String employeeId, String projectId);
	public List<Employee> getInterviewerDetails();
	public boolean insertEmployee(Employee employee);
	public boolean deleteEmployeeDetail(Employee employee);
}
