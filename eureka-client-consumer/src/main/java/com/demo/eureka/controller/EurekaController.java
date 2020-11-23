package com.demo.eureka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Eureka服务消费者
 *
 * @Author dingzi
 * @Date 2020/11/19 18:30
 */
@RestController
public class EurekaController {

    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * 获取Eureka Server中所有的服务实例信息
     *
     * @return
     */
    @GetMapping(value = "/service-instance")
    public Map<String, Object> queryServiceInstance() {
        Map<String, Object> map = new HashMap<>();

        // 获取所有的服务列表

        List<String> services = discoveryClient.getServices();
        map.put("services", services);


        List<List<ServiceInstance>> instanceList = services.stream().map(sid -> discoveryClient.getInstances(sid)).collect(Collectors.toList());
        map.put("instanceList", instanceList);

        List<ServiceInstance> instances = discoveryClient.getServices().stream()
                .map(sid -> discoveryClient.getInstances(sid))
                .collect(Collectors.toList())
                .stream().flatMap(list -> list.stream()).collect(Collectors.toList());
        map.put("instances", instances);
        return map;
    }

}
