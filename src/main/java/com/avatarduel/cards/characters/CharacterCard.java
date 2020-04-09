package com.avatarduel.cards.characters;

import com.avatarduel.cards.Card;
import com.avatarduel.cards.CardType;
import com.avatarduel.cards.Element;

/**
 * This class inherit Card class to representing additional CharacterCard data
 * and several method for CharacterCard.
 */
public class CharacterCard extends Card {
    private int attack;
    private int defense;
    private int power;
    /**
     * If is set true, the CharacterCard has already attack
     */
    private boolean hasAttacked;
    private Position position;
    /**
     * If is set true, the CharacterCard is just summoned to the field and can't attack
     */
    private boolean justSummoned;
    /**
     * If is set true, the CharacterCard is equipped with power up skill
     */
    private boolean isPowerUp;

    public CharacterCard(int id, String name, String description, Element element, String imagePath,
                         int attack, int defense, int power) {
        super(id, name, description, element, imagePath, CardType.CHARACTER);
        this.attack = attack;
        this.defense = defense;
        this.power = power;
        this.position = Position.ATTACK;
        this.hasAttacked = false;
        this.justSummoned = false;
        this.isPowerUp = false;
    }

    public int getAttack() {
        return this.attack;
    }
    public int getDefense() {
        return this.defense;
    }
    public int getPower() {
        return this.power;
    }
    public boolean getHasAttacked() {
        return this.hasAttacked;
    }
    public Position getPosition() {
        return this.position;
    }
    public boolean getJustSummoned() {
        return this.justSummoned;
    }
    public boolean getIsPowerUp() {
        return this.isPowerUp;
    }

    public void setHasAttacked(boolean param) {
        this.hasAttacked = param;
    }
    public void setPosition(Position pos) {
        this.position = pos;
    }
    public void setAtkPoint(int att){
        this.attack = att;
    }
    public void setDefPoint(int def){
        this.defense = def;
    }
    public void setJustSummoned(boolean param) {
        this.justSummoned = param;
    }
    public void setIsPowerUp(boolean param) {
        this.isPowerUp = param;
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.print("Attack:"); System.out.println(this.attack);
        System.out.print("Defense:"); System.out.println(this.defense);
        System.out.print("Power:"); System.out.println(this.power);
    }
}
