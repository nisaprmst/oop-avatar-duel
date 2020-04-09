package com.avatarduel.gamemanager;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import com.avatarduel.cards.*;

/**
 * This class store ArrayList of Card use to draw from a player.
 */
public class Deck extends ArrayList<Card>{
    private ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
    }
    public void loadDeck(ArrayList<Card> cardsList) {
        for (Card card: cardsList) {
            add(card);
        }
    }

    public Card drawCard() {
        final Random rand = new Random();
        final int idxDraw = rand.nextInt(cards.size()); // mengambil posisi random pada deck dari 0-(cards.size-1)

        Card randomCard = remove(idxDraw); // me-remove cards pada posisi ke-idxDraw dari deck

        return randomCard;
    }


    /**
     * Testing Deck
     */
    public static void main(String[] args) throws IOException, URISyntaxException {

        CardLoader cl = new CardLoader();
        cl.loadLandCardsFromFile("../card/data/land.csv");
        cl.loadCharacterCardsFromFile("../card/data/character.csv");
        cl.loadAuraSkillFromFile("../card/data/skill_aura.csv");

        Deck deck = new Deck();

        deck.loadDeck(cl.getLoadedCards());

        Card testCard1 = deck.drawCard();
        Card testCard2 = deck.drawCard();
        Card testCard3 = deck.drawCard();
        testCard1.printInfo();
        testCard2.printInfo();
        testCard3.printInfo();
    }
}