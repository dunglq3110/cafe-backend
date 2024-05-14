package com.example.api;

import com.example.dto.ApiResponse;
import com.example.dto.order.*;
import com.example.dto.receipt.ReceiptResponse;
import com.example.service.IOrderService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderAPI {

    IOrderService orderService;

    @PostMapping(value = "/new-order")
    public ApiResponse<ReceiptResponse> createNewOrder() {
        return ApiResponse.<ReceiptResponse>builder()
                .result(orderService.createNewOrder())
                .build();
    }

    @PutMapping(value = "/update-customer-receipt")
    public ApiResponse<ReceiptResponse> updateCustomerReceipt(@RequestBody UpdateCustomerReceiptRequest updateCustomerReceiptRequest) {
        return ApiResponse.<ReceiptResponse>builder()
                .result(orderService.updateCustomerReceipt(updateCustomerReceiptRequest))
                .build();
    }

    @PostMapping(value = "/add-product-receipt")
    public ApiResponse<ReceiptResponse> addProductReceipt(@RequestBody AddProductReceiptRequest addProductReceiptRequest) {
        return ApiResponse.<ReceiptResponse>builder()
                .result(orderService.addProductReceipt(addProductReceiptRequest))
                .build();
    }

    @PostMapping(value = "/add-condiment-receipt")
    public ApiResponse<ReceiptResponse> addCondimentReceipt(@RequestBody AddCondimentReceiptRequest addCondimentReceiptRequest) {
        return ApiResponse.<ReceiptResponse>builder()
                .result(orderService.addCondimentReceipt(addCondimentReceiptRequest))
                .build();
    }

    @PutMapping(value = "/update-product-receipt")
    public ApiResponse<ReceiptResponse> updateProductReceipt(@RequestBody UpdateProductReceiptRequest updateProductReceiptRequest) {
        return ApiResponse.<ReceiptResponse>builder()
                .result(orderService.updateProductReceipt(updateProductReceiptRequest))
                .build();
    }

    @PutMapping(value = "/update-condiment-receipt")
    public ApiResponse<ReceiptResponse> updateCondimentReceipt(@RequestBody UpdateCondimentReceiptRequest updateCondimentReceiptRequest) {
        return ApiResponse.<ReceiptResponse>builder()
                .result(orderService.updateCondimentReceipt(updateCondimentReceiptRequest))
                .build();
    }

    @DeleteMapping(value = "/delete-product-receipt/{id}")
    public ApiResponse<ReceiptResponse> deleteProductReceipt(@PathVariable("id") Long id) {
        return ApiResponse.<ReceiptResponse>builder()
                .result(orderService.deleteProductReceipt(id))
                .build();
    }

    @DeleteMapping(value = "/delete-condiment-receipt/{id}")
    public ApiResponse<ReceiptResponse> deleteCondimentReceipt(@PathVariable("id") Long id) {
        return ApiResponse.<ReceiptResponse>builder()
                .result(orderService.deleteCondimentReceipt(id))
                .build();
    }










}
