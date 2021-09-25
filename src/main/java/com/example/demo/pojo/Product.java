package com.example.demo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {
    @JsonIgnore
    int id;

    String name;

    int number;

    BigDecimal price;
}
