package com.example.entity;


import jakarta.persistence.*;

import java.util.Date;
import java.util.LinkedList;

@Entity
public class Receipt {

    @Id
    @GeneratedValue
    private Long id;

    private Date date;

    private double discount;
    private double totalPrice;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "receipt")
    private LinkedList<ProductDetail> productDetails = new LinkedList<>();

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
