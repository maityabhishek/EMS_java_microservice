package com.ems.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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


import com.ems.model.Task;
import com.ems.model.TaskSummary;
import com.ems.model.UserLoginCredential;
import com.ems.model.UserToken;
import com.ems.repository.EmployeeService;
import com.ems.repository.TaskService;
import com.ems.service.EMSUserDetailsService;
import com.ems.service.JwtUtil;
import com.ems.service.RoleVerify;

@RestController
public class TaskController {

	@Autowired
	private TaskService taskservice;
	
	@Autowired
	private JwtUtil jwtutil;
	
	@Autowired
	private EmployeeService employeeservice;
	
	@Autowired
	private RoleVerify roleverify;
	
	@Autowired
	private AuthenticationManager authmanager;
	
	@Autowired 
	private EMSUserDetailsService userdetailservice;
	
	@RequestMapping(value="Task/view",method=RequestMethod.GET)
	public  ResponseEntity<?>  getallinfo(@RequestHeader(name = "Authorization") String token) 
	{
		token= token.substring(7);
		if(roleverify.canTaskManager(jwtutil.extractUsername(token)))
		{
			return new ResponseEntity<>( (List<Task>) taskservice.findAllByManager(jwtutil.extractUsername(token)), HttpStatus.OK) ;
			
		}
		else if(roleverify.canTaskEmp(jwtutil.extractUsername(token)))
		{
			return new ResponseEntity<>( (List<Task>) taskservice.findAllByEmployee(jwtutil.extractUsername(token)), HttpStatus.OK) ;
		}
		else
		{
			return new ResponseEntity<>( "You are not authorized", HttpStatus.OK) ;
		}
		
		
	}
	
	@RequestMapping(value="/Task/Assign",method=RequestMethod.POST)
	public ResponseEntity<?> AddTask(@RequestBody Task task,@RequestHeader(name = "Authorization") String token)
	{
		token= token.substring(7);
		if(roleverify.canTaskManager(jwtutil.extractUsername(token)))
		{
			//if(task==null || task.getTask_Name()==null || task.getEmployee() ==null || task.getTask_Description()==null )
		//	{
		//		return new ResponseEntity<>("The Body is missing or Some of the Parameters are missing ", HttpStatus.BAD_REQUEST) ;
		//	}
		//	else 
		//	{
			task.setTask_Id((int)taskservice.count()+1);
			task.setTask_Status("ASSIGNED");
			task.setManager(jwtutil.extractUsername(token));
			taskservice.save(task);
			return new ResponseEntity<>("Your Task Has been Assigned TO "+task.getEmployee(), HttpStatus.OK) ;		
		//	}
		}
		else
		{
			return new ResponseEntity<>("You are not Authorised  to perform this action ", HttpStatus.BAD_REQUEST) ;	
		}
		
		
		
	}
	@RequestMapping(value="/Task/{Taskid}/{Status}",method=RequestMethod.POST)
	public ResponseEntity<?> Update(@PathVariable int task_id,@PathVariable String status,@RequestHeader(name = "Authorization") String token )
	{
		
		//should be employee only
		
		token= token.substring(7);
		boolean ismanager,isemployee;
		ismanager= roleverify.canTaskManager(jwtutil.extractUsername(token));
		isemployee= roleverify.canTaskEmp(jwtutil.extractUsername(token));
		
		if(task_id==0 || status== null )
		{
		
		return new ResponseEntity<>("Missing parrameter values ", HttpStatus.BAD_REQUEST) ;		
		}
		else
		{
			
			if(status.equalsIgnoreCase("APPROVED"))
			{
				if(ismanager)
				{
					status="APPROVED";
				}
				else
				{
					return new ResponseEntity<>("You are Not Authorized for this action ", HttpStatus.BAD_REQUEST) ;	
				}
				
			}
			else if(status.equalsIgnoreCase("SUBMITTED"))
			{
				if(isemployee)
				{
					status="SUBMITTED";
				}
				else
				{
					return new ResponseEntity<>("You are Not Authorized for this action ", HttpStatus.BAD_REQUEST) ;	
				}
				
			}
			else if(status.equalsIgnoreCase("REJECTED"))
			{
				if(ismanager)
				{
					status="REJECTED";
				}
				else
				{
					return new ResponseEntity<>("You are Not Authorized for this action ", HttpStatus.BAD_REQUEST) ;	
				}
				
			}
			else
			{
				return new ResponseEntity<>("Invalid Status value ", HttpStatus.BAD_REQUEST) ;	
			}
			 Task task= taskservice.findById(task_id);
			 task.setTask_Status(status);
			 taskservice.save(task);
			 return new ResponseEntity<>("Task Id"+task.getTask_Id()+" Updated to "+task.getTask_Status(), HttpStatus.BAD_REQUEST) ;	
			
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
	@RequestMapping(value="/Task/Dashboard",method=RequestMethod.GET)
	public ResponseEntity<?> test(@RequestHeader(name = "Authorization") String token)
	{
		token= token.substring(7);
		if(roleverify.canTaskEmp(jwtutil.extractUsername(token)))
		{
			List<Task> tasks=taskservice.findAllByEmployee(jwtutil.extractUsername(token)) ;
			TaskSummary taskSummary=new TaskSummary();
			for(Task t: tasks)
			{
				if(t.getTask_Status().equals("ASSIGNED"))
				{
					taskSummary.setAssigned(taskSummary.getAssigned()+1);
				}
				else if(t.getTask_Status().equals("APPROVED"))
				{
					taskSummary.setApproved(taskSummary.getApproved()+1);
				}
				else if(t.getTask_Status().equals("REJECTED"))
				{
					taskSummary.setRejected(taskSummary.getRejected()+1);
				}
				else if(t.getTask_Status().equals("SUBMITTED"))
				{
					taskSummary.setSubmitted(taskSummary.getSubmitted()+1);
				}
						
			}
			return new ResponseEntity<>(taskSummary,HttpStatus.OK);
			
					
			
		}
		else if(roleverify.canTaskManager(jwtutil.extractUsername(token)))
		{
			List<Task> tasks=taskservice.findAllByManager(jwtutil.extractUsername(token)) ;
			TaskSummary taskSummary=new TaskSummary();
			for(Task t: tasks)
			{
				if(t.getTask_Status().equals("ASSIGNED"))
				{
					taskSummary.setAssigned(taskSummary.getAssigned()+1);
				}
				else if(t.getTask_Status().equals("APPROVED"))
				{
					taskSummary.setApproved(taskSummary.getApproved()+1);
				}
				else if(t.getTask_Status().equals("REJECTED"))
				{
					taskSummary.setRejected(taskSummary.getRejected()+1);
				}
				else if(t.getTask_Status().equals("SUBMITTED"))
				{
					taskSummary.setSubmitted(taskSummary.getSubmitted()+1);
				}
						
			}
			return new ResponseEntity<>(taskSummary,HttpStatus.OK);
		}
		else
			return new ResponseEntity<>("You are Not Authorized",HttpStatus.OK);
	}
	

}
