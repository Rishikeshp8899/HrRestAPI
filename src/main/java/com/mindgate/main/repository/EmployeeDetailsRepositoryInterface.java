package com.mindgate.main.repository;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mindgate.main.domain.Employee;


public interface EmployeeDetailsRepositoryInterface 
{
	public Employee validateEmployee(String username ,String password);
	
	public boolean checkEmployeeExist(String username);
	public Employee getEmployee(String employeeId);
	public List<Employee> getEmployeeOnWorkBench();
	public boolean updateEmployeeProjectId(String employeeId, String projectId);
	public List<Employee> getInterviewerDetails();
	public boolean insertEmployee(Employee employee);
	public boolean deleteEmployeeDetail(String FIRSTNAME,String LASTNAME,int AGE,String ROLE,String PRIMARY_SKILL,String SECONDARY_SKILL,String TERNARY_SKILL,String  DESIGNATION);
}
