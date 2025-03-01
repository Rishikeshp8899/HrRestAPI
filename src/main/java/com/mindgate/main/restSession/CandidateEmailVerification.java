package com.mindgate.main.restSession;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindgate.main.domain.Candidate;
import com.mindgate.main.restcontroller.CandidateDetailsRestController;
import com.mindgate.main.restdomain.RequestBodyMailVerifyCation;
import com.mindgate.main.service.CandidateServiceInterface;
import com.mindgate.main.service.EmailService;

import jakarta.servlet.http.HttpSession;
@RestController
@RequestMapping("rest/candidate")
public class CandidateEmailVerification {

	@Autowired
	private CandidateServiceInterface candidateServiceInterface;

	@Autowired
	private EmailService emailService;

	Logger logger = LoggerFactory.getLogger(CandidateEmailVerification.class);

	HttpHeaders header = new HttpHeaders();

	@PostMapping("applyforjob")
	public ResponseEntity<String> saveCandidateDetails(@RequestBody Candidate candidate, HttpSession httpSession) {

		Random rand = new Random();
		int rand_int1 = rand.nextInt(1000);
		httpSession.setAttribute("verfyNumber", rand_int1);
		httpSession.setAttribute("userData", candidate);
		String contant = "This is auto generated mail copy the link to verify your mail=http://localhost:8080/rest/candidate/verifyEmail/"+Integer.toString(rand_int1)+"or send request with json ";
		int result = emailService.sendSimpleMessage(candidate.getEmail(), "Email Verification", contant,
				"rishikeshp88@gmail.com" );
		if (result > 0) {
			logger.info("OTP sended Successfully");
			header.add("OTP sended ", "Successfully");
			return ResponseEntity.status(HttpStatusCode.valueOf(200)).headers(header).body("Checked Mail");
		}
		logger.info("Email Id not valid");
		header.add("Email Id", "not valid");
		return ResponseEntity.status(HttpStatusCode.valueOf(400)).headers(header).body("Email Id not valid");

	}

	@GetMapping("/verifyEmail")
	public ResponseEntity<String> verifyMails(@RequestBody RequestBodyMailVerifyCation requestBodyMailVerifyCation,
			HttpSession httpSession) {
		int otp = (int) httpSession.getAttribute("verfyNumber");
		Candidate candidate = (Candidate) httpSession.getAttribute("userData");
		if (requestBodyMailVerifyCation.getOtp() == otp) {
			if (candidateServiceInterface.addCandidate(candidate)) {
				logger.info("Applied Successfully");
				header.add("Applied", "Successfully");
				return ResponseEntity.status(HttpStatusCode.valueOf(201)).headers(header).body("Applied Successfully");
			}

			logger.info("Failed To Applied");
			header.add("Applied", "Failed");
			return ResponseEntity.status(HttpStatusCode.valueOf(400)).headers(header).body("Failed  to Applied");
		}
		logger.info("Not authorized");
		header.add("Not authorized", "False");
		return ResponseEntity.status(HttpStatusCode.valueOf(403)).headers(header).body("Not authorized");
	}
	

	@RequestMapping("verifyEmail/{otp}")
	public ResponseEntity<String> verifyMails(@PathVariable String otp,HttpSession httpSession) {
		
		int otpSession = (int) httpSession.getAttribute("verfyNumber");
		
		Candidate candidate = (Candidate) httpSession.getAttribute("userData");
		if (Integer.parseInt(otp) == otpSession) {
			if (candidateServiceInterface.addCandidate(candidate)) {
				logger.info("Applied Successfully");
				header.add("Applied", "Successfully");
				return ResponseEntity.status(HttpStatusCode.valueOf(201)).headers(header).body("Applied Successfully");
			}

			logger.info("Failed To Applied");
			header.add("Applied", "Failed");
			return ResponseEntity.status(HttpStatusCode.valueOf(400)).headers(header).body("Failed  to Applied");
		}
		logger.info("Not authorized");
		header.add("Not authorized", "False");
		return ResponseEntity.status(HttpStatusCode.valueOf(403)).headers(header).body("Not authorized");
	}
}
