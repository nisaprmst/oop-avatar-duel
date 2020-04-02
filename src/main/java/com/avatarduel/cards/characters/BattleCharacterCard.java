package com.avatarduel.cards.characters;

import com.avatarduel.cards.Element;

public class BattleCharacterCard extends CharacterCard {
    private int turnUsed = 0;
    private Position position;
    private boolean hasChangedPos = false;
    private boolean hasAttacked = false;
    // private ArrayList<SkillCard> skillCards;

    public BattleCharacterCard(int id, String name, String description, Element element, int attack, int defense,
                               int power, Position position) {
        super(id, name, description, element, attack, defense, power);
        this.position = position;
    }
}
