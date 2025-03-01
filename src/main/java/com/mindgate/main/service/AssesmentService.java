package com.mindgate.main.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindgate.main.domain.Assesment;
import com.mindgate.main.repository.AssesmentRepositoryInterface;
@Service
public class AssesmentService implements AssesmentServiceInterface {

	@Autowired
	private AssesmentRepositoryInterface assesmentRepositoryInterface;
	
	@Override
	public List<String> getAllCandidateNotSelected() {
		// TODO Auto-generated method stub
		List<Assesment> assesmentList=assesmentRepositoryInterface.getAllNotSelectedCandidate();
	List<String> listOfCandidate = new ArrayList<String>();
	assesmentList.stream().forEach((assesment)->{
		listOfCandidate.add(assesment.getCandidate().getCandidateId());
		});
	
	return listOfCandidate;
	}

	@Override
	public Assesment getSelectedAssesment(String candidateId) {
		
		return assesmentRepositoryInterface.getSelectedAssesment(candidateId);
	}

	@Override
	public boolean insertAssesmentDetails(Assesment assesment) {
		
		if(assesmentRepositoryInterface.insertAssesmentDetails(assesment))
			return true;
		return false;
	}

	@Override
	public List<Assesment> getAllAssesmentDetails() {
		return assesmentRepositoryInterface.getAllAssesmentDetails();
	}

	@Override
	public Assesment getAssesmentByCandidateId(String candidateId) {
		
		Assesment assesment= assesmentRepositoryInterface.getAssesmentByCandidateId(candidateId);
		
		return assesment;
	}

}
