package com.example.service;

import com.example.dto.CustomerDTO;
import com.example.entity.Customer;

import java.util.List;

public interface ICustomerService {

    CustomerDTO save(CustomerDTO customerDTO);
    List<CustomerDTO> findCustomersByPhoneNumberContains(String phoneNumber);
    List<CustomerDTO> findAll();
}
