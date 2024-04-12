package com.example.repository;

import com.example.dto.ProductDTO;
import com.example.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    @Override
    List<Product> findAll();


}
