package com.mindgate.main.repository;

import java.util.List;

import com.mindgate.main.domain.Project;

public interface ProjectRepositoryInterface {
    
    public Project getProjectDetails(Long projcetId);
    public boolean updateProjectDetails(Project project);
    public boolean addProjectDetails(Project project);
    public boolean updateAvailableFund(double availableFund, Long projectId);
    

}