package com.avatarduel.gamemanager;

import com.avatarduel.cards.*;
import com.avatarduel.cards.characters.*;
import com.avatarduel.cards.skills.*;
import java.util.*;


public class Field {
    private Map<Integer, CharacterCard>  characterRow;
    private Map<Integer, SkillCard> skillRow;

    // ctor
    public Field() {
        this.characterRow = new HashMap<>();
        this.skillRow = new HashMap<>();
    }

    // get set
    public CharacterCard getCharacterRow(int row) {
        return this.characterRow.get(row);
    }
    public SkillCard getSkillRow(int row) {
        return this.skillRow.get(row);
    }

    public void placeCharacter(CharacterCard card, int position) {
        this.characterRow.put(position, card);
    }
    public void placeSkill(SkillCard card, int position) {
        this.skillRow.put(position, card);
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
}