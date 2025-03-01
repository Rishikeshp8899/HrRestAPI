package com.mindgate.main.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;

import com.mindgate.main.domain.Project;

public class ProjectRowMappers implements RowMapper<Project>{
	Logger logger = LoggerFactory.getLogger(ProjectRowMappers.class);

    @Override
    public Project mapRow(ResultSet rs, int rowNum) throws SQLException {
    	logger.info("ProjectRowMappers");
        Project project = new Project();
        project.setProjectname(rs.getString("PROJECTNAME"));
        project.setProjectId(rs.getString("PROJECT_ID"));
        project.setDescribsion(rs.getString("DESCRIBSION"));
        project.setBudget(rs.getDouble("BUDGET"));
        project.setStatus(rs.getString("STATUS"));
        project.setAvailable_fund(rs.getDouble("AVAILABLE_FUND"));
        return project;
    }
    

}