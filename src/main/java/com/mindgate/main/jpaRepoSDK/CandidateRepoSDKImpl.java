package com.mindgate.main.jpaRepoSDK;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindgate.main.domain.Candidate;
import com.mindgate.main.domain.Employee;
import com.mindgate.main.jpaRepo.CandidateRepo;
import com.mindgate.main.jpaRepo.EmployeeRepo;
import com.mindgate.main.repository.CandidateRepositoryInterface;

@Service
public class CandidateRepoSDKImpl implements CandidateRepositoryInterface {

	private static final Logger logger = LoggerFactory.getLogger(CandidateRepoSDKImpl.class);

	@Autowired
	private CandidateRepo candidateRepo;

	@Autowired
	private EmployeeRepo employeeRepo;

	@Override
	public boolean addCandidate(Candidate candidate) throws Exception {
		try {

			logger.info("CandidateRepoSDKImpl --> addCandidate --> insider");

			Candidate candidateData = candidateRepo.save(candidate);

			if (candidateData == null) {
				logger.error("candidate not able to insert in db");
				throw new Exception("addCandidate is null");
			}
			logger.info("insertion successful");
			return true;

		} catch (Exception ex) {
			logger.error(ex.getMessage());
			return false;
		}

	}

	@Override
	public boolean assignInterviewer(Long interviewerId, Long candidateId) throws Exception {
		logger.info("CandidateRepoSDKImpl --> assignInterviewer --> insider");
		try {
			Candidate candidate = candidateRepo.getReferenceById(candidateId);
			Employee employee = employeeRepo.getReferenceById(interviewerId);
			candidate.setEmployee(employee);
			Candidate candidateData = candidateRepo.save(candidate);
			if (candidateData == null) {
				logger.error("candidate not able to update in db");
				throw new Exception("candidate is null");
			}
			logger.info("interviewer is assigned");
			return true;
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			return false;
		}

	}

	@Override
	public boolean deleteCandidate(Long candidateId) throws Exception {
		logger.info("CandidateRepoSDKImpl --> deleteCandidate --> insider");
		try {
			Candidate candidate = candidateRepo.getReferenceById(candidateId);

			if (candidate != null) {
				candidateRepo.delete(candidate);
				logger.info("candidate is delete successfully");
				return true;
			}

			logger.error("candidate is not present");

		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return false;
	}

	@Override
	public List<Candidate> getAllCandidate() throws Exception {
		try {
			logger.info("CandidateRepoSDKImpl --> getAllCandidate --> insider");
			List<Candidate> listCandidate = candidateRepo.findAll();
			if (listCandidate.isEmpty())
				throw new Exception("list candidate is null");
			return listCandidate;
		} catch (Exception ex) {
			logger.error(ex.getMessage());

		}
		return null;
	}

	@Override
	public List<Candidate> getCandidateByInterviewerId(Long InterviewerId) throws Exception {
		logger.info("CandidateRepoSDKImpl --> getCandidateByInterviewerId --> insider");
		try {
			List<Candidate> candidate = candidateRepo.findAll().stream()
					.filter(n -> n.getEmployee().getEmployeeId() == InterviewerId).toList();
			if (candidate.isEmpty())
				throw new Exception("list candidate is null");
			logger.info(candidate.toString());
			return candidate;
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return null;
	}

	@Override
	public List<Candidate> getCandidateByStatus(String status) throws Exception {
		logger.info("CandidateRepoSDKImpl --> getCandidateByStatus --> insider");
		try {
			List<Candidate> candidate = candidateRepo.findAll().stream().filter(n -> n.getStatus() == status).toList();
			if (candidate.isEmpty())
				throw new Exception("list candidate is null");
			logger.info(candidate.toString());
			return candidate;
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return null;
	}

	@Override
	public Candidate getCandidateByCandidateId(Long candidateId) {
		logger.info("CandidateRepoSDKImpl --> getCandidateByCandidateId --> insider");
		try {
			Optional<Candidate> candidate = candidateRepo.findById(candidateId);
			if (candidate.isEmpty())
				throw new Exception("list candidate is null");
			logger.info(candidate.get().toString());
			return candidate.get();
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return null;
	}

	@Override
	public boolean updateInterviewerId(Long interviewerId, Long candidateId, Date interviewDate) throws Exception {
		logger.info("CandidateRepoSDKImpl --> updateInterviewerId --> insider");
		try {
			Candidate candidate = candidateRepo.getReferenceById(candidateId);
			if (candidate == null)
				throw new Exception("candidate is  null");
			Employee employee = employeeRepo.getReferenceById(interviewerId);
			if (employee == null)
				throw new Exception("employee is null");
			candidate.setEmployee(employee);
			candidate.setInterviewDate(interviewDate);
			Candidate candidateData = candidateRepo.save(candidate);
			if (candidateData == null)
				throw new Exception("candidate is not able to update");
			logger.info("candidate is updated successfully");
			return true;
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return false;
	}

	@Override
	public List<Candidate> getCandidateHaveInterviewSchedule() throws Exception {

		logger.info("CandidateRepoSDKImpl --> getCandidateHaveInterviewSchedule --> insider");
		try {
			List<Candidate> candidate = candidateRepo.findAll().stream().filter(n -> n.getInterviewDate() != null)
					.toList();
			if (candidate.isEmpty())
				throw new Exception("list candidate is null");
			logger.info(candidate.toString());
			return candidate;
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return null;
	}

	@Override
	public boolean setCandidateJobdescriptionIdNull(Long candidateId) throws Exception {
		logger.info("CandidateRepoSDKImpl --> getCandidateHaveInterviewSchedule --> insider");
		try {
			Optional<Candidate> candidate = candidateRepo.findById(candidateId);
			if (candidate.isEmpty())
				throw new Exception("list candidate is null");
			Candidate candidateData = candidate.get();
			candidateData.setJobDescription(null);
			logger.info(candidate.toString());
			candidateData = candidateRepo.save(candidateData);
			if (candidateData != null)
				return true;
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return false;
	}

	@Override
	public boolean updateCandidateSetSendMail(Long candidateId) throws Exception {
		logger.info("CandidateRepoSDKImpl --> updateCandidateSetSendMail --> insider");
		try {
			Optional<Candidate> candidate = candidateRepo.findById(candidateId);
			if (candidate.isEmpty())
				throw new Exception("candidate is null");
			Candidate candidateData = candidate.get();
			candidateData.setSendOfferLetter("yes");
			candidateData = candidateRepo.save(candidateData);
			if (candidateData == null)
				throw new Exception("insertion failed");
			logger.info("Insertion successfull");
			return true;
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return false;
	}

	@Override
	public boolean updateSendOfferLetter(String sendOfferLetter, Long candidateId) throws Exception {
		logger.info("CandidateRepoSDKImpl --> updateSendOfferLetter --> insider");
		try {
			Optional<Candidate> candidate = candidateRepo.findById(candidateId);
			if (candidate.isEmpty())
				throw new Exception("candidate is null");
			Candidate candidateData = candidate.get();
			candidateData.setSendOfferLetter(sendOfferLetter);
			candidateData.setJobDescription(null);
			candidateData = candidateRepo.save(candidateData);
			if (candidateData == null)
				throw new Exception("insertion failed");
			logger.info("Insertion successfull");
			return true;
		}
		catch(Exception ex) {
			logger.error(ex.getMessage());
		}
		return false;
	}

}
