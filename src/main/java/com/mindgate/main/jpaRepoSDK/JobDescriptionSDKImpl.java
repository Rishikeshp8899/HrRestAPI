package com.mindgate.main.jpaRepoSDK;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.mindgate.main.domain.JobDescription;
import com.mindgate.main.jpaRepo.JobDescriptionRepo;
import com.mindgate.main.repository.JobDescriptionRepositoryInterface;

public class JobDescriptionSDKImpl implements JobDescriptionRepositoryInterface {

	Logger logger = LoggerFactory.getLogger(JobDescriptionSDKImpl.class);
	
	@Autowired
	private JobDescriptionRepo jobDescriptionRepo;
	
	@Override
	public List<JobDescription> getJobDescriptionBySendToHR() {
		logger.info("JobDescriptionSDKImpl --> getJobDescriptionBySendToHR --> insider");
		List<JobDescription> listJobDescriptions=jobDescriptionRepo.findAll().stream().filter(n->n.getSendToHr()=="yse").toList();
		if(listJobDescriptions.isEmpty())
			
		return null;
	}

	@Override
	public boolean addJobDescription(JobDescription jobDescription) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateJobDescription(JobDescription jobDescription) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateRequiredCandidate(int requided_candidate, Long jobDescriptionId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateSendToHr(Long jobDescriptionId, String value) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<JobDescription> getAllJobdescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JobDescription> getJobDescriptionByPRojectId(Long ProjectId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JobDescription> getJobJobDescriptionByPRojectIdAndPending(Long ProjectId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JobDescription> getJobJobDescriptionByPRojectIdAndNotFullfilled(Long ProjectId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateJobDescriptionStatus(Long jobDescriptionId, String status) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateJobDescriptionSalary(Long jobDescriptionId, double salary) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public JobDescription getJobdescriptionById(Long jobDescriptionId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean postJobDescription(Long jobDescriptionId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<JobDescription> getAllJobDiscriptionByPost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean decrementRequiredCandidate(Long jobDescriptionId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateStatusJobDescription(Long jobDescriptionId) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
