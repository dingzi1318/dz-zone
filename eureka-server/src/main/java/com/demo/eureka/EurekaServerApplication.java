package com.demo.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * eureka 服务启动类
 *
 * @Author dingzi
 * @Date 2020/11/19 17:08
 */
@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

    /**
     * 启动入口
     *
     * @param args
     */
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
