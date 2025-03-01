package com.mindgate.main.restcontroller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindgate.main.domain.Employee;
import com.mindgate.main.domain.JobDescription;
import com.mindgate.main.restdomain.RequestManagerRestController;
import com.mindgate.main.service.EmployeeDetailsService;
import com.mindgate.main.service.EmployeeDetailsServiceInterface;
import com.mindgate.main.service.JobDescriptionService;
import com.mindgate.main.service.JobDescriptionServiceInterface;

@RestController
@RequestMapping("rest/employees/projectmanager")
public class ManagerRestController {
	@Autowired
	private JobDescriptionServiceInterface jobDescriptionService;
	@Autowired
	private EmployeeDetailsServiceInterface employeeDetailsService;
	HttpHeaders header = new HttpHeaders();

	Logger logger = LoggerFactory.getLogger(ManagerRestController.class);

	
	@PutMapping("sendtohr")
	public ResponseEntity<String> setvalueOfSendToHr(
			@RequestBody RequestManagerRestController requestManagerRestController) {
		Employee employee = employeeDetailsService.getEmployee(requestManagerRestController.getEmployeeIdPm());
		if(employee==null) {
			logger.info("Record  not found");
			header.add("Record ", "not found");
			return ResponseEntity.status(HttpStatusCode.valueOf(403)).headers(header).body("false");
		}
		if (employee.getRole().equals("Project Manager")) {
			if (jobDescriptionService.updateSendToHr(requestManagerRestController.getJobDescriptionId(),
					requestManagerRestController.getValue())) {
				return ResponseEntity.status(HttpStatusCode.valueOf(201)).body("true");
			}
			return ResponseEntity.status(HttpStatusCode.valueOf(400)).body("false");
		}
		return ResponseEntity.status(HttpStatusCode.valueOf(403)).body("false");
	}
	
	@GetMapping("getjobDescriptionByemployeeId")
	public ResponseEntity<List<JobDescription>> getjobDescriptionByemployeeId(
			@RequestBody RequestManagerRestController requestManagerRestController) {
		Employee employee = employeeDetailsService.getEmployee(requestManagerRestController.getEmployeeIdPm());
		if(employee==null) {
			logger.info("Record  not found");
			header.add("Record ", "not found");
			return ResponseEntity.status(HttpStatusCode.valueOf(403)).headers(header).body(null);
		}
		if (employee.getRole().equals("Project Manager")) {
			List<JobDescription> list=jobDescriptionService.getJobDescriptionByPRojectId(employee.getProject().getProjectId());
			if (!list.isEmpty()) {
				return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(list);
			}
			return ResponseEntity.status(HttpStatusCode.valueOf(400)).body(null);
		}
		return ResponseEntity.status(HttpStatusCode.valueOf(403)).body(null);
	}

	@GetMapping("jobdiscriptionapproval")
	public ResponseEntity<String> setJobDiscriptionApproval(@RequestBody RequestManagerRestController requestManagerRestController) {
		Employee employee = employeeDetailsService.getEmployee(requestManagerRestController.getEmployeeIdPm());
		if (employee.getRole().equals("Project Manager")) {
			if (jobDescriptionService.updateJobDescriptionStatus(requestManagerRestController.getJobDescriptionId(),
					"notFulfiled")) {
				return ResponseEntity.status(HttpStatusCode.valueOf(201)).body("true");
			}
			return ResponseEntity.status(HttpStatusCode.valueOf(400)).body("false");
		}
		return ResponseEntity.status(HttpStatusCode.valueOf(403)).body("false");
	}

	@GetMapping("getemployeeonworkbench")
	public ResponseEntity<List<Employee>> getEmployeeOnWorkbench(
			@RequestBody RequestManagerRestController requestManagerRestController) {
		Employee employee = employeeDetailsService.getEmployee(requestManagerRestController.getEmployeeIdPm());
		if (employee.getRole().equals("Project Manager")) {
			List<Employee> listEmployee = employeeDetailsService.getEmployeeOnWorkBench();
			if (!listEmployee.isEmpty()) {
				return ResponseEntity.ok(listEmployee);
			}
			return ResponseEntity.status(HttpStatusCode.valueOf(204)).body(null);
		}
		return ResponseEntity.status(HttpStatusCode.valueOf(403)).body(null);
	}

