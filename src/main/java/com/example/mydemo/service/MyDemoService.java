package com.example.mydemo.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.mydemo.domains.model.Users;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MyDemoService {

	
	@Autowired
	MyDemoLondonCityUsersService myDemoLondonCityUsersService;
	
	@Autowired
	MyDemoAroundLondonService myDemoAroundLondonService;
	
	@Autowired
	ObjectMapper objectMapper;
	
	

	public List<Users> getUsersInOrAroundLondonService() throws JsonProcessingException {

		List<Users> usersInLondon=null;
		List<Users> usersAroundLondon=null;
			
		//users in london
		usersInLondon = myDemoLondonCityUsersService.getUsersInLondon();
		
		//users around 50 miles of london
		usersAroundLondon = myDemoAroundLondonService.getUserAroundLondon();
		
		log.info("Combined results...");
		
		
		return Stream.of(usersInLondon, usersAroundLondon)
		        .flatMap(Collection::stream)
		        .distinct()
		        .collect(Collectors.toList());
		
			
	}

	

	
}