package com.mindgate.main.restservicetokenencryption.controller;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mindgate.main.domain.Employee;
import com.mindgate.main.restdomain.LoginRequest;
import com.mindgate.main.restservicetokenencryption.EncryptionDecryptionUtility;
import com.mindgate.main.restservicetokenencryption.TokenWrapper;
import com.mindgate.main.service.EmployeeDetailsServiceInterface;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
@RequestMapping("rest/token")
public class LoginController {

	@Autowired
	private EmployeeDetailsServiceInterface employeeDetailsService;

	@Autowired
	private EncryptionDecryptionUtility encryptionDecryptionUtility;

	@PostMapping("login")
	public ResponseEntity<String> getValidateLoginToken(@RequestBody LoginRequest loginRequest) {

		HttpHeaders header = new HttpHeaders();

		Employee employee = employeeDetailsService.validateEmployee(loginRequest.getUsername(),loginRequest.getPassword());
		if (employee != null) {
			TokenWrapper tokenWrapper = new TokenWrapper(employee,Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()),"Welcome@123");
			String data = encryptionDecryptionUtility.encryptData(tokenWrapper);
			header.add("Authentication", "successful");
			return ResponseEntity.status(HttpStatusCode.valueOf(200)).headers(header).body(data);
		}

		header.add("Authentication", "Failed");
		return ResponseEntity.status(HttpStatusCode.valueOf(401)).headers(header)
				.body("you are not authorized to login");

	}


}
