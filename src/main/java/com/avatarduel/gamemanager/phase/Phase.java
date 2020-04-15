package com.avatarduel.gamemanager.phase;

import com.avatarduel.cards.characters.Position;
import com.avatarduel.gamemanager.Command;
import com.avatarduel.gamemanager.GameManager;

public abstract class Phase {
    // attribute
    GameManager game;
    private PhaseType type;


    // ctor
    public Phase(GameManager game, PhaseType type) {
        this.game = game;
        this.type = type;
    }
    /**
     * @return the type
     */
    public PhaseType getType() {
        return type;
    }
    /**
     * @param type the type to set
     */
    public void setType(PhaseType type) {
        this.type = type;
    }
    public abstract void nextPhase();
    public abstract void phaseInfo();
    public abstract void process(Command command, int posInHand, int posInField, int target, boolean isOnPlayer);
}