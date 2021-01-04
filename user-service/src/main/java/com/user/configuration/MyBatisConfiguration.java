package com.user.configuration;

import com.user.context.ApplicationContextProvider;
import com.user.interceptor.UserUpdateInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(ApplicationContextProvider.class)
public class MyBatisConfiguration {

    @Bean
    public UserUpdateInterceptor userUpdateInterceptor() {
        return new UserUpdateInterceptor();
    }
}
