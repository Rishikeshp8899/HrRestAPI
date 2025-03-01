package com.mindgate.main.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.security.auth.Subject;

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

import com.mindgate.main.domain.Candidate;
import com.mindgate.main.domain.Employee;
import com.mindgate.main.domain.JobDescription;
import com.mindgate.main.domain.Project;
import com.mindgate.main.service.AssesmentDetailsServiceInterface;
import com.mindgate.main.service.CandidateServiceInterface;
import com.mindgate.main.service.EmailService;
import com.mindgate.main.service.EmployeeDetailsServiceInterface;
import com.mindgate.main.service.JobDescriptionServiceInterface;
import com.mindgate.main.service.PdfGenerator;

import jakarta.servlet.http.HttpSession;

@Controller
public class HrController 
{
	Logger logger=LoggerFactory.getLogger(HrController.class);
	
	@Autowired
	private JobDescriptionServiceInterface jobDescriptionServiceInterface;
	
	@Autowired
	private CandidateServiceInterface candidateServiceInterface;
	
	@Autowired
	private EmployeeDetailsServiceInterface employeeDetailsServiceInterface;
	
	@Autowired
	private PdfGenerator generator;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private AssesmentDetailsServiceInterface assesmentDetailsServiceInterface;
	
	@GetMapping("mindgate/hr/home")
	public String hrhome(HttpSession httpSession,Model model) 
	{
		 Employee employee=(Employee) httpSession.getAttribute("employee");
		 List<JobDescription> jobDescriptions=jobDescriptionServiceInterface.getJobDescriptionBySendToHR();
		 model.addAttribute("jobrequestList", jobDescriptions);
		 logger.info(jobDescriptions.toString());
		return"hr";
	}
	
	
	@GetMapping("/mindgate/hr/home/previouscandidate/{primary}/{jobrequestid}")
	public String previousCandidate(@PathVariable("primary") String primarySkill,@PathVariable("jobrequestid") String jobRequestID,Model model,HttpSession httpSession)
	{
		httpSession.setAttribute("skill", primarySkill);
		httpSession.setAttribute("jobrequestid", jobRequestID);
		logger.info(primarySkill);
		List<Candidate> candidates=candidateServiceInterface.candidateBasedOnSkill(primarySkill);
		List<Employee> employees=employeeDetailsServiceInterface.getInterviewerDetails();
		logger.info(candidates.toString());
		logger.info(employees.toString());
		model.addAttribute("previouscandidate", candidates);
		model.addAttribute("interviewer", employees);
		return"previouscandidate";
	}
	
	@PostMapping("mindgate/hr/home/candidate/asigninterviewer/{candidateid}")
	public String assigninterviewer(@RequestParam("interviewer") String interviewer ,@RequestParam("date") LocalDate date,@PathVariable("candidateid") String candidateId,HttpSession httpSession,RedirectAttributes redirectAttributes) 
	{
		String skill= (String)httpSession.getAttribute("skill");
		String jobrequestid= (String)httpSession.getAttribute("jobrequestid");
		Date date2=Date.valueOf(date);
		logger.info(interviewer);
		logger.info(date+"");
		logger.info(candidateId);
		if(LocalDate.now().compareTo(date)>0)
		{
			logger.info("datenotvalid");
			redirectAttributes.addFlashAttribute("datenotvalid", true);
		}
		else
		{
			if(candidateServiceInterface.updateInterviewerId(interviewer, candidateId, date2,jobrequestid))
			{
				logger.info("intervieweradded");
				redirectAttributes.addFlashAttribute("assigned", true);
				
			}
			else
			{
				redirectAttributes.addFlashAttribute("failedtoupload", true);
			}
			
		}
		
		if(assesmentDetailsServiceInterface.getAssesmentDetailsBycandidateId(candidateId)!=null)
		{
			return"redirect:/mindgate/hr/home/previouscandidate/"+skill+"/"+jobrequestid;
		}
		else
		{
			return"redirect:/mindgate/hr/home/newapplycandidate/"+jobrequestid;
		}
	}
	
	@GetMapping("/mindgate/hr/selectedcandidate/{jobrequestId}")
	public String selectedCandidate(@PathVariable("jobrequestId") String jobrequestId, Model model,HttpSession httpSession) 
	{
		httpSession.setAttribute("jdid", jobrequestId);
		List<Candidate> candidates = candidateServiceInterface.selectedCandidate( jobrequestId);
		model.addAttribute("candidates", candidates);
		return "selected";
		
	}
	
	
	
