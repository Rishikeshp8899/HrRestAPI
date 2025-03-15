package com.mindgate.main.jpaRepoSDK;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.mindgate.main.domain.Assesment;
import com.mindgate.main.jpaRepo.AssesmentRepo;
import com.mindgate.main.repository.AssesmentRepositoryInterface;

@Service
public class AssesmentRepoSDKImpl implements AssesmentRepositoryInterface {

	private static final Logger logger = LoggerFactory.getLogger(AssesmentRepoSDKImpl.class);

	@Autowired
	private AssesmentRepo assesmentRepo;

	@Override
	public List<Assesment> getAllNotSelectedCandidate() throws Exception {
		logger.info("AssesmentRepoSDKImpl --> getAllNotSelectedCandidate --> insider");
		try {
			List<Assesment> listAssesment = assesmentRepo.findAll().stream().filter(n -> n.getStatus() == "notSelected")
					.toList();
			if (listAssesment.isEmpty()) {
				logger.info("getAllNotSelectedCandidate is empty");
				throw new Exception("getAllNotSelectedCandidate is empty");
			}
			return listAssesment;
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new Exception(ex.getMessage());
		}
	}

	@Override
	public Assesment getSelectedAssesment(Long candidateId) throws Exception {
		logger.info("AssesmentRepoSDKImpl --> getSelectedAssesment --> insider");
		try {
			Optional<Assesment> assesment = assesmentRepo.findAll().stream()
					.filter(n -> n.getStatus() == "Selected" & n.getAssesmentId() == candidateId).findFirst();
			if (assesment.isEmpty()) {
				logger.error("getSelectedAssesment is empty");
				throw new Exception("getAllNotSelectedCandidate is empty");
			}
			return assesment.get();
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new Exception(ex.getMessage());
		}
	}

	@Override
	public void insertAssesmentDetails(Assesment assesment) throws Exception {
		logger.info("AssesmentRepoSDKImpl --> insertAssesmentDetails --> insider");
		if (assesmentRepo.save(assesment) == null) {
			logger.error("assesment not able to insert in db");
			throw new Exception("insertAssesmentDetails is null");
		}
	}

	@Override
	public List<Assesment> getAllAssesmentDetails() throws Exception {

		logger.info("AssesmentRepoSDKImpl --> getAllAssesmentDetails --> insider");
		try {
			List<Assesment> listAssesment = assesmentRepo.findAll();
			if (listAssesment.isEmpty()) {
				logger.info("getAllAssesmentDetails is empty");
				throw new Exception("getAllAssesmentDetails is empty");
			}
			return listAssesment;
		} catch (Exception ex) {
			logger.error(ex.getMessage());
			throw new Exception(ex.getMessage());
		}
	}

	@Override
	public Assesment getAssesmentByCandidateId(Long candidateId) throws Exception {

		logger.info("AssesmentRepoSDKImpl --> getAssesmentByCandidateId --> insider");
		try {
			Optional<Assesment> assesment = assesmentRepo.findAll().stream()
					.filter(n -> n.getCandidate().getCandidateId() == candidateId).findFirst();
			if (assesment.isEmpty()) {
				logger.info("getAssesmentByCandidateId is empty");
				throw new Exception("getAssesmentByCandidateId is empty");
			}
			return assesment.get();
		} catch (Exception ex) {
			logger.error(ex.getMessage());

			throw new Exception(ex.getMessage());
		}
	}

	@Override
	public boolean updateAssesmentStatus(Long assesmentId) throws Exception {
		Optional<Assesment> assesment = assesmentRepo.findAll().stream().filter(n -> n.getAssesmentId() == assesmentId)
				.findFirst();

		if (assesment.isEmpty()) {
			logger.info("getAssesmentByCandidateId is empty");
			throw new Exception("getAssesmentByCandidateId is empty");
		} else {

		}

		return false;
	}

}
