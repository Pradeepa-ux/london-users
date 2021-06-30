package com.example.mydemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.mydemo.controller.MyDemoController;

@SpringBootApplication
public class MyDemoApplication{

	@Autowired
	MyDemoController myDemoController;

	public static void main(String[] args) {

		SpringApplication.run(MyDemoApplication.class, args);

	}

}
