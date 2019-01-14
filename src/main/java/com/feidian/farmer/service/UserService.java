package com.feidian.farmer.service;

import com.feidian.farmer.dao.entity.User;
import com.feidian.farmer.dao.mapper.UserMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    UserMapper userMapper;

    @Transactional
    public User getOne(String username) {
        User user = userMapper.selectOneByUsername(username);
        if (user != null) {
            user.setPermissions(userMapper.selectPermissionsByUserType(user.getUserType()));
        }
        return user;
    }

}
