package com.example.api;

import com.example.dto.ApiResponse;
import com.example.dto.customer.CustomerRequest;
import com.example.entity.Customer;
import com.example.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer")
public class CustomerAPI {
    @Autowired
    ICustomerService customerService;

    @PostMapping
    public Customer addCustomer(@RequestBody CustomerRequest customerRequest) {
        return customerService.addCustomer(customerRequest);
    }

    @GetMapping({"/{id}"})
    public ApiResponse<Customer> getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);
        if (customer != null) {
            return ApiResponse.<Customer>builder()
                    .code(200)
                    .result(customer)
                    .message("Get customer successfully")
                    .build();
        } else {
            return ApiResponse.<Customer>builder()
                    .code(404)
                    .message("Customer not found")
                    .build();
        }
    }
}
