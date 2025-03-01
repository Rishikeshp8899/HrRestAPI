package com.mindgate.main.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.mindgate.main.domain.Candidate;
import com.mindgate.main.domain.Employee;
import com.mindgate.main.repository.CandidateRepositoryInterface;

@Service
public class CandidateService implements CandidateServiceInterface {
	@Autowired
	private CandidateRepositoryInterface candidateRepositoryInterface;

	@Override
	public boolean addCandidate(Candidate candidate) {

		if (candidateRepositoryInterface.addCandidate(candidate))
			return true;
		return false;
	}

	@Override
	public boolean assignInterviewer(String interviewerId, String candidateId) {
		if (candidateRepositoryInterface.assignInterviewer(interviewerId, candidateId))
			return true;
		return false;
	}

	@Override
	public boolean deleteCandidate(String candidateId) {
		if (candidateRepositoryInterface.deleteCandidate(candidateId))
			return true;

		return false;
	}

	@Override
	public List<Candidate> getAllCandidate() {
		List<Candidate> candidateList = candidateRepositoryInterface.getAllCandidate();
		if (!candidateList.isEmpty())
			return candidateList;
		return null;
	}

	@Override
	public List<Candidate> getCandidateByInterviewerId(String InterviewerId) {
		List<Candidate> candidateByInterviewerId = candidateRepositoryInterface
				.getCandidateByInterviewerId(InterviewerId);
		if (!candidateByInterviewerId.isEmpty())
			return candidateByInterviewerId;

		return null;
	}

	@Override
	public List<Candidate> getCandidateByStatus(String status) {
		List<Candidate> candidateByStatus = candidateRepositoryInterface.getCandidateByStatus(status);

		if (!candidateByStatus.isEmpty())
			return candidateByStatus;
		return null;
	}

	@Override
	public Candidate getCandidateByCandidateId(String candidateId) {

		return candidateRepositoryInterface.getCandidateByCandidateId(candidateId);
	}

	@Override
	public List<Candidate> getAllCandidateByRequirement(String primarySkill,String secondarySkill,String TernarySkill) {
		List<Candidate> listCandidate=getAllCandidate();
		List<Candidate> listCandidate2=new ArrayList<Candidate>();
		List<String> JobRequirement=Arrays.asList(primarySkill,secondarySkill,TernarySkill);
		HashMap<String, Integer> listHash=new HashMap<>();
		if(!listCandidate.isEmpty()) {
			for(Candidate candidate:listCandidate) {
				List<String> candidateSkills=Arrays.asList(candidate.getPrimarySkill(),candidate.getSecondarySkill(),candidate.getTernarySkill());
				int result=matcher(JobRequirement,candidateSkills);
				listHash.put(candidate.getCandidateId(),result);
			}
			System.out.println(listHash);
	Map<String, Integer> sortedMap=listHash.entrySet().stream()
			.sorted(Map.Entry.comparingByValue())
			.collect(Collectors.toMap(
					Map.Entry::getKey,
					Map.Entry::getValue,
					(e1,e2)->e1,
					LinkedHashMap::new
					));
	System.out.println(sortedMap);
	for(Map.Entry<String, Integer> entry:sortedMap.entrySet()) {
		Candidate candidate=candidateRepositoryInterface.getCandidateByCandidateId(entry.getKey());
		listCandidate2.add(candidate);
	}
	Collections.reverse(listCandidate2);
		 return listCandidate2;
		}
			
		return null;
	}
	
	private int matcher(List<String> input1,List<String> input2) {
		int matcher=0;
		int pointer=0;
	
		for(int i=input1.size();i>0;i--) {
			for(int j=0;j<input2.size();j++) {
				if(input1.get(pointer).equalsIgnoreCase(input2.get(j)) &&(input1.get(pointer)!= null)&&(input2.get(j) != null) )
					matcher=matcher+i;

			}
			if(pointer<(input1.size()-1))
				pointer++;
		}
		return matcher;
	}

	@Override
	public boolean updateInterviewerId(String interviewerId, String candidateId, Date interviewDate) {
		if(candidateRepositoryInterface.updateInterviewerId(interviewerId, candidateId,interviewDate))
			return true;
		return false;
	}

	@Override
	public List<Candidate> getCandidateHaveInterviewSchedule() {
		List<Candidate> getCandidateHaveInterviewScheduleList = candidateRepositoryInterface.getCandidateHaveInterviewSchedule();
		if(!getCandidateHaveInterviewScheduleList.isEmpty())
			return getCandidateHaveInterviewScheduleList;
		return null;
	}

	@Override
	public boolean setCandidateJobdescriptionIdNull(String candidateId) {
		if(candidateRepositoryInterface.setCandidateJobdescriptionIdNull(candidateId))
			return true;
		return false;
	}

	@Override
	public boolean updateCandidateSetSendMail(String candidateId) {
		if(candidateRepositoryInterface.updateCandidateSetSendMail(candidateId))
			return true;
		return false;
	}

	@Override
	public boolean updateSendOfferLetter(String sendOfferLetter, String candidateId) {
		if(candidateRepositoryInterface.updateSendOfferLetter(sendOfferLetter, candidateId)) {
			return true;
		}
		return false;
	}



}