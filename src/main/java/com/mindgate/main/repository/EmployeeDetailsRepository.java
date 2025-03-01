package com.mindgate.main.repository;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mindgate.main.domain.Employee;
import com.mindgate.main.domain.JobDescription;
import com.mindgate.main.domain.Project;

@Repository
public class EmployeeDetailsRepository implements EmployeeDetailsRepositoryInterface
{
	Logger logger=LoggerFactory.getLogger(EmployeeDetailsRepository.class);
	private String SQLBYUSERNAMEPASSWORD = "select * from employee_details where EMPLOYEE_ID=? and password=?";
	private String SQLBYUSERNAME = "select * from employee_details where EMPLOYEE_ID=?";
	private String UPDATE_EMPLOYEE_DETAIL_PROJECT_ID = 
            "update  EMPLOYEE_DETAILS SET project_id = ? WHERE employee_id = ?";
	private String GET_EMPLOYEE_FROM_WORK_BENCH = 
			"SELECT * FROM employee_details where project_id is null ";
	private final String GET_EMPLOYEE_IS_INTERVIWER="SELECT * FROM employee_details WHERE IS_INTERVIEWER='yes'";
	
	private final String INSERT_EMPLOYEE = 
			"INSERT INTO employee_details(employee_id,firstName,lastName,age,role,password,designation,PRIMARY_SKILL,SECONDARY_SKILL,TERNARY_SKILL)"
			+ "VALUES ('MGS'||employee_id_sequence.NEXTVAL,?,?,?,?,?,?,?,?,?)";
	private final String DeleteEmployee="DELETE FROM employee_details WHERE FIRSTNAME=? AND LASTNAME=?  AND AGE=? AND ROLE=? AND PRIMARY_SKILL=? AND SECONDARY_SKILL=? AND TERNARY_SKILL=? AND DESIGNATION=? limit 1 ";
	 
	private final String LOGINSQL="select * from employee_details join project_details on employee_details.PROJECT_ID=project_details.PROJECT_ID where EMPLOYEE_ID=? AND PASSWORD=?";
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Employee validateEmployee(String username, String password) 
	{
		
		Object [] object= {username, password};
        try {
        Employee employee=jdbcTemplate.queryForObject(LOGINSQL,(ResultSet rs, int rowNum)->{
        	   Employee employee2=new Employee();
               employee2.setEmployeeId(rs.getString("EMPLOYEE_ID"));
               employee2.setAge(rs.getInt("AGE"));
               employee2.setFirstname(rs.getString("FIRSTNAME"));
               employee2.setLastname(rs.getString("LASTNAME"));
               Project project = new Project();
               project.setProjectId(rs.getString("PROJECT_ID"));
               project.setDescribsion(rs.getString("DESCRIBSION"));
               project.setBudget(rs.getInt("BUDGET"));
               project.setAvailable_fund(rs.getInt("AVAILABLE_FUND"));
               project.setProjectname(rs.getString("PROJECTNAME"));
               project.setStatus(rs.getString("STATUS"));
               employee2.setProject(project);
               employee2.setDesignation(rs.getString("DESIGNATION"));
               employee2.setPassword(rs.getString("PASSWORD"));
               employee2.setIs_interviewer(rs.getString("IS_INTERVIEWER"));
               employee2.setRole(rs.getString("ROLE"));
               employee2.setPrimarySkill(rs.getString("PRIMARY_SKILL"));
               employee2.setSecondarySkill(rs.getString("SECONDARY_SKILL"));
               employee2.setTernarySkill(rs.getString("TERNARY_SKILL"));
               
               return employee2;
        }, object);
        
        if(employee != null)
            return employee;
        }
        catch(EmptyResultDataAccessException e) 
        {
           logger.info("employee not found");
        }
        return null;

	}

	@Override
	public boolean checkEmployeeExist(String username) 
	{
		try 
		{
			Employee employee=jdbcTemplate.queryForObject(SQLBYUSERNAME,new EmployeeDetailsRowMapper(), username);
			  if(employee != null)
		            return true;
		} 
		catch (EmptyResultDataAccessException e) 
		{
			logger.info(e.getMessage());
		}
		
		
		return false;
	}

	@Override
	public Employee getEmployee(String employeeId) {
		 try {
		        Employee employee=jdbcTemplate.queryForObject(SQLBYUSERNAME,new EmployeeDetailsRowMapper(), employeeId);
		        
		        if(employee != null)
		            return employee;
		        }
		        catch(EmptyResultDataAccessException e) 
		        {
		           logger.info("employee not found");
		        }
		return null;
	}
	
	@Override
	public List<Employee> getEmployeeOnWorkBench() {
		try {
			List<Employee> getEmployeebyWorkBeanch = jdbcTemplate.query(GET_EMPLOYEE_FROM_WORK_BENCH, new EmployeeDetailsRowMapper());
			if(!getEmployeebyWorkBeanch.isEmpty())
				return getEmployeebyWorkBeanch;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateEmployeeProjectId(String employeeId, String projectId) {
		Object[] object = { projectId,employeeId};
        int result = jdbcTemplate.update(UPDATE_EMPLOYEE_DETAIL_PROJECT_ID, object);
        if(result > 0)
            return true;
		
		return false;
	}

	@Override
	public List<Employee> getInterviewerDetails() {
		try {
			List<Employee> getInterviewerDetailsList = jdbcTemplate.query(GET_EMPLOYEE_IS_INTERVIWER, new EmployeeDetailsRowMapper());
			if(!getInterviewerDetailsList.isEmpty())
				return getInterviewerDetailsList;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
//employee_id,firstName,lastName,age,role,password,designation,PRIMARY_SKILL,SECONDARY_SKILL,TERNARY_SKILL
	@Override
	public boolean insertEmployee(Employee employee) {
		Object [] object = {
				employee.getFirstname(),
				employee.getLastname(),
				employee.getAge(),
				employee.getRole(),
				employee.getPassword(),
				employee.getDesignation(),
				employee.getPrimarySkill(),
				employee.getSecondarySkill(),
				employee.getTernarySkill()
				
		};
		
		int result = jdbcTemplate.update(INSERT_EMPLOYEE, object);
		
		if(result > 0)
			return true;
		return false;
	}

	public boolean deleteEmployeeDetail(String FIRSTNAME,String LASTNAME,int AGE,String ROLE,String PRIMARY_SKILL,String SECONDARY_SKILL,String TERNARY_SKILL,String  DESIGNATION) {
		Object [] object= {FIRSTNAME,LASTNAME,AGE,ROLE,PRIMARY_SKILL,SECONDARY_SKILL,TERNARY_SKILL,DESIGNATION};
	int result=jdbcTemplate.update(DeleteEmployee,object);
	if(result >0) {
		return true;
	}
		return false;
	}
	

}

