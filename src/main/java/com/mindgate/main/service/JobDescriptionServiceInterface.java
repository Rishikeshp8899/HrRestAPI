package com.mindgate.main.service;

import java.util.List;

import com.mindgate.main.domain.JobDescription;

public interface JobDescriptionServiceInterface {
    
    public List<JobDescription> getJobDescriptionBySendToHR();
    public boolean addJobDescription(JobDescription jobDescription);
    public boolean updateJobDescription(JobDescription jobDescription);
    public boolean updateRequiredCandidate( String jobDescriptionId );
    public boolean updateSendToHr(String jobDescriptionId);
    public List<JobDescription> getAllJobdescription();
    public List<JobDescription> getJobDescriptionByPRojectId(String ProjectId);

    
    public List<JobDescription> getJobJobDescriptionByPRojectIdAndPending(String ProjectId);
    public List<JobDescription> getJobJobDescriptionByPRojectIdAndNotFullfilled(String ProjectId);
    public boolean updateJobDescriptionStatus(String jobDescriptionId, String status);
    public boolean acceptTheRequest(String jobDescriptionId,double salary);
    public JobDescription getJobRequest(String jobRequestId);
    public List<JobDescription> getApprovedJobDescription(String projectId);
    public List<JobDescription> getJobdescriptionByPosted();
    public boolean postJobDescription(String jobDescriptionId);
}