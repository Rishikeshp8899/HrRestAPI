package com.mindgate.main.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindgate.main.domain.Candidate;
import com.mindgate.main.domain.JobDescription;
import com.mindgate.main.repository.JobDescriptionRepository;
import com.mindgate.main.repository.JobDescriptionRepositoryInterface;
import com.mindgate.main.restcontroller.EmployeeDetailsRestController;

@Service
public class JobDescriptionService implements JobDescriptionServiceInterface {

	@Autowired
	private JobDescriptionRepositoryInterface jobDescriptionRepositoryInterface;

	Logger logger = LoggerFactory.getLogger(JobDescriptionService.class);

	@Override
	public List<JobDescription> getJobDescriptionBySendToHR() {
		List<JobDescription> JobDescriptionList = jobDescriptionRepositoryInterface.getJobDescriptionBySendToHR();
		if (!JobDescriptionList.isEmpty())
			return JobDescriptionList;
		return null;
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
	public boolean updateRequiredCandidate(int requided_candidate, String jobDescriptionId) {
		if (jobDescriptionRepositoryInterface.updateRequiredCandidate(requided_candidate, jobDescriptionId))
			return true;
		return false;
	}

	@Override
	public boolean updateSendToHr(String jobDescriptionId, String value) {
		logger.info("In service");
		if (jobDescriptionRepositoryInterface.updateSendToHr(jobDescriptionId, value))
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

		if ((listJobGetJobDescriptionByPRojectId != null) && (!listJobGetJobDescriptionByPRojectId.isEmpty()))
			return listJobGetJobDescriptionByPRojectId;
		return null;
	}

	@Override
	public List<JobDescription> getJobJobDescriptionByPRojectIdAndPending(String ProjectId) {
		List<JobDescription> listJobGetJobDescriptionForPending = jobDescriptionRepositoryInterface
				.getJobJobDescriptionByPRojectIdAndPending(ProjectId);

		if (!listJobGetJobDescriptionForPending.isEmpty())
			return listJobGetJobDescriptionForPending;

		return null;
	}

	@Override
	public List<JobDescription> getJobJobDescriptionByPRojectIdAndNotFullfilled(String ProjectId) {
		List<JobDescription> listJobGetJobDescriptionForNotFulfiled = jobDescriptionRepositoryInterface
				.getJobJobDescriptionByPRojectIdAndNotFullfilled(ProjectId);

		if (!listJobGetJobDescriptionForNotFulfiled.isEmpty())
			return listJobGetJobDescriptionForNotFulfiled;
		return null;
	}

	@Override
	public boolean updateJobDescriptionStatus(String jobDescriptionId, String status) {
		if (jobDescriptionRepositoryInterface.updateJobDescriptionStatus(jobDescriptionId, status))
			return true;
		return false;
	}

	@Override
	public boolean updateJobDescriptionSalary(String jobDescriptionId, double salary) {
		if (jobDescriptionRepositoryInterface.updateJobDescriptionSalary(jobDescriptionId, salary))
			return true;
		return false;
	}

	@Override
	public JobDescription getJobdescriptionById(String jobDescriptionId) {
		JobDescription jobDescription = jobDescriptionRepositoryInterface.getJobdescriptionById(jobDescriptionId);

		if (jobDescription != null) {
			return jobDescription;
			
		}

		return null;
	}

	@Override
	public boolean postJobDescription(String jobDescriptionId) {
		if (jobDescriptionRepositoryInterface.postJobDescription(jobDescriptionId))
			return true;
		return false;
	}

	@Override
	public List<JobDescription> getAllJobDiscriptionByPost() {
		List<JobDescription> getAllJobDiscriptionByPostList = jobDescriptionRepositoryInterface
				.getAllJobDiscriptionByPost();

		if (!getAllJobDiscriptionByPostList.isEmpty()) {
			return getAllJobDiscriptionByPostList;
		}
		return null;
	}

	@Override
	public boolean decrementRequiredCandidate(String jobDescriptionId) {

		JobDescription jobDescription = jobDescriptionRepositoryInterface.getJobdescriptionById(jobDescriptionId);

		if (jobDescription.getRequiredCandidate() > 0) {
			if (jobDescriptionRepositoryInterface.decrementRequiredCandidate(jobDescriptionId)) {
				JobDescription	jobDescriptionUpdate = jobDescriptionRepositoryInterface.getJobdescriptionById(jobDescriptionId);
				if (jobDescriptionUpdate.getRequiredCandidate() == 0) {
					if (jobDescriptionRepositoryInterface.updateStatusJobDescription(jobDescriptionId)) {
						return true;
					}
					jobDescriptionRepositoryInterface.updateJobDescription(jobDescription);
					return false;
				}
				return true;
			}

			return false;
		}

		return false;

	}
}