	@PutMapping("assignproject")
	public ResponseEntity<String> setEmployeeProjectId(
			@RequestBody RequestManagerRestController requestManagerRestController) {
		Employee employeePm = employeeDetailsService.getEmployee(requestManagerRestController.getEmployeeIdPm());
		JobDescription jobDescription = jobDescriptionService
				.getJobdescriptionById(requestManagerRestController.getJobDescriptionId());
		if (jobDescription == null || employeePm == null) {
			logger.info("Record  not found");
			header.add("Record ", "not found");
			return ResponseEntity.status(HttpStatusCode.valueOf(403)).headers(header).body("false");
		}
		if (!employeePm.getProject().getProjectId().equals(jobDescription.getProjectId().getProjectId())) {
			logger.info("project  not same");
			header.add("project ", "not same");
			return ResponseEntity.status(HttpStatusCode.valueOf(400)).headers(header).body("false");
		}
		if (employeeDetailsService.getEmployee(requestManagerRestController.getEmployeeId()).getProject().getProjectId() != null) {
			header.add("Employee is", "not on workBentch");
			return ResponseEntity.status(HttpStatusCode.valueOf(304)).headers(header).body("false");
		}

		if (employeePm.getRole().equals("Project Manager")) {
			if (employeeDetailsService.updateEmployeeProjectId(requestManagerRestController.getEmployeeId(),
					jobDescription.getProjectId().getProjectId())) {
				if (jobDescriptionService
						.decrementRequiredCandidate(requestManagerRestController.getJobDescriptionId())) {
					return ResponseEntity.status(HttpStatusCode.valueOf(201)).body("true");
				}
				employeeDetailsService.updateEmployeeProjectId(requestManagerRestController.getEmployeeId(), null);
				return ResponseEntity.status(HttpStatusCode.valueOf(304)).body("false");
			}
			return ResponseEntity.status(HttpStatusCode.valueOf(400)).body("false");
		}
		return ResponseEntity.status(HttpStatusCode.valueOf(403)).body("false");
	}

	@PutMapping("updatesalary")
	public ResponseEntity<String> updateJobDescriptionSalary(
			@RequestBody RequestManagerRestController requestManagerRestController) {
		Employee employee = employeeDetailsService.getEmployee(requestManagerRestController.getEmployeeIdPm());
		JobDescription jobDescription = jobDescriptionService
				.getJobdescriptionById(requestManagerRestController.getJobDescriptionId());
		if (jobDescription == null || employee == null) {
			logger.info("Record  not found");
			header.add("Record ", "not found");
			return ResponseEntity.status(HttpStatusCode.valueOf(403)).headers(header).body("false");
		}
		if (employee.getRole().equals("Project Manager")) {
			if (employee.getProject().getProjectId().equals(jobDescription.getProjectId().getProjectId())) {
				if (jobDescriptionService.updateJobDescriptionSalary(requestManagerRestController.getJobDescriptionId(),
						requestManagerRestController.getSalary())) {
					logger.info("Salary  set SUCCESFULL");
					header.add("Salary set ", "SUCCESFULL");
					return ResponseEntity.status(HttpStatusCode.valueOf(201)).headers(header).body("true");
				}
				logger.info("Salary  set false");
				header.add("Salary set", "False");
				return ResponseEntity.status(HttpStatusCode.valueOf(400)).headers(header).body("false");
			}
			logger.info("Project id false");
			header.add("project id", "False");
			return ResponseEntity.status(HttpStatusCode.valueOf(400)).headers(header).body("false");
		}
		logger.info("Role mismatch ");
		header.add("Role mismatch", "False");
		return ResponseEntity.status(HttpStatusCode.valueOf(403)).headers(header).body("false");
	}

}
