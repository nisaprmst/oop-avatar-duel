package com.avatarduel.cards.characters;

import com.avatarduel.cards.Card;
import com.avatarduel.cards.Element;

public class CharacterCard extends Card {
    private int attack;
    private int defense;
    private int power;
    private boolean hasAttacked;
    private Position position;
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

    public int getAttack() {
        return this.attack;
    }

    public int getDefense() {
        return this.defense;
    }

    public int getPower() {
        return this.power;
    }

    public boolean getHasAttack() {
        return this.hasAttacked;
    }

    public Position getPosition() {
        return this.position;
    }

    public boolean getJustSummoned() {
        return this.justSummoned;
    }

    public void getHasAttack(boolean param) {
        this.hasAttacked = param;
    }

    public void getPosition(Position pos) {
        this.position = pos;
    }

    public void getJustSummoned(boolean param) {
        this.justSummoned = param;
    }
    @Override
    public Element getElement() {
        return super.getElement();
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.print("Attack:"); System.out.println(this.attack);
        System.out.print("Defense:"); System.out.println(this.defense);
        System.out.print("Power:"); System.out.println(this.power);
    }
}
