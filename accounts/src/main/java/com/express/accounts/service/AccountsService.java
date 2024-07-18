package com.express.accounts.service;

import com.express.accounts.dto.CustomerDetailsDto;
import com.express.accounts.dto.CustomerDto;
import com.express.accounts.model.Accounts;

public interface AccountsService {

	Accounts createAccounts(CustomerDto customerDto);

	CustomerDetailsDto fetchAccountDetails(String mobileNumber);

	String updateAccountDetails(CustomerDetailsDto accountWithCustomerDto);

	String deleteAccountDetails(String mobileNumbe);

}
