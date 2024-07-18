package com.express.cards.service;

import com.express.cards.controller.CardController;
import com.express.cards.dto.CardDto;
import com.express.cards.exception.CardAlreadyExist;
import com.express.cards.exception.CardNotExistException;
import com.express.cards.model.Card;
import com.express.cards.repository.CardRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class CardService implements ICardService{

    public final Logger logger = LoggerFactory.getLogger(CardController.class);

    @Autowired
    CardRepository cardRepository;
    @Override
    public Card createCard(String mobileNumber) {
        Optional card= cardRepository.findByMobileNumber(mobileNumber);
        if(card.isPresent()) {
                throw new  CardAlreadyExist("Card already exist with given Mobile number");
        }
        Card newCard = new Card();
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardNumber(10000000L + new Random().nextInt(90000000));
        newCard.setCardType("CREDIT_CARD");
        newCard.setTotalLimit(100000);
        newCard.setAmountUsed(50000);
        newCard.setAvailableAmount(newCard.getTotalLimit()-newCard.getAmountUsed());
        cardRepository.save(newCard);
        return newCard;
    }

    @Override
    public CardDto getCardDetails(String mobileNumber) {
        Card card= cardRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(()->new CardNotExistException("Card Not exist with given MobileNumber {}"+mobileNumber));
        CardDto cardDto = new CardDto();
        BeanUtils.copyProperties(card,cardDto);
        return cardDto;
    }

    @Override
    public boolean updateCard(CardDto cardDto) {
        Card card = cardRepository.findByCardNumber(cardDto.getCardNumber())
                .orElseThrow(()->new CardNotExistException("Card Not exist with given cardNumber {}"+ cardDto.getCardNumber()));

        BeanUtils.copyProperties(cardDto,card);
        cardRepository.save(card);
        return true;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        Card card= cardRepository.findByMobileNumber(mobileNumber)
                .orElseThrow(()->new CardNotExistException("MobileNumber not associated with the card {}"+mobileNumber));
        cardRepository.deleteById(card.getCardId());
        return true;
    }
}
