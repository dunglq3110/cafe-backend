package com.example.dto.cart;

import com.example.dto.CondimentDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CondimentDetailsResponse {

    private List<ProductDetailResponse> condiments;
}
