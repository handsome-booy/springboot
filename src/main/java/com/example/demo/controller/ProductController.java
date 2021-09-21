package com.example.demo.controller;

import com.example.demo.common.CommonResponse;
import com.example.demo.common.CommonServerResponse;
import com.example.demo.common.Const;
import com.example.demo.pojo.Product;
import com.example.demo.pojo.User;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    /**
     * 商品上架
     */
    @RequestMapping("/addProduct")
    public CommonServerResponse addProduct(Product product, HttpSession session){
        User user = (User) session.getAttribute(Const.MANAGER);
        if(user == null){
            return CommonServerResponse.setResponse(CommonResponse.FAIL.getCode(), "管理员未登录");
        }
        productService.addProduct(product);
        return CommonServerResponse.setResponse(CommonResponse.SUCCESS.getCode(), "商品上架成功");
    }
}
