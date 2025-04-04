package com.mindgate.main.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mindgate.main.domain.Employee;
import com.mindgate.main.domain.JobDescription;
import com.mindgate.main.repository.EmployeeDetailsRepositoryInterface;

@Service
public class EmployeeDetailsService implements EmployeeDetailsServiceInterface {
	@Autowired
	@Qualifier("EmployeeRepoSDKImpl")
	private EmployeeDetailsRepositoryInterface employeeDetailsRepositoryInterface;

	@Override
	public Employee validateEmployee(String username, String password) {
		try {
			return employeeDetailsRepositoryInterface.validateEmployee(username, password);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean checkEmployeeExist(String username) {

		return employeeDetailsRepositoryInterface.checkEmployeeExist(username);
	}

	@Override
	public Employee getEmployee(String employeeId) {

		Employee employee = null;
		try {
			employee = employeeDetailsRepositoryInterface.getEmployee(Long.parseLong(employeeId));
		} catch (Exception e) {

			e.printStackTrace();
		}
		return employee;
	}

	@Override
	public boolean updateEmployeeProjectId(String employeeId, String projectId) {
		try {
			if (employeeDetailsRepositoryInterface.updateEmployeeProjectId(Long.parseLong(employeeId),
					Long.parseLong(projectId)))
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Employee> getEmployeeOnWorkBench() {
		List<Employee> getEmployeeByWorkBench = null;
		try {
			getEmployeeByWorkBench = employeeDetailsRepositoryInterface.getEmployeeOnWorkBench();
		} catch (Exception e) {

			e.printStackTrace();
		}

		return getEmployeeByWorkBench;
	}

	@Override
	public List<Employee> getInterviewerDetails() {
		List<Employee> getInterviewerDetailsList = null;
		try {
			getInterviewerDetailsList = employeeDetailsRepositoryInterface.getInterviewerDetails();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return getInterviewerDetailsList;
	}

	@Override
	public boolean insertEmployee(Employee employee) {
		System.out.println("in service");
		boolean result = false;
		try {
			result = employeeDetailsRepositoryInterface.insertEmployee(employee);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return result;
	}

	@Override
	public boolean deleteEmployeeDetail(Employee employee) {
		try {
			if (employeeDetailsRepositoryInterface.deleteEmployeeDetail(employee.getFirstname(), employee.getLastname(),
					employee.getAge(), employee.getRole(), employee.getPrimarySkill(), employee.getSecondarySkill(),
					employee.getTernarySkill(), employee.getDesignation()))
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
