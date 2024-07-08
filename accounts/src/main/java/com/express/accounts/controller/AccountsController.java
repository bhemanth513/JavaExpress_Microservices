package com.express.accounts.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.express.accounts.dto.AccountWithCustomerDto;
import com.express.accounts.dto.CustomerDto;
import com.express.accounts.service.AccountsService;


@RestController
@RequestMapping("api/javaExpress")
public class AccountsController {

	private final Logger logger = LoggerFactory.getLogger(AccountsController.class);

	
	@Autowired
	AccountsService accountsService;
	
	@PostMapping("createAccount")
	public String createAccounts(@RequestBody CustomerDto customerDto){
		logger.info("AccountsController:: createAccounts {}", customerDto.getMobileNumber());
		accountsService.createAccounts(customerDto);
		return "Account Created Successfully!!";
	}
	
	@GetMapping("getAccount/{mobileNumber}")
	public AccountWithCustomerDto getAccountDetails(@PathVariable("mobileNumber") String mobileNumber) {
		logger.info("AccountsController:: getAccountDetails {}", mobileNumber);
		return accountsService.fetchAccountDetails(mobileNumber);
	}
	
	@PutMapping("updateAccount")
	public String updateAccountDetails(@RequestBody AccountWithCustomerDto accountWithCustomerDto) {
		logger.info("AccountsController:: updateAccountDetails() updated with given dto {}", accountWithCustomerDto);
		return accountsService.updateAccountDetails(accountWithCustomerDto);
	}
	
	@DeleteMapping("deleteAccount/{mobileNumber}")
	public String deleteAccountDetails(@PathVariable("mobileNumber") String mobileNumber) {
		logger.info("AccountsController:: deleteAccountDetails() deleted for the given mobileNumber {}", mobileNumber);
		return accountsService.deleteAccountDetails(mobileNumber);
	}
}
