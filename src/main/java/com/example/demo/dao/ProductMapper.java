package com.example.demo.dao;

import com.example.demo.pojo.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {
    @Insert("insert into product(name,number,price) values(#{name},#{number},#{price})")
    int addProduct(Product product);

    @Delete("delete from product where name = #{name}")
    int deleteProduct(String name);


}
