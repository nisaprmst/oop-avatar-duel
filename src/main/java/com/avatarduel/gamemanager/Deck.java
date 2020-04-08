package com.avatarduel.gamemanager;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;
import com.avatarduel.cards.Card;
import com.avatarduel.cards.CardLoader;
import com.avatarduel.cards.LandCard;
import com.avatarduel.cards.characters.CharacterCard;
import com.avatarduel.cards.skills.AuraSkill;

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

    public static void main(String[] args) throws IOException, URISyntaxException {
        CardLoader cl = new CardLoader();
        ArrayList<LandCard> llc = cl.loadLandCardsFromFile("../card/data/land.csv");
        ArrayList<CharacterCard> lcc = cl.loadCharacterCardsFromFile("../card/data/character.csv");
        ArrayList<AuraSkill> las = cl.loadAuraSkillFromFile("../card/data/skill_aura.csv");

        Deck deck = new Deck();

        for (LandCard lc : llc) {
            deck.addCard(lc);
        }
        for (CharacterCard cc : lcc) {
            deck.addCard(cc);
        }
        for (AuraSkill as : las) {
            deck.addCard(as);
        }

//        Card testCard1 = deck.drawCard();
//        Card testCard2 = deck.drawCard();
//        Card testCard3 = deck.drawCard();
//        testCard1.printInfo();
//        testCard2.printInfo();
//        testCard3.printInfo();

    }
}