package com.example.mydemo.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.lucene.util.SloppyMath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.mydemo.domains.model.Users;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MyDemoAroundLondonService {
	
	@Value("${HerokuApp.apiUri1}")
	String herokuAppApiUri1;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired	
	RestTemplate restTemplate;
	
	final private double RADIUS = 80467.2;
	
	final private double LONDON_LATITUDE = 51.50853;
	
	final private double LONDON_LONGITUDE = -0.12574;
	
	public List<Users> getUserAroundLondon() throws JsonProcessingException {
		Users[] usersAroundLondon;
		log.info("HerokuApp API URI FOR USERS AROUND LONDON=====:{}", herokuAppApiUri1);
		ResponseEntity<Users[]> users1 = restTemplate.exchange(herokuAppApiUri1, HttpMethod.GET, null, Users[].class);
		usersAroundLondon=users1.getBody();
		List<Users> usersList = usersAroundLondon != null ? Arrays.asList(usersAroundLondon) : new ArrayList<>();
		log.info("List of people living around 50 miles of London:{}", objectMapper.writeValueAsString(usersList));
		return usersList.stream()
        .filter(user -> SloppyMath.haversinMeters(LONDON_LATITUDE, LONDON_LONGITUDE, user.getLatitude(), user.getLongitude())
            <= RADIUS)
        .collect(Collectors.toList());
				
	}

}
