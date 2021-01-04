package com.user.controller;

import com.user.service.ITokenService;
import com.user.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {

    @Autowired
    private ITokenService tokenService;

    @Autowired
    private RedisUtil redisUtil;

    @GetMapping(value = "/token/create")
    public String createToken() {
        return tokenService.createToken();
    }

    @GetMapping(value = "/token/get")
    public String getTokenValue(String token) {
        return redisUtil.get(token).toString();
    }

}
