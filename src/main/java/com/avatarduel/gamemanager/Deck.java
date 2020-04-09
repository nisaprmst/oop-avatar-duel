package com.avatarduel.gamemanager;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import com.avatarduel.cards.*;
import com.avatarduel.exceptions.EmptyDeckException;

/**
 * This class store ArrayList of Card use to draw from a player.
 */
public class Deck extends ArrayList<Card>{

    public Deck() {
        super();
    }
    public void loadDeck(ArrayList<Card> cardsList) {
        for (Card card: cardsList) {
            add(card);
        }
    }

    /**
     * Take random position in valid deck size range then get and remove the card from the array list.
     * @return Card the random card available in array list.
     * @throws EmptyDeckException exception if Deck is empty.
     */
    public Card drawCard() throws EmptyDeckException {
        if (this.size() == 0) {
            throw new EmptyDeckException("The deck is empty");
        }
        final Random rand = new Random();
        final int idxDraw = rand.nextInt(this.size()); // mengambil posisi random pada deck dari 0-(cards.size-1)

        Card randomCard = remove(idxDraw); // me-remove cards pada posisi ke-idxDraw dari deck

        return randomCard;
    }

    public static void main(String[] args) {
        try {
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
        } catch (IOException e) {
            System.out.println("Failed to load card");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}