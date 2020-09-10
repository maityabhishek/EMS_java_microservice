package com.ems.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Task {
	
	@Id
	@Column(name="Task_Id")
	private int Task_Id;
	@Column(name="Task_Name")
	private String Task_Name;
	@Column(name="Task_Description")
	private String Task_Description;
	@Column(name="Manager")
	private String manager;
	@Column(name="Employee")
	private String employee;
	@Column(name="Task_Status")
	private String task_Status;
	public int getTask_Id() {
		return Task_Id;
	}
	public void setTask_Id(int task_Id) {
		Task_Id = task_Id;
	}
	public String getTask_Name() {
		return Task_Name;
	}
	public void setTask_Name(String task_Name) {
		Task_Name = task_Name;
	}
	public String getTask_Description() {
		return Task_Description;
	}
	public void setTask_Description(String task_Description) {
		Task_Description = task_Description;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public String getEmployee() {
		return employee;
	}
	public void setEmployee(String employee) {
		this.employee = employee;
	}
	public String getTask_Status() {
		return task_Status;
	}
	public void setTask_Status(String task_Status) {
		this.task_Status = task_Status;
	}
	public Task(int task_Id, String task_Name, String task_Description, String manager, String employee,
			String task_Status) {
		super();
		Task_Id = task_Id;
		Task_Name = task_Name;
		Task_Description = task_Description;
		this.manager = manager;
		this.employee = employee;
		this.task_Status = task_Status;
	}
	public Task() {
		super();
	}
	
	

}
