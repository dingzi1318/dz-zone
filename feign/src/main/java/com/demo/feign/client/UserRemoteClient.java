package com.demo.feign.client;


import com.demo.feign.config.FeignConfiguration;
import com.demo.feign.dto.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// value: 服务提供方的服务名称，也就是Eureka中的服务名
@FeignClient(value = "feign-user-service", configuration = FeignConfiguration.class, /**fallback = UserRemoteClientFallback.class**/fallbackFactory = UserRemoteClientFallbackFactory.class)
public interface UserRemoteClient {

    @GetMapping("/user/get")
    public User getUser(@RequestParam("id")int id);

}
