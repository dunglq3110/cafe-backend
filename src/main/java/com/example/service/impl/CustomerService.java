package com.example.service.impl;

import com.example.dto.customer.CustomerRequest;
import com.example.dto.customer.CustomerResponse;
import com.example.entity.Customer;
import com.example.exeption.AppException;
import com.example.exeption.ErrorCode;
import com.example.mapper.CustomerMapper;
import com.example.repository.CustomerRepository;
import com.example.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomerService implements ICustomerService {

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerMapper customerMapper;
    @Override
    public CustomerResponse addCustomer(CustomerRequest customerRequest){
        Customer customer = customerMapper.toCustomer(customerRequest);
        customerRepository.save(customer);
        return customerMapper.toResponse(customer);
    }
    @Override
    public CustomerResponse getCustomerById(Long id){
        return customerMapper.toResponse(customerRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOTFOUND)));
    }

    @Override
    public List<CustomerResponse> getAll() {
        return customerMapper.toResponse(customerRepository.findAll());
    }
}
