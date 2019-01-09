package com.feidian.farmer.dao.mapper;

import com.feidian.farmer.dao.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    User selectOneByUsername(String username);

    List<String> selectPermissionsByUserType(short userType);

}
