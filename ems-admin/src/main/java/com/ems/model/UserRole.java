package com.ems.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="userrole")
public class UserRole {
	@Id
	private int roleid;
	private int viewall;
	private int editall ;
	private int deleteall ;
	private int viewarchive ;
	private int taskmordifyemp ;
	private int taskmordifymanager ;
	private int viewtask ; 
	private int viewteam;
	public int getRoleid() {
		return roleid;
	}
	public void setRoleid(int roleid) {
		this.roleid = roleid;
	}
	public int getViewall() {
		return viewall;
	}
	public void setViewall(int viewall) {
		this.viewall = viewall;
	}
	public int getEditall() {
		return editall;
	}
	public void setEditall(int editall) {
		this.editall = editall;
	}
	public int getDeleteall() {
		return deleteall;
	}
	public void setDeleteall(int deleteall) {
		this.deleteall = deleteall;
	}
	public int getViewarchive() {
		return viewarchive;
	}
	public void setViewarchive(int viewarchive) {
		this.viewarchive = viewarchive;
	}
	public int getTaskmordifyemp() {
		return taskmordifyemp;
	}
	public void setTaskmordifyemp(int taskmordifyemp) {
		this.taskmordifyemp = taskmordifyemp;
	}
	public int getTaskmordifymanager() {
		return taskmordifymanager;
	}
	public void setTaskmordifymanager(int taskmordifymanager) {
		this.taskmordifymanager = taskmordifymanager;
	}
	public int getViewtask() {
		return viewtask;
	}
	public void setViewtask(int viewtask) {
		this.viewtask = viewtask;
	}
	public int getViewteam() {
		return viewteam;
	}
	public void setViewteam(int viewteam) {
		this.viewteam = viewteam;
	}
	public UserRole(int roleid, int viewall, int editall, int deleteall, int viewarchive, int taskmordifyemp,
			int taskmordifymanager, int viewtask, int viewteam) {
		super();
		this.roleid = roleid;
		this.viewall = viewall;
		this.editall = editall;
		this.deleteall = deleteall;
		this.viewarchive = viewarchive;
		this.taskmordifyemp = taskmordifyemp;
		this.taskmordifymanager = taskmordifymanager;
		this.viewtask = viewtask;
		this.viewteam = viewteam;
	}
	public UserRole() {
		super();
	} 
	

}
