package com.avatarduel.util;

import com.avatarduel.cards.*;
import com.avatarduel.cards.characters.*;
import com.avatarduel.cards.skills.*;

public class Field {
    private final Card[] characterRow;
    private final Card[] skillRow;

    // ctor
    public Field() {
        this.characterRow = new Card[8];
        this.skillRow = new Card[8];
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