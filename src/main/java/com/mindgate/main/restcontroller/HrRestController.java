package com.mindgate.main.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindgate.main.domain.Assesment;
import com.mindgate.main.domain.Candidate;
import com.mindgate.main.domain.Employee;
import com.mindgate.main.domain.JobDescription;
import com.mindgate.main.domain.Project;
import com.mindgate.main.repository.ProjectRepositoryInterface;
import com.mindgate.main.restdomain.RequestHRBody;
import com.mindgate.main.service.AssesmentServiceInterface;
import com.mindgate.main.service.CandidateServiceInterface;
import com.mindgate.main.service.EmployeeDetailsServiceInterface;
import com.mindgate.main.service.JobDescriptionServiceInterface;
import com.mindgate.main.service.ProjectServiceInterface;

@RestController
@RequestMapping("rest/employees/hr")
public class HrRestController {
	@Autowired
	private JobDescriptionServiceInterface jobDescriptionServiceInterface;

	@Autowired
	private EmployeeDetailsServiceInterface employeeDetailsServiceInterface;

	@Autowired
	private CandidateServiceInterface candidateServiceInterface;

	@Autowired
	private AssesmentServiceInterface assesmentServiceInterface;
	
	@Autowired
	private ProjectServiceInterface projectServiceInterface;

	Logger logger = LoggerFactory.getLogger(HrRestController.class);
	HttpHeaders header = new HttpHeaders();

	@PutMapping("postjobdescrption")
	public ResponseEntity<String> postJobDescription(@RequestBody RequestHRBody requestHRBody) {
		Employee employee = employeeDetailsServiceInterface.getEmployee(requestHRBody.getEmployeeId());
		JobDescription jobDescription = jobDescriptionServiceInterface
				.getJobdescriptionById(requestHRBody.getJobDescriptionId());

		if (employee.getRole().equals("HR")) {
			if (employee.getProject().getProjectId().equals(jobDescription.getProjectId().getProjectId())) {
				if (jobDescriptionServiceInterface.postJobDescription(requestHRBody.getJobDescriptionId())) {
					logger.info("Job description posted Successfull");
					header.add("Job description posted", "Successfull");
					return ResponseEntity.status(HttpStatusCode.valueOf(201)).headers(header).body("true");
				}
				logger.info("Job description posted Failed");
				header.add("Job description posted", "Failed");
				return ResponseEntity.status(HttpStatusCode.valueOf(400)).headers(header).body("false");
			}
			logger.info("Project id false");
			header.add("project id", "False");
			return ResponseEntity.status(HttpStatusCode.valueOf(400)).headers(header).body("false");
		}
		logger.info("Role mismatch False");
		header.add("Role mismatch", "False");
		return ResponseEntity.status(HttpStatusCode.valueOf(403)).headers(header).body("false");
	}

	@GetMapping("getpreviouscandidate")
	public ResponseEntity<List<Candidate>> getAllPreviouslyAppliedCandidates(@RequestBody RequestHRBody requestHRBody) {
		Employee employee = employeeDetailsServiceInterface.getEmployee(requestHRBody.getEmployeeId());
		if (employee == null)
			return ResponseEntity.status(HttpStatusCode.valueOf(400)).body(null);
		if (employee.getRole().equals("HR")) {
			List<String> listOfCandidateId = assesmentServiceInterface.getAllCandidateNotSelected();

			List<Candidate> listCandidate = new ArrayList<Candidate>();

			listOfCandidateId.stream().forEach((candidateId) -> {
				Candidate candidate = candidateServiceInterface.getCandidateByCandidateId(candidateId);
				if (candidate != null)
					listCandidate.add(candidate);
			});
			if (!listOfCandidateId.isEmpty())
				return ResponseEntity.ok(listCandidate);
		}
		return ResponseEntity.status(HttpStatusCode.valueOf(403)).body(null);

	}

