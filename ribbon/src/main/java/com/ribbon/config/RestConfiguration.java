package com.ribbon.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Spring提供的访问Rest服务的客户端：RestTemplate
 * 配置负载均衡
 *
 * @Author dingzi
 * @Date 2020/11/20 15:11
 */
@Configuration
public class RestConfiguration {


    @Bean
    @LoadBalanced // 整合Ribbon
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
