package com.mindgate.main.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.mindgate.main.domain.Project;
import com.mindgate.main.repository.ProjectRepositoryInterface;
import com.mindgate.main.restcontroller.EmployeeDetailsRestController;
@Service
public class ProjectService implements ProjectServiceInterface {
    @Autowired
    @Qualifier("ProjectRepoSDKImpl")
    private ProjectRepositoryInterface projectRepositoryInterface;

    Logger logger = LoggerFactory.getLogger(ProjectService.class);
    @Override
    public Project getProjectDetails(String projcetId) {
        Project projectList = projectRepositoryInterface.getProjectDetails(Long.parseLong(projcetId));
        logger.info(projcetId);
        if(projectList != null) {
            return projectList;
        }
        return null;
    }

    @Override
    public boolean updateProjectDetails(Project project) {
        if(projectRepositoryInterface.updateProjectDetails(project))
            return true;
        return false;
    }

    @Override
    public boolean addProjectDetails(Project project) {
        if(projectRepositoryInterface.addProjectDetails(project))
            return true;
        return false;
    }

    @Override
    public boolean updateAvailableFund(double availableFund, String projectId) {
        if(projectRepositoryInterface.updateAvailableFund(availableFund, Long.parseLong(projectId)))
            return true;
        return false;
    }
    
    
}