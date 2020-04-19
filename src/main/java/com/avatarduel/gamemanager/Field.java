package com.avatarduel.gamemanager;

import com.avatarduel.cards.*;
import com.avatarduel.cards.characters.*;
import com.avatarduel.cards.skills.*;
import com.avatarduel.exceptions.AlreadyPlacedCardException;
import com.avatarduel.exceptions.InvalidFieldIndexException;
import com.avatarduel.exceptions.NoCardInFieldException;

import java.util.*;

/**
 * A Field is  a class that store game field of a player, consist of two rows. One row is for CharacterCard
 * and another row is for Skill Card.
 */
public class Field {
    private Map<Integer, CharacterCard>  characterRow; // {1: CC1, 2: CC2, 3: CC3, 4: CC4, 5: CC5, 6: CC6}
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
    public CharacterCard getCharacterInColumn(int column) throws InvalidFieldIndexException, NoCardInFieldException {
        if (column < 0 || column >= this.maximumCardsPerRow) {
            throw new InvalidFieldIndexException(column);
        }

        if (this.characterRow.get(column) == null) {
            throw new NoCardInFieldException(column);
        }

        return this.characterRow.get(column);
    }

    /**
     * Get the SkillCard in given column position
     * @param column the number column of the SkillCard want to get
     * @return SkillCard
     * @throws InvalidFieldIndexException given when the column argument not a valid number
     */
    public SkillCard getSkillInColumn(int column) throws InvalidFieldIndexException, NoCardInFieldException {
        if (column < 0 || column >= this.maximumCardsPerRow) {
            throw new InvalidFieldIndexException(column);
        }

        if (this.skillRow.get(column) == null) {
            throw new NoCardInFieldException(column);
        }

        return this.skillRow.get(column);
    }

    /**
     * Place the a CharacterCard in given column position
     * @param card the CharacterCard want to place
     * @param column the number of column position
     * @throws InvalidFieldIndexException given when the column argument not a valid number
     */
    public void placeCharacterInColumn(CharacterCard card, int column) throws InvalidFieldIndexException, AlreadyPlacedCardException {
        if (column < 0 || column >= this.maximumCardsPerRow) {
            throw new InvalidFieldIndexException(column);
        }

        if (characterRow.containsKey(column)) {
            throw new AlreadyPlacedCardException(column, characterRow.get(column));
        }

        this.characterRow.put(column, card);
    }

    /**
     * Place the a CharacterCard in given column position
     * @param card the CharacterCard want to place
     * @param column the number of column position
     * @throws InvalidFieldIndexException given when the column argument not a valid number
     */
    public void placeSkillInColumn(SkillCard card, int column) throws InvalidFieldIndexException, AlreadyPlacedCardException {
        if (column < 0 || column >= this.maximumCardsPerRow) {
            throw new InvalidFieldIndexException(column);
        }

        if (skillRow.containsKey(column)) {
            throw new AlreadyPlacedCardException(column, characterRow.get(column));
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
    public CharacterCard removeCharacterInColumn(int column) throws NoCardInFieldException {
        if (this.characterRow.get(column) == null) {
            throw new NoCardInFieldException(column);
        }

        return this.characterRow.remove(column);
    }

    /**
     * Remove the SkillCard in field at given column number
     * @param column the number of column position
     * @return SkillCard at given column if exist
     */
    public SkillCard removeSkillInColumn(int column) throws NoCardInFieldException {
        if (this.skillRow.get(column) == null) {
            throw new NoCardInFieldException(column);
        }

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
    /* This is for debugging purposes. Testing code will commited soon */
        try {
            CharacterCard card1 = new CharacterCard(1);
            CharacterCard card2 = new CharacterCard(2);

            Field field = new Field();
            field.placeCharacterInColumn(card1, 0);
            field.placeCharacterInColumn(card2, 0);
            // field.placeCharacterInColumn(card2, 6); // assert e.toString() == "6 is invalid index access";

            CharacterCard cc = field.getCharacterInColumn(0); // assert cc.getId() == 1;

            CharacterCard ccNull = field.getCharacterInColumn(1);

            // cc.printInfo();
            ccNull.printInfo();
            // field.printCharacterRow();

        } catch (Exception e) {
            System.out.print("Error catch: ");
            System.out.println(e.toString());
        }
    }
}