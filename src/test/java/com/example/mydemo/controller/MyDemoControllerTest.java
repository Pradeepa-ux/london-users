package com.example.mydemo.controller;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.mydemo.domains.model.Users;
import com.example.mydemo.service.MyDemoService;
import com.fasterxml.jackson.core.JsonProcessingException;

@ExtendWith(SpringExtension.class)
class MyDemoControllerTest {
	
	@InjectMocks
	MyDemoController myDemoController;
	
	@Mock
	MyDemoService myDemoService;

	@Test
	@DisplayName("When No user is returned then empty array list will be returned")
	void noUsersFound_ReturnEmptyArrayList() throws JsonProcessingException {
		
		when(myDemoService.getUsersInOrAroundLondonService()).thenReturn(new ArrayList<>());	
		List<Users> users=myDemoController.listOfUsers();
		assertThat("Controller returns empty list when no users returned", users, is(new ArrayList<>()));
		
		
	}
	
	@Test
	@DisplayName("When user list is null then empty array list will be returned")
	void nullUsersCheck() throws JsonProcessingException {
		
		when(myDemoService.getUsersInOrAroundLondonService()).thenReturn(null);	
		List<Users> users=myDemoController.listOfUsers();
		assertThat("Controller returns empty list if user list null",users, is(new ArrayList<>()));
		
	}
	
	@Test
	@DisplayName("When Users are in/around london then userlist will be returned")
	void usersInOrAroundLondonCheck() throws JsonProcessingException {
		
		Users user = new Users(1,"Pradeepa","BAlakrishnan","@gamil","113.71.242.187",-6.5115909,105.652983);
		List<Users> users=new ArrayList<>();
		users.add(user);
		when(myDemoService.getUsersInOrAroundLondonService()).thenReturn(users);	
		List<Users> usersControllerCheck=myDemoController.listOfUsers();
		assertThat("Controller returns empty list if user list null",usersControllerCheck, is(users));
		
	}

}
