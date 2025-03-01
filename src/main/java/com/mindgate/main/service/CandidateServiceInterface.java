package com.mindgate.main.service;

import java.util.Date;
import java.util.List;

import com.mindgate.main.domain.Candidate;
import com.mindgate.main.domain.Employee;

public interface CandidateServiceInterface {

	public boolean addCandidate(Candidate candidate);

	public boolean assignInterviewer(String interviewerId, String candidateId);

	public boolean deleteCandidate(String candidateId);

	public List<Candidate> getAllCandidate();

	public List<Candidate> getCandidateByInterviewerId(String InterviewerId);

	public List<Candidate> getCandidateByStatus(String status);

	public Candidate getCandidateByCandidateId(String candidateId);

	public List<Candidate> getAllCandidateByRequirement(String primarySkill, String secondarySkill,
			String TernarySkill);

	public boolean updateInterviewerId(String interviewerId, String candidateId, Date interviewDate);

	public List<Candidate> getCandidateHaveInterviewSchedule();

	public boolean setCandidateJobdescriptionIdNull(String candidateId);

	public boolean updateCandidateSetSendMail(String candidateId);

	public boolean updateSendOfferLetter(String sendOfferLetter, String candidateId);
}
