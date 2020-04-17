package com.avatarduel.gamemanager;

import com.avatarduel.cards.*;
import com.avatarduel.cards.characters.*;
import com.avatarduel.cards.skills.*;
import com.avatarduel.exceptions.InvalidFieldIndexException;

import java.util.*;

/**
 * A Field is  a class that store game field of a player, consist of two rows. One row is for CharacterCard
 * and another row is for Skill Card.
 */
public class Field {
    private Map<Integer, CharacterCard>  characterRow;
    private Map<Integer, SkillCard> skillRow;
    private int maximumCardsPerRow;

    /**
     * Class Constructor. Declare each characterRow and skillRow to static array
     * with size 'maximumCardsPerRow'.
     */
    public Field() {
        this.maximumCardsPerRow = 6;
        this.characterRow = new HashMap<>();
        this.skillRow = new HashMap<>();
    }
    /**
     * User defined Class Constructor. Declare each characterRow and skillRow to static array
     * with size 'maximumCardsPerRow' defined by parameter.
     */
    public Field(int max) {
        this.maximumCardsPerRow = max;
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
     * @throws InvalidFieldIndexException given when the column argument not a valid number
     */
    public void placeCharacterInColumn(CharacterCard card, int column)throws InvalidFieldIndexException {
        if (column < 0 || column >= this.maximumCardsPerRow) {
            throw new InvalidFieldIndexException(column);
        }
        this.characterRow.put(column, card);
    }

    /**
     * Place the a CharacterCard in given column position
     * @param card the CharacterCard want to place
     * @param column the number of column position
     * @throws InvalidFieldIndexException given when the column argument not a valid number
     */
    public void placeSkillInColumn(SkillCard card, int column) throws InvalidFieldIndexException {
        if (column < 0 || column >= this.maximumCardsPerRow) {
            throw new InvalidFieldIndexException(column);
        }
        this.skillRow.put(column, card);
    }

    /**
     * Check whether the characterRow is empty
     * @return boolean
     */
    public boolean isCharacterEmpty() {
        return this.characterRow.isEmpty();
    }

    /**
     * Check whether the skillRow is empty
     * @return boolean
     */
    public boolean isSkillEmpty() {
        return this.skillRow.isEmpty();
    }

    /**
     * Remove the CharacterCard in field at given column number
     * @param column the number of column  position
     * @return CharacterCard at given column if exist
     */
    public CharacterCard removeCharacterInColumn(int column) {
        return this.characterRow.remove(column);
    }

    /**
     * Remove the SkillCard in field at given column number
     * @param column the number of column position
     * @return SkillCard at given column if exist
     */
    public SkillCard removeSkillInColumn(int column) {
        return this.skillRow.remove(column);
    }

    /** Set all hasAttacked attribute of CharacterCard in characterRow to false */
    public void resetHasAttacked() {
        for (CharacterCard value : characterRow.values()) {
            value.setHasAttacked(false);
        }
    }

    /** Set all justSummoned attribute of CharacterCard in characterRow to false */
    public void resetJustSummoned() {
        for (CharacterCard value : characterRow.values()) {
            value.setJustSummoned(false);
        }
    }

    /** Print all CharacterCard in characterRow with their index to the console. */
    public void printCharacterRow() {
        for (int i = 0; i < this.maximumCardsPerRow; i++) {
            System.out.println();
            System.out.print("index ");
            System.out.println(i);
            if(this.characterRow.containsKey(i)) {
                this.characterRow.get(i).printInfo();
            }
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