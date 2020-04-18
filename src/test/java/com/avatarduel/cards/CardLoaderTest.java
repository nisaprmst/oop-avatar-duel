package com.avatarduel.cards;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CardLoaderTest {
    private CardLoader cardLoader1, cardLoader2;

    @Before
    public void setUp() throws IOException, URISyntaxException {
        System.out.println("Run @Before");
        final String LAND_CSV_FILE_PATH = "../card/data/land.csv";
        final String CHARACTER_CSV_FILE_PATH = "../card/data/land.csv";
        final String SKILL_CSV_FILE_PATH = "../card/data/land.csv";
        cardLoader1 = new CardLoader();
        cardLoader2 = new CardLoader();
        System.out.println("udah");
        cardLoader1.loadLandCardsFromFile(LAND_CSV_FILE_PATH);
        System.out.println("udah");
//        cardLoader1.loadCharacterCardsFromFile(CHARACTER_CSV_FILE_PATH);
//        System.out.println("udah");
//        cardLoader1.loadAuraSkillFromFile(SKILL_CSV_FILE_PATH);
//        System.out.println("udah");
    }

    @Test
    public void getSetLoadedCards() {
        ArrayList<Card> list = cardLoader1.getLoadedCards();
        cardLoader2.setLoadedCards(list);
        assertEquals(list, cardLoader2.getLoadedCards());

    }
}