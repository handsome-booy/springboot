package com.example.demo.controller;

import com.example.demo.common.CommonResponse;
import com.example.demo.common.CommonServerResponse;
import com.example.demo.common.Const;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import com.example.demo.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public CommonServerResponse register(User user){
        int num = userService.register(user);
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

    @RequestMapping("/forget")
    @ResponseBody
    public CommonServerResponse forget(String username,String answer,String newPassword){
        if(userService.forget(username).equals(answer)){
            return CommonServerResponse.setResponse(CommonResponse.FAIL.getCode(), "忘记密码问题答案错误，请重新输入回答");
        }else {
            int num = userService.updatePassword(username, newPassword);
            if(num == 0){
                return CommonServerResponse.setResponse(CommonResponse.FAIL.getCode(), "更新密码错误");
            }else{
                return CommonServerResponse.setResponse(CommonResponse.SUCCESS.getCode(), "更新密码成功");
            }
        }
    }



}
