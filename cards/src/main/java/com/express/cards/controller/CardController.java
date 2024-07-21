package com.express.cards.controller;

import com.express.cards.dto.CardDto;
import com.express.cards.service.CardService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("javaExpress/cards")
public class CardController {

    public final Logger logger = LoggerFactory.getLogger(CardService.class);

    @Autowired
    CardService cardService;

    @PostMapping(value = "createCard/{mobileNumber}")
    public String createCard(@PathVariable("mobileNumber") String mobileNumber){
        logger.info("CardController:: createCard {}", mobileNumber);
        cardService.createCard(mobileNumber);
        return "Card Created Successfully!!";
    }

    @GetMapping(value = "getCard/{mobileNumber}",produces = "application/json")
    public CardDto getCardDetails(@PathVariable("mobileNumber") String mobileNumber){
        logger.info("CardController:: getCardDetails {}", mobileNumber);
        CardDto cardDto = cardService.getCardDetails(mobileNumber);
        return cardDto;
    }

    @PutMapping(value = "updateCard")
    public boolean updateCardDetails(@RequestBody CardDto cardDto){
        cardService.updateCard(cardDto);
        return true;
    }

    @DeleteMapping("deleteCard/{mobileNumber}")
    public boolean deleteCardDetails(@PathVariable("mobileNumber") String mobileNumber){
        cardService.deleteCard(mobileNumber);
        return true;
    }
}
