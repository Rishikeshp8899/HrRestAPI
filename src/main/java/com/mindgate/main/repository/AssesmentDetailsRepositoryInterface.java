package com.mindgate.main.repository;

import java.util.List;

import com.mindgate.main.domain.AssesmentDetails;

public interface AssesmentDetailsRepositoryInterface 
{

	public boolean saveAssesmentDetails(AssesmentDetails assesmentDetails);

	public List<AssesmentDetails> getAssesmentByStatusIfSelected();

	public List<AssesmentDetails> getAssesmentByStatusIfNotSelected();
	
	public AssesmentDetails getAssesmentDetailsBycandidateId(String candidateId);
	
	public boolean updateAssesmentStatus(String assesmentId);

}