	@GetMapping("filterCandidate")
	public ResponseEntity<List<Candidate>> getAllCandidateByFilter(@RequestBody RequestHRBody requestHRBody) {
		Employee employee = employeeDetailsServiceInterface.getEmployee(requestHRBody.getEmployeeId());
		if (employee == null) {
			logger.info("Record  not found");
			header.add("Record", " not found");
			return ResponseEntity.status(HttpStatusCode.valueOf(400)).headers(header).body(null);
		}
		JobDescription jobDescription = jobDescriptionServiceInterface
				.getJobdescriptionById(requestHRBody.getJobDescriptionId());
		if (jobDescription == null) {
			logger.info("Record  not found");
			header.add("Record", " not found");
			return ResponseEntity.status(HttpStatusCode.valueOf(400)).headers(header).body(null);
		}

		if (employee.getRole().equals("HR")) {

			List<Candidate> listCandidate = candidateServiceInterface.getAllCandidateByRequirement(
					jobDescription.getPrimarySkill(), jobDescription.getSecondarySkill(),
					jobDescription.getTernarySkill());
			if (!listCandidate.isEmpty()) {
				return ResponseEntity.ok(listCandidate);
			}
			logger.info("List  not found");
			header.add("List", "not found");
			return ResponseEntity.status(HttpStatusCode.valueOf(204)).headers(header).body(null);

		}
		logger.info("Role mismatch false");
		header.add("Role mismatch", "False");
		return ResponseEntity.status(HttpStatusCode.valueOf(403)).headers(header).body(null);

	}

	@GetMapping("getinterviewerdetails")
	public ResponseEntity<List<Employee>> getInterviewerDetailsList(@RequestBody RequestHRBody requestHRBody) {

		Employee employee = employeeDetailsServiceInterface.getEmployee(requestHRBody.getEmployeeId());
		if (employee == null) {
			logger.info("User  not found");
			header.add("User", " not found");
			return ResponseEntity.status(HttpStatusCode.valueOf(400)).headers(header).body(null);
		}
		if (employee.getRole().equals("HR")) {

			List<Employee> listInterviewer = employeeDetailsServiceInterface.getInterviewerDetails();
			if (!listInterviewer.isEmpty()) {
				return ResponseEntity.ok(listInterviewer);
			}
			logger.info("List not found");
			header.add("List", "not found");
			return ResponseEntity.status(HttpStatusCode.valueOf(204)).headers(header).body(null);

		}
		logger.info("Role mismatch false");
		header.add("Role mismatch", "False");
		return ResponseEntity.status(HttpStatusCode.valueOf(403)).headers(header).body(null);

	}

	@PutMapping("selecttechnicalpanel")
	public ResponseEntity<String> setInterviewer(@RequestBody RequestHRBody requestHRBody) {
		System.out.println(requestHRBody.getInterviewer());
		Employee employee = employeeDetailsServiceInterface.getEmployee(requestHRBody.getEmployeeId());
		Employee interviewer = employeeDetailsServiceInterface.getEmployee(requestHRBody.getInterviewer());
		if (employee == null) {
			logger.info("User  not found");
			header.add("User", " not found");
			return ResponseEntity.status(HttpStatusCode.valueOf(400)).headers(header).body("false");
		}

		if (interviewer == null) {
			logger.info("Interviewer  not found");
			header.add("interviewer", " not found");
			return ResponseEntity.status(HttpStatusCode.valueOf(204)).headers(header).body("false");

		}

		if (!employee.getRole().equals("HR")) {
			logger.info("Role mismatch false");
			header.add("Role mismatch", "False");
			return ResponseEntity.status(HttpStatusCode.valueOf(403)).headers(header).body(null);
		}

		List<Employee> employeeDetails = employeeDetailsServiceInterface.getInterviewerDetails();

		boolean isInterviewer = candidateServiceInterface.updateInterviewerId(requestHRBody.getInterviewer(),
				requestHRBody.getCandidateId(), requestHRBody.getInterviewDate());
		if (isInterviewer) {
			return ResponseEntity.ok("true");
		}
		return ResponseEntity.status(HttpStatusCode.valueOf(400)).headers(header).body("false");

	}

