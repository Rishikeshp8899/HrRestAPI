package com.mindgate.main.repository;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mindgate.main.domain.Candidate;
import com.mindgate.main.domain.Employee;
import com.mindgate.main.domain.JobDescription;

@Repository
public class CandidateRepository implements CandidateRepositoryInterface {
    @Autowired
    JdbcTemplate jdbcTemplate;
    private final String ADD_ONE_CANDIDATE = "INSERT INTO candidate_details(candidate_id,job_description_id,name,age,experience,email,contact_no,primary_skill,secondary_skill,ternary_skill)"
            + "    VALUES('CAN'||candidate_id_sequence.NEXTVAL,?,?,?,?,?,?,?,?,?)";
    private final String ASSIGN_INTERVIEWER = "UPDATE candidate_details SET INTERVIEWER_ID = ? WHERE candidate_id=?";

    private final String DELETE_CANDIDATE = "DELETE FROM candidate_details WHERE candidate_id = ?";

    private final String GET_ALL_CANDIDATE = "SELECT * FROM candidate_details";

    private final String GET_CANDIDATE_BY_INTERVIEWER_ID = "SELECT * FROM candidate_details WHERE interviewer_id = ?";

    private final String GET_CANDIDATE_BY_STATUS = "SELECT * FROM candidate_details WHERE status = ?";
    
    private final String GET_CANDIDATE_BY_CANDIDATE_ID = "SELECT * FROM candidate_details WHERE CANDIDATE_ID = ?";

    private final String UPDATE_INTERVIEW_ID ="UPDATE candidate_details SET INTERVIEWER_ID =? , INTERVIEW_SCHEDULE = ? WHERE CANDIDATE_ID = ?";
    
    private final String GET_ALL_CANDIDATE_INTERVIEW_SCHEDULE="SELECT * FROM candidate_details WHERE INTERVIEW_SCHEDULE IS NOT NULL";
    
    private final String UPDATE_JOBDESCRIPTION_ID_BY_CANDIDATE_ID="UPDATE candidate_details set JOB_DESCRIPTION_ID= NULL where CANDIDATE_ID=?" ;
    
    private final String UPDATE_SEND_OFFERLETTER_TO_YES="UPDATE candidate_details SET SEND_OFFERLETTER='yes' WHERE CANDIDATE_ID=? ";
    
    private final String UPDATE_SEND_OFFERLETTER = "UPDATE candidate_details SET JOB_DESCRIPTION_ID = null, SEND_OFFERLETTER = ?  WHERE CANDIDATE_ID = ?";
    @Override
    public boolean addCandidate(Candidate candidate) {
        Object[] object = { candidate.getJobDescription().getJobDescriptionId(),
        		candidate.getName(),
        		candidate.getAge(),
                candidate.getExperience(), 
                candidate.getEmail(),
                candidate.getContactNumber(), 
                candidate.getPrimarySkill(),
                candidate.getSecondarySkill(),
                candidate.getTernarySkill() };
      
        int result = jdbcTemplate.update(ADD_ONE_CANDIDATE, object);
      
        if (result > 0)
            return true;

        return false;
    }

    @Override
    public boolean assignInterviewer(String interviewerId, String candidateId) {
        Object[] object = { interviewerId, candidateId };
        int result = jdbcTemplate.update(ASSIGN_INTERVIEWER, object);
        if (result > 0)
            return true;

        return false;
    }

    @Override
    public boolean deleteCandidate(String candidateId) {
        int result = jdbcTemplate.update(DELETE_CANDIDATE, candidateId);
        if (result > 0)
            return true;
        return false;
    }

