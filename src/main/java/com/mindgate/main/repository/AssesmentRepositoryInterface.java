package com.mindgate.main.repository;

import java.util.List;

import com.mindgate.main.domain.Assesment;

public interface AssesmentRepositoryInterface {
	public List<Assesment> getAllNotSelectedCandidate();
	public Assesment getSelectedAssesment(String candidateId);
	public boolean insertAssesmentDetails(Assesment assesment);
	public List<Assesment> getAllAssesmentDetails();
	public Assesment getAssesmentByCandidateId(String candidateId);
	public boolean updateAssesmentStatus(String assesmentId);

}