	@GetMapping("previousinterviewedcandidate")
	public ResponseEntity<List<Candidate>> getPreviousInterviewedCandidate(@RequestBody RequestHRBody requestHRBody) {
		Employee employee = employeeDetailsServiceInterface.getEmployee(requestHRBody.getEmployeeId());
		if (employee == null) {
			logger.info("User  not found");
			header.add("User", " not found");
			return ResponseEntity.status(HttpStatusCode.valueOf(400)).headers(header).body(null);
		}
		if (employee.getRole().equals("HR")) {
			System.out.println(employee.getRole());
			List<Candidate> listOfPreviousInterviewCandidate = candidateServiceInterface
					.getCandidateHaveInterviewSchedule();
			if (!listOfPreviousInterviewCandidate.isEmpty()) {
				return ResponseEntity.ok(listOfPreviousInterviewCandidate);
			}
			logger.info("List  not found");
			header.add("List", "not found");
			return ResponseEntity.status(HttpStatusCode.valueOf(204)).headers(header).body(null);

		}
		logger.info("Role mismatch false");
		header.add("Role ", "mismatch");
		return ResponseEntity.status(HttpStatusCode.valueOf(403)).headers(header).body(null);

	}

	@GetMapping("addselectedcandidatetoemployee")
	public ResponseEntity<String> addNewSelectedCandidateToEmployee(@RequestBody RequestHRBody requestHRBody) {

		Employee employee = employeeDetailsServiceInterface.getEmployee(requestHRBody.getEmployeeId());
		if (employee == null) {
			logger.info("User  not found");
			header.add("User", " not found");
			return ResponseEntity.status(HttpStatusCode.valueOf(400)).headers(header).body(null);
		}
		if (employee.getRole().equals("HR")) {
			Candidate candidate = candidateServiceInterface.getCandidateByCandidateId(requestHRBody.getCandidateId());
			Assesment assesment = assesmentServiceInterface.getAssesmentByCandidateId(requestHRBody.getCandidateId());
			JobDescription jobDescription = jobDescriptionServiceInterface
					.getJobdescriptionById(candidate.getJobDescription().getJobDescriptionId());

			if (jobDescription == null) {
				logger.info("Job Description  not found");
				header.add("Job Description", "Not Fount");
				return ResponseEntity.status(HttpStatusCode.valueOf(204)).headers(header).body("false");
			}
			if(jobDescription.getRequiredCandidate()<=0) {
				logger.info("Job requiredment fulfill");
				header.add("Job requiredment ", "fulfill");
				return ResponseEntity.status(HttpStatusCode.valueOf(400)).headers(header).body("false");
			}
			if (candidate == null) {
				logger.info("Candidate  not found");
				header.add("Candidate", "Not Fount");
				return ResponseEntity.status(HttpStatusCode.valueOf(204)).headers(header).body("false");
			}
			if (assesment == null) {
				logger.info("Assesment  not found");
				header.add("Assesment", "Not Fount");
				return ResponseEntity.status(HttpStatusCode.valueOf(204)).headers(header).body("false");
			}

			if (assesment.getStatus().equals("selected")) {
				Employee employee2 = new Employee();
				String[] names = candidate.getName().split(" ", 2);
				employee2.setFirstname(names[0]);
				employee2.setLastname(names[1]);
				employee2.setAge(candidate.getAge());
				employee2.setRole("employee");
				employee2.setProject(candidate.getJobDescription().getProjectId());
				employee2.setPassword("MGS@0000");
				employee2.setDesignation(candidate.getJobDescription().getRole());
				employee2.setPrimarySkill(candidate.getPrimarySkill());
				employee2.setSecondarySkill(candidate.getSecondarySkill());
				employee2.setTernarySkill(candidate.getTernarySkill());
				if (employeeDetailsServiceInterface.insertEmployee(employee2)) {

					candidateServiceInterface.setCandidateJobdescriptionIdNull(requestHRBody.getCandidateId());
					
					if (jobDescriptionServiceInterface
							.decrementRequiredCandidate(candidate.getJobDescription().getJobDescriptionId())) {
						if(employee.getRole().equals("HR")) {
							if(candidateServiceInterface.updateSendOfferLetter("added", requestHRBody.getCandidateId())) {
								logger.info("Employee deleted successfully");
								header.add("Employee deleted", "Successful");
								
								return ResponseEntity.status(HttpStatusCode.valueOf(200)).body("Employee Added Successfully");
							}
							return ResponseEntity.status(HttpStatusCode.valueOf(400)).body("Employee Added Successfully candidate not able to delete");
						
					}
					employeeDetailsServiceInterface.deleteEmployeeDetail(employee2);
					logger.info("Adding Employee Failed");
					header.add("Adding Employee", "failed");
					return ResponseEntity.status(HttpStatusCode.valueOf(204)).headers(header).body("false");

				}
				logger.info("Candidate not able to insert");
				header.add("Candidate ", "not able to insert");
				return ResponseEntity.status(HttpStatusCode.valueOf(403)).headers(header).body("false");
			}
				logger.info("Employee insertion failed");
				header.add("Employee insertion ", "failed");
				return ResponseEntity.status(HttpStatusCode.valueOf(403)).headers(header).body("false");

		}
			logger.info("Candidate is not selected");
			header.add("Candidate ", "is Not selected");
			return ResponseEntity.status(HttpStatusCode.valueOf(403)).headers(header).body("false");
		}
		logger.info("Role mismatch false");
		header.add("Role ", "mismatch");
		return ResponseEntity.status(HttpStatusCode.valueOf(403)).headers(header).body("false");

		}
		

