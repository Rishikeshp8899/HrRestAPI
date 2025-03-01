package com.mindgate.main.restservicetokenencryption;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.mindgate.main.domain.Employee;
import com.mindgate.main.service.EmployeeDetailsService;
import com.mindgate.main.service.EmployeeDetailsServiceInterface;

@Component
public class HelperClass {
	@Autowired
	private EncryptionDecryptionUtility encryptionDecryptionUtility;

	@Autowired
	private EmployeeDetailsServiceInterface employeeDetailsService;

	public boolean checkDateValidation(Date date) {
		LocalDate toDate = LocalDate.now();
		LocalDate checkDate = LocalDate.of(date.getYear(), date.getMonth(), date.getDate());
		if (toDate.isAfter(checkDate.plusDays(1)))
			return true;
		return false;

	}

	public String encryptData(TokenWrapper tokenWrapper) {
		return encryptionDecryptionUtility.encryptData(tokenWrapper);
	}

	public TokenWrapper decryptData(String token) {
		return encryptionDecryptionUtility.decryptData(token);
	}

	public TokenWrapper WrapperClass(Employee employee) {
		return new TokenWrapper(employee, Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()),
				"Welcome@123");
	}

	public boolean checkValidationOfToken(String token) {

		TokenWrapper tokenWrapper = encryptionDecryptionUtility.decryptData(token);
		if (tokenWrapper != null) {
			if (tokenWrapper.getKey().equals("Welcome@123")) {
				Employee employee = employeeDetailsService
						.getEmployee(tokenWrapper.getOriginalObject().getEmployeeId());
				if (employee != null) {
					System.out.println(employee.toString());
					if (employee.getPassword().equals(tokenWrapper.getOriginalObject().getPassword())) {
						if (employee.getFirstname().equals(tokenWrapper.getOriginalObject().getFirstname()))
							return true;
					}
					return false;
				}
				return false;
			}
			return false;
		}

		System.out.println("in check");
		return false;

	}

	public boolean checkRoleValidation(Employee employee, String role) {
		return employee.getRole().equals(role);
	}
}
