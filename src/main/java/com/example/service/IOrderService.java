package com.example.service;

import com.example.dto.cart.OrderRequest;
import com.example.dto.cart.OrderResponse;

import java.util.List;

public interface IOrderService {

    void placeOrder(OrderRequest orderRequest);
    void removeItem(String index);
    double calculateTotal();
    List<OrderResponse> getCartItems();
    boolean cartExists(Long staffId);
    void createCart(Long staffId);
}