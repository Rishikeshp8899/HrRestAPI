package com.mindgate.main.jpaRepoSDK;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindgate.main.domain.JobDescription;
import com.mindgate.main.jpaRepo.JobDescriptionRepo;
import com.mindgate.main.repository.JobDescriptionRepositoryInterface;

@Service("JobDescriptionSDKImpl")
public class JobDescriptionSDKImpl implements JobDescriptionRepositoryInterface {

	Logger logger = LoggerFactory.getLogger(JobDescriptionSDKImpl.class);

	@Autowired
	private JobDescriptionRepo jobDescriptionRepo;

	@Override
	public List<JobDescription> getJobDescriptionBySendToHR() {
		logger.info("JobDescriptionSDKImpl --> getJobDescriptionBySendToHR --> insider");
		List<JobDescription> listJobDescriptions = jobDescriptionRepo.findAll().stream()
				.filter(n -> n.getSendToHr() == "yse").toList();
		if (listJobDescriptions.isEmpty())
			return null;
		return listJobDescriptions;
	}

	@Override
	public boolean addJobDescription(JobDescription jobDescription) {

		logger.info("JobDescriptionSDKImpl --> addJobDescription --> inside");

		if (jobDescription == null) {
			logger.warn("Attempted to update a null JobDescription");
			return false;
		}

		JobDescription savedJobDescription = jobDescriptionRepo.save(jobDescription);

		return jobDescription.equals(savedJobDescription);
	}

	@Override
	public boolean updateJobDescription(JobDescription jobDescription) {
		logger.info("JobDescriptionSDKImpl --> updateJobDescription --> inside");

		if (jobDescription == null) {
			logger.warn("Attempted to update a null JobDescription");
			return false;
		}

		JobDescription savedJobDescription = jobDescriptionRepo.save(jobDescription);

		return jobDescription.equals(savedJobDescription);

	}

	@Override
	public boolean updateRequiredCandidate(int requiredCandidate, Long jobDescriptionId) {
		logger.info("JobDescriptionSDKImpl --> updateRequiredCandidate --> inside");

		if (jobDescriptionId == null || requiredCandidate <= 0) {
			return false;
		}

		return jobDescriptionRepo.findById(jobDescriptionId).map(jobDescription -> {
			jobDescription.setRequiredCandidate(requiredCandidate);
			jobDescriptionRepo.save(jobDescription);
			return true;
		}).orElse(false);
	}

	@Override
	public boolean updateSendToHr(Long jobDescriptionId, String value) {
		logger.info("JobDescriptionSDKImpl --> updateSendToHr --> inside");
		if (jobDescriptionId == null || value == null) {
			return false;
		}

		return jobDescriptionRepo.findById(jobDescriptionId).map(jobDescription -> {
			jobDescription.setSendToHr(value);
			jobDescriptionRepo.save(jobDescription);
			return true;
		}).orElse(false);
	}

	@Override
	public List<JobDescription> getAllJobdescription() {
		logger.info("JobDescriptionSDKImpl --> getAllJobdescription --> inside");

		return jobDescriptionRepo.findAll();
	}

	@Override
	public List<JobDescription> getJobDescriptionByPRojectId(Long ProjectId) {
		logger.info("JobDescriptionSDKImpl --> getJobDescriptionByPRojectId --> inside");
		return jobDescriptionRepo.findAll().stream().filter(n -> n.getProjectId().getProjectId() == ProjectId).toList();
	}

	@Override
	public List<JobDescription> getJobJobDescriptionByPRojectIdAndPending(Long ProjectId) {
		logger.info("JobDescriptionSDKImpl --> getJobJobDescriptionByPRojectIdAndPending --> inside");
		return jobDescriptionRepo.findAll().stream()
				.filter(n -> n.getProjectId().getProjectId() == ProjectId & n.getStatus() == "pending").toList();
	}

	@Override
	public List<JobDescription> getJobJobDescriptionByPRojectIdAndNotFullfilled(Long ProjectId) {
		logger.info("JobDescriptionSDKImpl --> getJobJobDescriptionByPRojectIdAndPending --> inside");
		return jobDescriptionRepo.findAll().stream()
				.filter(n -> n.getProjectId().getProjectId() == ProjectId & n.getStatus() == "notFulfiled").toList();
	}

