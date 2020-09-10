package com.ems.model;

public class UserToken {
	private String employee_id;
	private String authToken;
	public String getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	public UserToken(String employee_id, String authToken) {
		super();
		this.employee_id = employee_id;
		this.authToken = authToken;
	}
	public UserToken() {
		super();
	}
	
	
	
	

}
