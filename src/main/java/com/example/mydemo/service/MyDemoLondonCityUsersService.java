package com.example.mydemo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.mydemo.domains.model.Users;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Pradeepa
 * Service to return the users in London
 */
@Slf4j
@Service
public class MyDemoLondonCityUsersService {
	
	@Value("${HerokuApp.apiUri}")
	String herokuAppApiUri;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	ObjectMapper objectMapper;
	
	public List<Users> getUsersInLondon() throws JsonProcessingException {
		
		UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromUriString(herokuAppApiUri);
		String finalUrl = uriComponentsBuilder.buildAndExpand("London").toString();
		log.info("HerokuApp API URI OF USERS IN LONDON=====:{}", finalUrl);
		final ResponseEntity<Users[]> responseEntity = restTemplate.exchange(finalUrl, HttpMethod.GET, null, Users[].class);
		Users[] usersInLondon=responseEntity.getBody();
		log.info("List of people living in London:{}", objectMapper.writeValueAsString(responseEntity));
		return usersInLondon != null ? Arrays.asList(usersInLondon) : new ArrayList<>();
	  
	}
}
