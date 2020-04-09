package com.avatarduel.gamemanager.phase;

import com.avatarduel.gamemanager.GameManager;

public abstract class Phase {
    // attribute
    GameManager game;

    // ctor
    public Phase(GameManager game) {
        this.game = game;
    }
    public abstract void nextPhase();
    public abstract void phaseInfo();
}