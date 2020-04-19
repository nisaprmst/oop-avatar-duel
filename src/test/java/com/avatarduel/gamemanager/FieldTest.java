package com.avatarduel.gamemanager;

import com.avatarduel.cards.characters.CharacterCard;
import com.avatarduel.cards.skills.Skill;
import com.avatarduel.cards.skills.SkillCard;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FieldTest {
    private Field field;

    @Before
    public void setUp() {
        field = new Field();
    }

    @Test
    public void getPlaceRemoveCharacterInColumn() {
        CharacterCard characterCard = new CharacterCard();
        boolean thrown = false;
        try {
            field.placeCharacterInColumn(characterCard, 2);
            assertEquals(characterCard, field.getCharacterInColumn(2));
            field.removeCharacterInColumn(2);
            field.getCharacterInColumn(2);
        } catch (Exception e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void getPlaceRemoveSkillInColumn() {
        boolean thrown = false;
        SkillCard skillCard = new SkillCard();
        try {
            field.placeSkillInColumn(skillCard, 3);
            assertEquals(skillCard, field.getSkillInColumn(3));
            field.removeSkillInColumn(3);
            field.getSkillInColumn(3);
        } catch (Exception e) {
            thrown = true;
        }
        assertTrue(thrown);
    }


    @Test
    public void isCharacterEmpty() {
        assertTrue(field.isCharacterEmpty());
        CharacterCard characterCard = new CharacterCard();
        try {
            field.placeCharacterInColumn(characterCard, 2);
        } catch (Exception e) {

        }
        assertFalse(field.isCharacterEmpty());
    }

    @Test
    public void isSkillEmpty() {
        assertTrue(field.isSkillEmpty());
        SkillCard skillCard = new SkillCard();
        try {
            field.placeSkillInColumn(skillCard, 2);
        } catch (Exception e) {

        }
        assertFalse(field.isSkillEmpty());
    }

    @Test
    public void resetHasAttacked() {
        CharacterCard characterCard = new CharacterCard();
        characterCard.setHasAttacked(true);
        try {
            field.placeCharacterInColumn(characterCard, 2);
            field.resetHasAttacked();
            assertFalse(field.getCharacterInColumn(2).getHasAttacked());
        } catch (Exception e) {

        }
    }

    @Test
    public void resetJustSummoned() {
        CharacterCard characterCard = new CharacterCard();
        characterCard.setJustSummoned(true);
        try {
            field.placeCharacterInColumn(characterCard, 2);
            field.resetJustSummoned();
            assertFalse(field.getCharacterInColumn(2).getJustSummoned());
        } catch (Exception e) {

        }
    }
}