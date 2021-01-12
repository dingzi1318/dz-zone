package com.demo.feign.client;

import com.demo.feign.service.StudentRemoteService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "feign-user-service")
public interface StudentRemoteClient extends StudentRemoteService {

	
}