	@GetMapping("getJobDescriptionsByEmployeeId")
	public ResponseEntity<List<JobDescription>> getJobDescriptionsByEmployeeId(
			@RequestBody RequestHRBody requestHRBody) {

		Employee employee = employeeDetailsServiceInterface.getEmployee(requestHRBody.getEmployeeId());
		if (employee == null) {
			logger.info("User  not found");
			header.add("User", " not found");
			return ResponseEntity.status(HttpStatusCode.valueOf(400)).headers(header).body(null);
		}
		if (employee.getRole().equals("HR")) {
			List<JobDescription> list = jobDescriptionServiceInterface
					.getJobDescriptionByPRojectId(employee.getProject().getProjectId());
			if (!list.isEmpty()) {
				logger.info("List found");
				header.add("List", "found");
				return ResponseEntity.status(HttpStatusCode.valueOf(200)).headers(header).body(list);
			}
			logger.info("List  not found");
			header.add("List", "not found");
			return ResponseEntity.status(HttpStatusCode.valueOf(403)).headers(header).body(null);

		}
		logger.info("Role mismatch false");
		header.add("Role ", "mismatch");
		return ResponseEntity.status(HttpStatusCode.valueOf(403)).headers(header).body(null);

	}

	@PutMapping("deletecandidate")
	public ResponseEntity<String> deleteCandidateFromSendMail(@RequestBody RequestHRBody requestHRBody) {
		Employee employee = employeeDetailsServiceInterface.getEmployee(requestHRBody.getEmployeeId());
		if (employee == null) {
			logger.info("User  not found");
			header.add("User", " not found");
			return ResponseEntity.status(HttpStatusCode.valueOf(400)).headers(header).body(null);
		}
		Candidate candidate = candidateServiceInterface.getCandidateByCandidateId(requestHRBody.getCandidateId());
		if (candidate == null) {
			logger.info("Candidate  not found");
			header.add("Candidate", "Not Fount");
			return ResponseEntity.status(HttpStatusCode.valueOf(204)).headers(header).body("false");
		}
		
		if(employee.getRole().equals("HR")) {
			if(candidateServiceInterface.updateSendOfferLetter("requiredMentfull", requestHRBody.getCandidateId())) {
				logger.info("Employee deleted successfully");
				header.add("Employee deleted", "Successful");
				return ResponseEntity.status(HttpStatusCode.valueOf(200)).headers(header).body("employee delted");
			}
			logger.info("Employee deleted failed");
			header.add("Employee deleted", "Failed");
			return ResponseEntity.status(HttpStatusCode.valueOf(304)).headers(header).body("failed to delete employee");
		}
		logger.info("Role mismatch");
		header.add("Role mismatched", "Not Fount");
		return ResponseEntity.status(HttpStatusCode.valueOf(403)).body("Unauthorized");
	}
	
}
