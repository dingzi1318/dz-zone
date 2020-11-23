package com.ribbon.controller;

import com.ribbon.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Description TODO
 * @Author dingzi
 * @Date 2020/11/20 15:15
 */

@RestController
public class RibbonController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping(value = "/user")
    public User getUser() {
        // RestTemplate 在调用接口时，就不需要用固定的 IP 加端口的方式调用接口, 而是以服务名称的方式调用
        ResponseEntity<User> responseEntity = restTemplate.getForEntity("http://user-service/user/get?id=1", User.class);
        User user = responseEntity.getBody();
        return user;
    }

}
