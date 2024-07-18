package com.express.accounts.feignClient;

import com.express.accounts.dto.LoansDto;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "Loans")
@LoadBalancerClient("Loans")
public interface LoansFeignClient {

    @GetMapping("api/javaExpress/loans/getLoans")
    public LoansDto getLoans(@RequestParam String mobileNumber);

}
