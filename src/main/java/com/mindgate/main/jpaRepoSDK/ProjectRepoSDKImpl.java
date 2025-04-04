package com.mindgate.main.jpaRepoSDK;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindgate.main.domain.JobDescription;
import com.mindgate.main.domain.Project;
import com.mindgate.main.jpaRepo.ProjectRepo;
import com.mindgate.main.repository.ProjectRepositoryInterface;

@Service("ProjectRepoSDKImpl")
public class ProjectRepoSDKImpl implements ProjectRepositoryInterface {

	Logger logger = LoggerFactory.getLogger(ProjectRepoSDKImpl.class);

	@Autowired
	private ProjectRepo projectRepo;

	@Override
	public Project getProjectDetails(Long projcetId) {
		logger.info("ProjectRepoSDKImpl --> getProjectDetails --> inside");
		if (projcetId == null) {
			return null;
		}

		Optional<Project> savedProject = projectRepo.findById(projcetId);

		if (savedProject.isPresent())
			return savedProject.get();
		return null;
	}

	@Override
	public boolean updateProjectDetails(Project project) {
		logger.info("ProjectRepoSDKImpl --> updateProjectDetails --> inside");
		if (project == null) {
			return false;
		}

		try {
			Project project2 = projectRepo.save(project);
			if (project2 == null)
				return false;
			return true;

		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}

	}

	@Override
	public boolean addProjectDetails(Project project) {
		logger.info("ProjectRepoSDKImpl --> updateProjectDetails --> inside");
		if (project == null) {
			return false;
		}

		try {
			Project project2 = projectRepo.save(project);
			if (project2 == null)
				return false;
			return true;

		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean updateAvailableFund(double availableFund, Long projectId) {
		logger.info("ProjectRepoSDKImpl --> updateProjectDetails --> inside");
		if (projectId == null) {
			return false;
		}

		try {
			Optional<Project> project = projectRepo.findById(projectId);
			if (project.isEmpty())
				return false;
			Project project2 = project.get();
			project2.setAvailable_fund(availableFund);
			project2 = projectRepo.save(project2);
			if (project2 == null)
				return false;
			return true;

		} catch (Exception e) {
			logger.error(e.getMessage());
			return false;
		}
	}

}
