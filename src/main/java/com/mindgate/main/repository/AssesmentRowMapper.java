package com.mindgate.main.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mindgate.main.domain.Assesment;
import com.mindgate.main.domain.Candidate;
import com.mindgate.main.domain.Employee;

public class AssesmentRowMapper implements RowMapper<Assesment> {

	@Override
	public Assesment mapRow(ResultSet rs, int rowNum) throws SQLException {
		Assesment assesment=new Assesment();
		assesment.setAssesmentId(rs.getString("ASSESMENT_ID"));
		Candidate candidate=new Candidate();
		candidate.setCandidateId(rs.getString("CANDIDATE_ID"));
		assesment.setCandidate(candidate);
		assesment.setCommunication(rs.getString("COMMUNICATION"));
		assesment.setInterviewDate(rs.getDate("INTERVIEW_DATE"));
		assesment.setStatus(rs.getString("STATUS"));
		assesment.setSoftSkill(rs.getString("SOFTSKILLS"));
		assesment.setCommunication(rs.getString("COMMUNICATION"));
		return assesment;
	}

}
