package com.example.mapper;

import com.example.dto.ProductSizeDTO;
import com.example.entity.ProductSize;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ProductSizeMapper {

    private final ModelMapper mapper = new ModelMapper();
    public ProductSizeDTO toDTO(ProductSize productSize) {
        return mapper.map(productSize, ProductSizeDTO.class);
    }
    public ProductSize toEntity(ProductSizeDTO productSizeDTO) {
        return mapper.map(productSizeDTO, ProductSize.class);
    }
    public List<ProductSizeDTO> toDTOs(List<ProductSize> products) {
        List<ProductSizeDTO> productSizeDTOs = new ArrayList<>();
        for (ProductSize p : products) {
            productSizeDTOs.add(toDTO(p));
        }
        return productSizeDTOs;
    }
    public List<ProductSize> toEntities(List<ProductSizeDTO> productSizeDTOs) {
        List<ProductSize> products = new ArrayList<>();
        for (ProductSizeDTO p : productSizeDTOs) {
            products.add(toEntity(p));
        }
        return products;
    }
}
