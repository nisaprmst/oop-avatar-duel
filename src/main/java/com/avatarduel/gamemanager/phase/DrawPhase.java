package com.avatarduel.gamemanager.phase;

import com.avatarduel.gamemanager.GameManager;
import com.avatarduel.gamemanager.*;

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
    public void process(Command command, int posInHand, int posInField, int target, boolean isOnPlayer) {
        Player player, enemy;
        enemy = new Player();
        player = new Player();
        if (game.turn == 1) {
            player = game.player1;
            enemy = game.player2;
        } else if (game.turn == 2) {
            player = game.player2;
            enemy = game.player1;
        }
        player.draw();
        player.resetPower();
    }

}