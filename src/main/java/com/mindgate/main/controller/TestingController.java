package com.mindgate.main.controller;

import java.time.LocalDate;
import java.util.List;

import javax.xml.crypto.Data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindgate.main.domain.JobDescription;
import com.mindgate.main.service.JobDescriptionServiceInterface;

import jakarta.servlet.http.HttpSession;

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
	
	@GetMapping("/mindgate")
	public String showTestingPage() 
	{
		logger.info("showTestingPage() called");
		return"home";
	}
	
	@GetMapping("/mindgate/career")
	public String carrier(Model model,HttpSession httpSession)
	{
		httpSession.setAttribute("otp", 0);
		List<JobDescription> jobDescriptions=descriptionServiceInterface.getJobdescriptionByPosted();
		if(jobDescriptions.isEmpty())
		{
			model.addAttribute("empty", true);
		}
		else
		{
			model.addAttribute("jdList", jobDescriptions);
		}
		
		return"carrier";
	}
	
	@GetMapping("home")
	public String home() 
	{
		return"redirect:/";
	}
	
	@GetMapping("/mindgate/login")
	public String login(Model model) 
	{
		
		return"login";
	}
	
	@GetMapping("logout")
	public String logout(HttpSession httpSession) 
	{
		httpSession.invalidate();
		return"redirect:/mindgate";
	}
	
	
}
