package com.example.mydemo.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
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
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
class MyDemoServiceTest {

	@InjectMocks
	MyDemoService myDemoService;
	
	@Mock
	MyDemoLondonCityUsersService myDemoLondonCityUsersService;
	
	@Mock
	MyDemoAroundLondonService myDemoAroundLondonService;
	
	@Mock
	ObjectMapper objectMapper;
	
	@Test
	@DisplayName("When list of users in/around 50 miles of london returned then it will add both list")
	void checkUsersinOrAroundLondonwithMultipleOverlapping() throws JsonProcessingException {
		
		Users user = new Users(1,"Pradeepa","BAlakrishnan","@gamil","113.71.242.187",-6.5115909,105.652983);
		List<Users> users=new ArrayList<>();
		users.add(user);
		when(myDemoLondonCityUsersService.getUsersInLondon()).thenReturn(users);	
		Users user2 = new Users(2,"Pradeepa1111","BAlakrishnan","@gamil","113.71.242.187",-6.5115909,105.652983);
		List<Users> users1 = new ArrayList<>();
		users1.add(user);
		users1.add(user2);
		when(myDemoAroundLondonService.getUserAroundLondon()).thenReturn(users1);
		List<Users> users3=myDemoService.getUsersInOrAroundLondonService();
		assertThat("Returned List in service has size of 2",users3.size(),is(2));
		assertThat("Returned List in service contains user,user2 ",users3,contains(user,user2));
	
	}
	
	
	@Test
	@DisplayName("When both the list returns empty arraylist then service function return empty arraylist")
	void checkUsersinOrAroundLondonEmptyList() throws JsonProcessingException {
		
		when(myDemoLondonCityUsersService.getUsersInLondon()).thenReturn(new ArrayList<>());	
		when(myDemoAroundLondonService.getUserAroundLondon()).thenReturn(new ArrayList<>());
		List<Users> users=myDemoService.getUsersInOrAroundLondonService();
		assertThat("Service returns empty list when no users returned",users,is(new ArrayList<>()));
	
	}
	
	@Test
	@DisplayName("When list of users in/around 50 miles of london returned then it will add both list")
	void checkUsersinOrAroundLondonwithnoOverlapping() throws JsonProcessingException {
		
		Users user = new Users(1,"Pradeepa","BAlakrishnan","@gamil","113.71.242.187",-6.5115909,105.652983);
		List<Users> users=new ArrayList<>();
		users.add(user);
		when(myDemoLondonCityUsersService.getUsersInLondon()).thenReturn(users);	
		Users user2 = new Users(2,"Pradeepa1111","BAlakrishnan","@gamil","113.71.242.187",-6.5115909,105.652983);
		List<Users> users1 = new ArrayList<>();
		users1.add(user2);
		when(myDemoAroundLondonService.getUserAroundLondon()).thenReturn(users1);
		List<Users> users3=myDemoService.getUsersInOrAroundLondonService();
		assertThat("Returned List in service has size of 2",users3.size(),is(2));
		assertThat("Returned List in service contains user,user2 ",users3,contains(user,user2));
	
	}
	
	@Test
	@DisplayName("When london users is returned but empty arrayliist with users around london,then it will return london users list")
	public void oneUserInLondonAndAroundNoUser_GetUsersInOrAroundLondon() throws JsonProcessingException {

		Users user = new Users(1,"Pradeepa","BAlakrishnan","@gamil","113.71.242.187",-6.5115909,105.652983);

	    List<Users> londonUsers = new ArrayList<>();
	    londonUsers.add(user);

	    when(myDemoLondonCityUsersService.getUsersInLondon()).thenReturn(londonUsers);
	    when(myDemoAroundLondonService.getUserAroundLondon()).thenReturn(new ArrayList<>());


	    List<Users> actualUsers = myDemoService.getUsersInOrAroundLondonService();

	    assertThat("Service returns one user when one users in London and none within 50 miles",
	        actualUsers, is(londonUsers));
	  }

	  @Test
	  @DisplayName("When london users is empty but users around 50 miles of london,then it will return sers around 50 miles of london")
	  public void givenNoUserAndOneUser_GetUsersInOrAroundLondon_ReturnsOneUserInList() throws JsonProcessingException {
		  
		Users user = new Users(1,"Pradeepa","BAlakrishnan","@gamil","113.71.242.187",-6.5115909,105.652983);

	    List<Users> usersWithinFiftyMiles = new ArrayList<>();
	    usersWithinFiftyMiles.add(user);

	    when(myDemoLondonCityUsersService.getUsersInLondon()).thenReturn(new ArrayList<>());
	    when(myDemoAroundLondonService.getUserAroundLondon()).thenReturn(usersWithinFiftyMiles);


	    List<Users> actualUsers = myDemoService.getUsersInOrAroundLondonService();

	    assertThat("Service returns one user when no users in London and one within 50 miles",
	        actualUsers, is(usersWithinFiftyMiles));
	  }



}
