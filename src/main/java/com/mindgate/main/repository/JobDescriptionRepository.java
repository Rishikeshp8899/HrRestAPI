package com.mindgate.main.repository;

import java.sql.ResultSet;
import java.util.List;

import org.eclipse.tags.shaded.org.apache.regexp.recompile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mindgate.main.domain.JobDescription;
import com.mindgate.main.domain.Project;
import com.mindgate.main.service.JobDescriptionService;


public class JobDescriptionRepository{
//	@Autowired
//	JdbcTemplate jdbcTemplate;
//
//	private final String GET_JOB_DESCRIPTION_BY_SEND_TO_HR = "SELECT * FROM job_description where send_to_hr = 'yes' ";
//
//
//
//	private final String ADD_JOB_DESCRIPTION =
//			"INSERT INTO JOB_DESCRIPTION(jobdescription_id,ROLE,LOCATION,EXPERIENCE,ABOUT,PROJECT_ID,REQUIRED_CANDIDATE, PRIMARY_SKILL,SECONDARY_SKILL, TERNARY_SKILL)"
//			+ "VALUES('JD'||jobdescription_id_sequence.NEXTVAL,?,?,?,?,?,?,?,?,?)";
//
//	private final String UPDATE_JOB_DESCRIPTION = "UPDATE job_description SET  role=?, salary =?, location=? ,experience=? ,about=? ,project_id =?,"
//			+ "status = ?,requided_candidate =?,send_to_hr =?, primary_skill = ?, secondary_skill=?, tertary_skill = ? "
//			+ "WHERE  jobdescription_id=? ";
//
//	private final String UPDATE_REQUIRED_CANDIDATE = "UPDATE job_description SET requided_candidate = ? WHERE JOBDESCRIPTION_ID = ? ";
//
//	private final String UPDATE_SEND_TO_HR = "UPDATE job_description SET SEND_TO_HR= ? WHERE jobdescription_id=?";
//
//	private final String GET_JOB_DESCRIPTION_BY_PROJECT_ID = "SELECT * FROM job_description WHERE project_id = ?";
//	
//	private final String UPDATE_JOB_DESCRIPTION_SALARY = "UPDATE job_description SET salary =? WHERE jobdescription_id=? ";
//	
//    Logger logger = LoggerFactory.getLogger(JobDescriptionRepository.class);
//    private final String  GET_JOB_DESCRIPTION_BY_PROJECT_ID_AND_PENDING = 
//            "SELECT * FROM JOB_DESCRIPTION WHERE project_id = ? AND status = 'pending' ";
//    
//    private final String  GET_JOB_DESCRIPTION_BY_PROJECT_ID_AND_NOT_FULFILED  = 
//            "SELECT * FROM JOB_DESCRIPTION WHERE project_id = ? AND status = 'notFulfiled' ";
//    
//    private final String UPDATE_JOB_DESCRIPTION_STATUS =
//            "UPDATE job_description SET status = ? WHERE jobdescription_id=? ";
//    
//    private final String GET_JOB_DESCRIPTION_BY_ID = 
//    		"SELECT * FROM job_description WHERE jobdescription_id=? ";
//    
//    private final String POST_JOB_DESCRIPTION = 
//    		"UPDATE job_description SET POST_JOBDESCRIPTION = 'Post' WHERE jobdescription_id =? ";
//   
//    private final String GET_ALL_JOB_DISCRIPTION_BY_POST = "SELECT * FROM jobdescription_id WHERE POST_JOBDESCRIPTION = 'Post'";
//   
//    private final String UPDATE_REQUIDED_CANDIDATE_BY_ONE = "UPDATE job_description SET REQUIRED_CANDIDATE = REQUIRED_CANDIDATE-1 WHERE JOBDESCRIPTION_ID = ? ";
//    
//   // private final String UPDATE_JOB_DESCRIPTION_STATUS="UPDATE job_description SET STATUS = 'Fulfiled' WHERE JOBDESCRIPTION_ID = ? ";
//
//	public List<JobDescription> getJobDescriptionBySendToHR() {
//
//		try {
//			List<JobDescription> jobDescription = jdbcTemplate.query(GET_JOB_DESCRIPTION_BY_SEND_TO_HR,
//					new JobDescriptionRowMapper());
//
//			if (!jobDescription.isEmpty())
//				return jobDescription;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return null;
//	}
//	
////ROLE,LOCATION,EXPERIENCE,ABOUT,PROJECT_ID,REQUIRED_CANDIDATE, PRIMARY_SKILL,SECONDARY_SKILL, TERNARY_SKILL
//
//	public boolean addJobDescription(JobDescription jobDescription) {
//		Object[] object = {
//			jobDescription.getRole(),
//			jobDescription.getLocation(),
//			jobDescription.getExperience(),
//			jobDescription.getAbout(),
//			jobDescription.getProjectId().getProjectId(),
//			jobDescription.getRequiredCandidate(),
//			jobDescription.getPrimarySkill(),
//			jobDescription.getSecondarySkill(),
//			jobDescription.getTernarySkill()
//		};
//
//		int result = jdbcTemplate.update(ADD_JOB_DESCRIPTION, object);
//
//		if (result > 0)
//			return true;
//
//		return false;
//	}
//
//
//	public boolean updateJobDescription(JobDescription jobDescription) {
//		Object[] object = { jobDescription.getRole(),
//				jobDescription.getSalary(),
//				jobDescription.getLocation(),
//				jobDescription.getExperience(),
//				jobDescription.getAbout(),
//				jobDescription.getProjectId(),
//				jobDescription.getStatus(),
//				jobDescription.getRequiredCandidate(),
//				jobDescription.getSendToHr(),
//				jobDescription.getPrimarySkill(), 
//				jobDescription.getSecondarySkill(), 
//				jobDescription.getTernarySkill(),
//				jobDescription.getJobDescriptionId() };
//		int result = jdbcTemplate.update(UPDATE_JOB_DESCRIPTION, object);
//
//		if (result > 0)
//			return true;
//		return false;
//	}
//
//	public boolean updateRequiredCandidate(int requided_candidate, String jobDescriptionId) {
//
//		Object[] object = { requided_candidate, jobDescriptionId };
//		int result = jdbcTemplate.update(UPDATE_REQUIRED_CANDIDATE, object);
//
//		if (result > 0)
//			return true;
//
//		return false;
//	}
//
//
//	public boolean updateSendToHr(String jobDescriptionId, String value) {
//		Object[] objects = {value, jobDescriptionId };
//		
//		int result = jdbcTemplate.update(UPDATE_SEND_TO_HR, objects);
//		System.out.println(result);
//		if (result > 0)
//			return true;
//		else
//			return false;
//	}
//
//
//	public List<JobDescription> getAllJobdescription() {
//		try {
//			List<JobDescription> resultList = jdbcTemplate.query(GET_JOB_DESCRIPTION_BY_SEND_TO_HR,
//					new JobDescriptionRowMapper());
//			if (!resultList.isEmpty())
//				return resultList;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//
//		return null;
//	}
//
//
//	public List<JobDescription> getJobDescriptionByPRojectId(String ProjectId) {
//		try {
//			List<JobDescription> listGetJobDescriptionByPRojectId = jdbcTemplate
//					.query(GET_JOB_DESCRIPTION_BY_PROJECT_ID, new JobDescriptionRowMapper(),ProjectId);
//			if ((listGetJobDescriptionByPRojectId != null)&& (!listGetJobDescriptionByPRojectId.isEmpty()))
//				return listGetJobDescriptionByPRojectId;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//
//    public List<JobDescription> getJobJobDescriptionByPRojectIdAndPending(String ProjectId) {
//        try {
//            List<JobDescription> listGetJobDescriptionOfPending =
//                    jdbcTemplate.query(GET_JOB_DESCRIPTION_BY_PROJECT_ID_AND_PENDING, new JobDescriptionRowMapper(), ProjectId);
//            if(!listGetJobDescriptionOfPending.isEmpty())
//                return listGetJobDescriptionOfPending;
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//
//    public List<JobDescription> getJobJobDescriptionByPRojectIdAndNotFullfilled(String ProjectId) {
//        try {
//            List<JobDescription> listGetJobDescriptionOfPending =
//                    jdbcTemplate.query(GET_JOB_DESCRIPTION_BY_PROJECT_ID_AND_NOT_FULFILED, new JobDescriptionRowMapper(), ProjectId);
//            if(!listGetJobDescriptionOfPending.isEmpty())
//                return listGetJobDescriptionOfPending;
//            
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
//
//
//    public boolean updateJobDescriptionStatus(String jobDescriptionId, String status) {
//        Object [] object = {  status,jobDescriptionId};
//        int result = jdbcTemplate.update(UPDATE_JOB_DESCRIPTION_STATUS, object);
//        if( result > 0 )
//            return true;
//        return false;
//    }
//
//
//
//	public boolean updateJobDescriptionSalary(String jobDescriptionId, double salary) {
//		Object[] object = {salary, jobDescriptionId};
//		
//		int result = jdbcTemplate.update(UPDATE_JOB_DESCRIPTION_SALARY, object);
//		if(result > 0)
//			return true;
//		
//		return false;
//	}
//
//
//	public JobDescription getJobdescriptionById(String jobDescriptionId) {
//		try {
//		JobDescription result = jdbcTemplate.queryForObject(GET_JOB_DESCRIPTION_BY_ID, (ResultSet rs, int rowNum)->{
//			JobDescription jobDescription = new JobDescription();
//			jobDescription.setJobDescriptionId(rs.getString("JOBDESCRIPTION_ID"));
//			jobDescription.setRole(rs.getString("ROLE"));
//			jobDescription.setSalary(rs.getDouble("SALARY"));
//			jobDescription.setLocation(rs.getString("LOCATION"));
//			jobDescription.setPrimarySkill(rs.getString("PRIMARY_SKILL"));
//			jobDescription.setSecondarySkill(rs.getString("SECONDARY_SKILL"));
//			jobDescription.setTernarySkill(rs.getString("TERNARY_SKILL"));
//			jobDescription.setExperience(rs.getInt("EXPERIENCE"));
//			jobDescription.setAbout(rs.getString("ABOUT"));
//			Project project = new Project();
//			project.setProjectId(rs.getString("PROJECT_ID"));
//			jobDescription.setProjectId(project);
//			jobDescription.setStatus(rs.getString("STATUS"));
//			jobDescription.setRequiredCandidate(rs.getInt("REQUIRED_CANDIDATE"));
//			jobDescription.setSendToHr(rs.getString("SEND_TO_HR"));
//			jobDescription.setPostJobDescription(rs.getString("POST_JOBDESCRIPTION"));
//			return jobDescription;
//		},jobDescriptionId);
//		if(result != null)
//			return result;
//		}catch (Exception e) {
//			
//			e.printStackTrace();
//		}
//		return null;
//		
//	}
//
//
//	public boolean postJobDescription(String jobDescriptionId) {
//		int result = jdbcTemplate.update(POST_JOB_DESCRIPTION,jobDescriptionId );
//		if(result >0)
//			return true;
//		return false;
//	}
//
//
//
//	public List<JobDescription> getAllJobDiscriptionByPost() {
//		try {
//			List<JobDescription> getAllJobDiscriptionByPost = jdbcTemplate.query(GET_ALL_JOB_DISCRIPTION_BY_POST, new JobDescriptionRowMapper());
//			if(!getAllJobDiscriptionByPost.isEmpty()) {
//				return getAllJobDiscriptionByPost;
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//
//
//	public boolean decrementRequiredCandidate(String jobDescriptionId) {
//		int result = jdbcTemplate.update(UPDATE_REQUIDED_CANDIDATE_BY_ONE,jobDescriptionId );
//		if(result > 0)
//			return true;
//		return false;
//	}
//
//
//
//	public boolean updateStatusJobDescription(String jobDescriptionId) {
//		// TODO Auto-generated method stub
//		Object [] object= {
//				"fulfiled",
//				jobDescriptionId
//		};
//		int result =jdbcTemplate.update(UPDATE_JOB_DESCRIPTION_STATUS,object);
//		if(result >0)
//			return true;
//		return false;
//	}
}
