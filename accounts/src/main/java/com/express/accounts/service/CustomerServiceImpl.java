package com.express.accounts.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.express.accounts.dto.CustomerDto;
import com.express.accounts.exception.CustomerAlreadyExistException;
import com.express.accounts.model.Customer;
import com.express.accounts.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {


	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	public Customer createCustomer(CustomerDto customerDto) {
		Customer customer = new Customer(); 
		 BeanUtils.copyProperties(customerDto, customer);
		 if(customerRepository.findByMobileNumber(customerDto.getMobileNumber()).isPresent()) {
			 throw new CustomerAlreadyExistException("Customer Already Registered with given mobile number {}"+ customerDto.getMobileNumber());
		 }
		 Customer customerEntity = customerRepository.save(customer);
		 return customerEntity;
	}
	
}
