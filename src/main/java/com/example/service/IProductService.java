package com.example.service;

import com.example.dto.ProductDTO;
import com.example.entity.Product;

import java.util.List;

public interface IProductService {
    ProductDTO save(ProductDTO productDTO);
    ProductDTO findProductById(Long id);
    List<ProductDTO> findAll();
}
