package com.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailDTO {

    @JsonIgnore
    private Long id;
    private int productQuantity;
    private double productDiscount;
    private double productPrice;
    private Long productId;
    private Long productSizeId;
    private List<ProductCondimentDetailDTO> productCondimentDetails;
}