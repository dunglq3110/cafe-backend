package com.example.dto.codiment;

import com.example.util.ProductStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CondimentDTO {

    private Long id;
    private String name;
    private double unitPrice;
    private ProductStatus productStatus;
}
