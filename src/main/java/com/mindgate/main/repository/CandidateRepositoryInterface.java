package com.mindgate.main.repository;

import java.sql.Date;
import java.util.List;

import com.mindgate.main.domain.Candidate;

public interface CandidateRepositoryInterface {
    
    public boolean addCandidate(Candidate candidate);
    
    public boolean  assignInterviewer(String interviewerId, String candidateId);
    
    public boolean deleteCandidate(String candidateId);
    
    public List<Candidate> getAllCandidate();
    
    public List<Candidate> getCandidateByInterviewerId(String InterviewerId);
    
    public List<Candidate> getCandidateByStatus(String status);
    
    
    public Candidate getCandidateByCandidateId(String candidateId);
    
    public List<Candidate> candidateBasedOnSkill(String primarySkill);
 
    public boolean updateInterviewerId(String interviewerId, String candidateId,Date date,String jobrequestid);

    public List<Candidate> selectedCandidate(String jobrequestId);
    
    public List<Candidate> getNewCandidate(String job_descriptionId);
    
    public boolean updateOfferletterStatus (String candidateId);
    
    public List<Candidate> offerLetterecivedCandidate();
    
    public List<Candidate> candidateAccordingInterviewer(String Interviewer);
    
    public boolean updateCandidateFromOfferLetter(String candidateId,String status);
    
}
