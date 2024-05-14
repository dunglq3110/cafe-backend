package com.example.mapper;

import com.example.repository.ProductCondimentDetailRepository;
import com.example.repository.ProductDetailRepository;
import com.example.repository.ProductSizeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductDetailMapper {
    @Autowired
    ProductSizeRepository productSizeRepository;
    @Autowired
    ProductCondimentDetailMapper productCondimentDetailMapper;
    @Autowired
    ProductDetailRepository productDetailRepository;
    @Autowired
    ProductCondimentDetailRepository productCondimentDetailRepository;


}
