package com.mindgate.main.restcontroller;

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

import com.itextpdf.kernel.pdf.PdfDocument;
import com.mindgate.main.domain.Assesment;
import com.mindgate.main.domain.Candidate;
import com.mindgate.main.domain.Employee;
import com.mindgate.main.domain.JobDescription;
import com.mindgate.main.restdomain.MailSenderRequestBody;
import com.mindgate.main.service.AssesmentServiceInterface;
import com.mindgate.main.service.CandidateServiceInterface;
import com.mindgate.main.service.EmailService;
import com.mindgate.main.service.EmployeeDetailsServiceInterface;
import com.mindgate.main.service.JobDescriptionServiceInterface;
import com.mindgate.main.service.PdfGenerator;

@RestController
public class EmailSenderController {
	@Autowired
	private EmailService emailService;
	@Autowired
	private JobDescriptionServiceInterface jobDescriptionServiceInterface;

	@Autowired
	private EmployeeDetailsServiceInterface employeeDetailsServiceInterface;

	@Autowired
	private CandidateServiceInterface candidateServiceInterface;
	
	@Autowired
	private AssesmentServiceInterface assesmentServiceInterface;
	

	Logger logger = LoggerFactory.getLogger(EmailSenderController.class);
	HttpHeaders header = new HttpHeaders();

	@GetMapping("rest/employees/hr/sendMail")
	public ResponseEntity<String> sendEmail(@RequestBody MailSenderRequestBody mailSenderRequestBody) {

		
		Employee employee = employeeDetailsServiceInterface.getEmployee(mailSenderRequestBody.getEmployeeId());
		if (employee == null) {
			logger.info("User  not found");
			header.add("User", " not found");
			return ResponseEntity.status(HttpStatusCode.valueOf(400)).headers(header).body("false");
		}
		Candidate candidate=candidateServiceInterface.getCandidateByCandidateId(mailSenderRequestBody.getCandidateId());
		if(candidate ==null) {
			logger.info("Candidate not exist");
			header.add("Candidate", " not exist");
			return ResponseEntity.status(HttpStatusCode.valueOf(203)).headers(header).body("false");
		}
		
		Assesment candidateAssesment= assesmentServiceInterface.getSelectedAssesment(mailSenderRequestBody.getCandidateId());
		if(candidateAssesment == null) {
			header.add("Candidate assesment", " not availbale And Candidate is not selected");
			return ResponseEntity.status(HttpStatusCode.valueOf(204)).headers(header).body("false");
		}
		
		JobDescription jobDescription=jobDescriptionServiceInterface.getJobdescriptionById(mailSenderRequestBody.getJobDescriptionId());
		
			// String to,String subject,String text,String form,byte[] attachment,String
			// candidateId
		
			if (employee.getRole().equals("HR")) {
				if(candidate.getSendOfferLetter().equals("yes")) {
					logger.info("Mail already sended");
					header.add("mail", " already sended");
					return ResponseEntity.status(HttpStatusCode.valueOf(204)).headers(header).body("false");
				}
				
					String content="congratulations "+ candidate.getName()+" you are selected for the role "+jobDescription.getRole()+" at mindgate "
								+"\n Your salary package is "+ jobDescription.getSalary()+"lpa "+"\n i am sure you might have a greet integartion with all who were part of panel "
								+"regarding comapany future approach and that might have left a greet impact on you.";
				String subject="Offer Letter";
				String subject2="Required Document";
				String content2="required document you need to carry \n 1: Addhar card \n 2:pancard \n 3:degree certificate \n 4: 10 and 12th certificate \n";
			
				byte[] pdfArray = PdfGenerator.generatedPDF(content);
				byte[] pdfArray2 = PdfGenerator.generatedPDF(content2);

			int result=	emailService.sendSimpleMessage(candidate.getEmail(), subject,
						content, "rishikeshp88@gmail.com", pdfArray,
					mailSenderRequestBody.getCandidateId());
			int result2=emailService.sendSimpleMessage(candidate.getEmail(), subject2,
					content2, "rishikeshp88@gmail.com", pdfArray2,
				mailSenderRequestBody.getCandidateId());
			
			if(result >0 && result2 >0) {
				if(candidateServiceInterface.updateCandidateSetSendMail(mailSenderRequestBody.getCandidateId()))
				{
					return ResponseEntity.ok("mail send successfully");
				}
				logger.info("Mail sended successfully but data update is Failed");
				header.add("Mail sended successfully but data update is", "failed");
				return ResponseEntity.status(HttpStatusCode.valueOf(200)).headers(header).body("false");
			}

			}
			logger.info("Role mismatch false");
			header.add("Role Mismatch", "false");
			return ResponseEntity.status(HttpStatusCode.valueOf(403)).headers(header).body("false");

		}

	}


