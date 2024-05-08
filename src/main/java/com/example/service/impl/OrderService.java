package com.example.service.impl;

import com.example.dto.CondimentDTO;
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
    public List<OrderResponse> getCartItems() {
        StaffResponse staffResponse = staffService.getMyInfo();
        return orderItemsPerCustomer.getOrDefault(staffResponse.getId(), Collections.emptyList());
    }
    @Override
    public boolean cartExists(Long staffId) {
        return orderItemsPerCustomer.containsKey(staffId);
    }
    @Override
    public void createCart(Long staffId) {
        orderItemsPerCustomer.putIfAbsent(staffId, new ArrayList<>());
    }

    public void placeOrder(OrderRequest orderRequest) {
        StaffResponse staffResponse = staffService.getMyInfo();

        // Check if productCondimentDetails is not null before getting condiment
        List<CondimentRequest> condimentRequest = Optional.ofNullable(orderRequest.getProductCondimentDetails())
                .map(CondimentDetailsRequest::getCondiment)
                .orElse(Collections.emptyList());

        //Check Product is valid
        ProductRequest productRequest = orderRequest.getProduct();
        productService.findAll().stream()
                .filter(product -> product.getId() == productRequest.getId())
                .findFirst()
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        //Check if size exists
        SizeDTO reqSize = Optional.ofNullable(productService.checkProductSize(productRequest))
                .orElseThrow(() -> new AppException(ErrorCode.SIZE_NOT_FOUND));

        // Check if all condiments in the request exist
        List<CondimentDTO> allCondiments = condimentService.findAll();
        condimentRequest.forEach(request -> {
            allCondiments.stream()
                    .filter(condiment -> condiment.getId() == request.getId())
                    .findFirst()
                    .orElseThrow(() -> new AppException(ErrorCode.CONDIMENTS_NOT_FOUND));
        });

        //Map orderRequest to orderResponse
        OrderResponse orderResponse = new OrderResponse();
        ProductResponse productResponse = productService.findById(productRequest.getId());
        productResponse.setId(productRequest.getId());
        productResponse.setSize(reqSize);
        orderResponse.setProduct(productResponse);
        orderResponse.setQuantity(orderRequest.getQuantity());

        CondimentDetailsResponse condimentDetailsResponse = new CondimentDetailsResponse();
        List<ProductDetailResponse> productDetailResponses = condimentRequest.stream()
                .map(condiment -> {
                    ProductDetailResponse productDetailResponse = new ProductDetailResponse();
                    CondimentDTO condimentDTO = condimentService.findById(condiment.getId());
                    productDetailResponse.setCondiment(condimentDTO);
                    productDetailResponse.setQuantity(condiment.getQuantity());
                    return productDetailResponse;
                })
                .collect(Collectors.toList());

        condimentDetailsResponse.setCondiments(productDetailResponses);
        orderResponse.setProductCondimentDetails(condimentDetailsResponse);

        //Place Order to Staff's cart
        orderItemsPerCustomer.computeIfAbsent(staffResponse.getId(), k -> new ArrayList<>()).add(orderResponse);
    }

    @Override
    public void removeItem(String index) {
        StaffResponse staffResponse = staffService.getMyInfo();

        List<OrderResponse> orderItems = orderItemsPerCustomer.get(staffResponse.getId());

        if (orderItems == null || orderItems.isEmpty()) {
            throw new AppException(ErrorCode.CART_IS_EMPTY);
        }

        int idx = Integer.parseInt(index) - 1; // Convert to zero-based index
        if (idx >= 0 && idx < orderItems.size()) {
            orderItems.remove(idx);
        } else {
            throw new AppException(ErrorCode.INVALID_INDEX);
        }
    }
    @Override
    public void clearCart() {
        StaffResponse staffResponse = staffService.getMyInfo();
        List<OrderResponse> orderItems = orderItemsPerCustomer.get(staffResponse.getId());

        if (orderItems != null) {
            orderItems.clear();
        }
    }

    @Override
    public double calculateTotal() {
        StaffResponse staffResponse = staffService.getMyInfo();
        List<OrderResponse> orderItems = orderItemsPerCustomer.get(staffResponse.getId());

        if (orderItems == null) { return 0; }
        return orderItems.stream().mapToDouble(
                item -> (1 - item.getProduct().getDiscount())
                        * item.getProduct().getSize().getPrice()
                        * item.getQuantity()).sum() + calculateCondimentTotal(orderItems);
    }

    public double calculateCondimentTotal(List<OrderResponse> orderItems) {
        double total = 0;
        for (OrderResponse orderResponse : orderItems) {
            for (ProductDetailResponse productDetailResponse : orderResponse.getProductCondimentDetails().getCondiments()) {
                total += productDetailResponse.getCondiment().getUnitPrice() * productDetailResponse.getQuantity();
            }
        }
        return total;
    }
}