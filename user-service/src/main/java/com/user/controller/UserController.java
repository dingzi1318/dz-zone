package com.user.controller;

import com.user.dto.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description TODO
 * @Author dingzi
 * @Date 2020/11/20 15:57
 */
@RestController
public class UserController {

    @GetMapping(value = "/user/get")
    public User getUser(Long id) {
        return new User(123465454111L, "钉子使用Ribbon发起的调用");
    }

}
