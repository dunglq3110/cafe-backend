package com.example.service;

import com.example.dto.ProductDTO;
import com.example.entity.Product;

public interface IProductService {
    ProductDTO save(ProductDTO productDTO);
}
