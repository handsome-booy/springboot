package com.example.demo.service;

import com.example.demo.common.CommonServerResponse;
import com.example.demo.pojo.Cart;

public interface CartService {
    CommonServerResponse<Cart>  addCart(String product_name, int product_number,int user_id);

    CommonServerResponse<Cart>  delCart(String product_name, int user_id);

}
