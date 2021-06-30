package com.example.mydemo.domains.model;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Class defining Users object both being consumed and returning.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"id",
"first_name",
"last_name",
"email",
"ip_address",
"latitude",
"longitude"
})

public class Users {

@JsonProperty("id")
private Integer id;

public Users() {}

public Users(Integer id, String firstName, String lastName, String email, String ipAddress, Double latitude,
		Double longitude) {
	super();
	this.id = id;
	this.firstName = firstName;
	this.lastName = lastName;
	this.email = email;
	this.ipAddress = ipAddress;
	this.latitude = latitude;
	this.longitude = longitude;
}

	@JsonProperty("first_name")
	private String firstName;
	@JsonProperty("last_name")
	private String lastName;
	@JsonProperty("email")
	private String email;
	@JsonProperty("ip_address")
	private String ipAddress;
	@JsonProperty("latitude")
	private Double latitude;
	@JsonProperty("longitude")
	private Double longitude;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();

@JsonProperty("id")
public Integer getId() {
return id;
}

@JsonProperty("id")
public void setId(Integer id) {
this.id = id;
}

@JsonProperty("first_name")
public String getFirstName() {
return firstName;
}

@JsonProperty("first_name")
public void setFirstName(String firstName) {
this.firstName = firstName;
}

@JsonProperty("last_name")
public String getLastName() {
return lastName;
}

@JsonProperty("last_name")
public void setLastName(String lastName) {
this.lastName = lastName;
}

@JsonProperty("email")
public String getEmail() {
return email;
}

@JsonProperty("email")
public void setEmail(String email) {
this.email = email;
}

@JsonProperty("ip_address")
public String getIpAddress() {
return ipAddress;
}

@JsonProperty("ip_address")
public void setIpAddress(String ipAddress) {
this.ipAddress = ipAddress;
}

@JsonProperty("latitude")
public Double getLatitude() {
return latitude;
}

@JsonProperty("latitude")
public void setLatitude(Double latitude) {
this.latitude = latitude;
}

@JsonProperty("longitude")
public Double getLongitude() {
return longitude;
}

@JsonProperty("longitude")
public void setLongitude(Double longitude) {
this.longitude = longitude;
}

@JsonAnyGetter
public Map<String, Object> getAdditionalProperties() {
return this.additionalProperties;
}

@JsonAnySetter
public void setAdditionalProperty(String name, Object value) {
this.additionalProperties.put(name, value);
}

}
 