package com.example.demo.service.serviceImpl;

import com.example.demo.common.CommonResponse;
import com.example.demo.common.CommonServerResponse;
import com.example.demo.dao.CartMapper;
import com.example.demo.dao.ProductMapper;
import com.example.demo.pojo.Cart;
import com.example.demo.pojo.Product;
import com.example.demo.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    ProductMapper productMapper;

    @Autowired
    CartMapper cartMapper;

    @Override
    public CommonServerResponse<Cart> addCart(String product_name, int product_number, int user_id) {
        Product product = productMapper.searchProduct(product_name);
        if (product == null) {
            return CommonServerResponse.setResponse(CommonResponse.FAIL.getCode(), "商品已经下架");
        }
        if (product_number > product.getNumber()) {
            return CommonServerResponse.setResponse(CommonResponse.FAIL.getCode(), "商品库存不足");
        }
        Cart cart = new Cart();
        cart.setUser_id(user_id);
        cart.setProduct_id(product.getId());
        cart.setProduct_name(product_name);
        cart.setProduct_number(product_number);
        cart.setProduct_price(product.getPrice());
        cart.setChecked(false);
        int i = cartMapper.addCart(cart);
        if (i == 0) {
            return CommonServerResponse.setResponse(CommonResponse.FAIL.getCode(), "添加购物车出错");
        }
        return CommonServerResponse.setResponse(CommonResponse.SUCCESS.getCode(), "添加购物车成功", cart);
    }

    @Override
    public CommonServerResponse delCart(String product_name, int user_id) {
        Cart cart = cartMapper.searchCart(product_name, user_id);
        if (cart == null) {
            return CommonServerResponse.setResponse(CommonResponse.FAIL.getCode(), "购物车中不存在该商品");
        } else {
            int i = cartMapper.delCart(product_name, user_id);
            if (i == 0) {
                return CommonServerResponse.setResponse(CommonResponse.FAIL.getCode(), "删除失败");
            }
            return CommonServerResponse.setResponse(CommonResponse.SUCCESS.getCode(), "购物车删除商品成功");
        }
    }
}
