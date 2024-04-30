package com.example.dto.cart;

import com.example.entity.Condiment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private ProductResponse product;
    private int quantity;
    private CondimentDetailsResponse productCondimentDetails;
}
