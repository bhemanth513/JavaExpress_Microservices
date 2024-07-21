package com.express.accounts.controller;

import com.express.accounts.dto.CustomerDetailsDto;
import com.express.accounts.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/javaExpress/customerdetails/")
public class CustomerController {

    private final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    CustomerService customerService;

    @GetMapping("fetchCustomerDetails")
    public CustomerDetailsDto fetchCustomerDetails(@RequestParam String mobileNumber){
        logger.info("CustomerController:: CustomerDetailsDto {}", mobileNumber);
        return customerService.fetchCustomerDetails(mobileNumber);
    }
}
