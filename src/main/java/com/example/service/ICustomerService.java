package com.example.service;

import com.example.dto.customer.CustomerRequest;
import com.example.dto.customer.CustomerResponse;
import com.example.entity.Customer;

import java.util.List;

public interface ICustomerService {

    CustomerResponse addCustomer(CustomerRequest customerRequest);
    CustomerResponse getCustomerById(Long id);

    List<CustomerResponse> getAll();

}
