package com.avatarduel.gamemanager;

import com.avatarduel.cards.*;
import com.avatarduel.cards.characters.*;
import com.avatarduel.cards.skills.*;
import com.avatarduel.exceptions.InvalidFieldIndexException;

import java.util.*;

/**
 * This class
 */
public class Field {
    private Map<Integer, CharacterCard>  characterRow;
    private Map<Integer, SkillCard> skillRow;
    private int maximumCardsPerRow;

    /**
     * Class Constructor
     */
    public Field() {
        this.characterRow = new HashMap<>();
        this.skillRow = new HashMap<>();
    }

    /**
     * Get the CharacterCard in given column position
     * @param column the number column of the CharacterCard want to get
     * @return CharacterCard
     * @throws InvalidFieldIndexException given when the column argument not a valid number
     */
    public CharacterCard getCharacterInColumn(int column) throws InvalidFieldIndexException {
        if (column < 0 || column >= characterRow.size()) {
            throw new InvalidFieldIndexException(column);
        }
        return this.characterRow.get(column);
    }

    /**
     * Get the SkillCard in given column position
     * @param column the number column of the SkillCard want to get
     * @return SkillCard
     * @throws InvalidFieldIndexException given when the column argument not a valid number
     */
    public SkillCard getSkillInColumn(int column) throws InvalidFieldIndexException {
        if (column < 0 || column >= skillRow.size()) {
            throw new InvalidFieldIndexException(column);
        }
        return this.skillRow.get(column);
    }

    /**
     * Place the a CharacterCard in given column position
     * @param card the CharacterCard want to place
     * @param column the number of column position
     */
    public void placeCharacterInColumn(CharacterCard card, int column) {
        this.characterRow.put(column, card);
    }

    /**
     * Place the a CharacterCard in given column position
     * @param card the CharacterCard want to place
     * @param column the number of column position
     */
    public void placeSkillInColumn(SkillCard card, int column) {
        this.skillRow.put(column, card);
    }

    public boolean isCharacterEmpty() {
        return this.characterRow.isEmpty();
    }

    public boolean isSkillEmpty() {
        return this.skillRow.isEmpty();
    }

    public CharacterCard removeCharacter(int position) {
        return this.characterRow.remove(position);
    }

    public SkillCard removeSkill(int position) {
        return this.skillRow.remove(position);
    }
    public void resetHasAttacked() {
        for (CharacterCard value : characterRow.values()) {
            value.setHasAttacked(false);
        }
    }
    public void resetJustSummoned() {
        for (CharacterCard value : characterRow.values()) {
            value.setJustSummoned(false);
        }
    }

    public static void main(String[] args) {
        try {
            Field field = new Field();
            CharacterCard cc = field.getCharacterInColumn(-1);
            cc.printInfo();
        } catch (InvalidFieldIndexException e) {
            System.out.println(e.toString());
        }
    }
}