    @Override
    public List<Candidate> getAllCandidate() {
        try {
            List<Candidate> candidateList = jdbcTemplate.query(GET_ALL_CANDIDATE, new CandidateRowMapper());

            if (!candidateList.isEmpty())
                return candidateList;
            
        }catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Candidate> getCandidateByInterviewerId(String InterviewerId) {
        Object[] object = { InterviewerId };

        try {
            List<Candidate> candidateList = jdbcTemplate.query(GET_CANDIDATE_BY_INTERVIEWER_ID, object ,(ResultSet rs, int rowNum)->{
                Candidate candidate = new Candidate();
                Employee employee = new Employee();
                candidate.setCandidateId(rs.getString("CANDIDATE_ID"));
                candidate.setName(rs.getString("NAME"));
                candidate.setAge(rs.getInt("AGE"));
                candidate.setExperience(rs.getInt("EXPERIENCE"));
                candidate.setEmail(rs.getString("EMAIL"));
                candidate.setContactNumber(rs.getInt("CONTACT_NO"));
                candidate.setPrimarySkill(rs.getString("PRIMARY_SKILL"));
                candidate.setSecondarySkill(rs.getString("SECONDARY_SKILL"));
                candidate.setTernarySkill(rs.getString("TERNARY_SKILL"));
                JobDescription jobDescription=new JobDescription();
                jobDescription.setJobDescriptionId(rs.getString("JOB_DESCRIPTION_ID"));
                candidate.setJobDescription(jobDescription);
                employee.setEmployeeId(rs.getString("INTERVIEWER_ID"));
                candidate.setEmployee(employee);
                candidate.setSendOfferLetter(rs.getString("SEND_OFFERLETTER"));
               
                return candidate;
            });

            if (!candidateList.isEmpty())
                return candidateList;
        }catch(Exception e) {
            e.printStackTrace();
        }
        

        return null;
    }

    @Override
    public List<Candidate> getCandidateByStatus(String status) {
        Object[] object = { status };

        try {
        List<Candidate> candidateList = jdbcTemplate.query(GET_CANDIDATE_BY_STATUS, new CandidateRowMapper(), object);

        if (!candidateList.isEmpty())
            return candidateList;
        }catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Candidate getCandidateByCandidateId(String candidateId) {
    
    Object [] object= {
    		candidateId
    };
        try {
        Candidate candidate = jdbcTemplate.queryForObject(GET_CANDIDATE_BY_CANDIDATE_ID, new CandidateRowMapper(),object);
       
        if (candidate != null)
            return candidate;
        }catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

	@Override
	public boolean updateInterviewerId(String interviewerId, String candidateId,Date interviewDate) {
		
		Object[] object = {interviewerId,interviewDate,candidateId};
		int result = jdbcTemplate.update(UPDATE_INTERVIEW_ID, object);
		if(result>0)
			return true;
		return false;
	}

	@Override
	public List<Candidate> getCandidateHaveInterviewSchedule() {
		// TODO Auto-generated method stub
		try {
			List<Candidate> listCandidate=jdbcTemplate.query(GET_ALL_CANDIDATE_INTERVIEW_SCHEDULE, (ResultSet rs, int rowNum)->{
				  Candidate candidate = new Candidate();
			        Employee employee = new Employee();
			        candidate.setCandidateId(rs.getString("CANDIDATE_ID"));
			        candidate.setName(rs.getString("NAME"));
			        candidate.setAge(rs.getInt("AGE"));
			        candidate.setExperience(rs.getInt("EXPERIENCE"));
			        candidate.setEmail(rs.getString("EMAIL"));
			        candidate.setContactNumber(rs.getInt("CONTACT_NO"));
			        candidate.setPrimarySkill(rs.getString("primary_skill"));
			        candidate.setSecondarySkill(rs.getString("secondary_skill"));
			        candidate.setTernarySkill(rs.getString("ternary_skill"));
			        JobDescription jobDescription=new JobDescription();
			        jobDescription.setJobDescriptionId(rs.getString("JOB_DESCRIPTION_ID"));
			        candidate.setJobDescription(jobDescription);
			        employee.setEmployeeId(rs.getString("INTERVIEWER_ID"));
			        candidate.setEmployee(employee);
			        candidate.setInterviewDate(rs.getDate("INTERVIEW_SCHEDULE"));
			       
			        return candidate;
			});
			if(listCandidate != null) {
				return listCandidate;
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean setCandidateJobdescriptionIdNull(String candidateId) {
		int result=jdbcTemplate.update(UPDATE_JOBDESCRIPTION_ID_BY_CANDIDATE_ID,candidateId);
		if(result > 0)
			return true;
		return false;
	}

	@Override
	public boolean updateCandidateSetSendMail(String candidateId) {
	 int result=jdbcTemplate.update(UPDATE_SEND_OFFERLETTER_TO_YES,candidateId);
	 if(result >0)
		 return true;
		return false;
	}

	@Override
	public boolean updateSendOfferLetter(String sendOfferLetter, String candidateId) {
		Object [] object = {sendOfferLetter,candidateId };
		int result = jdbcTemplate.update(UPDATE_SEND_OFFERLETTER, object);
		if(result>0) {
			return true;
		}
		return false;
	}
	
	

}
