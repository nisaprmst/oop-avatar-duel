package com.avatarduel.gamemanager.phase;

import com.avatarduel.gamemanager.GameManager;

public class MainPhase extends Phase {
    // ctor
    private int count;
    public MainPhase(GameManager game, int count) {
        super(game);
        this.count = count;
    }
    public void nextPhase() {
        if (this.count == 1) {
            game.changePhase(new BattlePhase(game));
        } else if (this.count == 2) {
            game.changePhase(new DrawPhase(game));
        }
    }
    public void phaseInfo() {
        System.out.println("Starting main phase");
    }
    public void process() {
        
    }

}