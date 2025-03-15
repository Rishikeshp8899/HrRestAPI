
package com.mindgate.main.jpaRepo;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mindgate.main.domain.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, Long> {

	@Query("SELECT emp FROM Employee emp WHERE emp.username = :username AND emp.password = :password")
	public Employee getEmployeeByPasswordandUsername(String username,String password);
	
	public Employee getByUsername(String username);
	
}
