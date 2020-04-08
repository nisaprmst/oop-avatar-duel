package com.avatarduel.gamemanager;

import com.avatarduel.cards.*;
import com.avatarduel.cards.characters.*;
import com.avatarduel.cards.skills.*;
import java.util.*;


public class Field {
    private Map<Integer, Card>  characterRow;
    private Map<Integer, Card> skillRow;

    // ctor
    public Field() {
        this.characterRow = new HashMap<>();
        this.skillRow = new HashMap<>();
    }

    // get set
    public Card getCharacterRow(int row) {
        return this.characterRow.get(row);
    }

    public Card getSkillRow(int row) {
        return this.skillRow.get(row);
    }

    public void placeCard(Card card, int position) {
        if (card instanceof CharacterCard) {
            this.characterRow.put(position, card);
        } else if (card instanceof SkillCard) { 
            this.skillRow.put(position, card);
        }
    }
    public boolean isCharacterEmpty() {
        return this.characterRow.isEmpty();
    }

    public boolean isSkillEmpty() {
        return this.skillRow.isEmpty();
    }

    public Card removeCharacter(int position) {
        return this.characterRow.remove(position);
    }

    public Card removeSkill(int position) {
        return this.skillRow.remove(position);
    }
}