package com.express.loans.service;

import com.express.loans.dto.LoansDto;
import com.express.loans.exception.LoansAlreadyExistException;
import com.express.loans.exception.ResourceNotFoundException;
import com.express.loans.model.Loans;
import com.express.loans.repository.LoansRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class LoansService {

    @Autowired
    LoansRepository loansRepository;

    public void createLoanModel(LoansDto loansDto) {
        Optional<Loans> loansOptional = loansRepository.findByMobileNumber(loansDto.getMobileNumber()) ;
        if(loansOptional.isPresent()){
            throw new LoansAlreadyExistException("Loan already exist with mobile number "+loansDto.getMobileNumber());
        }
        Loans loans = new Loans();
        BeanUtils.copyProperties(loansDto,loans);
        loans.setLoanNumber(10000000L + new Random().nextInt(900000));
        loansRepository.save(loans);
    }

    public LoansDto getLoans(String mobileNumber) {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(()-> new ResourceNotFoundException("Loans not exist with given mobile number "+mobileNumber));
        LoansDto loansDto = new LoansDto();
        BeanUtils.copyProperties(loans,loansDto);
        return loansDto;
    }

    public void updateLoans(LoansDto loansDto) {
        Loans loans = loansRepository.findByLoanNumber(loansDto.getLoanNumber())
                .orElseThrow(()->new ResourceNotFoundException("Loans not exist with loan number "+loansDto.getLoanNumber()));
        BeanUtils.copyProperties(loansDto,loans);
        loansRepository.save(loans);
    }

    public void deleteLoans(String mobileNumber) {
        Loans loans = loansRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(()->new ResourceNotFoundException("Loans not exist already with mobile number"+mobileNumber));
        loansRepository.deleteById(loans.getLoadId());
    }
}
