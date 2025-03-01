package com.mindgate.main.restservicetokenencryption;

import java.time.LocalDate;
import java.util.Date;

import com.mindgate.main.domain.Employee;


public class TokenWrapper{

	private Employee originalObject;
	private Date date;
	private String key;


	public Employee getOriginalObject() {
		return originalObject;
	}
	public void setOriginalObject(Employee originalObject) {
		this.originalObject = originalObject;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}

public TokenWrapper() {}
public TokenWrapper( Employee originalObject, Date date, String key) {
	super();

	this.originalObject = originalObject;
	this.date = date;
	this.key = key;
}

}
