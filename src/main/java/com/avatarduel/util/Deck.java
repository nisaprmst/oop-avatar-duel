package com.avatarduel.util;

import java.util.*;
import com.avatarduel.cards.*;

public class Deck {
    private final ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
    }

    // get set
    public Card getCard(final int position) {
        return this.cards.get(position);
    }

    public void addCard(final Card card) {
        this.cards.add(card);
    }

    public void removeCard(final Card card) {
        this.cards.remove(card);
    }

    public Card drawCard() {
        final Random rand = new Random();
        final int idxDraw = rand.nextInt(cards.size()); // mengambil posisi random pada deck dari 0-(cards.size-1)
        Card randomCard = cards.get(idxDraw); // mengakses cards pada posisi ke-idxDraw
        cards.remove(idxDraw); // me-remove cards pada posisi ke-idxDraw dari deck
        return randomCard;
    }
}