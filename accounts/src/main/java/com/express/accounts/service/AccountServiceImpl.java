package com.express.accounts.service;

import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.express.accounts.dto.AccountWithCustomerDto;
import com.express.accounts.dto.AccountsDto;
import com.express.accounts.dto.CustomerDto;
import com.express.accounts.exception.ResourceNotFoundException;
import com.express.accounts.model.Accounts;
import com.express.accounts.model.Customer;
import com.express.accounts.repository.AccountRepository;
import com.express.accounts.repository.CustomerRepository;

@Service
public class AccountServiceImpl implements AccountsService {
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public Accounts createAccounts(CustomerDto customerDto) {
		//customer information in db
		Customer customerEntity = customerService.createCustomer(customerDto);
		
		//account in db
		 Accounts accounts = new Accounts();
		 accounts.setCustomerId(customerEntity.getCustomerId());
		 accounts.setAccountType("SAVINGS");
		 accounts.setAccountNumber(100000L + new Random().nextInt(99999));
		 accounts.setBranchAddress("Hyderabad");
		 accountRepository.save(accounts);
		 return accounts;
	}

	@Override
	public AccountWithCustomerDto fetchAccountDetails(String mobileNumber) {
		//find mobileNumber exist or not
		Customer customer = customerRepository.findByMobileNumber(mobileNumber)
		.orElseThrow(()-> new ResourceNotFoundException("Mobile number not fund "+ mobileNumber));
		
		//find customer exist or not
		Accounts accounts = accountRepository.findByCustomerId(customer.getCustomerId())
				.orElseThrow(()-> new ResourceNotFoundException("Customer Not Found for Mobile number"+ customer.getMobileNumber()));
		AccountWithCustomerDto customerDetailsDto = new AccountWithCustomerDto();
		BeanUtils.copyProperties(customer, customerDetailsDto);
		
		AccountsDto accountsDto = new AccountsDto();
		BeanUtils.copyProperties(accounts, accountsDto);
		customerDetailsDto.setAccountsDto(accountsDto);
		return customerDetailsDto; 
	}

	@Override
	public String updateAccountDetails(AccountWithCustomerDto accountWithCustomerDto) {
		
		if(accountWithCustomerDto!=null) {
	
			Accounts accounts = accountRepository.findById(accountWithCustomerDto.getAccountsDto().getAccountNumber())
					.orElseThrow(()-> new ResourceNotFoundException("Account Not found "+ accountWithCustomerDto.getAccountsDto().getAccountNumber()));
	
			BeanUtils.copyProperties(accountWithCustomerDto.getAccountsDto(),accounts);
			accounts = accountRepository.save(accounts);
			
			Long customerId = accounts.getCustomerId();
			Customer customer = customerRepository.findById(customerId)
					.orElseThrow(()-> new ResourceNotFoundException("Customer not fund "+ accountWithCustomerDto.getMobileNumber()));;
			BeanUtils.copyProperties(accountWithCustomerDto,customer);
			customerRepository.save(customer);
			
			return "Accounts updated succefully!!";
		}else {
			throw new RuntimeException("Account details are missing!!");
		}		
	}

	@Override
	public String deleteAccountDetails(String mobileNumber) {
		
		Customer customer = customerRepository.findByMobileNumber(mobileNumber)
				.orElseThrow(()-> new ResourceNotFoundException("Mobile number not found "+ mobileNumber));
	
//		Accounts accounts = accountRepository.findByCustomerId(customer.getCustomerId())
//				.orElseThrow(()-> new ResourceNotFoundException("Customer Not Found for Mobile number"+ customer.getMobileNumber()));
		
		//accountRepository.deleteByCustomerId(accounts.getCustomerId());
		customerRepository.deleteById(customer.getCustomerId());
		
		return "account deleted successfully!!";
	}

}
