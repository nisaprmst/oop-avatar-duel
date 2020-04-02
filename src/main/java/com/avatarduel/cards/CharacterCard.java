package com.avatarduel.cards;

public class CharacterCard extends Card {
    private int attack;
    private int defense;
    private int power;

    public CharacterCard(int id, String name, String description, Element element, int attack, int defense, int power) {
        super(id, name, description, element);
        this.attack = attack;
        this.defense = defense;
        this.power = power;
    }

    public int getAttack() {
        return this.attack;
    }

    public int getDefense() {
        return defense;
    }

    public int getPower() {
        return power;
    }
}
