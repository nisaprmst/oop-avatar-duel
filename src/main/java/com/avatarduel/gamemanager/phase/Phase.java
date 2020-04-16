package com.avatarduel.gamemanager.phase;

import com.avatarduel.cards.characters.Position;
import com.avatarduel.gamemanager.Command;
import com.avatarduel.gamemanager.GameManager;

public abstract class Phase {
    protected GameManager game;
    private PhaseType type;

    /**
     * GameManager passes itself through the Phase constructor. This
     * allow the Phase to fetch some useful data if it's needed.
     * @param game The context oject associated with the Phase.
     */
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

    /** This method is a backreference for GameManager to change the Phase */
    public abstract void nextPhase();

    /** This method will override according to the concrete Phase.  */
    public abstract void phaseInfo();

    /**
     * This method is a fetching method where all the data in GameManager will
     * be process according to what Phase currently the GameManager is.
     * */
    public abstract void process(Command command, int posInHand, int posInField, int target, boolean isOnPlayer) throws Exception;
}