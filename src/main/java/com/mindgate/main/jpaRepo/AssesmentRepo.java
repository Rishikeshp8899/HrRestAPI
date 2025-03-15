package com.mindgate.main.jpaRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindgate.main.domain.Assesment;

@Repository
public interface AssesmentRepo extends JpaRepository<Assesment, Long> {

}
