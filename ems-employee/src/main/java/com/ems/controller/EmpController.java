package com.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ems.model.Department;
import com.ems.service.DepartmentService;

@RestController
public class EmpController {
	
	@Autowired
	private DepartmentService departmentservice;
	
	@RequestMapping(value="/view/departments",method=RequestMethod.GET)
	public List<Department> getallinfo()
	{	
		return (List<Department>) departmentservice.findAll() ;	
	}
	
	
	
	

}
