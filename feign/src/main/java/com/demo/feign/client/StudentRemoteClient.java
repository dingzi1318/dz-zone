package com.demo.feign.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "feign-user-service")
public interface StudentRemoteClient extends StudentRemoteService {

	
}