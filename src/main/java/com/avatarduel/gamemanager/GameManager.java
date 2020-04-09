package com.avatarduel.gamemanager;

import com.avatarduel.gamemanager.phase.*;

public class GameManager {
    // attribute
    public Phase phase;
    public Player player1;
    public Player player2;
    public int turn;

    // ctor
    GameManager() {
        this.phase = new DrawPhase(this);
        this.player1 = new Player();
        this.player2 = new Player();
    }
    GameManager(Player P1, Player P2) {
        this.phase = new DrawPhase(this);
        this.player1 = P1;
        this.player2 = P2;
    }

    public void changeTurn() {
        if (this.turn == 1) {
            this.turn == 2;
        } else {
            this.turn = 1;
        }
    }
    public void printPhaseInfo() {
        this.phase.phaseInfo();
    }
    public void changePhase(Phase f) {
        this.phase = f;
    }
}