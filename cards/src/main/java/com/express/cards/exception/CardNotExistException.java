package com.express.cards.exception;

public class CardNotExistException extends RuntimeException {
    public CardNotExistException(String msg) {
        super(msg);
    }
}
