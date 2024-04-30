package com.example.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.LinkedList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @ManyToOne
    @JoinColumn(name = "receipt_id")
    Receipt receipt;
    @ManyToOne
    @JoinColumn(name = "product_size_id")
    ProductSize productSize;
    int productQuantity;
    double productDiscount;
    double productPrice;
    @OneToMany(mappedBy = "productDetail")
    List<ProductCondimentDetail> productCondimentDetails;


}
