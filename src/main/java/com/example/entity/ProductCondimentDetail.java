package com.example.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductCondimentDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    int quantity;
    double condimentPrice;
    @ManyToOne
    @JoinColumn(name = "condiment_id")
    Condiment condiment;
    @ManyToOne
    @JoinColumn(name = "product_detail_id")
    ProductDetail productDetail;


}
