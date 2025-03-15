package com.mindgate.main.repository;

import java.util.List;

import com.mindgate.main.domain.Assesment;

public interface AssesmentRepositoryInterface {
	public List<Assesment> getAllNotSelectedCandidate() throws Exception;

	public Assesment getSelectedAssesment(Long candidateId) throws Exception;

	public void insertAssesmentDetails(Assesment assesment) throws Exception;

	public List<Assesment> getAllAssesmentDetails() throws Exception;

	public Assesment getAssesmentByCandidateId(Long candidateId) throws Exception;

	public boolean updateAssesmentStatus(Long assesmentId) throws Exception;

}
