package com.mindgate.main.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.mindgate.main.domain.Candidate;
import com.mindgate.main.repository.CandidateRepositoryInterface;

@Service
public class CandidateService implements CandidateServiceInterface {
    @Autowired
    private CandidateRepositoryInterface candidateRepositoryInterface;

    @Override
    public boolean addCandidate(Candidate candidate) {
        
        if(candidateRepositoryInterface.addCandidate(candidate))
            return true;
        return false;
    }

    @Override
    public boolean assignInterviewer(String interviewerId, String candidateId) {
        if(candidateRepositoryInterface.assignInterviewer(interviewerId, candidateId))
            return true;
        return false;
    }

    @Override
    public boolean deleteCandidate(String candidateId) {
        if(candidateRepositoryInterface.deleteCandidate(candidateId))
            return true;
        
        return false;
    }

    @Override
    public List<Candidate> getAllCandidate() {
        List<Candidate> candidateList = candidateRepositoryInterface.getAllCandidate();
        if(!candidateList.isEmpty())
            return candidateList;
        return null;
    }

    @Override
    public List<Candidate> getCandidateByInterviewerId(String InterviewerId) {
        List<Candidate> candidateByInterviewerId = candidateRepositoryInterface.getCandidateByInterviewerId(InterviewerId);
        if(!candidateByInterviewerId.isEmpty())
            return candidateByInterviewerId;
                
        return null;
    }

    @Override
    public List<Candidate> getCandidateByStatus(String status) {
        List<Candidate> candidateByStatus = candidateRepositoryInterface.getCandidateByStatus(status);
        
        if(!candidateByStatus.isEmpty())
            return candidateByStatus;
        return null;
    }

    @Override
    public Candidate getCandidateByCandidateId(String candidateId) {
        
        return candidateRepositoryInterface.getCandidateByCandidateId(candidateId);
    }

	@Override
	public List<Candidate> candidateBasedOnSkill(String primarySkill) 
	{
		// TODO Auto-generated method stub
		return candidateRepositoryInterface.candidateBasedOnSkill(primarySkill);
	}

	@Override
    public boolean updateInterviewerId(String interviewerId, String candidateId,Date date,String jobrequestid) {
        if(candidateRepositoryInterface.updateInterviewerId(interviewerId, candidateId,date,jobrequestid))
            return true;
        return false;
    }

	@Override
	public List<Candidate> selectedCandidate(String jobrequestId) {
		
		return candidateRepositoryInterface.selectedCandidate(jobrequestId);
	}

	
	@Override
//	@Cacheable("newcandoidate")
	public List<Candidate> getNewCandidate(String job_descriptionId) {
		
		return candidateRepositoryInterface.getNewCandidate(job_descriptionId);
	}
	
	
	@Override
//	@Cacheable("offerletterstatus")
	public boolean updateOfferletterStatus(String candidateId) 
	{
		
		return candidateRepositoryInterface.updateOfferletterStatus(candidateId);
	}

	@Override
//	@Cacheable("offerrecivedcandidate")
	public List<Candidate> offerLetterecivedCandidate() 
	{
		return candidateRepositoryInterface.offerLetterecivedCandidate();
	}

	@Override
	public List<Candidate> candidateAccordingInterviewer(String Interviewer) {
		// TODO Auto-generated method stub
		return candidateRepositoryInterface.candidateAccordingInterviewer(Interviewer);
	}

	@Override
	public boolean updateCandidateFromOfferLetter(String candidateId,String status) {
		
		return candidateRepositoryInterface.updateCandidateFromOfferLetter(candidateId,status);
	}
}