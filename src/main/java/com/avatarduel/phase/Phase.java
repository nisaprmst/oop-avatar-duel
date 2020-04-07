package com.avatarduel.phase;

import com.avatarduel.util.*;

public abstract class Phase {
    // attribute
    GameManager game;

    // ctor
    public Phase(GameManager game) {
        this.game = game;
    }
    public abstract void nextPhase();
    public abstract void phaseInfo();
    public abstract void process();
}