package com.mindgate.main.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindgate.main.domain.Employee;
import com.mindgate.main.domain.JobDescription;
import com.mindgate.main.repository.EmployeeDetailsRepository;
import com.mindgate.main.repository.EmployeeDetailsRepositoryInterface;

@Service
public class EmployeeDetailsService implements EmployeeDetailsServiceInterface
{
	@Autowired
	private EmployeeDetailsRepository employeeDetailsRepositoryInterface;
	
	@Override
	public Employee validateEmployee(String username, String password) 
	{
		return employeeDetailsRepositoryInterface.validateEmployee(username, password);
	}

	@Override
	public boolean checkEmployeeExist(String username) 
	{
		
		return employeeDetailsRepositoryInterface.checkEmployeeExist(username);
	}

	@Override
	public Employee getEmployee(String employeeId) {
		
		Employee employee=  employeeDetailsRepositoryInterface.getEmployee(employeeId);
		if(employee != null )
			return employee;
		return null;
	}

	@Override
	public boolean updateEmployeeProjectId(String employeeId, String projectId) {
		 if(employeeDetailsRepositoryInterface.updateEmployeeProjectId(employeeId, projectId))
	            return true;
		return false;
	}

	@Override
	public List<Employee> getEmployeeOnWorkBench() {
		List<Employee> getEmployeeByWorkBench = employeeDetailsRepositoryInterface.getEmployeeOnWorkBench();
		
		if(!getEmployeeByWorkBench.isEmpty())
			return getEmployeeByWorkBench;
		return null;
	}

	@Override
	public List<Employee> getInterviewerDetails() {
		List<Employee> getInterviewerDetailsList =employeeDetailsRepositoryInterface.getInterviewerDetails();
		
		if(!getInterviewerDetailsList.isEmpty())
			return getInterviewerDetailsList;
		return null;
	}

	@Override
	public boolean insertEmployee(Employee employee) {
		System.out.println("in service");
		boolean result=employeeDetailsRepositoryInterface.insertEmployee(employee);
		System.out.println(result);
		if(result)
			return true;
		return false;
	}
//String name, int age, int experience, String email, long contactNumber,String primarySkills, String secondarySkills, String ternarySkills,Date INTERVIEW_SCHEDULE
	@Override
	public boolean deleteEmployeeDetail(Employee employee) {
		// TODO Auto-generated method stub
		//String FIRSTNAME, String LASTNAME, int AGE, String ROLE, String PRIMARY_SKILL, String SECONDARY_SKILL, String TERNARY_SKILL, String DESIGNATION
		if(employeeDetailsRepositoryInterface.deleteEmployeeDetail(employee.getFirstname(), employee.getLastname(), employee.getAge(), employee.getRole(), employee.getPrimarySkill(), employee.getSecondarySkill(), employee.getTernarySkill(), employee.getDesignation()))
			return true;
		return false;
	}

}
