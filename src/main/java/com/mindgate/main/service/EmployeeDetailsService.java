package com.mindgate.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindgate.main.domain.Employee;
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
	public List<Employee> getEmployeeOnWorkbench() 
	{
		
		return employeeDetailsRepositoryInterface.getEmployeeOnWorkbench();
	}

	@Override
	public boolean assignEmployeeToProject(String projectId, String employeeId) 
	{
		
		return employeeDetailsRepositoryInterface.assignEmployeeToProject(projectId, employeeId);
	}

	@Override
	public boolean removeProjectId(String employeeId)
	{
		// TODO Auto-generated method stub
		return employeeDetailsRepositoryInterface.removeProjectId(employeeId);
	}
	
	@Override
    public List<Employee> getInterviewerDetails() {
        List<Employee> getInterviewerDetailsList =employeeDetailsRepositoryInterface.getInterviewerDetails();
        
        if(!getInterviewerDetailsList.isEmpty())
            return getInterviewerDetailsList;
        return null;
    }

	@Override
	public boolean addEmplyee(Employee employee) 
	{
		return employeeDetailsRepositoryInterface.addEmplyee(employee);
	}

	@Override
	public boolean deleteEployee(String fristName, String DESIGNATION, String PRIMARY_SKILL, String SECONDARY_SKILL,
			String TERNARY_SKILL) {
		
		return employeeDetailsRepositoryInterface.deleteEployee(fristName, DESIGNATION, PRIMARY_SKILL, SECONDARY_SKILL, TERNARY_SKILL);
	}
}
