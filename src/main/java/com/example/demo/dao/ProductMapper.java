package com.example.demo.dao;

import com.example.demo.pojo.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {
    @Insert("insert into product( id, productname, number, productprice) values(#{id},#{productname},#{number},#{productprice})")
    int addProduct(Product product);
}
