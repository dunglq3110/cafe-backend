package com.example.api;

import com.example.dto.ApiResponse;
import com.example.dto.cart.OrderRequest;
import com.example.dto.cart.OrderResponse;
import com.example.dto.staff.StaffResponse;
import com.example.exeption.ErrorCode;
import com.example.service.IAuthenticationService;
import com.example.service.IOrderService;
import com.example.service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/order")
public class OrderAPI {
    @Autowired
    IOrderService orderService;
    @Autowired
    IAuthenticationService authenticationService;
    @Autowired
    IStaffService staffService;

    @PostMapping
    public ApiResponse<Void> placeOrder(@RequestBody OrderRequest orderRequest) {

        StaffResponse staffResponse = staffService.getMyInfo();

        // Check if cart exists for the user
        if (!orderService.cartExists(staffResponse.getId())) {
            orderService.createCart(staffResponse.getId());
        }

        // Place orderItems
        orderService.placeOrder(orderRequest);
        return ApiResponse.<Void>builder()
                .result(null)
                .code(ErrorCode.ORDER_PLACED_SUCCESSFULLY.getCode())
                .message(ErrorCode.ORDER_PLACED_SUCCESSFULLY.getMessage())
                .build();
    }

    @GetMapping
    public ApiResponse<List<OrderResponse>> getOrder() {
        // Get orderItems
        List<OrderResponse> orderResponses = orderService.getCartItems();
        return ApiResponse.<List<OrderResponse>>builder()
                .code(ErrorCode.ORDER_PLACED_SUCCESSFULLY.getCode())
                .result(orderResponses)
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteOrder(@PathVariable Long id) {

        // Delete orderItems
        orderService.removeItem(String.valueOf(id));
        return ApiResponse.<Void>builder()
                .code(ErrorCode.ORDER_DELETED_SUCCESSFULLY.getCode())
                .message(ErrorCode.ORDER_DELETED_SUCCESSFULLY.getMessage())
                .build();
    }
}
