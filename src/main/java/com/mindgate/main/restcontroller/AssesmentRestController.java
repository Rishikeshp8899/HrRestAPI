package com.mindgate.main.restcontroller;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindgate.main.domain.Assesment;
import com.mindgate.main.domain.Candidate;
import com.mindgate.main.domain.Employee;
import com.mindgate.main.restdomain.RequestBodyForInterviewer;
import com.mindgate.main.service.AssesmentServiceInterface;
import com.mindgate.main.service.CandidateServiceInterface;
import com.mindgate.main.service.EmployeeDetailsServiceInterface;

import jakarta.mail.Header;

@RestController
@RequestMapping("rest/employees/interviewer")
public class AssesmentRestController {
	
	@Autowired
	private EmployeeDetailsServiceInterface employeeDetailsServiceInterface;

	@Autowired
	private CandidateServiceInterface candidateServiceInterface;

	
	@Autowired
	private AssesmentServiceInterface assesmentServiceInterface;
	
	Logger logger = LoggerFactory.getLogger(AssesmentRestController.class);
	HttpHeaders header = new HttpHeaders();
	@PostMapping("addassesment")
	public ResponseEntity<String> insertIntoInterviwer(@RequestBody RequestBodyForInterviewer requestBodyForInterviewer){
		Employee employee = employeeDetailsServiceInterface.getEmployee(requestBodyForInterviewer.getEmplpoyeeId());
		if(employee == null) {
			logger.info("User  not found");
			header.add("User", "not found");
			return ResponseEntity.status(HttpStatusCode.valueOf(400)).headers(header).body("false");
		}
		if(employee.getIs_interviewer().equals("yes")) {
			Candidate candidate = candidateServiceInterface.getCandidateByCandidateId(requestBodyForInterviewer.getCandidateId());
			if(candidate == null) {
				logger.info("Candidate  not existent");
				header.add("Candidate", "not existent");
				return ResponseEntity.status(HttpStatusCode.valueOf(203)).headers(header).body("false");
				
			}
			//techSkill, communication, softSkills, candidate_id, assesment_id, status, interview_date
			Assesment assesment=new Assesment();
			assesment.setCandidate(candidateServiceInterface.getCandidateByCandidateId(requestBodyForInterviewer.getCandidateId()));
			assesment.setCommunication(requestBodyForInterviewer.getCommunication());
			assesment.setSoftSkill(requestBodyForInterviewer.getSoftSkills());
			assesment.setStatus(requestBodyForInterviewer.getStatus());
			assesment.setTechSkill(requestBodyForInterviewer.getTectSkill());
			
			assesment.setCandidate(candidateServiceInterface.getCandidateByCandidateId(requestBodyForInterviewer.getCandidateId()));
			assesment.setInterviewDate(new Date(new Date().getYear(),new Date().getMonth(),LocalDate.now().getDayOfMonth()));
			if(assesmentServiceInterface.insertAssesmentDetails(assesment)) {
				return ResponseEntity.status(HttpStatusCode.valueOf(200)).body("Candidate assesment added successfully");
			}
			logger.info("candidate  Assesment Failed");
			header.add("candidate Assesment", "Failed");
			return ResponseEntity.status(HttpStatusCode.valueOf(204)).headers(header).body("false");
		}
		logger.info("Role mismatch false");
		header.add("Role mismatch", "false");
		return ResponseEntity.status(HttpStatusCode.valueOf(403)).headers(header).body("false");
	}
	
	@GetMapping("getcandidatedata")
	public ResponseEntity<List<Candidate>> getCandidateDetailsForInterview(@RequestBody RequestBodyForInterviewer requestBodyForInterviewer){
		Employee employee = employeeDetailsServiceInterface.getEmployee(requestBodyForInterviewer.getEmplpoyeeId());
		if(employee == null) {
			logger.info("User  not found");
			header.add("User", "not found");
			return ResponseEntity.status(HttpStatusCode.valueOf(400)).headers(header).body(null);
		}
		if(employee.getIs_interviewer().equals("yes")) {
			List<Candidate> candidate = candidateServiceInterface.getCandidateByInterviewerId(requestBodyForInterviewer.getEmplpoyeeId());
			if(candidate.isEmpty()) {
				logger.info("Candidate  not existent");
				header.add("Candidate", "not existent");
				return ResponseEntity.status(HttpStatusCode.valueOf(203)).headers(header).body(null);
			}
			return ResponseEntity.ok(candidate);
	}

		logger.info("Role mismatch false");
		header.add("Role mismatch", "false");
		return ResponseEntity.status(HttpStatusCode.valueOf(403)).headers(header).body(null);

}
}
