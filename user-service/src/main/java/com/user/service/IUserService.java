package com.user.service;

import com.user.model.UserInfo;

import java.util.List;

public interface IUserService {

    UserInfo selectById(Long id);

    Long insert(UserInfo userInfo);

    List<Long> updateName(String oldName, String newName);

}
