package com.mindgate.main.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindgate.main.domain.Employee;
import com.mindgate.main.service.EmployeeDetailsService;
import com.mindgate.main.service.EmployeeDetailsServiceInterface;

@Controller
public class EmployeeDetailsController 
{
	Logger logger=LoggerFactory.getLogger(EmployeeDetailsController.class);
	@Autowired
	private EmployeeDetailsService  employeeDetailsServiceInterface;
	
	@PostMapping("validate")
	public String validate(@RequestParam("username") String username ,@RequestParam("password") String password,Model model) 
	{
		logger.info(username);
		logger.info(password);
		logger.info("inside validate");
		
		if(employeeDetailsServiceInterface.checkEmployeeExist(username))
		{
			Employee employee= employeeDetailsServiceInterface.validateEmployee(username, password);
			if(employee!=null)
			{
				logger.info("notAuthoriedout side");
				if(employee.getRole() .equalsIgnoreCase("hr"))
				{
					return"hr";
				}
				else if(employee.getRole() .equalsIgnoreCase("project manager"))
				{
					return"projectmanager";
				}
				else if(employee.getRole() .equalsIgnoreCase("team leader"))
				{
					return"teamleader";
				}
				else if(employee.getRole() .equalsIgnoreCase("interviewer"))
				{
					return"interviewer";
				}
				else
				{
					logger.info("notAuthoried");
					model.addAttribute("notAuthoried", true);
					return"login";
				}
				
			}
			else
			{
				logger.info("invalidCredentials");
				model.addAttribute("invalidCredentials", true);
				return"login";
			}
		}
		else
		{
			logger.info("invalidId");
			model.addAttribute("invalidId", true);
			return"login";
		}
	}
}
