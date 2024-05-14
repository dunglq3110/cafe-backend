package com.example.service;

import com.example.dto.order.*;
import com.example.dto.receipt.ReceiptResponse;

public interface IOrderService {

    ReceiptResponse createNewOrder();
    ReceiptResponse updateCustomerReceipt(UpdateCustomerReceiptRequest updateCustomerReceiptRequest);
    ReceiptResponse addProductReceipt(AddProductReceiptRequest addProductReceiptRequest);
    ReceiptResponse addCondimentReceipt(AddCondimentReceiptRequest addCondimentReceiptRequest);
    ReceiptResponse updateProductReceipt(UpdateProductReceiptRequest updateProductReceiptRequest);
    ReceiptResponse updateCondimentReceipt(UpdateCondimentReceiptRequest updateCondimentReceiptRequest);
    ReceiptResponse deleteProductReceipt(Long id);
    ReceiptResponse deleteCondimentReceipt(Long id);
}