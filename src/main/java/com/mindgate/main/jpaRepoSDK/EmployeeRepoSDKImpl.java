package com.mindgate.main.jpaRepoSDK;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindgate.main.domain.Employee;
import com.mindgate.main.domain.Project;
import com.mindgate.main.jpaRepo.EmployeeRepo;
import com.mindgate.main.jpaRepo.ProjectRepo;
import com.mindgate.main.repository.EmployeeDetailsRepositoryInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service("EmployeeRepoSDKImpl")
public class EmployeeRepoSDKImpl implements EmployeeDetailsRepositoryInterface {

	Logger logger = LoggerFactory.getLogger(EmployeeRepoSDKImpl.class);

	@Autowired
	private EmployeeRepo employeeRepo;

	@Autowired
	private ProjectRepo projectRepo;

	@Override
	public Employee validateEmployee(String username, String password) throws Exception {

		logger.info("EmployeeRepoSDKImpl --> validateEmployee --> insider");

		Employee employee = employeeRepo.findByPasswordAndUsername(username, password);
		if (employee == null) {
			logger.error("employee is not present in db");
			throw new Exception("Validation failed");
		}
		return employee;
	}

	@Override
	public boolean checkEmployeeExist(String username) {
		logger.info("EmployeeRepoSDKImpl --> validateEmployee --> insider");
		Employee employee = employeeRepo.getByUsername(username);
		if (employee == null) {
			logger.info("username is not in db");
			return true;
		}
		logger.error("username is present in db");
		return false;
	}

	@Override
	public Employee getEmployee(Long employeeId) throws Exception {
		logger.info("EmployeeRepoSDKImpl --> getEmployee --> insider");
		Employee employee = employeeRepo.getReferenceById(employeeId);
		if (employee == null) {
			logger.error("employee is not present in db");
			throw new Exception("Validation failed");
		}

		logger.info(employee.toString());
		return employee;
	}

	@Override
	public List<Employee> getEmployeeOnWorkBench() throws Exception {
		logger.info("EmployeeRepoSDKImpl --> getEmployeeOnWorkBench --> insider");
		List<Employee> listEmployee = employeeRepo.findAll().stream().filter(n -> n.getProject() == null).toList();
		if (listEmployee.isEmpty())
			throw new Exception("No one on work Bench");
		logger.info(listEmployee.toString());
		return listEmployee;
	}

	@Override
	public boolean updateEmployeeProjectId(Long employeeId, Long projectId) throws Exception {
		logger.info("EmployeeRepoSDKImpl --> updateEmployeeProjectId --> insider");
		Employee employee = employeeRepo.getReferenceById(projectId);

		if (employee == null)
			throw new Exception("employee is not presence");

		Project project = projectRepo.getReferenceById(projectId);

		if (project == null)
			throw new Exception("project is not present in db");
		employee.setProject(project);

		employee = employeeRepo.save(employee);
		if (employee == null) {
			logger.debug("employee is null");
			return false;
		}
		return true;
	}

	@Override
	public List<Employee> getInterviewerDetails() throws Exception {
		logger.info("EmployeeRepoSDKImpl --> getInterviewerDetails --> insider");
		List<Employee> listEmployee = employeeRepo.findAll().stream()
				.filter(n -> n.getIs_interviewer().equalsIgnoreCase("yse")).toList();
		if (listEmployee.isEmpty())
			throw new Exception("No employee assigned as interview");
		return listEmployee;
	}

	@Override
	public boolean insertEmployee(Employee employee) throws Exception {
		logger.info("EmployeeRepoSDKImpl --> insertEmployee --> insider");
		Employee employee2 = employeeRepo.save(employee);
		if (employee2 == null)
			return false;
		return true;
	}

	@Override
	public boolean deleteEmployeeDetail(String FIRSTNAME, String LASTNAME, int AGE, String ROLE, String PRIMARY_SKILL,
			String SECONDARY_SKILL, String TERNARY_SKILL, String DESIGNATION) throws Exception {
		logger.info("EmployeeRepoSDKImpl --> deleteEmployeeDetail --> insider");
		try {
			Employee employee = new Employee();
			employee.setFirstname(FIRSTNAME);
			employee.setLastname(LASTNAME);
			employee.setAge(AGE);
			employee.setRole(ROLE);
			employee.setPrimarySkill(PRIMARY_SKILL);
			employee.setSecondarySkill(SECONDARY_SKILL);
			employee.setTernarySkill(TERNARY_SKILL);
			employee.setDesignation(DESIGNATION);
			employeeRepo.delete(employee);
			return true;
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return false;
	}

}
