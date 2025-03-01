package com.mindgate.main.service;

import java.util.List;

import com.mindgate.main.domain.Assesment;

public interface AssesmentServiceInterface {
	public List<String> getAllCandidateNotSelected();
	public Assesment getSelectedAssesment(String candidateId);
	public boolean insertAssesmentDetails(Assesment assesment);
	public List<Assesment> getAllAssesmentDetails();
	public Assesment getAssesmentByCandidateId(String candidateId);
}
