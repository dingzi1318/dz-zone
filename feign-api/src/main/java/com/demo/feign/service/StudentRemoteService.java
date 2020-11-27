package com.demo.feign.service;

import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

public interface StudentRemoteService {

	@GetMapping("/student/name")
	public String getStudentName(@SpringQueryMap StudentRequest request);
	
}
