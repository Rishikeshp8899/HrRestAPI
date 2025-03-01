package com.mindgate.main.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mindgate.main.domain.Project;
import com.mindgate.main.service.ProjectService;

@Repository
public class ProjectRepository implements ProjectRepositoryInterface {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	Logger logger = LoggerFactory.getLogger(ProjectRepository.class);
	private final String GET_PROJECT_DETAILS = "SELECT * FROM  project_details where PROJECT_ID=?";

	private final String UPDATE_PROJECT_DETAILS = "UPDATE project_details SET projectname =?,describsion =?,budget=?,status=?,available_fund=? "
			+ "WHERE project_id = ? ";
    private final String ADD_PROJECT_DETAILS = 
          "INSERT INTO project_details (projectname,project_id,describsion,budget,status,available_fund) "
          + "VALUES(?,'PRJ'||project_id_sequence.NEXTVAL,?,?,?,?)";  

	
	private final String UPDATE_AVAILABLE_FUND = "UPDATE project_details SET available_fund =? WHERE project_id = ?";

	@Override
	public Project getProjectDetails(String projcetId) {
		try {
			Object[] object = { projcetId };
			logger.info("ProjectRepository");
			Project listProjectDetails = jdbcTemplate.queryForObject(GET_PROJECT_DETAILS, new ProjectRowMappers(),
					object);
			if (listProjectDetails != null)
				return listProjectDetails;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean updateProjectDetails(Project project) {
		Object[] objects = { project.getProjectname(), project.getDescribsion(), project.getBudget(),
				project.getStatus(), project.getAvailable_fund(), project.getProjectId() };
		int result = jdbcTemplate.update(UPDATE_PROJECT_DETAILS, objects);
		if (result > 0)
			return true;
		return false;
	}

	@Override
	public boolean addProjectDetails(Project project) {
		Object[] objects = { project.getProjectId(), project.getProjectname(), project.getDescribsion(),
				project.getBudget(), project.getStatus(), project.getAvailable_fund() };
		int result = jdbcTemplate.update(ADD_PROJECT_DETAILS, objects);
		if (result > 0)
			return true;
		return false;
	}

	@Override
	public boolean updateAvailableFund(double availableFund, String projectId) {
		Object[] object = { availableFund, projectId };

		int result = jdbcTemplate.update(UPDATE_AVAILABLE_FUND, object);
		if (result > 0)
			return true;
		return false;
	}

}
