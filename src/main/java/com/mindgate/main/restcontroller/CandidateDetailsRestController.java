package com.mindgate.main.restcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindgate.main.domain.Candidate;
import com.mindgate.main.domain.JobDescription;
import com.mindgate.main.service.CandidateServiceInterface;

@RestController
@RequestMapping("rest/candidate")
public class CandidateDetailsRestController {

	@Autowired
	private CandidateServiceInterface candidateServiceInterface;

	Logger logger = LoggerFactory.getLogger(CandidateDetailsRestController.class);

	@PostMapping("apply")
	public ResponseEntity<String> saveCandidateDetails(@RequestBody Candidate candidate) {

		if (candidateServiceInterface.addCandidate(candidate))
			return ResponseEntity.status(HttpStatusCode.valueOf(200)).body("true");

		return ResponseEntity.status(HttpStatusCode.valueOf(400)).body("false");
	}
}
