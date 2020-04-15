package com.avatarduel.gamemanager.phase;

import com.avatarduel.gamemanager.GameManager;
import com.avatarduel.gamemanager.*;

public class DrawPhase extends Phase {
    // ctor
    public DrawPhase(GameManager game) {
        super(game, PhaseType.DRAW);
    }
    public void nextPhase() {
        game.changePhase(new MainPhase(game));
    }
    public void phaseInfo() {
        System.out.println("Starting draw phase");
    }
    public void process(Command command, int posInHand, int posInField, int target, boolean isOnPlayer) {
        Player player;
        player = game.getPlayer();
        player.draw();
        player.resetPower();
    }

}