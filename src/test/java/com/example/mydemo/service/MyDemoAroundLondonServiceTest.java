package com.example.mydemo.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.mydemo.domains.model.Users;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
class MyDemoAroundLondonServiceTest {

	@InjectMocks
	MyDemoAroundLondonService myDemoAroundLondonService;

	@Mock
	RestTemplate restTemplate;
	
	@Mock
	ObjectMapper objectMapper;
	
	@Mock
	UriComponentsBuilder uriComponentsBuilder;
	
		
	
	@DisplayName("When there is no user within radius it will return single list as result")
	@Test
	void returnNoUserInLondontest() throws JsonProcessingException {
		
			ReflectionTestUtils.setField(myDemoAroundLondonService,"herokuAppApiUri1","https://bpdts-test-app.herokuapp.com/city/users");
		
					    
		    Users[] myUsers=new Users[0];
		    
			ResponseEntity<Users[]> responseEntity = new ResponseEntity<>(myUsers, HttpStatus.OK);

		    when(restTemplate.exchange(Mockito.anyString(), Mockito.any(), Mockito.any(), eq(Users[].class)))
		        .thenReturn(responseEntity);
		    
		    List<Users> actualUsers = myDemoAroundLondonService.getUserAroundLondon();

		    assertThat("when there is no users within 50 miles", actualUsers,is(new ArrayList<>()));
	}
	
	@DisplayName("When there is  user with same latitude and longitude of london it will returned as result")
	@Test
	void returnSingleUserAroundLondonwithSameLAtitudeLongtest() throws JsonProcessingException {
		
			ReflectionTestUtils.setField(myDemoAroundLondonService,"herokuAppApiUri1","https://bpdts-test-app.herokuapp.com/city/users");
		
			Users userWithinRadius = new Users(1,"Pradeepa","BAlakrishnan","@gamil","113.71.242.187",51.50853,-0.12574);
					    
			List<Users> expectedUsers = new ArrayList<>();
		    expectedUsers.add(userWithinRadius);

		    Users[] users = new Users[1];
		    users[0] = userWithinRadius;
		    
			ResponseEntity<Users[]> responseEntity = new ResponseEntity<>(users, HttpStatus.OK);

		    when(restTemplate.exchange(Mockito.anyString(), Mockito.any(), Mockito.any(), eq(Users[].class)))
		        .thenReturn(responseEntity);
		    
		    List<Users> actualUsers = myDemoAroundLondonService.getUserAroundLondon();

		    assertThat("If distance between user and point is less than radius, user is returned", actualUsers,is(expectedUsers));
	}
	
	@DisplayName("When there is  user with different/not 50 miles latitude and longitude of london ,empty arrayList returned as result")
	@Test
	void returnSingleUserAroundLondonWithDifferentLatLongtest() throws JsonProcessingException {
		
			ReflectionTestUtils.setField(myDemoAroundLondonService,"herokuAppApiUri1","https://bpdts-test-app.herokuapp.com/city/users");
		
			Users userWithinRadius = new Users(1,"Pradeepa","BAlakrishnan","@gamil","113.71.242.187",58.50853,-0.42574);
					    
			List<Users> expectedUsers = new ArrayList<>();
		    expectedUsers.add(userWithinRadius);

		    Users[] users = new Users[1];
		    users[0] = userWithinRadius;
		    
			ResponseEntity<Users[]> responseEntity = new ResponseEntity<>(users, HttpStatus.OK);

		    when(restTemplate.exchange(Mockito.anyString(), Mockito.any(), Mockito.any(), eq(Users[].class)))
		        .thenReturn(responseEntity);
		    
		    List<Users> actualUsers = myDemoAroundLondonService.getUserAroundLondon();

		    assertThat("If distance between user and point is more than 50 miles, empty list is returned", actualUsers,is(new ArrayList<>()));
	}
	

}
