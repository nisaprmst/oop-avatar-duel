package com.avatarduel.util;

import com.avatarduel.util.*;
import com.avatarduel.cards.*;
import java.util.*;

public class Player {
    // atribut
    private int hp;
    private Deck deck;
    private ArrayList<Card> cardsInHand;
    private Field field;
    private Map power;

    // ctor 
    public Player() {
        this.hp = 0;
        this.deck = new Deck();
        this.cardsInHand = new ArrayList<Card>();
        this.field = new Field();
        this.power = new HashMap<>();
    }

    // getter
    public int getHp() {
        return this.hp;
    }
    public Deck getDeck() {
        return this.deck;
    }
    public ArrayList<Card> getCardsInHand() {
        return this.cardsInHand;
    }
    public Field getField() {
        return this.field;
    }
    public Map getPower() {
        return this.power;
    }

    // setter
    public void setHp(int hp) {
        this.hp = hp;
    }
    public void setDeck(Deck deck) {
        this.deck = deck;
    }
    public void setCardsInHand(ArrayList<Card> cards) {
        this.cardsInHand = cards;
    }
    public void setField(Field field) {
        this.field = field;
    }
    public void setPower(Map power) {
        this.power = power;
    }

    // method
    public void placeCard(Card card, int position) {
        this.field.placeCard(card, position);
    }
    public Card drawCard() {
        return this.deck.drawCard();
    }
    public boolean isPlayerSame(Player p) {
        return true;
    }

}