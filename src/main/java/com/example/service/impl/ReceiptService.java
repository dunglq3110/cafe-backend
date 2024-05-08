package com.example.service.impl;

import com.example.dto.ReceiptDTO;
import com.example.dto.cart.OrderResponse;
import com.example.dto.receipt.ReceiptResponse;
import com.example.dto.staff.StaffResponse;
import com.example.entity.Customer;
import com.example.entity.ProductDetail;
import com.example.entity.Receipt;
import com.example.mapper.ProductDetailMapper;
import com.example.mapper.ReceiptMapper;
import com.example.repository.CustomerRepository;
import com.example.repository.ReceiptRepository;
import com.example.repository.StaffRepository;
import com.example.service.ICustomerService;
import com.example.service.IOrderService;
import com.example.service.IReceptService;
import com.example.service.IStaffService;
import com.example.util.ReceiptStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReceiptService implements IReceptService {

    @Autowired
    ReceiptRepository receiptRepository;
    @Autowired
    IStaffService staffService;
    @Autowired
    ICustomerService customerService;
    @Autowired
    IOrderService orderService;
    @Autowired
    ReceiptMapper receiptMapper;
    @Autowired
    ProductDetailMapper productDetailMapper;
    @Autowired
    StaffRepository staffRepository;
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<ReceiptResponse> getAllReceipts() {
        List<Receipt> receipts = receiptRepository.findAll();
        return receipts.stream()
                .map(receiptMapper::mapReceiptToReceiptResponse)
                .collect(Collectors.toList());
    }

    @Override
    public Receipt placeReceipt() {
        StaffResponse staffResponse = staffService.getMyInfo();

        //Check if Cart exists
        if (!orderService.cartExists(staffResponse.getId())) {
            orderService.createCart(staffResponse.getId());
        }

        //Initiation a new ReceiptDTO {calculate totalPrice}
        ReceiptDTO receiptDTO = createReceipt(staffResponse);
        List<OrderResponse> orderResponses = orderService.getCartItems();
        receiptDTO.setTotalPrice(orderService.calculateTotal());

        //Fetch the existing Receipt from the database
        Receipt receipt = fetchAndUpdateReceipt(receiptDTO);
        mapOrderResponsesToProductDetails(receiptDTO, orderResponses, receipt);
        receipt.setTotalPrice(receiptDTO.getTotalPrice());

        //Save the updated Receipt in the database
        orderService.clearCart();
        receiptRepository.save(receipt);

        return receipt;
    }

    private ReceiptDTO createReceipt(StaffResponse staffResponse) {

        ReceiptDTO receiptDTO = new ReceiptDTO();
        receiptDTO.setStaffId(staffResponse.getId());
        receiptDTO.setDate(new Date());
        receiptDTO.setReceiptStatus(ReceiptStatus.PROGRESS);
        receiptDTO.setCustomerId(null);

        //Map ReceiptDTO to Receipt and save
        Receipt receipt = receiptMapper.mapReceiptDTOToReceipt(receiptDTO);
        receiptRepository.save(receipt);
        receiptDTO.setId(receipt.getId());

        return receiptDTO;
    }

    private Receipt fetchAndUpdateReceipt(ReceiptDTO receiptDTO) {
        Receipt receipt = receiptRepository.findById(receiptDTO.getId()).orElseThrow(()
                -> new RuntimeException("Receipt not found"));
        receipt.setStaff(staffRepository.getStaffById(receiptDTO.getStaffId()));
        return receiptRepository.save(receipt);
    }

    private void mapOrderResponsesToProductDetails(ReceiptDTO receiptDTO, List<OrderResponse> orderResponses, Receipt receipt) {
        receiptDTO.setProductDetails(productDetailMapper.mapOrderResponseToProductDetailDTO(orderResponses));
        List<ProductDetail> productDetails = receiptDTO.getProductDetails().stream()
                .map(productDetailMapper::toEntity)
                .collect(Collectors.toList());

        productDetails.forEach(productDetail -> productDetail.setReceipt(receipt));
        receipt.getProductDetails().addAll(productDetails);
    }
    @Override
    public void updateReceiptCustomer(Long receiptId, String customerPhoneNumber) {
        //get Customer
        List<Customer> customers = customerRepository.findCustomerByPhoneNumber(customerPhoneNumber);
        if (customers.isEmpty()) {
            throw new RuntimeException("No customer found with phone number: " + customerPhoneNumber);
        }
        Customer customer = customers.get(0);

        //get Receipt
        Receipt receipt = receiptRepository.findById(receiptId).orElseThrow(()
                -> new RuntimeException("Receipt not found with id: " + receiptId));
        receipt.setCustomer(customer);
        customer.setReceipts(Collections.singletonList(receipt));

        receiptRepository.save(receipt);
    }
    @Override
    public void updateReceiptStatus(Long receiptId) {
        Receipt receipt = receiptRepository.findById(receiptId).get();
        receipt.setReceiptStatus(ReceiptStatus.FINISHED);
        receiptRepository.save(receipt);
    }
}
