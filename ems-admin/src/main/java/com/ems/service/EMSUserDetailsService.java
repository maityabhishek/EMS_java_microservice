package com.ems.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ems.model.Employee;
import com.ems.repository.EmployeeService;

@Service
public class EMSUserDetailsService implements UserDetailsService {

	@Autowired
	private EmployeeService employeeservice;
	@Override
	public UserDetails loadUserByUsername(String empid) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Optional<Employee> e= employeeservice.findById(empid);
		
		Employee emp= e.orElse(null);
		
		return new User(emp.getEmp_id(), emp.getEmp_password(), new ArrayList<>());
		
	}

}