	@GetMapping("mindgate/hr/home/postjob/{jobDescriptionId}")
	public String postJob(@PathVariable("jobDescriptionId") String jobDescriptionId,RedirectAttributes attributes) 
	{
		boolean flag=jobDescriptionServiceInterface.postJobDescription(jobDescriptionId);
		if(!flag)
		{
			attributes.addFlashAttribute("failedtopost", true);
		}
		else {
			attributes.addFlashAttribute("jobposted", true);
		}
		return"redirect:/mindgate/hr/home";
	}
	
	
	
	@GetMapping("/mindgate/hr/home/newapplycandidate/{jobDescriptionId}")
	public String newCandidate(@PathVariable("jobDescriptionId") String jobDescriptionId,Model model,HttpSession httpSession) 
	{
		httpSession.setAttribute("jobrequestid", jobDescriptionId);
		logger.info("new candidate");
		logger.info(jobDescriptionId);
		List<Candidate> candidates=candidateServiceInterface.getNewCandidate(jobDescriptionId);
		List<Employee> employees=employeeDetailsServiceInterface.getInterviewerDetails();
		logger.info(candidates.toString());
		
		model.addAttribute("interviewer", employees);
		model.addAttribute("previouscandidate", candidates);
		return"previouscandidate";
	}
	
	
	
	@GetMapping("mindgate/hr/selectedcandidate/sendofferletter/{candidateid}")
	public String offerLetterForm(@PathVariable("candidateid") String candidateid,RedirectAttributes redirectAttributes,HttpSession httpSession)
	{
		String jobrequest=(String) httpSession.getAttribute("jdid");
		Candidate candidate=candidateServiceInterface.getCandidateByCandidateId(candidateid);
		if(candidate!=null)
		{
			if(candidate.getJobDesignation().getRequiredCandidate()>0)
			{
				logger.info("inside mail controller");
				JobDescription jobDescription=jobDescriptionServiceInterface.getJobRequest(candidate.getJobDesignation().getJobDescriptionId());
				String content="congratulations "+ candidate.getFirstName()+" "+candidate.getLastName()+" you are selected for the role "+jobDescription.getRole()+" at mindgate "
								+"\n Your salary package is "+ jobDescription.getSalary()+"lpa "+"\n i am sure you might have a greet integartion with all who were part of panel "
								+"regarding comapany future approach and that might have left a greet impact on you.";
				String subject="Offer Letter";
				String subject2="Required Document";
				String content2="required document you need to carry \n 1: Addhar card \n 2:pancard \n 3:degree certificate \n 4: 10 and 12th certificate \n";
				byte[] pdfdata= generator.generatedPDF(content);
				logger.info("pdg created");
				emailService.sendSimpleMessage(candidate.getEmail(), subject, content, pdfdata, candidate.getFirstName()+" "+candidate.getLastName());
				emailService.sendSimpleMessage(candidate.getEmail(), subject2, content2, null,candidate.getFirstName()+" "+candidate.getLastName());
				logger.info("mail send");
				
				if(candidateServiceInterface.updateOfferletterStatus(candidateid))
				{
					redirectAttributes.addFlashAttribute("offerlettersend",true);
				}
				else
				{
					redirectAttributes.addFlashAttribute("failedtoupdate", true);
				}
			
			}
			else
			{
				redirectAttributes.addFlashAttribute("requiredMentisFull", true);
			}
		}
		else
		{
			redirectAttributes.addFlashAttribute("failedtosend", true);
		}
		return"redirect:/mindgate/hr/selectedcandidate/"+jobrequest;
	}
	
	
//	@PostMapping("mindgate/hr/home/newcandidate/asigninterviewer/{candidateid}")
//	public String assigninterviewerfornew(@RequestParam("interviewer") String interviewer ,@RequestParam("date") LocalDate date,@PathVariable("candidateid") String candidateId,HttpSession httpSession,RedirectAttributes redirectAttributes) 
//	{
//		String jobDescription=(String) httpSession.getAttribute("jobDescription");
//		Date date2=Date.valueOf(date);
//		logger.info(interviewer);
//		logger.info(date+"");
//		logger.info(candidateId);
//		if(LocalDate.now().compareTo(date)>0)
//		{
//			logger.info("datenotvalid");
//			redirectAttributes.addFlashAttribute("datenotvalid", true);
//		}
//		else
//		{
//			if(candidateServiceInterface.updateInterviewerId(interviewer, candidateId, date2))
//			{
//				redirectAttributes.addFlashAttribute("assigned", true);
//			}
//			else
//			{
//				redirectAttributes.addFlashAttribute("failedtoupload", true);
//			}
//		}
//		return"redirect:/mindgate/hr/home/newapplycandidate/"+jobDescription;
//	}
//	
	
