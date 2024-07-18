package com.express.accounts.controller;

import com.express.accounts.dto.CustomerDetailsDto;
import com.express.accounts.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/javaExpress/customerdetails/")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("fetchCustomerDetails")
    public CustomerDetailsDto fetchCustomerDetails(@RequestParam String mobileNumber){
        return customerService.fetchCustomerDetails(mobileNumber);
    }
}
