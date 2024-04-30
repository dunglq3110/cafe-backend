package com.example.dto;

import com.example.entity.Size;
import com.example.util.ProductStatus;
import com.example.util.ProductType;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class ProductDTO {

    @JsonIgnore
    private Long id;
    private String name;
    private String image;
    private double discount;
    private ProductType productType;
    private ProductStatus productStatus;

    private List<SizeDTO> sizes;

    public List<SizeDTO> getSizes() {
        return sizes;
    }

    public void setSizes(List<SizeDTO> sizes) {
        this.sizes = sizes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public ProductStatus getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(ProductStatus productStatus) {
        this.productStatus = productStatus;
    }


}
