package com.mindgate.main.jpaRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindgate.main.domain.Project;

@Repository
public interface ProjectRepo extends JpaRepository<Project, Long>{

}
