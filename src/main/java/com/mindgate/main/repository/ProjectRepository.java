package com.mindgate.main.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mindgate.main.domain.Project;

@Repository
public class ProjectRepository implements ProjectRepositoryInterface
{
	Logger logger=LoggerFactory.getLogger(ProjectRepository.class);
	
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    
    private final String GET_PROJECT_DETAILS = "SELECT * FROM  project_details where project_id=?"; 
    
    private final String UPDATE_PROJECT_DETAILS =
            "UPDATE project_details SET projectname =?,describsion =?,budget=?,status=?,available_fund=? "
            + "WHERE project_id = ? ";
    private final String ADD_PROJECT_DETAILS = 
            "INSERT INTO project_details (projectname,project_id,describsion,budget,status,available_fund) "
            + "VALUES(?,'PRJ'||project_id_sequence.NEXTVAL,?,?,?,?)";
    
    private final String UPDATE_AVAILABLE_FUND = "UPDATE project_details SET available_fund =? WHERE project_id = ?";
    
    @Override
    public Project getProjectDetails(String projectId) {
        try {
            Project ProjectDetails = jdbcTemplate.queryForObject(GET_PROJECT_DETAILS, new ProjectRowMappers(),projectId);
            if(ProjectDetails!=null)
                return ProjectDetails;
        } 
        catch (EmptyResultDataAccessException e) 
        {
            logger.info(e.toString());
        }
        
        return null;
    }

    @Override
    public boolean updateProjectDetails(Project project) {
        Object [] objects = {
                project.getProjectname(),project.getDescribsion(),project.getBudget(),
                project.getStatus(),project.getAvailableFund(),project.getProjectId()
        };
        int result = jdbcTemplate.update(UPDATE_PROJECT_DETAILS, objects);
        if(result >0)
            return true;
        return false;
    }

    @Override
    public boolean addProjectDetails(Project project) {
        Object [] objects = {
                project.getProjectId(),project.getProjectname(),project.getDescribsion(),
                project.getBudget(),project.getStatus(),project.getAvailableFund()
        };
        int result = jdbcTemplate.update(ADD_PROJECT_DETAILS, objects);
        if(result >0)
            return true;
        return false;
    }

    @Override
    public boolean updateAvailableFund(double availableFund, String projectId) {
        Object [] object = {availableFund, projectId };
        
        int result = jdbcTemplate.update(UPDATE_AVAILABLE_FUND, object);
        if(result > 0)
            return true;
        return false;
    }

}
