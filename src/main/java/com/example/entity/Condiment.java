package com.example.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.LinkedList;

@Entity
public class Condiment {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String image;
    private double unitPrice;
    private double unitCost;

    @OneToMany(mappedBy = "condiment")
    private LinkedList<ProductDetail> productDetail = new LinkedList<>();




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

    public double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
    }

    public LinkedList<ProductDetail> getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(LinkedList<ProductDetail> productDetail) {
        this.productDetail = productDetail;
    }
}
