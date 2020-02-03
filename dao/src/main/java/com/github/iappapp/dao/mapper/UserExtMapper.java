package com.github.iappapp.dao.mapper;

import com.github.iappapp.dao.domain.User;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.cursor.Cursor;

import java.util.List;
import java.util.Map;

public interface UserExtMapper {
    // 使用注解
    // @Select(value = "SELECT host, `user` FROM user")
    List<User> listUser(@Param("id") int id, @Param("user") String user);

    List<User> selectUser(User user);

    @Select(value = "SELECT host, `user` FROM user")
    List<User> queryUser(@Param("id") int id, @Param("user") String user);

    Cursor<User> selectUserList();

    List<Map> selectUserByMap();

    @MapKey("user")
    Map<String, User> selectMap();
}
