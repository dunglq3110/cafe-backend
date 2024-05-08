package com.example.service.impl;

import com.example.dto.customer.CustomerRequest;
import com.example.entity.Customer;
import com.example.mapper.CustomerMapper;
import com.example.repository.CustomerRepository;
import com.example.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CustomerService implements ICustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerMapper customerMapper;
    @Override
    public Customer addCustomer(CustomerRequest customerRequest){
        Customer customer = customerMapper.toCustomer(customerRequest);
        return customerRepository.save(customer);
    }
    @Override
    public Customer getCustomerById(Long id){
        return customerRepository.findById(id).orElse(null);
    }
}
