package com.example.entity;


import jakarta.persistence.*;

import java.util.LinkedList;
import java.util.List;

@Entity
public class ProductDetail {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "receipt_id")
    private Receipt receipt;

    @ManyToOne
    @JoinColumn(name = "product_size_id")
    private ProductSize productSize;

    private int productQuantity;
    private double productDiscount;
    private double productPrice;

    @OneToMany(mappedBy = "productDetail")
    private List<ProductCondimentDetail> productCondimentDetails;


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Receipt getReceipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    public ProductSize getProductSize() {
        return productSize;
    }

    public void setProductSize(ProductSize productSize) {
        this.productSize = productSize;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }


    public double getProductDiscount() {
        return productDiscount;
    }

    public void setProductDiscount(double productDiscount) {
        this.productDiscount = productDiscount;
    }

    public List<ProductCondimentDetail> getProductCondimentDetails() {
        return productCondimentDetails;
    }

    public void setProductCondimentDetails(List<ProductCondimentDetail> productCondimentDetails) {
        this.productCondimentDetails = productCondimentDetails;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
}
