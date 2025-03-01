package com.mindgate.main.restcontroller;

import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.function.EntityResponse;

import com.mindgate.main.controller.EmployeeDetailsController;
import com.mindgate.main.domain.Employee;
import com.mindgate.main.repository.EmployeeDetailsRepositoryInterface;
import com.mindgate.main.restdomain.LoginRequest;
import com.mindgate.main.service.CandidateServiceInterface;
import com.mindgate.main.service.EmployeeDetailsService;

@RestController
@RequestMapping("rest")
public class LoginRestController {
	@Autowired
	private EmployeeDetailsService EmployeeDetailsServiceInterface;

	Logger logger = LoggerFactory.getLogger(LoginRestController.class);

	@PostMapping("login")
	public ResponseEntity<Employee> getValid(@RequestBody LoginRequest loginRequest) {

		Logger logger = LoggerFactory.getLogger(LoginRestController.class);
		HttpHeaders header = new HttpHeaders();
		Employee employee = EmployeeDetailsServiceInterface.validateEmployee(loginRequest.getUsername(), loginRequest.getPassword());
		if (employee != null) {
			System.out.println(employee.getRole());
			if (employee.getRole().equals("employee")||employee.getRole().equals("Team Leader")||employee.getRole().equals("HR")||employee.getRole().equals("Project Manager")) {
				logger.info("Authentication "+ " successful");
				header.add("Authentication", "successful");
				return ResponseEntity.status(HttpStatusCode.valueOf(200)).headers(header).body(employee);		
				}

			logger.info("Login SUCCESFULL");
			header.add("Login", "SUCCESFULL");
			return new ResponseEntity<Employee>(employee, header, HttpStatus.OK);
		}
		logger.info("login failed");
		header.add("Login", "Failed");
		return new ResponseEntity<Employee>(null, header, HttpStatus.NOT_FOUND);

	}

}
