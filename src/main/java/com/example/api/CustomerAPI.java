package com.example.api;


import com.example.dto.CustomerDTO;
import com.example.service.impl.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerAPI {

    @Autowired
    private CustomerService customerService;

    @PostMapping
    private CustomerDTO createCustomer(@RequestBody CustomerDTO customerDTO)
    {
        return customerService.save(customerDTO);
    }

    @PutMapping(value = "/{id}")
    private CustomerDTO updateCustomer (@RequestBody CustomerDTO customerDTO, @PathVariable ("id") Long id) {
        customerDTO.setId(id);
        return customerService.save(customerDTO);
    }

    @GetMapping
    private List<CustomerDTO> findAll() {
        return customerService.findAll();
    }

}
