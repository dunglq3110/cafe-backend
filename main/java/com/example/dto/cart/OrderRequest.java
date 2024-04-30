package com.example.dto.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {

    private ProductRequest product;
    private int quantity;
    private CondimentDetailsRequest productCondimentDetails;
}
