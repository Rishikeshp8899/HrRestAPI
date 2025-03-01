package com.mindgate.main.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mindgate.main.domain.Candidate;
import com.mindgate.main.domain.JobDescription;
import com.mindgate.main.service.CandidateService;
import com.mindgate.main.service.CandidateServiceInterface;

import jakarta.servlet.http.HttpSession;

@Controller
public class CandidateController 
{
	@Autowired
	private CandidateServiceInterface candidateServiceInterface;
	Logger logger=LoggerFactory.getLogger(CandidateController.class);
	
	@GetMapping("candidatedetails/{jdid}")
	public String candidateForm(@PathVariable("jdid") String JDId,Model model,HttpSession httpSession) 
	{
		model.addAttribute("candidate", new Candidate() );
		httpSession.setAttribute("job_description_id", JDId);		
		logger.info(JDId);
		return"candidateform";
	}
	
	
	@PostMapping("/savecandidate")
	public String savecandidate(@ModelAttribute("candidate") Candidate candidate,HttpSession session) 
	{
		JobDescription description=new JobDescription();
		String jdId=(String)session.getAttribute("job_description_id");
		logger.info(jdId);
		description.setJobDescriptionId(jdId);
		candidate.setJobDescription(description);
		logger.info(candidate.toString());
		if(candidateServiceInterface.addCandidate(candidate))
		{
			logger.info("Candidate Added Sueccessfully");
		}
		else
		{
			return"redirect:candidatedetails/"+jdId;
		}
		return"redirect:carrier";
	}
}
