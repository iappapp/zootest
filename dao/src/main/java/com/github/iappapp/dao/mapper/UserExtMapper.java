package com.github.iappapp.dao.mapper;

import com.github.iappapp.dao.domain.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserExtMapper {
    // 使用注解
    // @Select(value = "SELECT host, `user` FROM user")
    List<User> listUser(@Param("id") int id);
}
