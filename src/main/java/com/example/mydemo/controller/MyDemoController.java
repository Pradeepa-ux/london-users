package com.example.mydemo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mydemo.domains.model.Users;
import com.example.mydemo.service.MyDemoService;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.extern.slf4j.Slf4j;

/**
 * Controller act as REST endpoint of the application and calling service class
 */
@Slf4j
@RestController
public class MyDemoController {

	@Autowired
	MyDemoService myDemoService;

	@GetMapping(value = "/myapp/london-users")
	public List<Users> listOfUsers() throws JsonProcessingException {

		log.info("==============RECEIVED THE REQUEST FROM HEROKUAPP API TO RETURN THE PEOPLE LIVING IN LONDON/50 MILES OF LONDON===============");
		
		List<Users> users=myDemoService.getUsersInOrAroundLondonService();
		return users!=null? users: new ArrayList<>();

	}

}
