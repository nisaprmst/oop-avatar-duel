package com.avatarduel.phase;

import com.avatarduel.util.*;

public class BattlePhase extends Phase {
    // ctor
    public BattlePhase(GameManager game) {
        super(game);
    }
    public void nextPhase() {
        game.changePhase(new MainPhase(game, 2));
    }
    public void phaseInfo() {
        System.out.println("Starting battle phase");
    }
    public void process() {
        
    }

}