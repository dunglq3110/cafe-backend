package com.example.api;


import com.example.dto.ProductDTO;
import com.example.entity.Product;
import com.example.repository.ProductRepository;
import com.example.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = {"/product"})
public class ProductAPI {
    @Autowired
    private IProductService productService;
    @PostMapping
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO)
    {
        return productService.save(productDTO);
    }
    @PutMapping(value = "/{id}")
    public ProductDTO updateProduct(@RequestBody ProductDTO productDTO, @PathVariable("id") Long id)
    {
        productDTO.setId(id);
        return productService.save(productDTO);
    }
    @GetMapping(value = "/{id}")
    public ProductDTO findProductById(@PathVariable("id") Long id) {
        return productService.findProductById(id);
    }

    @GetMapping
    public List<ProductDTO> findAll() {
        return productService.findAll();
    }

}
