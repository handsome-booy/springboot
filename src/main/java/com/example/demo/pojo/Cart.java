package com.example.demo.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class Cart {
    @JsonIgnore
    int id;

    int user_id;

    @JsonIgnore
    int product_id;

    String product_name;

    int product_number;

    BigDecimal product_price;

    boolean checked;
}
