package com.mindgate.main.restservicetokenencryption.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
import com.mindgate.main.domain.Project;
import com.mindgate.main.restservicetokenencryption.EncryptionDecryptionUtility;
import com.mindgate.main.restservicetokenencryption.HelperClass;

import com.mindgate.main.restservicetokenencryption.TokenWrapper;
import com.mindgate.main.service.EmployeeDetailsServiceInterface;
import com.mindgate.main.service.JobDescriptionServiceInterface;
import com.mindgate.main.service.ProjectServiceInterface;

@RestController
@RequestMapping("token/teamleader")
public class TeamLeaderRestToken {

	@Autowired
	private JobDescriptionServiceInterface jobDescriptionServiceInterface;

	@Autowired
	HelperClass helperClass;

	@Autowired
	private EmployeeDetailsServiceInterface employeeDetailsServiceInterface;

	@Autowired
	private ProjectServiceInterface projectServiceInterface;

	// This method adds Job Description for team Leader role only
	@RequestMapping("addJobdescription")
	public ResponseEntity<String> addJobdescription(@RequestHeader(value = "token") String token,
			@RequestBody JobDescription jobDescription) {
		HttpHeaders header = new HttpHeaders();
		TokenWrapper tokenWrapper = helperClass.decryptData(token);
		if(tokenWrapper == null) {
			header.add("Token", "Invalid");
			return ResponseEntity.status(HttpStatusCode.valueOf(401)).headers(header).body("false");
		}
			
	
		if (helperClass.checkValidationOfToken(token)) {
			if (helperClass.checkDateValidation(tokenWrapper.getDate())) {
			
				if (helperClass.checkRoleValidation((Employee)tokenWrapper.getOriginalObject(), "Team Leader")) {

					if (jobDescriptionServiceInterface.addJobDescription(jobDescription)) {
						header.add("Job Description", "successfully added");
						return ResponseEntity.status(HttpStatusCode.valueOf(201)).headers(header).body("true");
					}
				}
				header.add("Role", "Mismatch");
				return ResponseEntity.status(HttpStatusCode.valueOf(403)).headers(header).body("false");
			}
			header.add("Token ", "Timeout");
			return ResponseEntity.status(HttpStatusCode.valueOf(204)).headers(header).body("false");
		}
		header.add("Token", "Invalid");
		return ResponseEntity.status(HttpStatusCode.valueOf(401)).headers(header).body("false");
	}

	//
	@GetMapping("getJobDescription")
	public ResponseEntity<Project> getJobDescriptionByProjectId(@RequestHeader(value = "token") String token) {
		HttpHeaders header = new HttpHeaders();
		TokenWrapper tokenWrapper = helperClass.decryptData(token);
		if(tokenWrapper == null) {
			header.add("Token", "Invalid");
			 return ResponseEntity.status(HttpStatusCode.valueOf(401)).headers(header).body(null);
		}
		if (helperClass.checkValidationOfToken(token)) {
			if (helperClass.checkDateValidation(tokenWrapper.getDate())) {
				if (helperClass.checkRoleValidation((Employee)tokenWrapper.getOriginalObject(), "Team Leader")) {
					
					System.out.println((Employee)tokenWrapper.getOriginalObject());
					Project project = projectServiceInterface
							.getProjectDetails(((Employee)tokenWrapper.getOriginalObject()).getProject().getProjectId());
					if (project != null) {
						header.add("List", "SUCCESFULL");
						return ResponseEntity.status(HttpStatusCode.valueOf(200)).headers(header).body(project);
					}
					header.add("project", "null");
					return ResponseEntity.status(HttpStatusCode.valueOf(204)).headers(header).body(null);
				}
				header.add("Role", "Mismatch");
				return ResponseEntity.status(HttpStatusCode.valueOf(200)).headers(header).body(null);
			}
			header.add("TOKEN", "Timeout");
			return ResponseEntity.status(HttpStatusCode.valueOf(403)).headers(header).body(null);
		}

		header.add("Token", "Invalid");
		return ResponseEntity.status(HttpStatusCode.valueOf(401)).headers(header).body(null);
	}

}
