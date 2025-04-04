package com.mindgate.main.repository;

import java.util.List;

import com.mindgate.main.domain.JobDescription;

public interface JobDescriptionRepositoryInterface {
    
    public List<JobDescription> getJobDescriptionBySendToHR() throws Exception;
    public boolean addJobDescription(JobDescription jobDescription) throws Exception;
    public boolean updateJobDescription(JobDescription jobDescription) throws Exception;
    public boolean updateRequiredCandidate(int requiredCandidate, Long jobDescriptionId ) throws Exception;
    public boolean updateSendToHr(Long jobDescriptionId, String value) throws Exception;
    public List<JobDescription> getAllJobdescription();
    public List<JobDescription> getJobDescriptionByPRojectId(Long ProjectId)throws Exception;
    public List<JobDescription> getJobJobDescriptionByPRojectIdAndPending(Long ProjectId) throws Exception;
    public List<JobDescription> getJobJobDescriptionByPRojectIdAndNotFullfilled(Long ProjectId) throws Exception;
    public boolean updateJobDescriptionStatus(Long jobDescriptionId, String status)throws Exception;
    public boolean updateJobDescriptionSalary(Long jobDescriptionId, double salary) throws Exception;
    public JobDescription getJobdescriptionById(Long jobDescriptionId) throws Exception;
    
    public boolean postJobDescription(Long jobDescriptionId ) throws Exception;
    public List<JobDescription> getAllJobDiscriptionByPost() throws Exception;
    public boolean decrementRequiredCandidate(Long jobDescriptionId) throws Exception;
    public boolean updateStatusJobDescriptionFulfiled(Long jobDescriptionId) throws Exception;
}