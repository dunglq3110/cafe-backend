package com.example.service;

import com.example.dto.ProductDTO;
import com.example.dto.SizeDTO;
import com.example.dto.cart.ProductRequest;
import com.example.dto.cart.ProductResponse;

import java.util.List;

public interface IProductService {
    ProductDTO save(ProductDTO productDTO);
    ProductDTO update(ProductDTO productDTO,long id);
    ProductDTO findProductById(Long id);
    List<ProductDTO> findAll();
    ProductResponse findById(int id);
    SizeDTO checkProductSize(ProductRequest productRequest);
}
