package com.example.demo.dao;

import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.*;

public interface UserMapper {
    @Select("select * from user where username=#{username} and password=#{password} limit 1")
    User login(@Param("username") String username,@Param("password") String password);

    @Insert("insert into user(username,password,question,answer) value(#{username},#{password},#{question},#{answer})")
    int register(@Param("username")String username,@Param("password")String password,@Param("question")String question,
                 @Param("answer")String answer);

    @Select("select answer from user where username=#{username}")
    String forget(String username);

    @Update("update user set password = #{newPassword} where username = #{username}")
    int updatePassword(@Param("username")String username,@Param("newPassword")String newPassword);
}
