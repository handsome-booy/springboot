package com.example.demo.service;

import com.example.demo.pojo.Product;

public interface ProductService {
    int addProduct(Product product);

    int deleteProduct(String name);

    Product searchProduct(String name);
}
