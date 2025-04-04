package com.mindgate.main.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mindgate.main.domain.Assesment;
import com.mindgate.main.domain.Candidate;
import com.mindgate.main.repository.AssesmentRepositoryInterface;

@Service
public class AssesmentService implements AssesmentServiceInterface {

	@Autowired
	@Qualifier("AssesmentRepoSDKImpl")
	private AssesmentRepositoryInterface assesmentRepositoryInterface;

	@Override
	public List<String> getAllCandidateNotSelected() {
		List<String> listOfCandidate = new ArrayList<>();
		List<Assesment> assesmentList;
		try {
			assesmentList = assesmentRepositoryInterface.getAllNotSelectedCandidate();

			assesmentList.stream().forEach((assesment) -> {
				listOfCandidate.add(String.valueOf(assesment.getCandidate().getCandidateId()));
			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listOfCandidate;
	}

	@Override
	public Assesment getSelectedAssesment(String candidateId) {

		try {
			return assesmentRepositoryInterface.getSelectedAssesment(Long.parseLong(candidateId));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean insertAssesmentDetails(Assesment assesment) {

		try {
			if (assesmentRepositoryInterface.insertAssesmentDetails(assesment))
				return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Assesment> getAllAssesmentDetails() {
		try {
			return assesmentRepositoryInterface.getAllAssesmentDetails();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Assesment getAssesmentByCandidateId(String candidateId) {

		Assesment assesment=null;
		try {
			assesment = assesmentRepositoryInterface.getAssesmentByCandidateId(Long.parseLong(candidateId));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return assesment;
	}

}