	@Override
	public boolean updateJobDescriptionStatus(Long jobDescriptionId, String status) {
		logger.info("JobDescriptionSDKImpl --> updateJobDescriptionStatus --> inside");
		if (jobDescriptionId == null || status == null) {
			return false;
		}

		return jobDescriptionRepo.findById(jobDescriptionId).map(jobDescription -> {
			jobDescription.setStatus(status);
			jobDescriptionRepo.save(jobDescription);
			return true;
		}).orElse(false);

	}

	@Override
	public boolean updateJobDescriptionSalary(Long jobDescriptionId, double salary) {
		logger.info("JobDescriptionSDKImpl --> updateJobDescriptionStatus --> inside");
		if (jobDescriptionId == null || salary == 0) {
			return false;
		}

		return jobDescriptionRepo.findById(jobDescriptionId).map(jobDescription -> {
			jobDescription.setSalary(salary);
			jobDescriptionRepo.save(jobDescription);
			return true;
		}).orElse(false);
	}

	@Override
	public JobDescription getJobdescriptionById(Long jobDescriptionId) {
		logger.info("JobDescriptionSDKImpl --> updateSendToHr --> inside");
		if (jobDescriptionId == null) {
			return null;
		}

		Optional<JobDescription> savedJobDescription = jobDescriptionRepo.findById(jobDescriptionId);

		if (savedJobDescription.isPresent())
			return savedJobDescription.get();
		return null;
	}

	@Override
	public boolean postJobDescription(Long jobDescriptionId) {
		logger.info("JobDescriptionSDKImpl --> postJobDescription --> inside");
		if (jobDescriptionId == null) {
			return false;
		}

		Optional<JobDescription> savedJobDescription = jobDescriptionRepo.findById(jobDescriptionId);

		if (savedJobDescription.isPresent()) {
			JobDescription jobDescription = savedJobDescription.get();
			jobDescription.setPostJobDescription("Post");
			JobDescription jobDescriptiontwo = jobDescriptionRepo.save(jobDescription);
			if (jobDescriptiontwo == null)
				return false;
			return true;
		}
		return false;
	}

	@Override
	public List<JobDescription> getAllJobDiscriptionByPost() {
		logger.info("JobDescriptionSDKImpl --> getAllJobDiscriptionByPost --> inside");
		return jobDescriptionRepo.findAll().stream().filter(n -> n.getPostJobDescription().equalsIgnoreCase("Post")).toList();
	}

	@Override
	public boolean decrementRequiredCandidate(Long jobDescriptionId) {
		 logger.info("JobDescriptionSDKImpl --> decrementRequiredCandidate --> inside");

		    if (jobDescriptionId == null) {
		        logger.warn("JobDescriptionSDKImpl --> decrementRequiredCandidate --> jobDescriptionId is null");
		        return false;
		    }

		    Optional<JobDescription> savedJobDescription = jobDescriptionRepo.findById(jobDescriptionId);

		    if (savedJobDescription.isPresent()) {
		        JobDescription jobDescription = savedJobDescription.get();
		        
		        // Prevent negative values
		        if (jobDescription.getRequiredCandidate() <= 0) {
		            logger.warn("JobDescriptionSDKImpl --> decrementRequiredCandidate --> No candidates left to decrement.");
		            return false;
		        }

		        jobDescription.setRequiredCandidate(jobDescription.getRequiredCandidate() - 1);

		        try {
		            jobDescriptionRepo.save(jobDescription);
		            return true;
		        } catch (Exception e) {
		            logger.error("Error saving job description: ", e);
		            return false;
		        }
		    } else {
		        logger.warn("JobDescriptionSDKImpl --> decrementRequiredCandidate --> JobDescription not found for ID: " + jobDescriptionId);
		    }

		    return false;
	
	}

	@Override
	public boolean updateStatusJobDescriptionFulfiled(Long jobDescriptionId) throws Exception {
		logger.info("JobDescriptionSDKImpl --> updateStatusJobDescriptionFulfiled --> inside");

		if (jobDescriptionId == null) {
			logger.warn("Attempted to update a null JobDescription");
			return false;
		}

		Optional<JobDescription> savedJobDescription = jobDescriptionRepo.findById(jobDescriptionId);
		
		if(savedJobDescription.isPresent()) {
			  JobDescription jobDescription = savedJobDescription.get();
		        jobDescription.setStatus("fulfiled");

		        try {
		            jobDescriptionRepo.save(jobDescription);
		            return true;
		        } catch (Exception e) {
		            logger.error("Error saving job description: ", e);
		            return false;
		        }
		}

		return false;
	}



}
