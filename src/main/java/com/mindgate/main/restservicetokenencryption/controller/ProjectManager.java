package com.mindgate.main.restservicetokenencryption.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindgate.main.domain.Employee;
import com.mindgate.main.domain.JobDescription;
import com.mindgate.main.restdomain.RequestManagerRestController;
import com.mindgate.main.restservicetokenencryption.HelperClass;
import com.mindgate.main.restservicetokenencryption.TokenWrapper;
import com.mindgate.main.service.EmployeeDetailsService;
import com.mindgate.main.service.EmployeeDetailsServiceInterface;
import com.mindgate.main.service.JobDescriptionService;

@RestController
@RequestMapping("token/projectmanager")
public class ProjectManager {

	@Autowired
	private JobDescriptionService jobDescriptionService;

	@Autowired
	private EmployeeDetailsServiceInterface employeeDetailsServiceInterface;
	@Autowired
	private HelperClass helperClass;

	@RequestMapping("sendToHr")
	public ResponseEntity<String> setSendToHr(@RequestHeader(value = "token") String token,
			@RequestParam(value = "JdId") String JbId, @RequestParam(value = "value") String value) {
		TokenWrapper tokenWrapper = helperClass.decryptData(token);
		HttpHeaders header = new HttpHeaders();
		if (helperClass.checkValidationOfToken( token)) {
			if (helperClass.checkDateValidation(tokenWrapper.getDate())) {
				if (helperClass.checkRoleValidation((Employee) tokenWrapper.getOriginalObject(), "Project Manager")) {
					if (jobDescriptionService.updateSendToHr(JbId, value)) {
						header.add("Send to Hr", "SUCCESFULL");
						return ResponseEntity.status(HttpStatusCode.valueOf(200)).headers(header).body("true");
					}
					header.add("Send to Hr", "Failed");
					return ResponseEntity.status(HttpStatusCode.valueOf(204)).headers(header).body("false");
				}
				header.add("Role", "Mismatch");
				return ResponseEntity.status(HttpStatusCode.valueOf(204)).headers(header).body("false");
			}
			header.add("Token", "Timeout");
			return ResponseEntity.status(HttpStatusCode.valueOf(204)).headers(header).body("false");
		}
		header.add("Token", "Unauthorised");
		return ResponseEntity.status(HttpStatusCode.valueOf(401)).headers(header).body("false");
	}

	@GetMapping("/getemployeeonworkbench")
	public ResponseEntity<List<Employee>> getEmployeeOnWorkbench(@RequestHeader(value = "token") String token) {
		TokenWrapper tokenWrapper = helperClass.decryptData(token);
		HttpHeaders header = new HttpHeaders();
		if (helperClass.checkValidationOfToken( token)) {
			if (helperClass.checkDateValidation(tokenWrapper.getDate())) {
				if (helperClass.checkRoleValidation((Employee) tokenWrapper.getOriginalObject(),"Project Manager")) {
					List<Employee> listEmployee = employeeDetailsServiceInterface.getEmployeeOnWorkBench();
					if (!employeeDetailsServiceInterface.getEmployeeOnWorkBench().isEmpty()) {
						return ResponseEntity.ok(listEmployee);
					}
					header.add("Getting Workbench ", "Failed");
					return ResponseEntity.status(HttpStatusCode.valueOf(204)).headers(header).body(null);
				}
				header.add("Role", "Mismatch");
				return ResponseEntity.status(HttpStatusCode.valueOf(204)).headers(header).body(null);

			}
			header.add("Token", "Timeout");
			return ResponseEntity.status(HttpStatusCode.valueOf(204)).headers(header).body(null);
		}
		header.add("Token", "Unauthorised");
		return ResponseEntity.status(HttpStatusCode.valueOf(401)).headers(header).body(null);
	}
	
	@GetMapping("/updatesalary")
	public ResponseEntity<String> updateJobDescriptionSalary( @RequestHeader(value = "token") String token,@RequestBody RequestManagerRestController requestManagerRestController){
		HttpHeaders header = new HttpHeaders();
		if(helperClass.checkValidationOfToken(token)) {
			if (helperClass.checkDateValidation(helperClass.decryptData(token).getDate())) {
				if(helperClass.checkRoleValidation(helperClass.decryptData(token).getOriginalObject(), "Project Manager")) {
					if(jobDescriptionService.updateJobDescriptionSalary(requestManagerRestController.getJobDescriptionId(), requestManagerRestController.getSalary())) {
						header.add("Salary set ", "SUCCESFULL");
						return ResponseEntity.status(HttpStatusCode.valueOf(201)).headers(header).body("true");
					}
					header.add("Salary set", "False");
					return ResponseEntity.status(HttpStatusCode.valueOf(400)).headers(header).body("false");
				}
				header.add("Role", "Mismatch");
				return ResponseEntity.status(HttpStatusCode.valueOf(204)).headers(header).body(null);
			}
			header.add("Token", "Timeout");
			return ResponseEntity.status(HttpStatusCode.valueOf(204)).headers(header).body(null);
		}
		header.add("Token", "Unauthorised");
		return ResponseEntity.status(HttpStatusCode.valueOf(401)).headers(header).body("false");
		

	}
}
