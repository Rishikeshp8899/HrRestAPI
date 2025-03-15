package com.mindgate.main.jpaRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindgate.main.domain.Candidate;

@Repository
public interface CandidateRepo extends JpaRepository<Candidate, Long> {

}
