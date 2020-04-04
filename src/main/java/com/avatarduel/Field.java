package com.avatarduel;

import com.avatarduel.cards.Card;
import com.avatarduel.cards.SkillCard;
import com.avatarduel.cards.characters.CharacterCard;

public class Field {
    private final Card[] characterRow;
    private final Card[] skillRow;

    // ctor
    public Field() {
        this.characterRow = new int[8];
        this.skillRow = new int[8];
    }

    // get set
    public Card getCharacterRow(final int row) {
        return this.characterRow[row];
    }

    public Card getSkillRow(final int row) {
        return this.skillRow[row];
    }

    public void placeCard(final Card card, final int position) {
        if (card instanceof CharacterCard) {
            this.characterRow[position] = card;
        } else if (card instanceof SkillCard) { 
            this.skillRow[position] = card;
        }
    }
}