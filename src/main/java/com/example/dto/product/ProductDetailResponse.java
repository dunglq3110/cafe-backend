package com.example.dto.product;

import com.example.dto.ProductDTO;
import com.example.dto.SizeDTO;
import com.example.dto.product.ProductCondimentDetailResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailResponse {

    Long id;
    int productQuantity;
    double productDiscount;
    double productPrice;
    ProductDTO product;
    SizeDTO size;
    List<ProductCondimentDetailResponse> productCondimentDetails;
}
