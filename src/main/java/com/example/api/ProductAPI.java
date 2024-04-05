package com.example.api;


import com.example.dto.ProductDTO;
import com.example.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductAPI {

    @Autowired
    private IProductService productService;

    @PostMapping(value = "/product")
    public ProductDTO createProduct(@RequestBody ProductDTO productDTO)
    {
        return productService.save(productDTO);
    }
}
