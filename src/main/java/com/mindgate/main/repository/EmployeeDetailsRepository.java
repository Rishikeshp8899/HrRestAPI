package com.mindgate.main.repository;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mindgate.main.domain.Employee;

@Repository
public class EmployeeDetailsRepository implements EmployeeDetailsRepositoryInterface
{
	Logger logger=LoggerFactory.getLogger(EmployeeDetailsRepository.class);
	private String SQLBYUSERNAMEPASSWORD = "select * from employee_details where EMPLOYEE_ID=? and password=?";
	
	private String SQLBYUSERNAME = "select * from employee_details where EMPLOYEE_ID=?";
	
	private String getEmployeeOnWorkBench = "select * from employee_details where project_id is null and role ='employee'";
	
	private String assignEmployeeToProject="update employee_details set project_id=? where EMPLOYEE_ID=? ";
	
	private String removeprojectId="update employee_details set project_id=null where EMPLOYEE_ID=?";
	
	private String addEmployee="INSERT INTO employee_details"
			+ "(employee_id,first_name,last_name,email,age,role,project_id,password,designation, primary_skill, secondary_skill, ternary_skill) "
			+ "values('MGS'||employee_id_sequence.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?)";
	
	private final String GET_EMPLOYEE_IS_INTERVIWER="SELECT * FROM employee_details WHERE IS_INTERVIEWER='yes'";
	
	private final String DELETE_EMPLOYEE="delete from employee_details where frist_name=? and DESIGNATION=? and PRIMARY_SKILL=? and SECONDARY_SKILL=? and TERNARY_SKILL=?";
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Employee validateEmployee(String username, String password) 
	{
		
		Object [] object= {username, password};
        try {
        Employee employee=jdbcTemplate.queryForObject(SQLBYUSERNAMEPASSWORD,new EmployeeDetailsRowMapper(), object);
        logger.info("in side emp validate try");
        if(employee != null)
            return employee;
        }
        catch(EmptyResultDataAccessException e) 
        {
           logger.info("employee not found");
           logger.info("in side emp validate catch");
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
	public List<Employee> getEmployeeOnWorkbench() 
	{
		List<Employee> employees=jdbcTemplate.query(getEmployeeOnWorkBench, new EmployeeDetailsRowMapper());
		if(!employees.isEmpty()) 
			return employees;
		
		
		return null;
	}

	@Override
	public boolean assignEmployeeToProject(String projectId, String employeeId) 
	{
		Object[] objects= {projectId,employeeId};
		int result=jdbcTemplate.update(assignEmployeeToProject, objects);
		if(result>0)
			return true;
		else
			return false;
	}

	@Override
	public boolean removeProjectId(String employeeId) 
	{
		int result=jdbcTemplate.update(removeprojectId, employeeId);
		if(result>0)
			return true;
		else
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

	@Override
	public boolean addEmplyee(Employee employee) 
	{
		Object [] objects= {
				employee.getFirstname(),
				employee.getLastname(),
				employee.getEmail(),
				employee.getAge(),
				employee.getRole(),
				employee.getProject().getProjectId(),
				employee.getPassword(),
				employee.getDesignation(),
				employee.getPrimarySkill(),
				employee.getSecondarySkill(),
				employee.getTernarySkill()
		};
		int flag=jdbcTemplate.update(addEmployee, objects);
		if(flag>0)
			return true;
		return false;
	}

	@Override
	public boolean deleteEployee(String fristName, String DESIGNATION, String PRIMARY_SKILL, String SECONDARY_SKILL,
			String TERNARY_SKILL) 
	{
		Object [] objects= {fristName,DESIGNATION,PRIMARY_SKILL,SECONDARY_SKILL,TERNARY_SKILL};
		int result=jdbcTemplate.update(DELETE_EMPLOYEE, objects);
		if(result>0)
			return true;
		return false;
	}

}