	@GetMapping("mindgate/hr/offerlettersendedcandidate")
	public String offerLetterSendCanidate(Model model) 
	{
		List<Candidate> candidates=candidateServiceInterface.offerLetterecivedCandidate();
		model.addAttribute("candidates", candidates);
		return"offerlettersendedcandidates";
	}
	
	@GetMapping("mindgate/hr/offerlettersendedcandidate/addtoemployee/{candidateId}")
	public String addToEmployee(@PathVariable("candidateId") String candidateId,RedirectAttributes redirectAttributes,HttpSession attributes) 
	{
		logger.info(candidateId);
		Candidate candidate = candidateServiceInterface.getCandidateByCandidateId(candidateId);
		logger.info(candidate.toString());
		if(candidate!=null)
		{
			if(candidate.getJobDesignation().getRequiredCandidate()>0)
			{
				
				Project project=new Project();
				Employee employee=new Employee();
				employee.setFirstname(candidate.getFirstName());
				employee.setLastname(candidate.getLastName());
				employee.setAge(candidate.getAge());
				employee.setRole("employee");
				employee.setDesignation(candidate.getJobDesignation().getRole());
				project.setProjectId(candidate.getJobDesignation().getProject().getProjectId());
				employee.setProject(project);
				employee.setPassword("Welcome@123");
				employee.setPrimarySkill(candidate.getPrimarySkill());
				employee.setSecondarySkill(candidate.getSecondarySkill());
				employee.setTernarySkill(candidate.getTernarySkill());
				if(employeeDetailsServiceInterface.addEmplyee(employee))
				{
					if(jobDescriptionServiceInterface.updateRequiredCandidate(candidate.getJobDesignation().getJobDescriptionId()))
					{
						candidateServiceInterface.updateCandidateFromOfferLetter(candidateId,"added");
					}
					else
					{
						employeeDetailsServiceInterface.deleteEployee(candidate.getFirstName(), candidate.getJobDesignation().getRole(), candidate.getPrimarySkill(), candidate.getSecondarySkill(), candidate.getTernarySkill());
					}
					
				}
				else
				{
					redirectAttributes.addFlashAttribute("faliedToAdd", true);
				}
			}
			else
			{
				redirectAttributes.addFlashAttribute("requiredmentFull", true);
			}
			
		}
		return"redirect:/mindgate/hr/offerlettersendedcandidate";
	}
	
	
	@GetMapping("mindgate/hr/offerlettersendedcandidate/delete/{candidateId}")
	public String deleteCandidatedFromSelectedtoNon(@PathVariable("candidateId") String candidateId,RedirectAttributes attributes,HttpSession httpSession) 
	{
		logger.info(candidateId);
		Candidate candidate=candidateServiceInterface.getCandidateByCandidateId(candidateId);
		if(candidate!=null)
		{
			if(candidateServiceInterface.updateCandidateFromOfferLetter(candidateId,"requiredMentfull"))
			{
					attributes.addFlashAttribute("deleted", true);
			}
		}
		else
		{
			attributes.addFlashAttribute("failedtodelete", true);
		}
		return"redirect:/mindgate/hr/offerlettersendedcandidate";
	}
	
	@GetMapping("mindgate/hr/selectedcandidate/delete/{candidateId}")
	public String deleteCandidatedFromSelected(@PathVariable("candidateId") String candidateId,HttpSession httpSession,RedirectAttributes redirectAttributes) 
	{
		String jobrequest=(String) httpSession.getAttribute("jdid");
		logger.info(candidateId);
		Candidate candidate=candidateServiceInterface.getCandidateByCandidateId(candidateId);
		if(candidate!=null)
		{
			if(candidateServiceInterface.updateCandidateFromOfferLetter(candidateId,"requiredMentfull"))
			{
				redirectAttributes.addFlashAttribute("deleted", true);
			}
		}
		return"redirect:/mindgate/hr/selectedcandidate/"+jobrequest;
	}
	
	
}
