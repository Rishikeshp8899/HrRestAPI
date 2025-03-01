package com.mindgate.main.repository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import com.mindgate.main.domain.JobDescription;

@Repository
public class JobDescriptionRepository implements JobDescriptionRepositoryInterface 
{
	 Logger logger=LoggerFactory.getLogger(JobDescriptionRepository.class);
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	private final String GET_JOB_DESCRIPTION_BY_SEND_TO_HR = "SELECT * FROM job_description where send_to_hr='yes' and status='notFulfiled'";
	
	private final String GET_JOB_DESCRIPTION_BY_posted = "SELECT * FROM job_description where POSTJOBDESCRIPTION='Post' and status='notFulfiled'";
	

	private final String ADD_JOB_DESCRIPTION = "INSERT INTO JOB_DESCRIPTION(jobdescription_id,ROLE,LOCATION,EXPERIENCE,ABOUT,PROJECT_ID,REQUIRED_CANDIDATE, PRIMARY_SKILL,SECONDARY_SKILL, TERNARY_SKILL)VALUES('JD'||jobdescription_id_sequence.NEXTVAL,?,?,?,?,?,?,?,?,?)";

	private final String UPDATE_JOB_DESCRIPTION = "UPDATE job_description SET  role=?, salary =?, location=? ,experience=? ,about=? ,project_id =?,"
			+ "status = ?,requided_candidate =?, primary_skill = ?, secondary_skill=?, tertary_skill = ? "
			+ "WHERE  jobdescription_id=? ";

	private final String UPDATE_REQUIRED_CANDIDATE = "UPDATE job_description SET REQUIRED_CANDIDATE = REQUIRED_CANDIDATE-1  WHERE jobdescription_id = ? ";

	private final String UPDATE_SEND_TO_HR = "UPDATE job_description SET send_to_hr ='yes' WHERE jobdescription_id=?";

	private final String GET_JOB_DESCRIPTION_BY_PROJECT_ID = "SELECT * FROM job_description WHERE project_id = ?";

	private final String GET_JOB_DESCRIPTION_BY_PROJECT_ID_AND_PENDING = "SELECT * FROM JOB_DESCRIPTION WHERE project_id = ? AND status ='pending'";

	private final String GET_JOB_DESCRIPTION_BY_PROJECT_ID_AND_NOT_FULFILED = "SELECT * FROM JOB_DESCRIPTION WHERE project_id = ? AND status = 'notFulfiled' ";

	private final String UPDATE_JOB_DESCRIPTION_STATUS = "UPDATE job_description SET status = ? WHERE jobdescription_id=? ";
	
	private final String accet_job_request_update_salary = "UPDATE job_description SET status ='notFulfiled',salary=? WHERE jobdescription_id=? ";
	
	private final String get_job_description_byID="select * from job_description where jobdescription_id=?";
	
	private final String accepted_job_request="select * from job_description where status ='notFulfiled' and project_id=? and send_to_hr='no'";
	
	private final String post_the_job = "UPDATE job_description SET POSTJOBDESCRIPTION='Post' WHERE jobdescription_id=?";
	
	

	@Override
	public List<JobDescription> getJobDescriptionBySendToHR() {

		List<JobDescription> jobDescription = jdbcTemplate.query(GET_JOB_DESCRIPTION_BY_SEND_TO_HR,
				new JobDescriptionRowMapper());

		if (jobDescription!=null)
			return jobDescription;

		return new ArrayList<JobDescription>();
	}

	@Override
	public boolean addJobDescription(JobDescription jobDescription) {
		Object[] object = {
				jobDescription.getRole(),
				jobDescription.getLocation(), 
				jobDescription.getExperience(),
				jobDescription.getAbout(),
				jobDescription.getProject().getProjectId(),
				jobDescription.getRequiredCandidate(), 
				jobDescription.getPrimarySkill(),
				jobDescription.getSecondarySkill(),
				jobDescription.getTernarySkill(),
				};
		int result = jdbcTemplate.update(ADD_JOB_DESCRIPTION, object);

		if (result > 0)
			return true;

		return false;
	}

	@Override
	public boolean updateJobDescription(JobDescription jobDescription) {
		Object[] object = { jobDescription.getRole(), jobDescription.getSalary(), jobDescription.getLocation(),
				jobDescription.getExperience(), jobDescription.getAbout(), jobDescription.getProject().getProjectId(),
				jobDescription.getStatus(), jobDescription.getRequiredCandidate(), jobDescription.getSendToHr(),
				jobDescription.getPrimarySkill(), jobDescription.getSecondarySkill(), jobDescription.getTernarySkill(),
				jobDescription.getJobDescriptionId() };
		int result = jdbcTemplate.update(UPDATE_JOB_DESCRIPTION, object);

		if (result > 0)
			return true;
		return false;
	}

