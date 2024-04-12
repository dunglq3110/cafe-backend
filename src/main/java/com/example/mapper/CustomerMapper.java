package com.example.mapper;


import com.example.dto.CustomerDTO;
import com.example.entity.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerMapper {
    private final ModelMapper mapper = new ModelMapper();
    public CustomerDTO toDTO(Customer customer) {
        return mapper.map(customer, CustomerDTO.class);
    }
    public Customer toEntity(CustomerDTO customerDTO) {
        return mapper.map(customerDTO, Customer.class);
    }
    public List<CustomerDTO> toDTOs(List<Customer> customers) {
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        for (Customer customer : customers) {
            customerDTOS.add(toDTO(customer));
        }
        return customerDTOS;
    }
    public List<Customer> toEntities(List<CustomerDTO> customerDTOS) {
        List<Customer> customers = new ArrayList<>();
        for (CustomerDTO customerDTO : customerDTOS) {
            customers.add(toEntity(customerDTO));
        }
        return customers;
    }
}
