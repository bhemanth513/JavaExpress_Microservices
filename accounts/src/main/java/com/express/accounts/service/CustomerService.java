package com.express.accounts.service;

import com.express.accounts.dto.CustomerDto;
import com.express.accounts.model.Customer;

public interface CustomerService {

	Customer createCustomer(CustomerDto customerDto);
	//String getCustomerDetails(String mobileNumebr);
}
