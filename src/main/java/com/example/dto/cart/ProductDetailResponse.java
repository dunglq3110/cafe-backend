package com.example.dto.cart;

import com.example.dto.CondimentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailResponse {

    private CondimentDTO condiment;
    private int quantity;
}
