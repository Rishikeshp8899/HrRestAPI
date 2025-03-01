package com.mindgate.main.service;

import java.util.List;

import com.mindgate.main.domain.Project;

public interface ProjectServiceInterface {
    public Project getProjectDetails(String projectId);
    public boolean updateProjectDetails(Project project);
    public boolean addProjectDetails(Project project);
    public boolean updateAvailableFund(double availableFund, String projectId);

}