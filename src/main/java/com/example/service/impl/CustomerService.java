package com.example.service.impl;

import com.example.dto.CustomerDTO;
import com.example.entity.Customer;
import com.example.mapper.CustomerMapper;
import com.example.repository.CustomerRepository;
import com.example.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService implements ICustomerService {

    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerDTO save(CustomerDTO customerDTO) {
        Customer customer = customerMapper.toEntity(customerDTO);
        customer = customerRepository.save(customer);
        return customerMapper.toDTO(customer);
    }

    @Override
    public List<CustomerDTO> findCustomersByPhoneNumberContains(String phoneNumber) {

        return customerMapper.toDTOs(customerRepository.findCustomersByPhoneNumberContains(phoneNumber));
    }

    @Override
    public List<CustomerDTO> findAll() {
        return customerMapper.toDTOs(customerRepository.findAll());
    }
}
