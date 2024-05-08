package com.example.dto.cart;

import com.example.dto.SizeDTO;
import com.example.util.ProductStatus;
import com.example.util.ProductType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private long id;
    private String name;
    private double discount;
    private ProductType productType;
    private ProductStatus productStatus;
    private SizeDTO size;
}
