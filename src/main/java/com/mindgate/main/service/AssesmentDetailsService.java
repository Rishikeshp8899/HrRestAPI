package com.mindgate.main.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import com.mindgate.main.domain.AssesmentDetails;
import com.mindgate.main.repository.AssesmentDetailsRepositoryInterface;

@Component
public class AssesmentDetailsService implements AssesmentDetailsServiceInterface{
    
    @Autowired
    private AssesmentDetailsRepositoryInterface assesmentDetailsRepositoryInterface;
    
    @Override
    public boolean saveAssesmentDetails(AssesmentDetails assesmentDetails) {
        
        return assesmentDetailsRepositoryInterface.saveAssesmentDetails(assesmentDetails);
    }

    @Override
    public List<AssesmentDetails> getAssesmentByStatusIfSelected() {
        
        return assesmentDetailsRepositoryInterface.getAssesmentByStatusIfSelected();
    }

    @Override
    public List<AssesmentDetails> getAssesmentByStatusIfNotSelected() {
        
        return assesmentDetailsRepositoryInterface.getAssesmentByStatusIfNotSelected();
    }

  
	@Override
//	  @Cacheable("candidatebyid")
	public AssesmentDetails getAssesmentDetailsBycandidateId(String candidateId) 
	{
		return assesmentDetailsRepositoryInterface.getAssesmentDetailsBycandidateId(candidateId);
	}

	@Override
	public boolean updateAssesmentStatus(String assesmentId) {
		
		return assesmentDetailsRepositoryInterface.updateAssesmentStatus(assesmentId);
	}

}

