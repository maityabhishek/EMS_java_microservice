package com.ems.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee {
	
	
	@Id
	@Column(name="emp_id")
	private String emp_id;
	@Column(name="emp_name")
	private String emp_name;
	@Column(name="age")
	private int age;
	@Column(name="salary")
	private int salary;
	@Column(name="dept_id")
	private int dept_id;
	@Column(name="manager")
	private String manager;
	@Column(name="emp_password")
	private String emp_password;
	@Column(name="status")
	private int status;
	@Column(name="roleid")
	private int roleid;
	public String getEmp_id() {
		return emp_id;
	}
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	public String getEmp_name() {
		return emp_name;
	}
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public int getDept_id() {
		return dept_id;
	}
	public void setDept_id(int dept_id) {
		this.dept_id = dept_id;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public String getEmp_password() {
		return emp_password;
	}
	public void setEmp_password(String emp_password) {
		this.emp_password = emp_password;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	public Employee(String emp_id, String emp_name, int age, int salary, int dept_id, String manager,
			String emp_password, int status, int roleid) {
		super();
		this.emp_id = emp_id;
		this.emp_name = emp_name;
		this.age = age;
		this.salary = salary;
		this.dept_id = dept_id;
		this.manager = manager;
		this.emp_password = emp_password;
		this.status = status;
		this.roleid = roleid;
	}
	public Employee() {
		super();
	}
	
	
	
}
