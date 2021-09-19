package com.example.demo.controller;

import com.example.demo.common.CommonResponse;
import com.example.demo.common.CommonServerResponse;
import com.example.demo.common.Const;
import com.example.demo.dao.UserMapper;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public CommonServerResponse login(String username, String password, HttpSession session){
        User user = userService.login(username, password);
        if(user == null){
            return CommonServerResponse.setResponse(CommonResponse.FAIL.getCode(), "用户名或者密码错误");
        }else{
            session.setAttribute(Const.USER,user);
            return CommonServerResponse.setResponse(CommonResponse.SUCCESS.getCode(), "登录成功");
        }
    }

    @RequestMapping("/register")
    @ResponseBody
    public CommonServerResponse register(String username, String password,String question,String answer){
        int num = userService.register(username,password,question,answer);
        if(num == 0){
            return CommonServerResponse.setResponse(CommonResponse.FAIL.getCode(), "注册失败");
        }else{
            return CommonServerResponse.setResponse(CommonResponse.SUCCESS.getCode(), "注册成功");
        }
    }

    @RequestMapping("/detail")
    @ResponseBody
    public CommonServerResponse detail(HttpSession session){
        User user = (User)session.getAttribute(Const.USER);
        if(user == null){
            return CommonServerResponse.setResponse(CommonResponse.FAIL.getCode(), "没有登录");
        }
        return CommonServerResponse.setResponse(CommonResponse.SUCCESS.getCode(), user.toString());
    }

    @RequestMapping("/logout")
    @ResponseBody
    public CommonServerResponse logout(HttpSession session){
        session.removeAttribute(Const.USER);
        return CommonServerResponse.setResponse(CommonResponse.SUCCESS.getCode(), "退出成功");
    }


}
