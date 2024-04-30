package com.example.entity;


import com.example.util.ProductStatus;
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
public class Condiment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     Long id;
     String name;
     String image;
     double unitPrice;
     ProductStatus productStatus;
    @OneToMany(mappedBy = "condiment")
     List<ProductCondimentDetail> productCondimentDetails;

}
