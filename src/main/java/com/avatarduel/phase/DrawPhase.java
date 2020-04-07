package com.avatarduel.phase;

import com.avatarduel.util.*;

public class DrawPhase extends Phase {
    // ctor
    public DrawPhase(GameManager game) {
        super(game);
    }
    public void nextPhase() {
        game.changePhase(new MainPhase(game));
    }
    public void phaseInfo() {
        System.out.println("Starting draw phase");
    }
    public void process() {
        
    }

}