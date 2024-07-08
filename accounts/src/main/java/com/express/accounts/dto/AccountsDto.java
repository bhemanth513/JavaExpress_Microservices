package com.express.accounts.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AccountsDto {
	
	private Long accountNumber;
	private Long customerId;
	private String accountType;
	private String branchAddress;
	
}
