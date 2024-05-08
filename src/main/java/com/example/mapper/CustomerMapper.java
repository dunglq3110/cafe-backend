package com.example.mapper;

import com.example.dto.customer.CustomerRequest;
import com.example.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer toCustomer(CustomerRequest customerRequest){
        Customer customer = new Customer();
        customer.setFirstName(customerRequest.getFirstName());
        customer.setLastName(customerRequest.getLastName());
        customer.setDateOfBirth(customerRequest.getDateOfBirth());
        customer.setGender(customerRequest.getGender());
        customer.setPhoneNumber(customerRequest.getPhoneNumber());

        return customer;
    }
}
