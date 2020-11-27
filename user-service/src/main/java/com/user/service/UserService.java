package com.user.service;

import com.user.mapper.UserInfoMapper;
import com.user.model.UserInfo;
import com.user.model.UserInfoExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfo selectById(Long id) {
        return userInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public Long insert(UserInfo userInfo) {
        userInfo.setCreateTime(new Date());
        int id = userInfoMapper.insertSelective(userInfo);
        return Long.valueOf(id);
    }

    @Override
    @Transactional
    public List<Long> updateName(String oldName, String newName) {
        UserInfoExample example = new UserInfoExample();
        example.createCriteria().andNameEqualTo(oldName);
        List<UserInfo> userInfos = userInfoMapper.selectByExample(example);
        userInfos.forEach(k -> {
            k.setName(newName);
            logger.info("执行用户数据更新，用户ID：{}", k.getId());
            userInfoMapper.updateByPrimaryKeySelective(k);
        });
        List<Long> userIds = userInfos.stream().map(UserInfo::getId).collect(Collectors.toList());
        return userIds;
    }
}
