package com.example.demo.dao;

import com.example.demo.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from user where username=#{username} and password=#{password} limit 1")
    User login(@Param("username") String username,@Param("password") String password);

    @Insert("insert into user(username,password,question,answer) value(#{username},#{password},#{question},#{answer})")
    int register(@Param("username")String username,@Param("password")String password,@Param("question")String question,
                 @Param("answer")String answer);


}
