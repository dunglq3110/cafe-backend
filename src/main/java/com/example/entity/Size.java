package com.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.LinkedList;

@Entity
public class Size {
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    @OneToMany(mappedBy = "size")
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

    public LinkedList<ProductSize> getProductSizes() {
        return productSizes;
    }

    public void setProductSizes(LinkedList<ProductSize> productSizes) {
        this.productSizes = productSizes;
    }
}
