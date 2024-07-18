package com.express.loans.controller;

import com.express.loans.dto.LoansDto;
import com.express.loans.service.LoansService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/javaExpress/loans")
@Validated
public class LoansController {

    @Autowired
    LoansService loansService;

    @PostMapping("createLoans")
    public String createLoansInfo(@RequestBody LoansDto loansDto){
        loansService.createLoanModel(loansDto);
        return "Loans created successfully!!";
    }

    @GetMapping("getLoans")
    public LoansDto getLoans(@RequestParam @Pattern(regexp = "(^[0-9]{10})", message = "Mobile Number must be 10 digits") String mobileNumber){

        return loansService.getLoans(mobileNumber);
    }

    @PutMapping("updateLoans")
    public String updateLoans(@RequestBody @Valid LoansDto loansDto){
        loansService.updateLoans(loansDto);
        return "loan data successfully!!";
    }

    @DeleteMapping("deleteLoans/{mobileNumber}")
    public String deleteLoans(@PathVariable("mobileNumber") String mobileNumber){
        loansService.deleteLoans(mobileNumber);
        return "Loans deleted Successfully which associated with the mobileNumber "+mobileNumber;
    }
}
