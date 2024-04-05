package com.example.entity;


import jakarta.persistence.*;

import java.util.LinkedList;

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

    @OneToMany(mappedBy = "productDetail")
    private LinkedList<ProductCondimentDetail> productCondimentDetails = new LinkedList<>();

    private int productQuantity;
    private int condimentQuantity;

    private double productDiscount;

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

    public int getCondimentQuantity() {
        return condimentQuantity;
    }

    public void setCondimentQuantity(int condimentQuantity) {
        this.condimentQuantity = condimentQuantity;
    }

    public double getProductDiscount() {
        return productDiscount;
    }

    public void setProductDiscount(double productDiscount) {
        this.productDiscount = productDiscount;
    }

    public LinkedList<ProductCondimentDetail> getProductCondimentDetails() {
        return productCondimentDetails;
    }

    public void setProductCondimentDetails(LinkedList<ProductCondimentDetail> productCondimentDetails) {
        this.productCondimentDetails = productCondimentDetails;
    }
}
