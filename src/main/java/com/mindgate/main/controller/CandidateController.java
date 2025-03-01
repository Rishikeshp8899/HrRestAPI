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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.mindgate.main.domain.Candidate;
import com.mindgate.main.domain.JobDescription;
import com.mindgate.main.service.CandidateService;
import com.mindgate.main.service.CandidateServiceInterface;
import com.mindgate.main.service.EmailService;

import jakarta.servlet.http.HttpSession;

@Controller
public class CandidateController 
{
	@Autowired
	private CandidateServiceInterface candidateServiceInterface;
	
	@Autowired
	private EmailService emailService;
	
	Logger logger=LoggerFactory.getLogger(CandidateController.class);
	
	
	
	@GetMapping("mindgate/career/candidatedetails/{jdid}")
	public String candidateForm(@PathVariable("jdid") String JDId,Model model,HttpSession httpSession) 
	{
		model.addAttribute("candidate", new Candidate() );
		httpSession.setAttribute("job_description_id", JDId);		
		logger.info(JDId);
		return"candidateform";
	}
	
	
	@PostMapping("/mindgate/career/savecandidate/otpsend")
	public String verifyemail(@ModelAttribute("candidate") Candidate candidate,HttpSession httpSession,Model model,RedirectAttributes redirectAttributes) 
	{
		int otp=(int)(Math.random() * 9000) + 1000;
		httpSession.setAttribute("candidateobject", candidate);
		httpSession.setAttribute("otp", otp);
		
		String jdId=(String)httpSession.getAttribute("job_description_id");
		String Subject="Verifying Email";
		String contect="The otp for the email verification is : "+otp;
		logger.info("inside email");
		if(emailService.verifyemail(candidate.getEmail(), Subject, contect))
		{
			logger.info("email verified");
			return"redirect:/mindgate/career/savecandidate/verifyemail";
		}
		
//		redirectAttributes.addFlashAttribute("candidate", candidate);
		redirectAttributes.addFlashAttribute("invalidemail", true);
		return"redirect:/mindgate/career/candidatedetails/"+jdId;
		
		
	}
	
	@GetMapping("/mindgate/career/savecandidate/verifyemail")
	public String otpform() 
	{
		return"verifyemail";
	}
	
	@PostMapping("/mindgate/career/savecandidate/verify/verifyotp")
	public String verifyotp(@RequestParam("otp") int otp, HttpSession httpSession,RedirectAttributes attributes) 
	{
		int otp2=(int) httpSession.getAttribute("otp");
		logger.info(otp+" "+otp2);
		if(otp==otp2)
		{
			return"redirect:/mindgate/career/savecandidate";
		}
		attributes.addFlashAttribute("invalidotp", true);
		return"redirect:/mindgate/career/savecandidate/verifyemail";
	}
	
	
	@GetMapping("/mindgate/career/savecandidate")
	public String savecandidate(HttpSession session,Model model,RedirectAttributes redirectAttributes,HttpSession httpSession) 
	{
//		Candidate candidate=(candidate) session.getAttribute("candidateobject");
		Candidate candidate=(Candidate) httpSession.getAttribute("candidateobject");
		JobDescription description=new JobDescription();
		String jdId=(String)session.getAttribute("job_description_id");
		
		logger.info(jdId);
		description.setJobDescriptionId(jdId);
		candidate.setJobDesignation(description);
//		logger.info(candidate.toString());
		if(candidateServiceInterface.addCandidate(candidate))
		{
			logger.info("Candidate Added Sueccessfully");
			redirectAttributes.addFlashAttribute("addded", true);
		}
		else
		{
			redirectAttributes.addFlashAttribute("notadded", true);
		}
		return"redirect:/mindgate/career";
	}
}
