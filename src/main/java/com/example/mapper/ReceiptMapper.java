package com.example.mapper;

import com.example.dto.CondimentDTO;
import com.example.dto.ProductDetailDTO;
import com.example.dto.ReceiptDTO;
import com.example.dto.SizeDTO;
import com.example.dto.cart.CondimentDetailsResponse;
import com.example.dto.cart.OrderResponse;
import com.example.dto.cart.ProductDetailResponse;
import com.example.dto.cart.ProductResponse;
import com.example.dto.receipt.ReceiptResponse;
import com.example.entity.ProductDetail;
import com.example.entity.Receipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReceiptMapper {
    @Autowired
    ProductDetailMapper productDetailMapper;

    public Receipt mapReceiptDTOToReceipt(ReceiptDTO receiptDTO){
        Receipt receipt = new Receipt();
        receipt.setId(receiptDTO.getId());
        receipt.setDate(receiptDTO.getDate());
        receipt.setDiscount(receiptDTO.getDiscount());
        receipt.setTotalPrice(receiptDTO.getTotalPrice());
        receipt.setReceiptStatus(receiptDTO.getReceiptStatus());

        //set list product
        List<ProductDetailDTO> productDetailDTOs = receiptDTO.getProductDetails();
        if (productDetailDTOs == null) {
            productDetailDTOs = new ArrayList<>();
        }
        List<ProductDetail> productDetails = productDetailDTOs.stream()
                .map(productDetailMapper::toEntity)
                .collect(Collectors.toList());
        receipt.setProductDetails(productDetails);
        return receipt;
    }
    public ReceiptResponse mapReceiptToReceiptResponse(Receipt receipt){
        ReceiptResponse receiptResponse = new ReceiptResponse();
        receiptResponse.setId(receipt.getId());
        receiptResponse.setDate(receipt.getDate());
        receiptResponse.setDiscount(receipt.getDiscount());
        receiptResponse.setTotalPrice(receipt.getTotalPrice());
        receiptResponse.setReceiptStatus(receipt.getReceiptStatus());
        receiptResponse.setStaffId(receipt.getStaff().getId());
        if (receipt.getCustomer() != null) {
            receiptResponse.setCustomerId(receipt.getCustomer().getId());
        } else {
            receiptResponse.setCustomerId(null);
        }

        //map product
        List<OrderResponse> orderResponses = receipt.getProductDetails().stream().map(productDetail -> {
            OrderResponse orderResponse = new OrderResponse();
            ProductResponse productResponse = new ProductResponse();
            productResponse.setId(productDetail.getProductSize().getProduct().getId());
            productResponse.setName(productDetail.getProductSize().getProduct().getName());
            productResponse.setDiscount(productDetail.getProductDiscount());
            productResponse.setProductType(productDetail.getProductSize().getProduct().getProductType());
            productResponse.setProductStatus(productDetail.getProductSize().getProduct().getProductStatus());
            productResponse.setSize(new SizeDTO(
                    productDetail.getProductSize().getSize().getId(),
                    productDetail.getProductSize().getSize().getName(),
                    productDetail.getProductSize().getPrice()
            ));
            orderResponse.setProduct(productResponse);
            orderResponse.setQuantity(productDetail.getProductQuantity());

            CondimentDetailsResponse condimentDetailsResponse = new CondimentDetailsResponse();
            condimentDetailsResponse.setCondiments(productDetail.getProductCondimentDetails().stream().map(productCondimentDetail -> {
                ProductDetailResponse productDetailResponse = new ProductDetailResponse();
                CondimentDTO condimentDTO = new CondimentDTO();
                condimentDTO.setId(productCondimentDetail.getCondiment().getId());
                condimentDTO.setName(productCondimentDetail.getCondiment().getName());
                condimentDTO.setUnitPrice(productCondimentDetail.getCondiment().getUnitPrice());
                condimentDTO.setProductStatus(productCondimentDetail.getCondiment().getProductStatus());
                productDetailResponse.setCondiment(condimentDTO);
                productDetailResponse.setQuantity(productCondimentDetail.getQuantity());
                return productDetailResponse;
            }).collect(Collectors.toList()));
            orderResponse.setProductCondimentDetails(condimentDetailsResponse);
            return orderResponse;
        }).collect(Collectors.toList());
        receiptResponse.setReceiptItems(orderResponses);

        return receiptResponse;
    }
}