package com.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(WrapService.class)
@EnableConfigurationProperties(WrapProperties.class)// 使@ConfigurationProperties注解生效
public class WrapAutoConfiguration {

    @Autowired
    private WrapProperties properties;

    @Bean
    @ConditionalOnProperty(name = "wrap.service.enable", havingValue = "true", matchIfMissing = true)
    public WrapService someService() {
        return new WrapService(properties.getBefore(), properties.getAfter());
    }

    @Bean
    @ConditionalOnMissingBean
    public WrapService someService2() {
        return new WrapService("", "");
    }

}
