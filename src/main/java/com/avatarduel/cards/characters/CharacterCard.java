package com.avatarduel.cards.characters;

import com.avatarduel.cards.Card;
import com.avatarduel.cards.Element;

public class CharacterCard extends Card {
    private int attack;
    private int defense;
    private int power;

    public CharacterCard(int id, String name, String description, Element element, String imagePath,
                         int attack, int defense, int power) {
        super(id, name, description, element, imagePath);
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
