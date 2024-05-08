package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCondimentDetailDTO {

    private Long id;
    private Long condimentId;
    private int quantity;
    private double condimentPrice;
    private Long productDetailId;
}