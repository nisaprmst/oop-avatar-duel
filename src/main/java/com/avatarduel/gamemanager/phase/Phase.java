package com.avatarduel.gamemanager.phase;

import com.avatarduel.cards.characters.Position;
import com.avatarduel.gamemanager.Command;
import com.avatarduel.gamemanager.GameManager;

public abstract class Phase {
    // attribute
    private GameManager game;
    private PhaseType type;


    // ctor
    public Phase(GameManager game, PhaseType type) {
        this.game = game;
        this.type = type;
    }
    /**
     * @return the game
     */
    public GameManager getGame() {
        return game;
    }
    /**
     * @return the type
     */
    public PhaseType getType() {
        return type;
    }
    /**
     * @param game the game to set
     */
    public void setGame(GameManager game) {
        this.game = game;
    }
    /**
     * @param type the type to set
     */
    public void setType(PhaseType type) {
        this.type = type;
    }
    public abstract void nextPhase();
    public abstract void phaseInfo();
    public abstract void process(Command command, int posInHand, int posInField, int target, boolean isOnPlayer) throws Exception;
}