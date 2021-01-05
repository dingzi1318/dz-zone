package com.user.controller;

import com.user.annotation.AutoIdempotent;
import com.user.annotation.PrintTime;
import com.user.context.ApplicationContextProvider;
import com.user.dto.ApiResult;
import com.user.dto.RpcResult;
import com.user.model.UserInfo;
import com.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description TODO
 * @Author dingzi
 * @Date 2020/11/20 15:57
 */
@RestController
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ApplicationContextProvider contextProvider;

    @GetMapping(value = "/user/get")
    @PrintTime
    public ApiResult getUser(Long id) {
        RpcResult<UserInfo> rpcResult = userService.selectById(id);
        return ApiResult.success(rpcResult.getData());
    }

    /**
     * 幂等性校验
     *
     * @param userInfo
     * @return
     */
    @AutoIdempotent
    @PostMapping(value = "users/save")
    public Long insert(UserInfo userInfo) {
        return userService.insert(userInfo);
    }



    @PostMapping(value = "update")
    public List<Long> update(String oldName, String newName) {
        return userService.updateName(oldName, newName);
    }

}
