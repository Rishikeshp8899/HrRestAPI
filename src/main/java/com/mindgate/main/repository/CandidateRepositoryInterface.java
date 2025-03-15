package com.mindgate.main.repository;

import java.util.Date;
import java.util.List;

import com.mindgate.main.domain.Candidate;

public interface CandidateRepositoryInterface {
    
    public boolean addCandidate(Candidate candidate) throws Exception;
    
    public boolean  assignInterviewer(Long interviewerId, Long candidateId) throws Exception;
    
    public boolean deleteCandidate(Long candidateId) throws Exception;
    
    public List<Candidate> getAllCandidate() throws Exception;
    
    public List<Candidate> getCandidateByInterviewerId(Long InterviewerId) throws Exception;
    
    public List<Candidate> getCandidateByStatus(String status) throws Exception;
    
    
    public Candidate getCandidateByCandidateId(Long candidateId);
    
    public boolean updateInterviewerId(Long interviewerId, Long candidateId, Date interviewDate) throws Exception;
    
    public List<Candidate> getCandidateHaveInterviewSchedule() throws Exception;
    public boolean setCandidateJobdescriptionIdNull(Long candidateId) throws Exception;
    
    public boolean updateCandidateSetSendMail(Long candidateId)throws Exception;
    
    public boolean updateSendOfferLetter(String sendOfferLetter, Long candidateId)throws Exception;
  
    
    
}
