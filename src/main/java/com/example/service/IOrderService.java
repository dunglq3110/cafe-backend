package com.example.service;

import com.example.dto.order.AddCondimentReceiptRequest;
import com.example.dto.order.AddProductReceiptRequest;
import com.example.dto.receipt.ReceiptResponse;
import com.example.dto.order.UpdateCustomerReceiptRequest;

public interface IOrderService {

    ReceiptResponse createNewOrder();
    ReceiptResponse updateCustomerReceipt(UpdateCustomerReceiptRequest updateCustomerReceiptRequest);
    ReceiptResponse addProductReceipt(AddProductReceiptRequest addProductReceiptRequest);

    ReceiptResponse addCondimentReceipt(AddCondimentReceiptRequest addCondimentReceiptRequest);
}