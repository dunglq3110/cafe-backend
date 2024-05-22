package com.example.dto.codiment;

import com.example.util.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CondimentCreateRequest {

    String name;
    double unitPrice;
    String image;
    ProductStatus productStatus;
}
