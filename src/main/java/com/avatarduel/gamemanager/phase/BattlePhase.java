package com.avatarduel.gamemanager.phase;

import com.avatarduel.gamemanager.GameManager;

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
    public void process () {
        
    }
    // public void attack() {
    //     Player player, enemy;
    //     enemy = new Player();
    //     player = new Player();
    //     if (game.turn == 1) {
    //         player = game.player1;
    //         enemy = game.player2;
    //     } else if (game.turn == 2) {
    //         player = game.player2;
    //         enemy = game.player1;
    //     }
    //     if ()
    // }

}