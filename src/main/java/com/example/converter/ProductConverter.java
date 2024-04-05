package com.example.converter;

import com.example.dto.ProductDTO;
import com.example.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductConverter {

    public Product toEntity(ProductDTO productDTO) {
        Product product = new Product();
        product.setId(productDTO.getId());
        product.setName(productDTO.getName());
        product.setImage(productDTO.getImage());
        product.setDiscount(productDTO.getDiscount());
        product.setProductType(productDTO.getProductType());
        product.setProductStatus(productDTO.getProductStatus());
        return product;
    }

    public ProductDTO toDTO(Product product) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(product.getId());
        productDTO.setName(product.getName());
        productDTO.setImage(product.getImage());
        productDTO.setDiscount(product.getDiscount());
        productDTO.setProductType(product.getProductType());
        productDTO.setProductStatus(product.getProductStatus());
        return productDTO;
    }

}
