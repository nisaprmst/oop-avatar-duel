package com.avatarduel.exceptions;

import com.avatarduel.cards.Card;

public class AlreadyPlacedCardException extends Exception {
    private int index;
    private Card card;

    public AlreadyPlacedCardException(int index, Card card) {
        this.index = index;
        this.card = card;
    }

    public String toString() {
        return "There already card " + card.getId() + " in index number " + index;
    }

}
