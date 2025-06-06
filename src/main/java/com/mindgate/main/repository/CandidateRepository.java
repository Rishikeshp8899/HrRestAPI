package com.mindgate.main.repository;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.xml.crypto.Data;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mindgate.main.domain.Candidate;

@Repository
public class CandidateRepository implements CandidateRepositoryInterface {
    @Autowired
    JdbcTemplate jdbcTemplate;
    private final String ADD_ONE_CANDIDATE = "INSERT INTO candidate_details(candidate_id,job_description_id,first_name,last_name,age,experience,email,contact_no,primary_skill,secondary_skill,ternary_skill)"
            + "    VALUES(?,?,?,?,?,?,?,?,?,?,?)";
    private final String ASSIGN_INTERVIEWER = "UPDATE candidate_details SET INTERVIEWER_ID = ? WHERE candidate_id=?";

    private final String DELETE_CANDIDATE = "DELETE FROM candidate_details WHERE candidate_id = ?";

    private final String GET_ALL_CANDIDATE = "SELECT * FROM candidate_details";

    private final String GET_CANDIDATE_BY_INTERVIEWER_ID = "SELECT * FROM candidate_details WHERE interviewer_id = ?";

    private final String GET_CANDIDATE_BY_STATUS = "SELECT * FROM candidate_details WHERE status = ?";
    
    private final String GET_CANDIDATE_BY_CANDIDATE_ID = "SELECT * FROM candidate_details c,job_description j \r\n"
    		+ "where c.job_description_id = j.jobdescription_id and  c.candidate_id=?";
    
    private final String getCandidateBySkill="select * from candidate_details where candidate_id  \r\n"
    		+ "in(select candidate_id from assesment_details)\r\n"
    		+ "and (PRIMARY_SKILL=? or SECONDARY_SKILL=?) and JOB_DESCRIPTION_ID is null and SEND_OFFERLETTER='requiredMentfull'";

    private final String UPDATE_INTERVIEW_ID ="UPDATE candidate_details SET INTERVIEWER_ID =?,interview_schedule=?,JOB_DESCRIPTION_ID=? WHERE CANDIDATE_ID = ?";
    
    private final String SELECTED_CANDIDATE = "SELECT * FROM candidate_details WHERE candidate_id IN(SELECT candidate_id FROM assesment_details WHERE status='selected') and send_offerletter='no' and JOB_DESCRIPTION_ID=?";
    
    private final String NEW_CANDIDATE="select * from candidate_details where candidate_id not in(select candidate_id from assesment_details )and  send_offerletter='no' and job_description_id=?  and INTERVIEWER_ID is null and INTERVIEW_SCHEDULE is null";
    
    private final String UPDATE_OFFER_LETTER_STATUS="UPDATE candidate_details set send_offerletter='yes' where candidate_id=?";
    
    private final String OFFER_LETTER_SENDED_CANDIDATE="SELECT * FROM candidate_details where send_offerletter='yes'";
    
    private final String CANDIDATE_ACCORDDING_INTERVIEWER="SELECT * from candidate_details where INTERVIEWER_ID=? and candidate_id not in (SELECT candidate_id FROM assesment_details WHERE status='selected' or status='notSelected')";
    
    private final String UPDATE_JOBREQUEST_ID="update candidate_details set JOB_DESCRIPTION_ID = null where candidate_id=?";
    
    private final String UPDATE_CANDIDATE_FROM_OFFER_LETTER = "UPDATE candidate_details SET JOB_DESCRIPTION_ID = null, SEND_OFFERLETTER = ? WHERE CANDIDATE_ID = ?";
    
    private final String UPDATE_CANDIDATE_befor_OFFER_LETTER = "UPDATE candidate_details SET JOB_DESCRIPTION_ID = null, SEND_OFFERLETTER = 'no' WHERE CANDIDATE_ID = ?";
    @Override
    public boolean addCandidate(Candidate candidate) {
        Object[] object = {"CAN"+UUID.randomUUID().toString().substring(0, 8), candidate.getJobDesignation().getJobDescriptionId(), candidate.getFirstName(),candidate.getLastName(), candidate.getAge(),
                candidate.getExperience(), candidate.getEmail(), candidate.getContactNumber(), candidate.getPrimarySkill(),candidate.getSecondarySkill(),candidate.getTernarySkill() };
        int result = jdbcTemplate.update(ADD_ONE_CANDIDATE, object);
        if (result > 0)
            return true;

        return false;
    }

    @Override
    public boolean assignInterviewer(String interviewerId, String candidateId) {
        Object[] object = { interviewerId, candidateId };
        int result = jdbcTemplate.update(ASSIGN_INTERVIEWER, object);
        if (result > 0)
            return true;

        return false;
    }

