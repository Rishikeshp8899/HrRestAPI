package com.mindgate.main.jpaRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindgate.main.domain.JobDescription;

@Repository
public interface JobDescriptionRepo extends JpaRepository<JobDescription, Long> {

}
