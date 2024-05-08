package com.example.api;

import com.example.dto.ApiResponse;
import com.example.dto.receipt.ReceiptResponse;
import com.example.entity.Receipt;
import com.example.service.IReceptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/receipt")
public class ReceiptAPI {
    @Autowired
    IReceptService receiptService;

    @GetMapping
    public ApiResponse<List<ReceiptResponse>> getAllReceipts() {
        List<ReceiptResponse> receiptResponses = receiptService.getAllReceipts();
        return ApiResponse.<List<ReceiptResponse>>builder()
                .result(receiptResponses)
                .code(200)
                .build();
    }   // get all Receipts

    @PostMapping
    public ResponseEntity<Receipt> placeReceipt() {
        return new ResponseEntity<>(receiptService.placeReceipt(), HttpStatus.CREATED);
    }   // This method place all Items from Cart to Receipt

    @PutMapping("/{receiptId}/customer")
    public ResponseEntity<Receipt> updateReceiptCustomer(@PathVariable Long receiptId,
                                                         @RequestParam String customerPhoneNumber) {
        receiptService.updateReceiptCustomer(receiptId, customerPhoneNumber);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{receiptId}/status")
    public ResponseEntity<Receipt> updateReceiptStatus(@PathVariable Long receiptId) {
        receiptService.updateReceiptStatus(receiptId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}