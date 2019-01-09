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
    String username;

    // 密码
    String password;

    // 用户类型
    Short userType;

    List<String> permissions;

}
