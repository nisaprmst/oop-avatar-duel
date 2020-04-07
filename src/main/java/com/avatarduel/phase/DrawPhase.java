package com.avatarduel.phase;

import com.avatarduel.util.*;
import com.avatarduel.cards.*;

public class DrawPhase extends Phase {
    // ctor
    public DrawPhase(GameManager game) {
        super(game);
    }
    public void nextPhase() {
        game.changePhase(new MainPhase(game, 1));
    }
    public void phaseInfo() {
        System.out.println("Starting draw phase");
    }
    public void process() {
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
        Card card = player.drawCard();
    }

}