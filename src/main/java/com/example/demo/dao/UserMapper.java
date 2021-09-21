package com.example.demo.dao;

import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("select * from user where username = #{username} and password=#{password}")
    User login(@Param("username") String username, @Param("password") String password);

    @Insert("insert into user(username,password,question,answer,manager) values(#{username},#{password},#{question},#{answer},#{manager})")
    int register(User user);

    @Select("select * from user where username = #{username}")
    User checkedUser(String username);

    @Select("select answer from user where username = #{username}")
    String forget(String username);

    @Update("update user set password = #{newPassword} where username = #{username}")
    int updatePassword(@Param("username")String username,@Param("newPassword")String newPassword);
}