package com.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(SomeService.class)
@EnableConfigurationProperties(SomeServiceProperties.class)
public class SomeServiceAutoConfiguration {

    @Autowired
    private SomeServiceProperties properties;

    @Bean
    @ConditionalOnProperty(name = "some.service.enable", havingValue = "true", matchIfMissing = true)
    public SomeService someService() {
        return new SomeService(properties.getBefore(), properties.getAfter());
    }

    @Bean
    @ConditionalOnMissingBean
    public SomeService someService2() {
        return new SomeService("", "");
    }

}
