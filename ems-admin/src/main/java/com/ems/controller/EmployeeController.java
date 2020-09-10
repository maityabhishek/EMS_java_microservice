package com.ems.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ems.model.Department;
import com.ems.model.Employee;
import com.ems.model.TaskSummary;
import com.ems.model.UserLoginCredential;
import com.ems.model.UserToken;
import com.ems.service.CommunicationService;
import com.ems.service.EMSUserDetailsService;
import com.ems.service.EncryptToHash;
import com.ems.repository.DepartmentService;
import com.ems.repository.EmployeeService;
import com.ems.service.JwtUtil;
import com.ems.service.RoleVerify;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;

@RestController
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeservice;
	
	@Autowired
	private AuthenticationManager authmanager;
	
	@Autowired 
	private EMSUserDetailsService userdetailservice;
	
	@Autowired
	private JwtUtil jwtutil;
	
	@Autowired
	private DepartmentService deptservice;
	
	@Autowired
	private EncryptToHash encoder;
	
	@Autowired
	private RoleVerify roleverify;
	
	@Autowired
	private CommunicationService communicationservice;
	
	@Autowired
	private EurekaClient discoveryClient;
	
	@RequestMapping(value="employee/add",method=RequestMethod.POST)
	public ResponseEntity<?>  addEmployee(@RequestBody Employee employee ,@RequestHeader(name="Authorization") String token  )
	{
		token= token.substring(7);
		if(roleverify.canEditAll(jwtutil.extractUsername(token)))
		{
			employee.setEmp_id(deptservice.findById(employee.getDept_id()).getDept_Name()+""+(employeeservice.count()+1));
		
			employee.setStatus(1);
			try {
				employee.setEmp_password(encoder.getHash(employee.getEmp_password()));
				employeeservice.save(employee);
				return new ResponseEntity<>("Employee Added ID:"+employee.getEmp_id(), HttpStatus.OK);
				}
			catch (Exception e)
			{
				return new ResponseEntity<>("Try again with different password", HttpStatus.OK);
			}
		}
		else
		{
			return new ResponseEntity<>("You Dont have the Permission", HttpStatus.FORBIDDEN);
		}
			
		
	}
	@RequestMapping(value="employee/view",method=RequestMethod.GET)
	public ResponseEntity<?> viewEmployee(@RequestHeader(name="Authorization")String token)
	{
		//check for admin
	
		token= token.substring(7);
		if(roleverify.canViewAll(jwtutil.extractUsername(token)))
		{
			return new ResponseEntity<>(employeeservice.findAllByStatus(1), HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>(employeeservice.findById(jwtutil.extractUsername(token)),HttpStatus.OK);
		}
		
	}
	
	@RequestMapping(value="employee/archive",method=RequestMethod.GET)
	public ResponseEntity<?> viewOldEmployee(@RequestHeader(name = "Authorization") String token)
	{
		token= token.substring(7);
		if(roleverify.canViewAll(jwtutil.extractUsername(token)))
		{
			return new ResponseEntity<>(employeeservice.findAllByStatus(0), HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>("You Dont have the Permission", HttpStatus.FORBIDDEN);
		}
	}
	
	
	@RequestMapping(value="employee/edit",method=RequestMethod.PUT)
	public ResponseEntity<?> editEmployee(@RequestBody Employee employee,@RequestHeader(name = "Authorization") String token)
	{
		token= token.substring(7);
		if(roleverify.canEditAll(jwtutil.extractUsername(token)))
		{
			employeeservice.save(employee);
			return new ResponseEntity<>("Employee Updated", HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>("You Dont have the Permission", HttpStatus.FORBIDDEN);
		}
		
	}
	@RequestMapping(value="employee/{empid}/delete",method=RequestMethod.DELETE)
	public ResponseEntity<?> editEmployee(@PathVariable String empid,@RequestHeader(name = "Authorization") String token)
	{
		token= token.substring(7);
		if(roleverify.canEditAll(jwtutil.extractUsername(token)))
		{
			Optional<Employee> emp= employeeservice.findById(empid);
			emp.ifPresent(employee -> {
			
				employee.setStatus(0);
				employeeservice.save(employee);
			});
		
			return new ResponseEntity<>("Employee Deleted", HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>("You Dont have the Permission", HttpStatus.FORBIDDEN);
		}
	}
	
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody UserLoginCredential userlogincredentials)
	{
		
		try
		{
		authmanager.authenticate(new UsernamePasswordAuthenticationToken(userlogincredentials.getEmpid(), userlogincredentials.getPassword()));
		
		}
		catch(Exception e)
		{
			System.out.println(e);
			return new ResponseEntity<>("Invalid UserId/Password",HttpStatus.FORBIDDEN);
		}
		final UserDetails userdetails = userdetailservice.loadUserByUsername(userlogincredentials.getEmpid()); 
		
		return new ResponseEntity<>(new UserToken(userlogincredentials.getEmpid(),jwtutil.generateToken(userdetails)),HttpStatus.OK);
	
		
	}
	
	@RequestMapping(value="/view/team",method=RequestMethod.GET)
	public ResponseEntity<?> viewTeam(@RequestHeader(name = "Authorization") String token){
		token= token.substring(7);
		if(roleverify.canViewTeam(jwtutil.extractUsername(token)))
		{
			return new ResponseEntity<>(employeeservice.findAllByManagerAndStatus(jwtutil.extractUsername(token),1),HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<>("You Dont have the Permission", HttpStatus.FORBIDDEN);
		}
		
	}
	
	@RequestMapping(value="/Taskinfo",method=RequestMethod.GET)
	public ResponseEntity<?> testhello(@RequestHeader(name = "Authorization") String token)
	{
		HttpHeaders header =new HttpHeaders();
		header.add("Authorization", token);
		HttpEntity<String> entity =new HttpEntity<String>(header);
		InstanceInfo instance = discoveryClient.getNextServerFromEureka("Employee-Task", false);
		System.out.println("\n\n\n\n\n\n\t\t\t\tHere\n\n\n\n\n\n");
		return communicationservice.getRestTemplate().exchange(instance.getHomePageUrl()+"Task/Dashboard",HttpMethod.GET, entity, TaskSummary.class);
	}
	
	
	
	
	
	
	
	

}
