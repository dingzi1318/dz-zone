package com.demo.feign.controller;

import com.demo.feign.client.StudentRemoteClient;
import com.demo.feign.client.UserRemoteClient;
import com.demo.feign.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@Autowired
	private UserRemoteClient userRemoteClient;
	
	@Autowired
	private StudentRemoteClient studentRemoteClient;
	
	@GetMapping("/getUser")
	public User getUser() {
		return userRemoteClient.getUser(1);
	}
	
	@GetMapping("/getStudent")
	public String getStudent() {
		StudentRequest request = new StudentRequest();
		request.setName("yinjihuan");
		request.setAge(18);
		return studentRemoteClient.getStudentName(request);
	}
	
}
