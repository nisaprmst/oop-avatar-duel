package com.avatarduel.cards;

import com.avatarduel.cards.characters.CharacterCard;
import com.avatarduel.cards.skills.AuraSkill;
import com.avatarduel.cards.skills.DestroySkill;
import com.avatarduel.cards.skills.PowerUpSkill;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class CardLoaderTest {

    @Test
    public void getLoadedCards() {
        CardLoader cardLoader = new CardLoader();
        ArrayList<Card> cards = cardLoader.getLoadedCards();
        assertTrue(cards.isEmpty());
    }

    @Test
    public void falsePathLoadCharacterCardsFromFile() {
        CardLoader cardLoader = new CardLoader();
        boolean thrown = false;
        try {
            cardLoader.loadCharacterCardsFromFile("/");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            thrown = true;
        } finally {
            assertTrue(thrown);
        }
    }

    @Test
    public void truePathLoadCharacterCardsFromFile() {
        CardLoader cardLoader = new CardLoader();
        boolean thrown = false;
        try {
            cardLoader.loadCharacterCardsFromFile("../card/data/character.csv");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            thrown = true;
        } finally {
            assertFalse(thrown);

            ArrayList<Card> cards = cardLoader.getLoadedCards();
            CharacterCard firstCard = (CharacterCard) cards.get(0);

            // Test whether the loaded cards is in the right file given the first card information
            assertEquals(17, firstCard.getId());
            assertEquals("Katara", firstCard.getName());
            assertEquals(Element.WATER, firstCard.getElement());
            assertEquals("Waterbending master from Southern Water Tribe, sister of Sokka, and friend of Aang.", firstCard.getDescription());
            assertEquals("Katara.png", firstCard.getImagePath());
            assertEquals(13, firstCard.getAttack());
            assertEquals(7, firstCard.getDefense());
            assertEquals(5, firstCard.getPower());
        }
    }

    @Test
    public void falsePathLoadLandCardsFromFile() {
        CardLoader cardLoader = new CardLoader();
        boolean thrown = false;
        try {
            cardLoader.loadLandCardsFromFile("/");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            thrown = true;
        } finally {
            assertTrue(thrown);
        }
    }

    @Test
    public void truePathLoadLandCardsFromFile() {
        CardLoader cardLoader = new CardLoader();
        boolean thrown = false;
        try {
            cardLoader.loadLandCardsFromFile("../card/data/land.csv");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            thrown = true;
        } finally {
            assertFalse(thrown);

            ArrayList<Card> cards = cardLoader.getLoadedCards();
            LandCard firstCard = (LandCard) cards.get(0);

            // Test whether the loaded cards is in the right file given the first card information
            assertEquals(1, firstCard.getId());
            assertEquals("Eastern Air Temple", firstCard.getName());
            assertEquals(Element.AIR, firstCard.getElement());
            assertEquals("One of the two temples exclusively housing female airbenders.", firstCard.getDescription());
            assertEquals("Eastern Air Temple.png", firstCard.getImagePath());
        }
    }

    @Test
    public void falsePathLoadAuraSkillFromFile() {
        CardLoader cardLoader = new CardLoader();
        boolean thrown = false;
        try {
            cardLoader.loadAuraSkillFromFile("/");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            thrown = true;
        } finally {
            assertTrue(thrown);
        }
    }

    @Test
    public void truePathLoadAuraSkillFromFile() {
        CardLoader cardLoader = new CardLoader();
        boolean thrown = false;
        try {
            cardLoader.loadAuraSkillFromFile("../card/data/skill_aura.csv");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            thrown = true;
        } finally {
            assertFalse(thrown);

            ArrayList<Card> cards = cardLoader.getLoadedCards();
            AuraSkill firstCard = (AuraSkill) cards.get(0);

            // Test whether the loaded cards is in the right file given the first card information
            assertEquals(66, firstCard.getId());
            assertEquals("Air Scooter", firstCard.getName());
            assertEquals(Element.AIR, firstCard.getElement());
            assertEquals("A form of transportation invented by Aang.", firstCard.getDescription());
            assertEquals("Air Scooter.png", firstCard.getImagePath());
            assertEquals(2, firstCard.getPower());
            assertEquals(2, firstCard.getAtkPoint());
            assertEquals(4, firstCard.getDefPoint());
        }
    }

    @Test
    public void falsePathLoadDestroySkillFromFile() {
        CardLoader cardLoader = new CardLoader();
        boolean thrown = false;
        try {
            cardLoader.loadDestroySkillFromFile("/");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            thrown = true;
        } finally {
            assertTrue(thrown);
        }
    }

    @Test
    public void truePathLoadDestroySkillFromFile() {
        CardLoader cardLoader = new CardLoader();
        boolean thrown = false;
        try {
            cardLoader.loadDestroySkillFromFile("../card/data/skill_destroy.csv");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            thrown = true;
        } finally {
            assertFalse(thrown);

            ArrayList<Card> cards = cardLoader.getLoadedCards();
            DestroySkill firstCard = (DestroySkill) cards.get(0);

            // Test whether the loaded cards is in the right file given the first card information
            assertEquals(65, firstCard.getId());
            assertEquals("Air Funnel", firstCard.getName());
            assertEquals(Element.AIR, firstCard.getElement());
            assertEquals("Technique to create a cannon for small projectiles.", firstCard.getDescription());
            assertEquals("Air Funnel.png", firstCard.getImagePath());
            assertEquals(2, firstCard.getPower());
        }
    }

    @Test
    public void falsePathLoadPowerUpSkillFromFile() {
        CardLoader cardLoader = new CardLoader();
        boolean thrown = false;
        try {
            cardLoader.loadPowerUpSkillFromFile("/");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            thrown = true;
        } finally {
            assertTrue(thrown);
        }
    }

    @Test
    public void truePathLoadPowerUpSkillFromFile() {
        CardLoader cardLoader = new CardLoader();
        boolean thrown = false;
        try {
            cardLoader.loadPowerUpSkillFromFile("../card/data/skill_powerup.csv");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            thrown = true;
        } finally {
            assertFalse(thrown);

            ArrayList<Card> cards = cardLoader.getLoadedCards();
            PowerUpSkill firstCard = (PowerUpSkill) cards.get(0);

            // Test whether the loaded cards is in the right file given the first card information
            assertEquals(70, firstCard.getId());
            assertEquals("Glider", firstCard.getName());
            assertEquals(Element.AIR, firstCard.getElement());
            assertEquals("Aerial transportation made by Mechanist, inspired from Air nomad gliders.", firstCard.getDescription());
            assertEquals("Glider.png", firstCard.getImagePath());
            assertEquals(3, firstCard.getPower());
        }
    }
}