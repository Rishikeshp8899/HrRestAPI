package com.mindgate.main.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.mindgate.main.domain.Employee;
import com.mindgate.main.domain.Project;

public class EmployeeDetailsRowMapper  {

//    @Override
//    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
//        // TODO Auto-generated method stub
//        Employee employee=new Employee();
//        employee.setEmployeeId(rs.getString("EMPLOYEE_ID"));
//        employee.setAge(rs.getInt("AGE"));
//        employee.setFirstname(rs.getString("FIRSTNAME"));
//        employee.setLastname(rs.getString("LASTNAME"));
//        Project project = new Project();
//        project.setProjectId(rs.getString("PROJECT_ID"));
//        employee.setProject(project);
//        employee.setDesignation(rs.getString("DESIGNATION"));
//        employee.setPassword(rs.getString("PASSWORD"));
//        employee.setIs_interviewer(rs.getString("IS_INTERVIEWER"));
//        employee.setRole(rs.getString("ROLE"));
//        employee.setPrimarySkill(rs.getString("PRIMARY_SKILL"));
//        employee.setSecondarySkill(rs.getString("SECONDARY_SKILL"));
//        employee.setTernarySkill(rs.getString("TERNARY_SKILL"));
//        
//        return employee;
//    }

    
}