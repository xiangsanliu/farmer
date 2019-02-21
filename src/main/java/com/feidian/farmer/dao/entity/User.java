package com.feidian.farmer.dao.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class User {

    // 用户名
    private String username;

    // 密码
    private String password;

    // 用户类型
    private Short userType;

    // 用户权限
    private List<String> permissions;

}
