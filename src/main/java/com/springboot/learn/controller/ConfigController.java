package com.springboot.learn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 配置
 *
 * @Author dingzi
 * @Date 2020/11/7 15:17
 */
@RestController
public class ConfigController {

    @Value("${server.port:}")
    private String serverPort;

    @Autowired
    private Environment environment;

    @GetMapping(value = "/com/ribbon/config")
    public Map<String, Object> configInfo() {
        // 获取配置的三种方式
        Map<String, Object> map = new HashMap<>(6);
        map.put("port", serverPort);
        map.put("profile", environment.getProperty("spring.profiles.active"));
        return map;
    }

}
