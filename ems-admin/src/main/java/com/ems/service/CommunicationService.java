package com.ems.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CommunicationService {
	
	private RestTemplate restTemplate;
	
	CommunicationService()
	{
		restTemplate=new RestTemplate();
	}
	
	public RestTemplate getRestTemplate()
	{
		return restTemplate;
	}
	

}
