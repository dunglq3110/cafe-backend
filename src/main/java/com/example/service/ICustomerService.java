package com.example.service;

import com.example.dto.customer.CustomerRequest;
import com.example.entity.Customer;

public interface ICustomerService {

    Customer addCustomer(CustomerRequest customerRequest);
    Customer getCustomerById(Long id);
}
