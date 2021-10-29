package com.example.demo.service.serviceImpl;

import com.example.demo.dao.ProductMapper;
import com.example.demo.pojo.Product;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductMapper productMapper;

    @Override
    public int addProduct(Product product) {
        return productMapper.addProduct(product);
    }

    @Override
    public int deleteProduct(String name) {
        return productMapper.deleteProduct(name);

    }

    @Override
    public Product searchProduct(String name) {
        return productMapper.searchProduct(name);
    }
}
