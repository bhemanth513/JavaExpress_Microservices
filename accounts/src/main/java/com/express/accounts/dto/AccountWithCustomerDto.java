package com.express.accounts.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountWithCustomerDto {

	private String name;
	private String email;
	private String mobileNumber;

	AccountsDto accountsDto; 
}
