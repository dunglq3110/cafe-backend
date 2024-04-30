package com.example.dto;

import com.example.entity.Size;
import com.example.util.ProductStatus;
import com.example.util.ProductType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {

    @JsonIgnore
    private Long id;
    private String name;
    private String image;
    private double discount;
    private ProductType productType;
    private ProductStatus productStatus;
    private List<SizeDTO> sizes;
}
