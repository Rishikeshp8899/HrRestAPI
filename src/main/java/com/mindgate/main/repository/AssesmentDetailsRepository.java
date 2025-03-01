package com.mindgate.main.repository;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.mindgate.main.domain.AssesmentDetails;

@Component
public class AssesmentDetailsRepository implements AssesmentDetailsRepositoryInterface{
    
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    private  final String INSERT_NEW_ASSESMENT = "INSERT INTO assesment_details(assesment_id,candidate_id, techSkill, communication, softSkills, status, interview_date)" + " VALUES(assesment_id_sequence.NEXTVAL,?,?,?,?,?,?)";
    
    private  final String GET_ASSESMENT_BY_STATUS_IFSELECTED = "SELECT * FROM assesment_details WHERE status = 'selected'";
    
    private  final String GET_ASSESMENT_BY_STATUS_IFNOTSELECTED = "SELECT * FROM assesment_details WHERE status = 'notSelected'";
    
    private final String GET_ASSESMENT_BY_CANDIDATE_ID="select * from assesment_details where CANDIDATE_ID=?";
      
    private final String SET_ASSESMENT_STATUS=
    		"UPDATE assesment_details SET STATUS ='notSelected' WHERE CANDIDATE_ID =?";
    @Override
    public boolean saveAssesmentDetails(AssesmentDetails assesmentDetails) {
        Object [] parameters = {
        		assesmentDetails.getCandidate().getCandidateId(),
        		assesmentDetails.getTechSkill(), 
        		assesmentDetails.getCommunication(), 
        		assesmentDetails.getSoftSkills(), 
        		assesmentDetails.getStatus(),
        		Date.valueOf(assesmentDetails.getInterviewDate())};
        int result  = jdbcTemplate.update(INSERT_NEW_ASSESMENT, parameters);
        if(result>0)
            return true;
        return false;
    }

    @Override
    public List<AssesmentDetails> getAssesmentByStatusIfSelected() {
        AssesmentDetailsRowMapper assesmentDetailsRowMapper = new AssesmentDetailsRowMapper();
        List<AssesmentDetails> listAssesment = jdbcTemplate.query(GET_ASSESMENT_BY_STATUS_IFSELECTED, assesmentDetailsRowMapper);
        if(listAssesment!=null)
            return listAssesment;
        return new ArrayList<AssesmentDetails>();
    }

    @Override
    public List<AssesmentDetails> getAssesmentByStatusIfNotSelected() {
        AssesmentDetailsRowMapper assesmentDetailsRowMapper = new AssesmentDetailsRowMapper();
        List<AssesmentDetails> listAssesmentDetails = jdbcTemplate.query(GET_ASSESMENT_BY_STATUS_IFNOTSELECTED, assesmentDetailsRowMapper);
        if(listAssesmentDetails!=null)
            return listAssesmentDetails;
        return new ArrayList<AssesmentDetails>();
    }

	@Override
	public AssesmentDetails getAssesmentDetailsBycandidateId(String candidateId) 
	{
		try
		{
			AssesmentDetails assesmentDetails=jdbcTemplate.queryForObject(GET_ASSESMENT_BY_CANDIDATE_ID,new AssesmentDetailsRowMapper() ,candidateId);
			return assesmentDetails;
		}
		catch (EmptyResultDataAccessException e) 
		{
			return null;
		}

	}

	@Override
	public boolean updateAssesmentStatus(String assesmentId) {
		int result = jdbcTemplate.update(SET_ASSESMENT_STATUS, assesmentId);
		
		if(result >0) {
			return true;
		}
		return false;
	}
}
