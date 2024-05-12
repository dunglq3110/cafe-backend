package com.example.service.impl;

import com.example.dto.codiment.CondimentDTO;
import com.example.dto.SizeDTO;
import com.example.dto.cart.*;
import com.example.dto.staff.StaffResponse;
import com.example.exeption.AppException;
import com.example.exeption.ErrorCode;
import com.example.service.ICondimentService;
import com.example.service.IOrderService;
import com.example.service.IProductService;
import com.example.service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService implements IOrderService {
    public final Map<Long, List<OrderResponse>> orderItemsPerCustomer = new HashMap<>();
    @Autowired
    IProductService productService;
    @Autowired
    ICondimentService condimentService;
    @Autowired
    IStaffService staffService;


    @Override
    public void placeOrder(OrderRequest orderRequest) {

    }

    @Override
    public void removeItem(String index) {

    }

    @Override
    public void clearCart() {

    }

    @Override
    public double calculateTotal() {
        return 0;
    }

    @Override
    public List<OrderResponse> getCartItems() {
        return null;
    }

    @Override
    public boolean cartExists(Long staffId) {
        return false;
    }

    @Override
    public void createCart(Long staffId) {

    }
}