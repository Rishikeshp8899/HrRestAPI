
package com.mindgate.main.jpaRepo;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mindgate.main.domain.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {


	@Query("SELECT e FROM Employee e WHERE e.password = :password AND e.username = :username")
	Employee findByPasswordAndUsername(@Param("password") String password, @Param("username") String username);


	
	public Employee getByUsername(String username);
	
}