	@Override
	public boolean updateRequiredCandidate(String jobDescriptionId) 
	{

		int result = jdbcTemplate.update(UPDATE_REQUIRED_CANDIDATE, jobDescriptionId);

		if (result > 0)
			return true;

		return false;
	}

	@Override
	public boolean updateSendToHr(String jobDescriptionId) {
		int result = jdbcTemplate.update(UPDATE_SEND_TO_HR, jobDescriptionId);
		if (result > 0)
			return true;
		return false;
	}

	@Override
	public List<JobDescription> getAllJobdescription() {
		try {
			List<JobDescription> resultList = jdbcTemplate.query(GET_JOB_DESCRIPTION_BY_SEND_TO_HR,
					new JobDescriptionRowMapper());
			if (resultList!=null)
				return resultList;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ArrayList<JobDescription>();
	}

	@Override
	public List<JobDescription> getJobDescriptionByPRojectId(String ProjectId) {
		try {
			System.out.println(ProjectId);
			List<JobDescription> listGetJobDescriptionByPRojectId = jdbcTemplate
					.query(GET_JOB_DESCRIPTION_BY_PROJECT_ID, new JobDescriptionRowMapper(), ProjectId);
			if (!listGetJobDescriptionByPRojectId.isEmpty())
				return listGetJobDescriptionByPRojectId;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<JobDescription> getJobJobDescriptionByPRojectIdAndPending(String ProjectId) {
		try {
			List<JobDescription> listGetJobDescriptionOfPending = jdbcTemplate
					.query(GET_JOB_DESCRIPTION_BY_PROJECT_ID_AND_PENDING, new JobDescriptionRowMapper(), ProjectId);
			
			logger.info("list fetch");
			logger.info(ProjectId);
			logger.info(listGetJobDescriptionOfPending.toString());
			
			if (listGetJobDescriptionOfPending!=null)
				return listGetJobDescriptionOfPending;
		} 
		catch (Exception e) 
		{
			logger.info(e.getMessage());
		}
		return new ArrayList<JobDescription>();
	}

	@Override
	public List<JobDescription> getJobJobDescriptionByPRojectIdAndNotFullfilled(String ProjectId) {
		try {
			List<JobDescription> listGetJobDescriptionOfPending = jdbcTemplate.query(
					GET_JOB_DESCRIPTION_BY_PROJECT_ID_AND_NOT_FULFILED, new JobDescriptionRowMapper(), ProjectId);
			if (listGetJobDescriptionOfPending!=null)
				return listGetJobDescriptionOfPending;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<JobDescription>() ;
	}

	@Override
	public boolean updateJobDescriptionStatus(String jobDescriptionId, String status) {
		Object[] object = { status, jobDescriptionId };
		int result = jdbcTemplate.update(UPDATE_JOB_DESCRIPTION_STATUS, object);
		if (result > 0)
			return true;
		return false;
	}

	@Override
	public boolean acceptTheRequest(String jobDescriptionId, double salary) 
	{
		Object[] objects= {salary,jobDescriptionId};
		int result=jdbcTemplate.update(accet_job_request_update_salary, objects);
		logger.info(result+"");
		if(result>0)
		{
			return true;
		}
		return false;
	}

	@Override
	public JobDescription getJobRequest(String jobRequestId) 
	{
		try
		{
			JobDescription jobDescription=jdbcTemplate.queryForObject(get_job_description_byID, new JobDescriptionRowMapper(), jobRequestId);
			return jobDescription;
		}
		catch (Exception e) 
		{
			logger.info(e.getMessage());
		}
		return null;
	}

	@Override
	public List<JobDescription> getApprovedJobDescription(String projectId) 
	{
		try
		{
			List<JobDescription> jobDescriptions=jdbcTemplate.query(accepted_job_request,new JobDescriptionRowMapper(),projectId);
			if(!jobDescriptions.isEmpty())
				return jobDescriptions;
		}
		catch (Exception e) 
		{
			logger.info(e.getMessage());
		}
		
		return null;
	}

	@Override
	public List<JobDescription> getJobdescriptionByPosted() 
	{
		List<JobDescription> descriptions=jdbcTemplate.query(GET_JOB_DESCRIPTION_BY_posted, new JobDescriptionRowMapper());
		if(descriptions!=null)
		{
			return descriptions;
		}
		return new ArrayList<JobDescription>();
	}

	@Override
	public boolean postJobDescription(String jobDescriptionId) {
		int flag=jdbcTemplate.update(post_the_job,jobDescriptionId);
		if(flag>0)
		{
			return true;
		}
		return false;
	}

}
