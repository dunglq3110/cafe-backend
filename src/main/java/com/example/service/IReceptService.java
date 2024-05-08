package com.example.service;

import com.example.dto.receipt.ReceiptResponse;
import com.example.entity.Receipt;

import java.util.List;

public interface IReceptService {
    List<ReceiptResponse> getAllReceipts();
    Receipt placeReceipt();
    void updateReceiptCustomer(Long receiptId, String customerPhoneNumber);
    void updateReceiptStatus(Long receiptId);
}
