package com.mindgate.main.restSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mindgate.main.domain.Employee;
import com.mindgate.main.restdomain.LoginRequest;
import com.mindgate.main.service.EmployeeDetailsService;
import com.mindgate.main.service.EmployeeDetailsServiceInterface;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("restsession")
public class RestloginSession {

	@Autowired
	private EmployeeDetailsServiceInterface employeeDetailsServiceInterface;

	@PostMapping("/login")
	public ResponseEntity<String> validLogin(@RequestBody LoginRequest loginRequest, HttpSession session) {
		Employee employee = employeeDetailsServiceInterface.validateEmployee(loginRequest.getUsername(), loginRequest.getPassword());
		if (employee != null) {
			session.setAttribute("userData", employee);
			return ResponseEntity.status(HttpStatusCode.valueOf(201)).body("true");
		}
		return ResponseEntity.status(HttpStatusCode.valueOf(403)).body("false");
	}
	
	@GetMapping("/checkSession")
	public ResponseEntity<Employee> getCheck(HttpSession session){
		Employee employee=(Employee) session.getAttribute("userData");
		if(employee != null) {
			return ResponseEntity.status(HttpStatusCode.valueOf(201)).body(employee);
		}
		return ResponseEntity.status(HttpStatusCode.valueOf(403)).body(null);
	}
	

}
