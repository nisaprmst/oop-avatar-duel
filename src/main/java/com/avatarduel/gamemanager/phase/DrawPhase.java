package com.avatarduel.gamemanager.phase;

import com.avatarduel.gamemanager.GameManager;
import com.avatarduel.gamemanager.*;

public class DrawPhase extends Phase {
    // ctor
    public DrawPhase(GameManager game) {
        super(game, PhaseType.DRAW);
    }
    public void nextPhase() {
        GameManager game = getGame();
        game.changePhase(new MainPhase(game));
    }
    public void phaseInfo() {
        System.out.println("Starting draw phase");
    }
    public void process(Command command, int posInHand, int posInField, int target, boolean isOnPlayer) throws Exception {
        Player player;
        player = getGame().getPlayer();
        player.draw();
        player.resetPower();
    }

}