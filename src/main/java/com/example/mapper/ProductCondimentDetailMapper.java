package com.example.mapper;

import com.example.repository.CondimentRepository;
import com.example.repository.ProductCondimentDetailRepository;
import com.example.repository.ProductDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductCondimentDetailMapper {

    @Autowired
    CondimentRepository condimentRepository;
    @Autowired
    ProductDetailRepository productDetailRepository;
    @Autowired
    ProductCondimentDetailRepository productCondimentDetailRepository;


}
