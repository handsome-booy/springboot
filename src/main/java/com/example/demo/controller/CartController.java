package com.example.demo.controller;

import com.example.demo.common.CommonResponse;
import com.example.demo.common.CommonServerResponse;
import com.example.demo.common.Const;
import com.example.demo.pojo.Cart;
import com.example.demo.pojo.User;
import com.example.demo.service.CartService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;

    @Autowired
    UserService userService;

    @RequestMapping("/addCart")
    public CommonServerResponse addCart(String product_name, int product_number,HttpSession session){
        User user = (User) session.getAttribute(Const.USER);
        if(user == null){
            return CommonServerResponse.setResponse(CommonResponse.FAIL.getCode(), "用户名没有登录，无法添加购物车");
        }
        return cartService.addCart(product_name,product_number,user.getId());
    }

    @RequestMapping("/delCart")
    public CommonServerResponse delCart(String product_name,HttpSession session){
        User user = (User) session.getAttribute(Const.USER);
        if(user == null){
            return CommonServerResponse.setResponse(CommonResponse.FAIL.getCode(), "用户名没有登录，无法从购物车删除商品");
        }
        return cartService.delCart(product_name,user.getId());
    }
}
