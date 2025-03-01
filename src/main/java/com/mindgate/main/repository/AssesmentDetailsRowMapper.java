package com.mindgate.main.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import com.mindgate.main.domain.AssesmentDetails;
import com.mindgate.main.domain.Candidate;

public class AssesmentDetailsRowMapper implements RowMapper<AssesmentDetails>{

    @Override
    public AssesmentDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
        AssesmentDetails assesmentDetails = new AssesmentDetails();
        
        assesmentDetails.setAssesmentId(rs.getString("assesment_id"));
        assesmentDetails.setTechSkill(rs.getString("techSkill"));
        assesmentDetails.setCommunication(rs.getString("communication"));
        assesmentDetails.setSoftSkills(rs.getString("softSkills"));
        assesmentDetails.setStatus(rs.getString("status"));
        assesmentDetails.setInterviewDate(rs.getDate("interview_date").toLocalDate());
        
        Candidate candidate = new Candidate();
        candidate.setCandidateId(rs.getString("candidate_id"));
        assesmentDetails.setCandidate(candidate);

        return assesmentDetails;
    }

}
