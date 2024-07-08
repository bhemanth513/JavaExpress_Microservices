package com.express.accounts.service;

import com.express.accounts.dto.AccountWithCustomerDto;
import com.express.accounts.dto.CustomerDto;
import com.express.accounts.model.Accounts;

public interface AccountsService {

	Accounts createAccounts(CustomerDto customerDto);

	AccountWithCustomerDto fetchAccountDetails(String mobileNumber);

	String updateAccountDetails(AccountWithCustomerDto accountWithCustomerDto);

	String deleteAccountDetails(String mobileNumbe);

}
