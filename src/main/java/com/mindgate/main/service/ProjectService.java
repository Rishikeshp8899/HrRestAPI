package com.mindgate.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindgate.main.domain.Project;
import com.mindgate.main.repository.ProjectRepositoryInterface;
@Service
public class ProjectService implements ProjectServiceInterface {
    @Autowired
    private ProjectRepositoryInterface projectRepositoryInterface;

    @Override
    public Project getProjectDetails(String projectId) {
        return projectRepositoryInterface.getProjectDetails(projectId);
       
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
        if(projectRepositoryInterface.updateAvailableFund(availableFund, projectId))
            return true;
        return false;
    }
    
    
}