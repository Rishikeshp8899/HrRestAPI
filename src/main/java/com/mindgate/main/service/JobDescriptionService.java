package com.mindgate.main.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindgate.main.domain.Candidate;
import com.mindgate.main.domain.JobDescription;
import com.mindgate.main.repository.JobDescriptionRepositoryInterface;
import com.mindgate.main.restcontroller.EmployeeDetailsRestController;

@Service
public class JobDescriptionService implements JobDescriptionServiceInterface {

	@Autowired
	private JobDescriptionRepositoryInterface jobDescriptionRepositoryInterface;

	Logger logger = LoggerFactory.getLogger(JobDescriptionService.class);

	@Override
	public List<JobDescription> getJobDescriptionBySendToHR() {
		List<JobDescription> JobDescriptionList = null;
		try {
			JobDescriptionList = jobDescriptionRepositoryInterface.getJobDescriptionBySendToHR();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return JobDescriptionList;
	}

	@Override
	public boolean addJobDescription(JobDescription jobDescription) {
		try {
			if (jobDescriptionRepositoryInterface.addJobDescription(jobDescription))
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateJobDescription(JobDescription jobDescription) {
		try {
			if (jobDescriptionRepositoryInterface.updateJobDescription(jobDescription))
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateRequiredCandidate(int requided_candidate, String jobDescriptionId) {
		try {
			if (jobDescriptionRepositoryInterface.updateRequiredCandidate(requided_candidate,
					Long.parseLong(jobDescriptionId)))
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateSendToHr(String jobDescriptionId, String value) {
		logger.info("In service");
		try {
			if (jobDescriptionRepositoryInterface.updateSendToHr(Long.parseLong(jobDescriptionId), value))
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		List<JobDescription> listJobGetJobDescriptionByPRojectId = null;
		try {
			listJobGetJobDescriptionByPRojectId = jobDescriptionRepositoryInterface
					.getJobDescriptionByPRojectId(Long.parseLong(ProjectId));
		} catch (Exception e) {
			e.printStackTrace();
		}

		return listJobGetJobDescriptionByPRojectId;

	}

	@Override
	public List<JobDescription> getJobJobDescriptionByPRojectIdAndPending(String ProjectId) {
		List<JobDescription> listJobGetJobDescriptionForPending = null;
		try {
			listJobGetJobDescriptionForPending = jobDescriptionRepositoryInterface
					.getJobJobDescriptionByPRojectIdAndPending(Long.parseLong(ProjectId));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listJobGetJobDescriptionForPending;

	}

	@Override
	public List<JobDescription> getJobJobDescriptionByPRojectIdAndNotFullfilled(String ProjectId) {
		List<JobDescription> listJobGetJobDescriptionForNotFulfiled = null;
		try {
			listJobGetJobDescriptionForNotFulfiled = jobDescriptionRepositoryInterface
					.getJobJobDescriptionByPRojectIdAndNotFullfilled(Long.parseLong(ProjectId));
		} catch (Exception e) {

			e.printStackTrace();
		}

		return listJobGetJobDescriptionForNotFulfiled;
	}

	@Override
	public boolean updateJobDescriptionStatus(String jobDescriptionId, String status) {
		try {
			if (jobDescriptionRepositoryInterface.updateJobDescriptionStatus(Long.parseLong(jobDescriptionId), status))
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateJobDescriptionSalary(String jobDescriptionId, double salary) {
		try {
			if (jobDescriptionRepositoryInterface.updateJobDescriptionSalary(Long.parseLong(jobDescriptionId), salary))
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public JobDescription getJobdescriptionById(String jobDescriptionId) {
		JobDescription jobDescription = null;
		try {
			jobDescription = jobDescriptionRepositoryInterface.getJobdescriptionById(Long.parseLong(jobDescriptionId));
		} catch (Exception e) {

			e.printStackTrace();
		}

		return jobDescription;
	}

	@Override
	public boolean postJobDescription(String jobDescriptionId) {
		try {
			if (jobDescriptionRepositoryInterface.postJobDescription(Long.parseLong(jobDescriptionId)))
				return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<JobDescription> getAllJobDiscriptionByPost() {
		List<JobDescription> getAllJobDiscriptionByPostList = null;
		try {
			getAllJobDiscriptionByPostList = jobDescriptionRepositoryInterface.getAllJobDiscriptionByPost();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getAllJobDiscriptionByPostList;
	}

	@Override
	public boolean decrementRequiredCandidate(String jobDescriptionId) {

		try {
			Long jobId = Long.parseLong(jobDescriptionId);

		
			JobDescription jobDescription = jobDescriptionRepositoryInterface.getJobdescriptionById(jobId);
			if (jobDescription == null || jobDescription.getRequiredCandidate() <= 0) {
				return false; 
			}

			
			if (!jobDescriptionRepositoryInterface.decrementRequiredCandidate(jobId)) {
				return false; 
			}

			// Fetch updated job description
			JobDescription updatedJobDescription = jobDescriptionRepositoryInterface.getJobdescriptionById(jobId);
			if (updatedJobDescription != null && updatedJobDescription.getRequiredCandidate() == 0) {
				return jobDescriptionRepositoryInterface.updateJobDescriptionStatus(jobId, "fulfilled");
			}
			return true;

		} catch (Exception e) {
			e.printStackTrace(); 
		}
		return false;

	}
}