    @Override
    public boolean deleteCandidate(String candidateId) {
        int result = jdbcTemplate.update(DELETE_CANDIDATE, candidateId);
        if (result > 0)
            return true;
        return false;
    }

    @Override
    public List<Candidate> getAllCandidate() {
        try {
            List<Candidate> candidateList = jdbcTemplate.query(GET_ALL_CANDIDATE, new CandidateRowMapper());

            if (!candidateList.isEmpty())
                return candidateList;
            
        }catch(Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<Candidate>();
    }

    @Override
    public List<Candidate> getCandidateByInterviewerId(String InterviewerId) {
        Object[] object = { InterviewerId };

        try {
            List<Candidate> candidateList = jdbcTemplate.query(GET_CANDIDATE_BY_INTERVIEWER_ID, new CandidateRowMapper(), object);

            if (!candidateList.isEmpty())
                return candidateList;
        }catch(Exception e) {
            e.printStackTrace();
        }
        

        return new ArrayList<Candidate>();
    }

    @Override
    public List<Candidate> getCandidateByStatus(String status) {
        Object[] object = { status };

        try {
        List<Candidate> candidateList = jdbcTemplate.query(GET_CANDIDATE_BY_STATUS, new CandidateRowMapper(), object);

        if (!candidateList.isEmpty())
            return candidateList;
        }catch(Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<Candidate>();
    }

    @Override
    public Candidate getCandidateByCandidateId(String candidateId) {
    
        try {
        Candidate candidate = jdbcTemplate.queryForObject(GET_CANDIDATE_BY_CANDIDATE_ID,new CandidateRowMapperForJoin(),candidateId);

        if (candidate != null)
            return candidate;
        }catch(EmptyResultDataAccessException e) {
           System.out.println(e.getMessage());
        }
        return null;
    }

	@Override
	public List<Candidate> candidateBasedOnSkill(String primarySkill) 
	{
		Object[] objects= {primarySkill ,primarySkill};
		List<Candidate> candidates=jdbcTemplate.query(getCandidateBySkill, new CandidateRowMapper(), objects);
		if(!candidates.isEmpty())
			return candidates;
			
		return new ArrayList<Candidate>();
	}
	
	@Override
	public boolean updateInterviewerId(String interviewerId, String candidateId,Date date,String jobrequestid) 
	{
        
        Object[] object = {interviewerId,date,jobrequestid, candidateId};
        int result = jdbcTemplate.update(UPDATE_INTERVIEW_ID, object);
        if(result>0)
            return true;
        return false;
	}

	@Override
	public List<Candidate> selectedCandidate(String jobrequestId) {
		List<Candidate> candidates = jdbcTemplate.query(SELECTED_CANDIDATE, new CandidateRowMapper(),jobrequestId);
		if(candidates!=null) {
			return candidates;
		}
		return new ArrayList<Candidate>();
	}

	@Override
	public List<Candidate> getNewCandidate(String jobdescriptionId) {
		List<Candidate> newCandidate = jdbcTemplate.query(NEW_CANDIDATE, new CandidateRowMapper(),new Object[] {jobdescriptionId});
		if(newCandidate!=null) {
			return newCandidate;
		}
		return new ArrayList<Candidate>();
	}

	@Override
	public boolean updateOfferletterStatus(String candidateId) 
	{
		int flag=jdbcTemplate.update(UPDATE_OFFER_LETTER_STATUS,candidateId);
		if(flag>0)
			return true;
		return false;
	}

	@Override
	public List<Candidate> offerLetterecivedCandidate() 
	{
		List<Candidate> candidates=jdbcTemplate.query(OFFER_LETTER_SENDED_CANDIDATE, new CandidateRowMapper());
		if(candidates!=null)
		{
			return candidates;
		}
		return new ArrayList<Candidate>();
	}

	@Override
	public List<Candidate> candidateAccordingInterviewer(String interviewerId) 
	{
		List<Candidate> candidates=jdbcTemplate.query(CANDIDATE_ACCORDDING_INTERVIEWER,new CandidateRowMapper(), interviewerId);
		if(candidates!=null)
			return candidates;
		return new ArrayList<Candidate>();
	}

	@Override
	public boolean updateCandidateFromOfferLetter(String candidateId,String status) {
		Object[] objects= {status,candidateId};
		int result = jdbcTemplate.update(UPDATE_CANDIDATE_FROM_OFFER_LETTER, objects);
		if(result > 0 ) {
			return true;
		}
		return false;
	}
}
