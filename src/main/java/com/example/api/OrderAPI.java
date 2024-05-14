package com.example.api;

import com.example.dto.ApiResponse;
import com.example.dto.order.AddCondimentReceiptRequest;
import com.example.dto.order.AddProductReceiptRequest;
import com.example.dto.receipt.ReceiptResponse;
import com.example.dto.order.UpdateCustomerReceiptRequest;
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

    @PostMapping(value = "/update-customer-receipt")
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



}
