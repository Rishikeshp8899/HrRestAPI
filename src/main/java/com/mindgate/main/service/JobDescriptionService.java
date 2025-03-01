package com.mindgate.main.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindgate.main.domain.JobDescription;
import com.mindgate.main.repository.JobDescriptionRepositoryInterface;

@Service
public class JobDescriptionService implements JobDescriptionServiceInterface {
	Logger logger = LoggerFactory.getLogger(JobDescriptionService.class);

	@Autowired
	private JobDescriptionRepositoryInterface jobDescriptionRepositoryInterface;

	@Override
	public List<JobDescription> getJobDescriptionBySendToHR() {

		return jobDescriptionRepositoryInterface.getJobDescriptionBySendToHR();
	}

	@Override
	public boolean addJobDescription(JobDescription jobDescription) {
		if (jobDescriptionRepositoryInterface.addJobDescription(jobDescription))
			return true;
		return false;
	}

	@Override
	public boolean updateJobDescription(JobDescription jobDescription) {
		if (jobDescriptionRepositoryInterface.updateJobDescription(jobDescription))
			return true;
		return false;
	}

	@Override
	public boolean updateRequiredCandidate(String jobDescriptionId) {
		JobDescription jobDescription1 = jobDescriptionRepositoryInterface.getJobRequest(jobDescriptionId);
		if (jobDescription1.getRequiredCandidate() > 0) 
		{
			if (jobDescriptionRepositoryInterface.updateRequiredCandidate(jobDescriptionId)) 
			{
				JobDescription jobDescription = jobDescriptionRepositoryInterface.getJobRequest(jobDescriptionId);
				logger.info(jobDescription.toString());
				if(jobDescription.getRequiredCandidate()==0)
				{
					if (jobDescriptionRepositoryInterface.updateJobDescriptionStatus(jobDescriptionId, "fulfiled")) 
					{
						logger.info("job request status update");
						return true;
					} 
					else 
					{
						jobDescriptionRepositoryInterface.updateJobDescription(jobDescription1);
						return false;
					}
				}
				return true;
			} 
			else 
			{
				return false;
			}
		}
		return false;
	}

	@Override
	public boolean updateSendToHr(String jobDescriptionId) {
		if (jobDescriptionRepositoryInterface.updateSendToHr(jobDescriptionId))
			return true;
		return false;
	}

	@Override
	public List<JobDescription> getAllJobdescription() {
		List<JobDescription> JobDescriptionList = jobDescriptionRepositoryInterface.getAllJobdescription();
		if (!JobDescriptionList.isEmpty())
			return JobDescriptionList;
		return null;
	}

	@Override
	public List<JobDescription> getJobDescriptionByPRojectId(String ProjectId) {
		List<JobDescription> listJobGetJobDescriptionByPRojectId = jobDescriptionRepositoryInterface
				.getJobDescriptionByPRojectId(ProjectId);

		if (!listJobGetJobDescriptionByPRojectId.isEmpty())
			return listJobGetJobDescriptionByPRojectId;
		return null;
	}

	@Override
	public List<JobDescription> getJobJobDescriptionByPRojectIdAndPending(String ProjectId) {
		return jobDescriptionRepositoryInterface.getJobJobDescriptionByPRojectIdAndPending(ProjectId);
	}

	@Override
	public List<JobDescription> getJobJobDescriptionByPRojectIdAndNotFullfilled(String ProjectId) {
		return jobDescriptionRepositoryInterface.getJobJobDescriptionByPRojectIdAndNotFullfilled(ProjectId);

	}

	@Override
	public boolean updateJobDescriptionStatus(String jobDescriptionId, String status) {
		return jobDescriptionRepositoryInterface.updateJobDescriptionStatus(jobDescriptionId, status);
	}

	@Override
	public boolean acceptTheRequest(String jobDescriptionId, double salary) {

		return jobDescriptionRepositoryInterface.acceptTheRequest(jobDescriptionId, salary);
	}

	@Override
	public JobDescription getJobRequest(String jobRequestId) {

		return jobDescriptionRepositoryInterface.getJobRequest(jobRequestId);
	}

	@Override
	public List<JobDescription> getApprovedJobDescription(String projectId) {

		return jobDescriptionRepositoryInterface.getApprovedJobDescription(projectId);
	}

	@Override
	public List<JobDescription> getJobdescriptionByPosted() {
		// TODO Auto-generated method stub
		return jobDescriptionRepositoryInterface.getJobdescriptionByPosted();
	}

	@Override
	public boolean postJobDescription(String jobDescriptionId) {
		// TODO Auto-generated method stub
		return jobDescriptionRepositoryInterface.postJobDescription(jobDescriptionId);
	}

}
