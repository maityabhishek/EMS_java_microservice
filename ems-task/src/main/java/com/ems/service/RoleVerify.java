package com.ems.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.model.Employee;
import com.ems.model.UserRole;
import com.ems.repository.EmployeeService;
import com.ems.repository.UserRoleAccess;

@Service
public class RoleVerify {
	
	@Autowired
	private EmployeeService employeeservice;
	
	@Autowired
	private UserRoleAccess userroles;
	
	public UserRole getRoles(String employeeid)
	{
		Employee e = (employeeservice.findById(employeeid)).orElse(null);
		if(e==null)
		{
			return null;
		}
		else
		{
			return (userroles.findById(e.getRoleid())).orElse(null);
		}
	}
	
	public boolean canViewAll(String employeeid)
	{
		UserRole userrole=getRoles(employeeid);
		if(userrole!=null)
		{
			if(userrole.getViewall()==1)
				return true;
			else
				return false;
		}
		else
			return false;
	}
	public boolean canEditAll(String employeeid)
	{
		UserRole userrole=getRoles(employeeid);
		if(userrole!=null)
		{
			if(userrole.getEditall()==1)
				return true;
			else
				return false;
		}
		else
			return false;
	}
	public boolean canViewArchive(String employeeid)
	{
		UserRole userrole=getRoles(employeeid);
		if(userrole!=null)
		{
			if(userrole.getViewarchive()==1)
				return true;
			else
				return false;
		}
		else
			return false;
	}
	public boolean canDeleteAll(String employeeid)
	{
		UserRole userrole=getRoles(employeeid);
		if(userrole!=null)
		{
			if(userrole.getDeleteall()==1)
				return true;
			else
				return false;
		}
		else
			return false;
	}
	public boolean canTaskEmp(String employeeid)
	{
		UserRole userrole=getRoles(employeeid);
		if(userrole!=null)
		{
			if(userrole.getTaskmordifyemp()==1)
				return true;
			else
				return false;
		}
		else
			return false;
	}
	public boolean canTaskManager(String employeeid)
	{
		UserRole userrole=getRoles(employeeid);
		if(userrole!=null)
		{
			if(userrole.getTaskmordifymanager()==1)
				return true;
			else
				return false;
		}
		else
			return false;
	}
	public boolean canViewTask(String employeeid)
	{
		UserRole userrole=getRoles(employeeid);
		if(userrole!=null)
		{
			if(userrole.getViewtask()==1)
				return true;
			else
				return false;
		}
		else
			return false;
	}
	public boolean canViewTeam(String employeeid)
	{
		UserRole userrole=getRoles(employeeid);
		if(userrole!=null)
		{
			if(userrole.getViewteam()==1)
				return true;
			else
				return false;
		}
		else
			return false;
	}
	
	

}
