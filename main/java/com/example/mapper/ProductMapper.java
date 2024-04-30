package com.example.mapper;

import com.example.dto.ProductDTO;
import com.example.entity.Product;
import com.example.repository.SizeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductMapper {
    @Autowired
    private SizeRepository sizeRepository;
    private final ModelMapper mapper = new ModelMapper();
    public ProductDTO toDTO(Product product) {
        ProductDTO result = new ProductDTO();
        return mapper.map(product, ProductDTO.class);
    }
    public Product toEntity(ProductDTO productDTO) {
        Product result = mapper.map(productDTO, Product.class);
        return result;
    }
    public List<ProductDTO> toDTOs(List<Product> products) {
        List<ProductDTO> productDTOS = new ArrayList<>();
        for (Product p : products) {
            productDTOS.add(toDTO(p));
        }
        return productDTOS;
    }
    public List<Product> toEntities(List<ProductDTO> productDTOS) {
        List<Product> products = new ArrayList<>();
        for (ProductDTO p : productDTOS) {
            products.add(toEntity(p));
        }
        return products;
    }
}
