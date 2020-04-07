package com.avatarduel.util;

import com.avatarduel.phase.*;
import com.avatarduel.util.*;

public class GameManager {
    // attribute
    Phase phase;
    Player player1;
    Player player2;

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
    public void printPhaseInfo() {
        this.phase.phaseInfo();
    }
    public void changePhase(Phase f) {
        this.phase = f;
    }
    public void run() {

    }
}