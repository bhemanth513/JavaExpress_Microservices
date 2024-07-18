package com.express.accounts.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CustomerDetailsDto {

	private String name;
	private String email;
	private String mobileNumber;

	AccountsDto accountsDto;
	CardDto cardDto;
	LoansDto loansDto;
}
