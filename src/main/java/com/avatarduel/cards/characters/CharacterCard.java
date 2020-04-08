package com.avatarduel.cards.characters;

import com.avatarduel.cards.Card;
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

    public CharacterCard(int id, String name, String description, Element element, String imagePath,
                         int attack, int defense, int power) {
        super(id, name, description, element, imagePath);
        this.attack = attack;
        this.defense = defense;
        this.power = power;
        this.position = Position.ATTACK;
        this.hasAttacked = false;
        this.justSummoned = false;
    }

    public int getAttack() { return this.attack; }
    public int getDefense() { return this.defense; }
    public int getPower() { return this.power; }
    public boolean getHasAttacked() { return this.hasAttacked; }
    public Position getPosition() { return this.position; }
    public boolean getJustSummoned() { return this.justSummoned; }

    public void setHasAttacked(boolean param) { this.hasAttacked = param; }
    public void setPosition(Position pos) { this.position = pos; }
    public void setJustSummoned(boolean param) { this.justSummoned = param; }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.print("Attack:"); System.out.println(this.attack);
        System.out.print("Defense:"); System.out.println(this.defense);
        System.out.print("Power:"); System.out.println(this.power);
    }
}
