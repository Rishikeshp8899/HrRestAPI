package com.mindgate.main.controller;

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

import com.mindgate.main.domain.Employee;
import com.mindgate.main.domain.JobDescription;
import com.mindgate.main.domain.Project;
import com.mindgate.main.service.EmployeeDetailsServiceInterface;
import com.mindgate.main.service.JobDescriptionServiceInterface;
import com.mindgate.main.service.ProjectServiceInterface;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProjectManagerController {
	Logger logger = LoggerFactory.getLogger(ProjectManagerController.class);

	@Autowired
	private ProjectServiceInterface projectServiceInterface;

	@Autowired
	private JobDescriptionServiceInterface jobDescriptionServiceInterface;

	@Autowired
	private EmployeeDetailsServiceInterface employeeDetailsServiceInterface;

	@GetMapping("mindgate/projectmanager/home")
	public String projectManagerHome(HttpSession httpSession, Model model) {
		Employee employee = (Employee) httpSession.getAttribute("employee");

		Project project = projectServiceInterface.getProjectDetails(employee.getProject().getProjectId());
		httpSession.setAttribute("projectId", project.getProjectId());
		logger.info(project.toString());
		logger.info(project.getProjectId());
		List<JobDescription> jobDescriptionList = jobDescriptionServiceInterface
				.getJobJobDescriptionByPRojectIdAndPending(project.getProjectId());

		model.addAttribute("jobDescriptionList", jobDescriptionList);
		model.addAttribute("project", project);
		return "projectmanager";
	}

	@GetMapping("/mindgate/projectmanager/newrequest/{jobdescriptionid}")
	public String sendObjectToUpdate(@PathVariable("jobdescriptionid") String jdId, Model model,
			RedirectAttributes attributes) {
		JobDescription jobDescription = jobDescriptionServiceInterface.getJobRequest(jdId);
		if (jobDescription != null) {
			model.addAttribute("jobrequest", jobDescription);
			return "jdupdate";
		} else {
			attributes.addFlashAttribute("falidtoadd", true);
			return "redirect:/mindgate/projectmanager/home";
		}

	}

	@PostMapping("/mindgate/projectmanager/newrequest/accept")
	public String accept(@ModelAttribute("jobrequest") JobDescription jobDescription, RedirectAttributes attributes) {
		logger.info("accept");
		logger.info(jobDescription.toString());
		if (jobDescriptionServiceInterface.acceptTheRequest(jobDescription.getJobDescriptionId(),
				jobDescription.getSalary())) {
			logger.info("in side id");
			logger.info(jobDescription.toString());
			attributes.addFlashAttribute("add", true);
			return "redirect:/mindgate/projectmanager/home";
		}
		logger.info(jobDescription.toString());
		attributes.addFlashAttribute("falidtoadd", true);
		return "redirect:/mindgate/projectmanager/home";
	}

	@GetMapping("/mindgate/projectmanager/newrequest/reject/{jobdescriptionid}")
	public String reject(@PathVariable("jobdescriptionid") String jdId, RedirectAttributes attributes) {
		if (jobDescriptionServiceInterface.updateJobDescriptionStatus(jdId, "rejected")) {
			logger.info("reject");
			attributes.addFlashAttribute("remove", true);
			return "redirect:/mindgate/projectmanager/home";
		}
		attributes.addFlashAttribute("falidtoremove", true);
		return "redirect:/mindgate/projectmanager/home";
	}

	@GetMapping("mindgate/projectmanager/approvedrequests")
	public String approvedrequests(Model model, HttpSession httpSession) {
		String projectId = (String) httpSession.getAttribute("projectId");
		List<JobDescription> descriptions = jobDescriptionServiceInterface.getApprovedJobDescription(projectId);
		logger.info("approved list");
		model.addAttribute("AcceptedRequest", descriptions);
		return "appprovedJd";

	}

	@GetMapping("mindgate/projectmanager/approvedrequests/workbench/{jobrequestid}")
	public String getEmployeeOnWorkbench(@PathVariable("jobrequestid") String jobrequestid, Model model,
			HttpSession httpSession) {
		List<Employee> employees = employeeDetailsServiceInterface.getEmployeeOnWorkbench();
		httpSession.setAttribute("jobrequestid", jobrequestid);
//		logger.info(employees.toString());
		model.addAttribute("employeelist", employees);
		return "workbench";
	}

	@GetMapping("mindgate/projectmanager/approvedrequests/addemptoproject/{employeeId}")
	public String assignEmployeeToProject(@PathVariable("employeeId") String employeeId, HttpSession httpSession,
			RedirectAttributes attributes) {
		String projectId = (String) httpSession.getAttribute("projectId");
		String jobrequestid = (String) httpSession.getAttribute("jobrequestid");
		logger.info(projectId);
		logger.info(employeeId);
		logger.info(jobrequestid +" jd");
		JobDescription description=jobDescriptionServiceInterface.getJobRequest(jobrequestid);
		logger.info(description.toString());
		if(description.getRequiredCandidate()>0)
		{
			if (employeeDetailsServiceInterface.assignEmployeeToProject(projectId, employeeId)) 
			{
				if (jobDescriptionServiceInterface.updateRequiredCandidate(jobrequestid)) 
				{
					attributes.addFlashAttribute("added", true);
				} 
				else {
					employeeDetailsServiceInterface.removeProjectId(employeeId);
				}

			} else {
				attributes.addFlashAttribute("notadd", true);

			}

		}else {
			attributes.addFlashAttribute("reuirementfull", true);
			return"redirect:/mindgate/projectmanager/approvedrequests";
		}
		
		return "redirect:/mindgate/projectmanager/approvedrequests/workbench/" + jobrequestid;
	}

	@GetMapping("/mindgate/projectmanager/approvedrequests/sendtohr/{jobrequestid}")
	public String sendToHr(@PathVariable("jobrequestid") String jobrequestid, RedirectAttributes redirectAttributes) {
		if (jobDescriptionServiceInterface.updateSendToHr(jobrequestid)) {
			redirectAttributes.addFlashAttribute("sendedtohr", true);
		} else {
			redirectAttributes.addFlashAttribute("failedtosend", true);
		}

		return "redirect:/mindgate/projectmanager/approvedrequests";
	}
}
