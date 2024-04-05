package com.example.service.impl;


import com.example.converter.ProductConverter;
import com.example.dto.ProductDTO;
import com.example.entity.Product;
import com.example.repository.ProductRepository;
import com.example.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {

    @Autowired
    private ProductRepository productRepository;


    @Autowired
    private ProductConverter productConverter;


    @Override
    public ProductDTO save(ProductDTO productDTO) {
        Product product;
        product = productConverter.toEntity(productDTO);
        product = productRepository.save(product);
        return productConverter.toDTO(product);
    }
}
