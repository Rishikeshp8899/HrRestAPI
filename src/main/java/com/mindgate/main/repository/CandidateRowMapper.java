package com.mindgate.main.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mindgate.main.domain.Candidate;
import com.mindgate.main.domain.Employee;
import com.mindgate.main.domain.JobDescription;

public class CandidateRowMapper implements RowMapper<Candidate>{

    @Override
    public Candidate mapRow(ResultSet rs, int rowNum) throws SQLException {
        Candidate candidate = new Candidate();
        Employee employee = new Employee();
        candidate.setCandidateId(rs.getString("CANDIDATE_ID"));
        candidate.setName(rs.getString("NAME"));
        candidate.setAge(rs.getInt("AGE"));
        candidate.setExperience(rs.getInt("EXPERIENCE"));
        candidate.setEmail(rs.getString("EMAIL"));
        candidate.setContactNumber(rs.getInt("CONTACT_NO"));
        candidate.setPrimarySkill(rs.getString("primary_skill"));
        candidate.setSecondarySkill(rs.getString("secondary_skill"));
        candidate.setTernarySkill(rs.getString("ternary_skill"));
        JobDescription jobDescription=new JobDescription();
        jobDescription.setJobDescriptionId(rs.getString("JOB_DESCRIPTION_ID"));
        candidate.setJobDescription(jobDescription);
        employee.setEmployeeId(rs.getString("INTERVIEWER_ID"));
        candidate.setEmployee(employee);
        candidate.setSendOfferLetter(rs.getString("SEND_OFFERLETTER"));
       
        return candidate;
    }

}

