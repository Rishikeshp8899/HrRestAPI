package com.mindgate.main.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mindgate.main.domain.Assesment;

@Repository
public class AssesmentRepository implements AssesmentRepositoryInterface {

	private final String GET_ALL_CANDIDATE="SELECT * FROM ASSESMENT_DETAILS WHERE STATUS='notSelected'";
	private final String GET_SELECTED_ASSESMENT =
			"SELECT * FROM ASSESMENT_DETAILS WHERE STATUS='selected' AND CANDIDATE_ID =?";
	
	private final String INSERT_ASSESMENT_DETAILS =
			"INSERT INTO ASSESMENT_DETAILS (techSkill, communication, softSkills, candidate_id, assesment_id, status, interview_date) "
			+ "VALUES (?, ?, ?,?, assesment_id_sequence.NEXTVAL,?, ?)";
	private final String get_all_assesment="select * from ASSESMENT_DETAILS";
	
	private final String GET_ASSESMENT_BY_CANDIDATE_ID = "SELECT * FROM ASSESMENT_DETAILS WHERE candidate_id =?";
	Logger logger = LoggerFactory.getLogger(AssesmentRepository.class);
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Assesment> getAllNotSelectedCandidate() {
		
		try {
			List<Assesment> CandidateList=jdbcTemplate.query(GET_ALL_CANDIDATE, new AssesmentRowMapper());
		
			if(!CandidateList.isEmpty())
				return CandidateList;
			
			
		}catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
		}
		
		return null;
	}

	@Override
	public Assesment getSelectedAssesment(String candidateId) {
		try {
			Assesment Candidate=jdbcTemplate.queryForObject(GET_SELECTED_ASSESMENT, new AssesmentRowMapper(), candidateId);
		
			if(Candidate != null)
				return Candidate;
			
			
		}catch (Exception e) {
			// TODO: handle exception
			logger.info(e.getMessage());
		}
		return null;
	}
//techSkill, communication, softSkills, candidate_id, assesment_id, status, interview_date
	@Override
	public boolean insertAssesmentDetails(Assesment assesment) {
		System.out.println("out");
		Object[]  object = {assesment.getTechSkill(),
				assesment.getCommunication(),
				assesment.getSoftSkill(),
				assesment.getCandidate().getCandidateId(),
				assesment.getStatus(),
				assesment.getInterviewDate() };
		int result=jdbcTemplate.update(INSERT_ASSESMENT_DETAILS,object);
		System.out.println("in"+result);
		if(result >0)
			return true;
		return false;
	}

	@Override
	public List<Assesment> getAllAssesmentDetails() {
		// TODO Auto-generated method stub
		try {
			List<Assesment> getAllAssesmentDetails=jdbcTemplate.query(get_all_assesment, new AssesmentRowMapper());
			if(!getAllAssesmentDetails.isEmpty())
				return getAllAssesmentDetails;
			return new ArrayList<Assesment>();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ArrayList<Assesment>();
	}

	@Override
	public Assesment getAssesmentByCandidateId(String candidateId) {
		try {
			Assesment assesment = 
					jdbcTemplate.queryForObject(GET_ASSESMENT_BY_CANDIDATE_ID, new AssesmentRowMapper(), candidateId);
			System.out.println(assesment);
			if(assesment != null)
				return assesment;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateAssesmentStatus(String assesmentId) {
		// TODO Auto-generated method stub
		return false;
	}

}
