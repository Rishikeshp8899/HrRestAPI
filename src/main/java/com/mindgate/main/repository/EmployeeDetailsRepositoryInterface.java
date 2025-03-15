package com.mindgate.main.repository;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.mindgate.main.domain.Employee;


public interface EmployeeDetailsRepositoryInterface 
{
	public Employee validateEmployee(String username ,String password) throws Exception;
	
	public boolean checkEmployeeExist(String username);
	public Employee getEmployee(Long employeeId) throws Exception;
	public List<Employee> getEmployeeOnWorkBench() throws Exception;
	public boolean updateEmployeeProjectId(Long employeeId,Long projectId) throws Exception;
	public List<Employee> getInterviewerDetails() throws Exception;
	public boolean insertEmployee(Employee employee) throws Exception;
	public boolean deleteEmployeeDetail(String FIRSTNAME,String LASTNAME,int AGE,String ROLE,String PRIMARY_SKILL,String SECONDARY_SKILL,String TERNARY_SKILL,String  DESIGNATION) throws Exception;
}
