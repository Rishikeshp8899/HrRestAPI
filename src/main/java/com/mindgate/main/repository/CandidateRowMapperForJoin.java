package com.mindgate.main.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mindgate.main.domain.Candidate;
import com.mindgate.main.domain.Employee;
import com.mindgate.main.domain.JobDescription;
import com.mindgate.main.domain.Project;

public class CandidateRowMapperForJoin implements RowMapper<Candidate>
{

	@Override
	public Candidate mapRow(ResultSet rs, int rowNum) throws SQLException 
	{
		Candidate candidate = new Candidate();
        Employee employee = new Employee();
        Project project=new Project();
        JobDescription jobDescription=new JobDescription();
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
        jobDescription.setJobDescriptionId(rs.getString("JOB_DESCRIPTION_ID"));
        jobDescription.setRole(rs.getString("ROLE"));
        jobDescription.setRequiredCandidate(rs.getInt("REQUIRED_CANDIDATE"));
        project.setProjectId(rs.getString("PROJECT_ID"));
        jobDescription.setProject(project);
        candidate.setJobDesignation(jobDescription);
        
        return candidate;
	}
	
}
