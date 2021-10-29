package com.example.demo.controller;

import com.example.demo.common.CommonResponse;
import com.example.demo.common.CommonServerResponse;
import com.example.demo.common.Const;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/springsession/")
public class UserSpringSessionController {
    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public CommonServerResponse login(User u, HttpSession session, HttpServletResponse response) {
        User user = userService.login(u.getUsername(), u.getPassword());
        if (user == null) {
            return CommonServerResponse.setResponse(CommonResponse.FAIL.getCode(), "用户名或者密码错误");
        } else {
            /*CookieUtil.writeLoginToken(response,session.getId());
            RedisPoolUtil.set(session.getId(), JsonUtil.obj2String(user));*/
            session.setAttribute(Const.USER, user);

            return CommonServerResponse.setResponse(CommonResponse.SUCCESS.getCode(), "登录成功");
        }

    }



    @RequestMapping("/detail")
    @ResponseBody
    public CommonServerResponse<User> detail(HttpSession session,HttpServletRequest request) {
        User user = (User) session.getAttribute(Const.USER);

        if(user == null){
            return CommonServerResponse.setResponse(CommonResponse.FAIL.getCode(), "没有登录", null);
        }
        return CommonServerResponse.setResponse(CommonResponse.SUCCESS.getCode(), "返回成功", user);
        /*String redisKey = CookieUtil.redaLoginToken(request);
        if(redisKey == null){
            return CommonServerResponse.setResponse(CommonResponse.FAIL.getCode(), "没有登录", null);
        }else {
            User user = JsonUtil.string2Obj(RedisPoolUtil.get(redisKey),User.class);
            if (user == null) {
                return CommonServerResponse.setResponse(CommonResponse.FAIL.getCode(), "没有登录", null);
            }
            return CommonServerResponse.setResponse(CommonResponse.SUCCESS.getCode(), "登录成功", user);
        }*/
    }


    @RequestMapping("/logout")
    @ResponseBody
    public CommonServerResponse logout(HttpSession session, HttpServletResponse response, HttpServletRequest request) {
        session.removeAttribute(Const.USER);
        //CookieUtil.delLoginToken(request,response);
        return CommonServerResponse.setResponse(CommonResponse.SUCCESS.getCode(), "用户退出成功");
    }
}
