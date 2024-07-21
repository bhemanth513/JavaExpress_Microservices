package com.express.accounts.feignClient;

import com.express.accounts.dto.CardDto;
import org.springframework.stereotype.Component;

@Component
public class CardsFallback implements CardsFeignClient{
    @Override
    public CardDto getCardDetails(String mobileNumber) {
        CardDto cardDto = new CardDto();
        cardDto.setStatus("Please try again after sometime!!");
        return cardDto;
    }
}
