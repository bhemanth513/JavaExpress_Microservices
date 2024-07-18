package com.express.cards.service;

import com.express.cards.dto.CardDto;
import com.express.cards.model.Card;

public interface ICardService {

    Card createCard(String mobileNumber);

    CardDto getCardDetails(String mobileNumber);

    boolean updateCard(CardDto cardDto);

    boolean deleteCard(String mobileNumber);
}
