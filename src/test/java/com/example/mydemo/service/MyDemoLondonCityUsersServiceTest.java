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
import org.springframework.beans.factory.annotation.Value;
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
class MyDemoLondonCityUsersServiceTest {
	
	@InjectMocks
	MyDemoLondonCityUsersService myDemoLondonCityUsersService;

	@Mock
	RestTemplate restTemplate;
	
	@Mock
	ObjectMapper objectMapper;
	
	@Mock
	UriComponentsBuilder uriComponentsBuilder;
	
	@Value("${HerokuApp.apiUri}")
	String herokuAppApiUri;
	
	
	
	
	@DisplayName("When london user is one then it will return single list as result")
	@Test
	void returnSingleUserInLondontest() throws JsonProcessingException {
		
			ReflectionTestUtils.setField(myDemoLondonCityUsersService,"herokuAppApiUri","https://bpdts-test-app.herokuapp.com/city/{city}/users");
		
			Users user = new Users(1,"Pradeepa","BAlakrishnan","@gamil","113.71.242.187",-6.5115909,105.652983);

		    List<Users> expectedUsers = new ArrayList<>();
		    expectedUsers.add(user);
		    
		    Users[] myUsers=new Users[1];
		    myUsers[0]=user;

			ResponseEntity<Users[]> responseEntity = new ResponseEntity<>(myUsers, HttpStatus.OK);

		    when(restTemplate.exchange(Mockito.anyString(), Mockito.any(), Mockito.any(), eq(Users[].class)))
		        .thenReturn(responseEntity);
		    
		    List<Users> actualUsers = myDemoLondonCityUsersService.getUsersInLondon();

		    assertThat("when resttemplate mocked users are returened", actualUsers, is(expectedUsers));
	}
	
	@Test
	@DisplayName("When there is bad request received  it will return empty array list as result")
	  public void mckedBadRequest() throws JsonProcessingException {


	    ResponseEntity<Users[]> responseEntity = new ResponseEntity<>(new Users[0], HttpStatus.BAD_REQUEST);
	    
	    ReflectionTestUtils.setField(myDemoLondonCityUsersService,"herokuAppApiUri","https://bpdts-test-app.herokuapp.com/city/{city}/users");

	    when(restTemplate.exchange(Mockito.anyString(), Mockito.any(), Mockito.any(), eq(Users[].class)))
        .thenReturn(responseEntity);
	    
	    List<Users> actualUsers = myDemoLondonCityUsersService.getUsersInLondon();

	    assertThat("Service returns empty list.", actualUsers, is(new ArrayList<>()));
	  }

}
