package com.example.dto;

import com.example.util.ReceiptStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReceiptDTO {

    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime date;
    private double discount;
    private double totalPrice;
    private ReceiptStatus receiptStatus;
    private Long staffId;
    private Long customerId;
    private List<ProductDetailDTO> productDetails;
}
