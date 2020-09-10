package com.ems.model;

public class UserLoginCredential {
	private String empid;
	private String password;
	public String getEmpid() {
		return empid;
	}
	public void setEmpid(String empid) {
		this.empid = empid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UserLoginCredential(String empid, String password) {
		super();
		this.empid = empid;
		this.password = password;
	}
	public UserLoginCredential() {
		super();
	}
	

}
