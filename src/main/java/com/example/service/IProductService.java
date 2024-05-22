package com.example.service;


import com.example.dto.product.ProductCreateRequest;
import com.example.dto.product.ProductResponse;
import com.example.dto.product.ProductUpdateRequest;


import java.util.List;

public interface IProductService {

    ProductResponse createProduct(ProductCreateRequest productCreateRequest);

    List<ProductResponse> getAll();
    ProductResponse findProductById(Long id);
    ProductResponse updateProduct(Long id, ProductUpdateRequest productUpdateRequest);
}
