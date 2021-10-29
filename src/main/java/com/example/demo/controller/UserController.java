package com.example.demo.controller;

import com.example.demo.common.CommonResponse;
import com.example.demo.common.CommonServerResponse;
import com.example.demo.common.Const;
import com.example.demo.pojo.User;
import com.example.demo.service.UserService;
import com.example.demo.util.CookieUtil;
import com.example.demo.util.JsonUtil;
import com.example.demo.util.RedisPoolUtil;
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
@RequestMapping("/user/")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    @ResponseBody
    public CommonServerResponse login(User u, HttpSession session, HttpServletResponse response) {
        int i = 0;
        int j = 2/i;
        User user = userService.login(u.getUsername(), u.getPassword());
        if (user == null) {
            return CommonServerResponse.setResponse(CommonResponse.FAIL.getCode(), "用户名或者密码错误");
        } else {
            CookieUtil.writeLoginToken(response,session.getId());
            RedisPoolUtil.set(session.getId(), JsonUtil.obj2String(user));
            //session.setAttribute(Const.USER, user);

            return CommonServerResponse.setResponse(CommonResponse.SUCCESS.getCode(), "登录成功");
        }

    }

    @RequestMapping("manager/login")
    @ResponseBody
    public CommonServerResponse managerLogin(String username, String password, HttpSession session) {
        User user = userService.login(username, password);
        if (user == null) {
            return CommonServerResponse.setResponse(CommonResponse.FAIL.getCode(), "用户名或者密码错误");
        } else {
            if (user.getManager() == 1) {
                session.setAttribute(Const.MANAGER, user);
                return CommonServerResponse.setResponse(CommonResponse.SUCCESS.getCode(), "登录成功");
            } else {
                return CommonServerResponse.setResponse(CommonResponse.FAIL.getCode(), "不是管理员");
            }

        }
    }

    @RequestMapping("/register")
    @ResponseBody
    public CommonServerResponse register(User user) {
        User user1 = userService.checkedUser(user.getUsername());
        if (user1 != null) {
            return CommonServerResponse.setResponse(CommonResponse.FAIL.getCode(), "用户名已经存在");
        }
        int num = userService.register(user);
        if (num == 0) {
            return CommonServerResponse.setResponse(CommonResponse.FAIL.getCode(), "注册失败");
        } else {
            return CommonServerResponse.setResponse(CommonResponse.SUCCESS.getCode(), "注册成功");
        }
    }

    @RequestMapping("/manager/register")
    @ResponseBody
    public CommonServerResponse managerRegister(User user) {
        User user1 = userService.checkedUser(user.getUsername());
        if (user1 != null) {
            return CommonServerResponse.setResponse(CommonResponse.FAIL.getCode(), "用户名已经存在");
        }
        user.setManager(1);
        int num = userService.register(user);
        if (num == 0) {
            return CommonServerResponse.setResponse(CommonResponse.FAIL.getCode(), "管理员注册失败");
        } else {
            return CommonServerResponse.setResponse(CommonResponse.SUCCESS.getCode(), "管理员注册成功");
        }
    }

    @RequestMapping("/detail")
    @ResponseBody
    public CommonServerResponse<User> detail(HttpSession session,HttpServletRequest request) {
        //User user = (User) session.getAttribute(Const.USER);

        String redisKey = CookieUtil.redaLoginToken(request);
        if(redisKey == null){
            return CommonServerResponse.setResponse(CommonResponse.FAIL.getCode(), "没有登录", null);
        }else {
            User user = JsonUtil.string2Obj(RedisPoolUtil.get(redisKey),User.class);
            if (user == null) {
                return CommonServerResponse.setResponse(CommonResponse.FAIL.getCode(), "没有登录", null);
            }
            return CommonServerResponse.setResponse(CommonResponse.SUCCESS.getCode(), "登录成功", user);
        }
    }

    @RequestMapping("/manager/detail")
    @ResponseBody
    public CommonServerResponse<User> managerDetail(HttpSession session) {
        User user = (User) session.getAttribute(Const.MANAGER);
        if (user == null) {
            return CommonServerResponse.setResponse(CommonResponse.FAIL.getCode(), "没有登录", null);
        }
        return CommonServerResponse.setResponse(CommonResponse.SUCCESS.getCode(), "登录成功", user);
    }

    @RequestMapping("/logout")
    @ResponseBody
    public CommonServerResponse logout(HttpSession session,HttpServletResponse response,HttpServletRequest request) {
        //session.removeAttribute(Const.USER);
        CookieUtil.delLoginToken(request,response);
        return CommonServerResponse.setResponse(CommonResponse.SUCCESS.getCode(), "用户退出成功");
    }

    @RequestMapping("manager/logout")
    @ResponseBody
    public CommonServerResponse managerLogout(HttpSession session) {
        session.removeAttribute(Const.MANAGER);
        return CommonServerResponse.setResponse(CommonResponse.SUCCESS.getCode(), "管理员退出成功");
    }

    @RequestMapping("/forget")
    @ResponseBody
    public CommonServerResponse forget(String username, String answer, String newPassword) {
        if (userService.forget(username).equals(answer)) {
            return CommonServerResponse.setResponse(CommonResponse.FAIL.getCode(), "忘记密码问题答案错误，请重新输入回答");
        } else {
            int num = userService.updatePassword(username, newPassword);
            if (num == 0) {
                return CommonServerResponse.setResponse(CommonResponse.FAIL.getCode(), "更新密码错误");
            } else {
                return CommonServerResponse.setResponse(CommonResponse.SUCCESS.getCode(), "更新密码成功");
            }
        }
    }


}
