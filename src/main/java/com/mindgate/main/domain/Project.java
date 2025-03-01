package com.mindgate.main.domain;

public class Project {
private String projectId;
private String projectname;
private String describsion;
private double budget;
private String status;
private double available_fund;
public String getProjectId() {
    return projectId;
}
public void setProjectId(String projectId) {
    this.projectId = projectId;
}
public String getProjectname() {
    return projectname;
}
public void setProjectname(String projectname) {
    this.projectname = projectname;
}
public String getDescribsion() {
    return describsion;
}
public void setDescribsion(String describsion) {
    this.describsion = describsion;
}
public double getBudget() {
    return budget;
}
public void setBudget(double d) {
    this.budget = d;
}
public String getStatus() {
    return status;
}
public void setStatus(String status) {
    this.status = status;
}
public double getAvailable_fund() {
    return available_fund;
}
public void setAvailable_fund(double d) {
    this.available_fund = d;
}
public Project(String projectId, String projectname, String describsion, double budget, String status,
        double available_fund) {
    
    this.projectId = projectId;
    this.projectname = projectname;
    this.describsion = describsion;
    this.budget = budget;
    this.status = status;
    this.available_fund = available_fund;
}
public Project(){}

}