package com.express.accounts.feignClient;

import com.express.accounts.dto.LoansDto;
import org.springframework.stereotype.Component;

@Component
public class LoansFallback implements LoansFeignClient{
    @Override
    public LoansDto getLoans(String mobileNumber) {
        LoansDto loansDto = new LoansDto();
        loansDto.setStatus("Please try again after sometime!!");
        return loansDto;
    }
}
