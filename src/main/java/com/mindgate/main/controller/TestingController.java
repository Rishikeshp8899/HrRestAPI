package com.mindgate.main.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindgate.main.domain.JobDescription;
import com.mindgate.main.service.JobDescriptionServiceInterface;

@Controller 
public class TestingController 
{
	@Autowired
	private JobDescriptionServiceInterface descriptionServiceInterface;
	
	Logger logger=LoggerFactory.getLogger(TestingController.class);
	public TestingController() 
	{
		logger.info("test constructor created");
	}
	
	@GetMapping("/")
	public String showTestingPage() 
	{
		logger.info("showTestingPage() called");
		return"home";
	}
	
	@GetMapping("carrier")
	public String carrier(Model model) 
	{
		List<JobDescription> jobDescriptions=descriptionServiceInterface.getJobDescriptionBySendToHR();
		System.out.println("inside jd");
		System.out.println(jobDescriptions);
		if(jobDescriptions.isEmpty())
		{
			System.out.println("empty");
			model.addAttribute("empty", true);
		}
		else
		{
			model.addAttribute("empty", false);
			model.addAttribute("jdList", jobDescriptions);
		}
		
		return"carrier";
	}
	
	@GetMapping("home")
	public String home() 
	{
		return"redirect:/";
	}
	
	@GetMapping("login")
	public String login(Model model) 
	{
		
		return"login";
	}
//	
//	@GetMapping("hr")
//	public String hr() 
//	{
//		return"hr";
//	}
//	
//	@GetMapping("projectmanager")
//	public String projectmanager() 
//	{
//		return"projectmanager";
//	}
//	
//	@GetMapping("teamleader")
//	public String teamleader() 
//	{
//		return"teamleader";
//	}
//	
//	@GetMapping("interviewer")
//	public String interviewer() 
//	{
//		return"interviewer";
//	}
}
