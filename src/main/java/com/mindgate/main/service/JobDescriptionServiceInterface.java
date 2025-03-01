package com.mindgate.main.service;

import java.util.List;

import com.mindgate.main.domain.JobDescription;

public interface JobDescriptionServiceInterface {
    
    public List<JobDescription> getJobDescriptionBySendToHR();
    public boolean addJobDescription(JobDescription jobDescription);
    public boolean updateJobDescription(JobDescription jobDescription);
    public boolean updateRequiredCandidate(int requided_candidate, String jobDescriptionId );
    public boolean updateSendToHr(String jobDescriptionId, String value);
    public List<JobDescription> getAllJobdescription();
    public List<JobDescription> getJobDescriptionByPRojectId(String ProjectId);

    public List<JobDescription> getJobJobDescriptionByPRojectIdAndPending(String ProjectId);
    public List<JobDescription> getJobJobDescriptionByPRojectIdAndNotFullfilled(String ProjectId);
    public boolean updateJobDescriptionStatus(String jobDescriptionId, String status);
    public boolean updateJobDescriptionSalary(String jobDescriptionId, double salary);
    public JobDescription getJobdescriptionById(String jobDescriptionId);
    
    public boolean postJobDescription(String jobDescriptionId );
    public List<JobDescription> getAllJobDiscriptionByPost();
    public boolean decrementRequiredCandidate(String jobDescriptionId);
}