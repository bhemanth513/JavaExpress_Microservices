package com.express.accounts.service;

import com.express.accounts.dto.*;
import com.express.accounts.exception.ResourceNotFoundException;
import com.express.accounts.feignClient.CardsFeignClient;
import com.express.accounts.feignClient.LoansFeignClient;
import com.express.accounts.model.Accounts;
import com.express.accounts.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.express.accounts.exception.CustomerAlreadyExistException;
import com.express.accounts.model.Customer;
import com.express.accounts.repository.CustomerRepository;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

	public final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);


	private CustomerRepository customerRepository;
	private AccountRepository accountRepository;
	private CardsFeignClient cardsFeignClient;
	private LoansFeignClient loansFeignClient;


	@Override
	public Customer createCustomer(CustomerDto customerDto) {
		logger.info("CustomerServiceImpl:: createCustomer started {}", customerDto);
		Customer customer = new Customer(); 
		 BeanUtils.copyProperties(customerDto, customer);
		 if(customerRepository.findByMobileNumber(customerDto.getMobileNumber()).isPresent()) {
			 throw new CustomerAlreadyExistException("Customer Already Registered with given mobile number {}"+ customerDto.getMobileNumber());
		 }
		 Customer customerEntity = customerRepository.save(customer);
		logger.info("CustomerServiceImpl:: createCustomer completed{}", customerDto);
		 return customerEntity;
	}

	@Override
	public CustomerDetailsDto fetchCustomerDetails(String mobileNumber) {
		logger.info("fetchCustomerDetails:: CustomerDetailsDto started {}", mobileNumber);
		Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(()->
				new ResourceNotFoundException("Customer not exist with given Mobile number "+mobileNumber));

		Accounts accounts = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(()->
				new ResourceNotFoundException("No Account exist with given customer id "+customer.getCustomerId()));

		CardDto  cardDto = cardsFeignClient.getCardDetails(mobileNumber);
		LoansDto loansDto = loansFeignClient.getLoans(mobileNumber);

		CustomerDetailsDto customerDetailsDto = new CustomerDetailsDto();

		BeanUtils.copyProperties(customer,customerDetailsDto);

		AccountsDto accountsDto = new AccountsDto();
		BeanUtils.copyProperties(accounts,accountsDto);
		customerDetailsDto.setAccountsDto(accountsDto);
		if(cardDto!=null){
			customerDetailsDto.setCardDto(cardDto);
		}
		if(loansDto!=null){
			customerDetailsDto.setLoansDto(loansDto);
		}
		return customerDetailsDto;
	}

}
