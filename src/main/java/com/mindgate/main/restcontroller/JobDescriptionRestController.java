package com.mindgate.main.restcontroller;

import org.springframework.http.HttpHeaders;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindgate.main.domain.Employee;
import com.mindgate.main.domain.JobDescription;
import com.mindgate.main.repository.EmployeeDetailsRepositoryInterface;
import com.mindgate.main.repository.JobDescriptionRepositoryInterface;
import com.mindgate.main.service.JobDescriptionService;

@RestController
@RequestMapping("rest/employees/teamleader")
public class JobDescriptionRestController {

	@Autowired
	private JobDescriptionService jobDescriptionService;

	@Autowired
	private EmployeeDetailsRepositoryInterface employeeDetailsRepositoryInterface;

	Logger logger = LoggerFactory.getLogger(JobDescriptionRestController.class);
	@PostMapping("addjobdescription/{employeeId}")
	public ResponseEntity<String> addJobDescription(@RequestBody JobDescription jobDescription,
			@PathVariable("employeeId") String employeeId) {
		HttpHeaders header=new HttpHeaders();
		Employee employee=null;
		try {
			employee = employeeDetailsRepositoryInterface.getEmployee(Long.parseLong(employeeId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (employee !=null&employee.getRole().equals("Team Leader")) {
			if (jobDescriptionService.addJobDescription(jobDescription))
				return ResponseEntity.ok("true");
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("false");
		}
		header.add("Role mismatch", "False");
		return ResponseEntity.status(HttpStatusCode.valueOf(403)).headers(header).body("false");
	}

	@GetMapping("jobdescription/{projectId}/{employeeId}")
	public ResponseEntity<List<JobDescription>> getJobDescriptionByProjectId(
			@PathVariable("projectId") String projectId,@PathVariable("employeeId") String employeeId) {
		HttpHeaders header=new HttpHeaders();
		Employee employee=null;
		try {
			employee = employeeDetailsRepositoryInterface.getEmployee((Long.parseLong(employeeId)));
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(employee !=null& !employee.getRole().equals("Team Leader")|| (employee == null)) {
			header.add("employee ", "is not available");
			return new ResponseEntity<List<JobDescription>>(null, header, HttpStatusCode.valueOf(400));
		}
		List<JobDescription> jobDescriptionsList = jobDescriptionService.getJobDescriptionByPRojectId(projectId);
		if (!jobDescriptionsList.isEmpty()) {
			header.add("Job Description list ", "is available");
			return new ResponseEntity<List<JobDescription>>(jobDescriptionsList, header, HttpStatus.OK);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

	}

}
