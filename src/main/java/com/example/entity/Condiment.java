package com.example.entity;


import com.example.util.ProductStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.LinkedList;
import java.util.List;

@Entity
public class Condiment {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String image;
    private double unitPrice;

    private ProductStatus productStatus;

    @OneToMany(mappedBy = "condiment")
    private List<ProductCondimentDetail> productCondimentDetails;

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

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public ProductStatus getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(ProductStatus productStatus) {
        this.productStatus = productStatus;
    }

    public List<ProductCondimentDetail> getProductCondimentDetails() {
        return productCondimentDetails;
    }

    public void setProductCondimentDetails(List<ProductCondimentDetail> productCondimentDetails) {
        this.productCondimentDetails = productCondimentDetails;
    }
}
