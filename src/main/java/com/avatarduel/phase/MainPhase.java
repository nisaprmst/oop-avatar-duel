package com.avatarduel.phase;

import com.avatarduel.util.*;

public class MainPhase extends Phase {
    // ctor
    public MainPhase(GameManager game) {
        super(game);
    }
    public void nextPhase() {
        game.changePhase(new BattlePhase(game));
    }
    public void phaseInfo() {
        System.out.println("Starting main phase");
    }
    public void process() {
        
    }

}