package com.ems.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Department {
	@Id
	@Column(name="Dept_Id")
	private int Dept_Id;
	@Column(name="Dept_Name")
	private String Dept_Name;
	
	public Department() {
		super();
	}
	public Department(int dept_Id, String dept_Name) {
		super();
		Dept_Id = dept_Id;
		Dept_Name = dept_Name;
	}
	public int getDept_Id() {
		return Dept_Id;
	}
	public void setDept_Id(int dept_Id) {
		Dept_Id = dept_Id;
	}
	public String getDept_Name() {
		return Dept_Name;
	}
	public void setDept_Name(String dept_Name) {
		Dept_Name = dept_Name;
	}
	

}
