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

    /**
     * Take another ArrayList of Card and add all its elements to this
     * @param cardsList other ArrayList of Card
     */
    public void loadDeck(ArrayList<Card> cardsList) {
        this.addAll(cardsList);
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
        final int idxDraw = rand.nextInt(this.size());

        return remove(idxDraw);
    }

    public void loadCardFromPath() throws IOException, URISyntaxException {
        final String LAND_CSV_FILE_PATH = "../card/data/land.csv";
        final String CHARACTER_CSV_FILE_PATH = "../card/data/character.csv";
        final String SKILL_CSV_FILE_PATH = "../card/data/skill_aura.csv";
        CardLoader cardLoader = new CardLoader();

        cardLoader.loadLandCardsFromFile(LAND_CSV_FILE_PATH);
        cardLoader.loadCharacterCardsFromFile(CHARACTER_CSV_FILE_PATH);
        cardLoader.loadAuraSkillFromFile(SKILL_CSV_FILE_PATH);

        loadDeck(cardLoader.getLoadedCards());
    }
}