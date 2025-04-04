package com.mindgate.main.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mindgate.main.domain.JobDescription;
import com.mindgate.main.domain.Project;

public class JobDescriptionRowMapper  {

//	@Override
//	public JobDescription mapRow(ResultSet rs, int rowNum) throws SQLException {
//		JobDescription jobDescription = new JobDescription();
//
//		jobDescription.setJobDescriptionId(rs.getString("JOBDESCRIPTION_ID"));
//		jobDescription.setRole(rs.getString("ROLE"));
//		jobDescription.setSalary(rs.getDouble("SALARY"));
//		jobDescription.setLocation(rs.getString("LOCATION"));
//		jobDescription.setPrimarySkill(rs.getString("primary_skill"));
//		jobDescription.setSecondarySkill(rs.getString("secondary_skill"));
//		jobDescription.setTernarySkill(rs.getString("ternary_skill"));
//		jobDescription.setExperience(rs.getInt("EXPERIENCE"));
//		jobDescription.setAbout(rs.getString("ABOUT"));
//
//		Project project = new Project();
//		project.setProjectId(rs.getString("PROJECT_ID"));
//		jobDescription.setProjectId(project);
//
//		jobDescription.setStatus(rs.getString("STATUS"));
//		jobDescription.setRequiredCandidate(rs.getInt("REQUIRED_CANDIDATE"));
//		jobDescription.setSendToHr(rs.getString("SEND_TO_HR"));
//
//		jobDescription.setPostJobDescription(rs.getString("POST_JOBDESCRIPTION"));
//
//		return jobDescription;
//	}

}
