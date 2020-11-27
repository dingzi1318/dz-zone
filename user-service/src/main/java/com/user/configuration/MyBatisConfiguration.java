package com.user.configuration;

import com.user.interceptors.UserUpdateInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisConfiguration {

    @Bean
    public UserUpdateInterceptor userUpdateInterceptor() {
        return new UserUpdateInterceptor();
    }
}
