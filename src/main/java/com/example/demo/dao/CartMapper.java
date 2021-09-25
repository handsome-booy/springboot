package com.example.demo.dao;

import com.example.demo.pojo.Cart;
import org.apache.ibatis.annotations.*;

@Mapper
public interface CartMapper {

    @Insert("insert into cart(user_id,product_id,product_name,product_number,product_price,checked) " +
            "values(#{user_id},#{product_id},#{product_name},#{product_number},#{product_price},#{checked})")
    int addCart(Cart cart);

    @Select("SELECT * FROM CART WHERE product_name=#{product_name} and user_id = #{user_id}")
    Cart searchCart(@Param("product_name") String product_name,@Param("user_id")int user_id);

    @Delete("delete from cart WHERE product_name=#{product_name} and user_id = #{user_id}")
    int delCart(@Param("product_name") String product_name,@Param("user_id")int user_id);
}
