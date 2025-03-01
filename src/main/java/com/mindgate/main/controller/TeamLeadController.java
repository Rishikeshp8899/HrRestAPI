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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mindgate.main.domain.JobDescription;
import com.mindgate.main.domain.Project;
import com.mindgate.main.service.JobDescriptionServiceInterface;

import jakarta.servlet.http.HttpSession;

@Controller
public class TeamLeadController {
	Logger logger = LoggerFactory.getLogger(TeamLeadController.class);

	@Autowired
	private JobDescriptionServiceInterface jobDescriptionServiceInterface;

	@GetMapping("mindgate/teamlead/jobrequest/{projectid}")
	public String jobRequest(@PathVariable("projectid") String projectid, Model model, HttpSession httpSession) {
		JobDescription description = new JobDescription();

		model.addAttribute("jobrequestobject", description);

		httpSession.setAttribute("projectid", projectid);

		return "jobdescriptionform";
	}

	@PostMapping("/mindgate/teamlead/jobrequest/addrequirement")
	public String savejobRequest(@ModelAttribute("jobrequestobject") JobDescription description,
			HttpSession httpSession, Model model,RedirectAttributes redirectAttributes) 
	{
		Project project = new Project();
		String projectid = (String) httpSession.getAttribute("projectid");

		project.setProjectId(projectid);

		description.setProject(project);

		if(jobDescriptionServiceInterface.addJobDescription(description))
		{
			logger.info(projectid);
			logger.info(description.toString());
			redirectAttributes.addFlashAttribute("alertMessage", true);
			return "redirect:/mindgate/teamlead/home";
		} 
		else 
		{
			redirectAttributes.addFlashAttribute("failedtodend", true);
			return "redirect:jobrequest/" + projectid;
		}

	}

}
