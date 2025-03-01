package com.mindgate.main.repository;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;

import org.springframework.jdbc.core.RowMapper;

import com.mindgate.main.domain.Candidate;
import com.mindgate.main.domain.Employee;
import com.mindgate.main.domain.JobDescription;

public class CandidateRowMapper implements RowMapper<Candidate>{

    @Override
    public Candidate mapRow(ResultSet rs, int rowNum) throws SQLException 
    {
        Candidate candidate = new Candidate();
        Employee employee = new Employee();
        
        candidate.setCandidateId(rs.getString("CANDIDATE_ID"));
        candidate.setFirstName(rs.getString("first_name"));
        candidate.setLastName(rs.getString("last_name"));
        candidate.setAge(rs.getInt("age"));
        candidate.setExperience(rs.getInt("EXPERIENCE"));
        candidate.setEmail(rs.getString("email"));
        candidate.setContactNumber(rs.getBigDecimal("CONTACT_NO"));
        employee.setEmployeeId(rs.getString("INTERVIEWER_ID"));
        candidate.setPrimarySkill(rs.getString("primary_skill"));
        candidate.setSecondarySkill(rs.getString("secondary_skill"));
        candidate.setTernarySkill(rs.getString("ternary_skill"));
//        candidate.setInterview_schedule(rs.getDate("interview_schedule").toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        candidate.setSendOfferLetter(rs.getString("send_offerletter"));
        candidate.setEmployee(employee);
        
        JobDescription jobDescription=new JobDescription();
        jobDescription.setJobDescriptionId(rs.getString("JOB_DESCRIPTION_ID"));
        candidate.setJobDesignation(jobDescription);
        
        return candidate;
    }

}

