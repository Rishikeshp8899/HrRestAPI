package com.mindgate.main.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mindgate.main.domain.Employee;
import com.mindgate.main.domain.JobDescription;
import com.mindgate.main.domain.Project;
import com.mindgate.main.service.EmployeeDetailsService;
import com.mindgate.main.service.EmployeeDetailsServiceInterface;
import com.mindgate.main.service.JobDescriptionServiceInterface;
import com.mindgate.main.service.ProjectServiceInterface;

import jakarta.servlet.http.HttpSession;

@Controller
public class EmployeeDetailsController 
{
	Logger logger=LoggerFactory.getLogger(EmployeeDetailsController.class);
	@Autowired
	private EmployeeDetailsService  employeeDetailsServiceInterface;
	
	@Autowired
	private ProjectServiceInterface projectServiceInterface;
	
	@Autowired
	private JobDescriptionServiceInterface jobDescriptionServiceInterface;
	
	@PostMapping("/mindgate/login/validate")
	public String validate(@RequestParam("username") String username ,@RequestParam("password") String password,Model model,HttpSession httpSession,RedirectAttributes redirectAttributes) 
	{
		logger.info(username);
		logger.info(password);
		logger.info("inside validate");
		
		if(employeeDetailsServiceInterface.checkEmployeeExist(username))
		{
			Employee employee= employeeDetailsServiceInterface.validateEmployee(username, password);
			logger.info("validate done");
			if(employee!=null)
			{
				logger.info("notAuthoriedout side");
				if(employee.getRole().equalsIgnoreCase("hr"))
				{
					httpSession.setAttribute("employee", employee);
					return"redirect:/mindgate/hr/home";
				}
				else if(employee.getRole().equalsIgnoreCase("project manager"))
				{
					httpSession.setAttribute("employee", employee);
					return"redirect:/mindgate/projectmanager/home";
				}
				else if(employee.getRole().equalsIgnoreCase("team leader"))
				{
					httpSession.setAttribute("employee", employee);
					logger.info("");
					return"redirect:/mindgate/teamlead/home";
				}
				else
				{
					if(employee.getIs_interviewer().equalsIgnoreCase("yes"))
					{
						httpSession.setAttribute("employee", employee);
						return"redirect:/mindgate/interviewer/home";
					}
					logger.info("notAuthoried");
//					model.addAttribute("notAuthoried", true);
					redirectAttributes.addFlashAttribute("notAuthoried", true);
					return"redirect:/mindgate/login";
				}
				
			}
			else
			{
				logger.info("invalidCredentials");
//				model.addAttribute("invalidCredentials", true);
				redirectAttributes.addFlashAttribute("invalidCredentials", true);
				return"redirect:/mindgate/login";
			}
		}
		else
		{
			logger.info("invalidId");
//			model.addAttribute("invalidId", true);
			redirectAttributes.addFlashAttribute("invalidId", true);
			return"redirect:/mindgate/login";
		}
	}
	
	@GetMapping("mindgate/teamlead/jobrequestlist")
	public String jobRequestList(Model model,HttpSession httpSession) 
	{
		logger.info("in side jdlist");
		Employee employee=(Employee) httpSession.getAttribute("employee");
		logger.info(employee.getProject().getProjectId());
		List<JobDescription> jobDescriptions=jobDescriptionServiceInterface.getJobDescriptionByPRojectId(employee.getProject().getProjectId());
		logger.info(jobDescriptions.toString());
		model.addAttribute("jobDescriptionList", jobDescriptions);
		return"jobrequestlist";
		
	}
	
	
	@GetMapping("mindgate/teamlead/home")
	public String teamLeadHome(HttpSession httpSession,Model model) 
	{
		logger.info("tlhome");
		if(httpSession.getAttribute("employee")==null)
		{
			logger.info("Session is invalid");
			return"redirect:/";
		}
		
		Employee employee=(Employee) httpSession.getAttribute("employee");
		
		Project project=projectServiceInterface.getProjectDetails(employee.getProject().getProjectId());
		
		logger.info(project.toString());
		model.addAttribute("project", project);
		
		return"teamleader";
	}
	
	
}
