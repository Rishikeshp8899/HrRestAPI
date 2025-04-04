package com.mindgate.main.restcontroller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindgate.main.domain.Employee;
import com.mindgate.main.domain.Project;
import com.mindgate.main.service.EmployeeDetailsServiceInterface;
import com.mindgate.main.service.ProjectServiceInterface;

@RestController
@RequestMapping("rest/employees")
public class EmployeeDetailsRestController {
	@Autowired
	private EmployeeDetailsServiceInterface employeeDetailsServiceInterface;
	
	@Autowired
	private ProjectServiceInterface projectServiceInterface;
	
	Logger logger = LoggerFactory.getLogger(EmployeeDetailsRestController.class);
	
	@GetMapping("/teamleader/{TlId}")
	public ResponseEntity<Project> getProjectByEmployeeId(@PathVariable("TlId") String TlId) {
	
		 Employee employee= employeeDetailsServiceInterface.getEmployee(TlId);
		 
		Project project=projectServiceInterface.getProjectDetails(String.valueOf( employee.getProject().getProjectId()));
		
		
		if(project != null) {
			
			HttpHeaders header=new HttpHeaders();
			return new ResponseEntity<Project>(project,header,HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	}
}
