package com.avatarduel;

import java.util.*;
import com.avatarduel.cards.Card;

public class Deck {
    private final ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<Card>();
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
        // mengambil posisi random pada deck dari 0-(cards.size-1)
        final int idxDraw = rand.nextInt(cards.size());
        cards.get(idxDraw); // mengakses cards pada posisi ke-idxDraw
        cards.remove(idxDraw); // me-remove cards pada posisi ke-idxDraw dari deck
    }
}