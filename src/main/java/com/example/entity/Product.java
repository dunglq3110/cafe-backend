package com.example.entity;


import com.example.util.ProductStatus;
import com.example.util.ProductType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String image;
    private double discount;
    private ProductType productType;
    private ProductStatus productStatus;

    @OneToMany(mappedBy = "product")
    private LinkedList<ProductSize> productSizes = new LinkedList<>();



    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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

    public LinkedList<ProductSize> getProductSizes() {
        return productSizes;
    }

    public void setProductSizes(LinkedList<ProductSize> productSizes) {
        this.productSizes = productSizes;
    }


}
