package com.express.loans.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoansDto {

    @Pattern(regexp = "(^[0-9]{10})", message = "Mobile Number must be 10 digits")
    private String mobileNumber;
    @NotNull
    private Long loanNumber;
    private String loanType;
    @Size(min = 1,message = "Total Loan amount should be greater than 0")
    private int totalLoan;
    private int amountPaid;
    private int outstandingAmount;
}
