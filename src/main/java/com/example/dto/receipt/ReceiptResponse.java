package com.example.dto.receipt;

import com.example.dto.cart.OrderResponse;
import com.example.util.ReceiptStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class ReceiptResponse {

    Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    Date date;
    double discount;
    double totalPrice;
    ReceiptStatus receiptStatus;
    Long StaffId;
    Long CustomerId;
    List<OrderResponse> ReceiptItems;
}

