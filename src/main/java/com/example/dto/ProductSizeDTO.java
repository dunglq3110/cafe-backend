package com.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSizeDTO {
    private Long id;
    private ProductDTO product;
    private SizeDTO size;
    private double unitPrice;

}
