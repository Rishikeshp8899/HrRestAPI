package com.mindgate.main.controller;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.annotation.JsonCreator.Mode;
import com.mindgate.main.domain.AssesmentDetails;
import com.mindgate.main.domain.Candidate;
import com.mindgate.main.domain.Employee;
import com.mindgate.main.service.AssesmentDetailsServiceInterface;
import com.mindgate.main.service.CandidateServiceInterface;

import jakarta.servlet.http.HttpSession;

@Controller
public class InterviewerController 
{
	@Autowired
	private CandidateServiceInterface candidateServiceInterface;
	
	
	@Autowired
	private AssesmentDetailsServiceInterface assesmentDetailsServiceInterface;
	
	Logger logger=LoggerFactory.getLogger(InterviewerController.class);
	
	@GetMapping("mindgate/interviewer/home")
	public String interviewerHome(HttpSession httpSession,Model model) 
	{	
		Employee employee=(Employee) httpSession.getAttribute("employee");
		List<Candidate> candidates=candidateServiceInterface.candidateAccordingInterviewer(employee.getEmployeeId());
		model.addAttribute("candidates", candidates);
		return"interviewer";
	}
	
	@GetMapping("mindgate/interviewer/home/assementform/{candidateID}")
	public String assesmentform(@PathVariable("candidateID") String candidateID , Model model,HttpSession httpSession) 
	{
		Candidate candidate=candidateServiceInterface.getCandidateByCandidateId(candidateID);
		if(candidate!=null)
		{
			httpSession.setAttribute("candidate", candidate);
			AssesmentDetails assesmentDetails=new AssesmentDetails();
			model.addAttribute("assesment", assesmentDetails);
			return"assesmentform";
		}
		return"redirect:/mindgate/interviewer/home";
		
	}
	
	@PostMapping("mindgate/interviewer/home/assementform/save")
	public String saveAssesmentDetails(@ModelAttribute("assesment") AssesmentDetails assesmentDetails,HttpSession httpSession,RedirectAttributes attributes) 
	{
		Candidate candidate=(Candidate) httpSession.getAttribute("candidate");
		assesmentDetails.setInterviewDate(LocalDate.now());
		assesmentDetails.setCandidate(candidate);
		logger.info(assesmentDetails.toString());
		if(assesmentDetailsServiceInterface.saveAssesmentDetails(assesmentDetails))
		{
			attributes.addFlashAttribute("assesmentsaved", true);
		}
		else
		{
			attributes.addFlashAttribute("failedtosave", true);
		}
		return"redirect:/mindgate/interviewer/home";
	}
}
