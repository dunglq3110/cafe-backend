package com.example.mapper;

import com.example.dto.receipt.ReceiptResponse;
import com.example.entity.Receipt;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
@Component
public class ReceiptMapper {


    private final ModelMapper mapper = new ModelMapper();

    public ReceiptResponse toResponse(Receipt receipt) {
        return mapper.map(receipt, ReceiptResponse.class);
    }


}