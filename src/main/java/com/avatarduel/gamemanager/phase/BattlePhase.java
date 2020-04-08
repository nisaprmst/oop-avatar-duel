package com.avatarduel.gamemanager.phase;

import com.avatarduel.gamemanager.*;
import com.avatarduel.cards.characters.*;

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
        // kyknya ga dipake

    }
    // untuk kalo ada karakter
    public void attackCharacter(int posPlayer, int posEnemy) {
        // pilih mana player mana enemy
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
        // kalo ga baru disummon brrti bisa attack
        if (player.canAttack(posPlayer)) {
            // itung selisih attack
            int att = player.getAttackAtPos(posPlayer) - enemy.getAttackAtPos(posEnemy);
            if (att >= 0) {
                enemy.removeCharacter(posEnemy);
                // kalo attack lebih besar dan posisi enemy bukan bertahan maka HP enemy berkurang
                if (att > 0 && enemy.getPositionAtPos(posEnemy) == Position.ATTACK) {
                    enemy.substractHp(att);
                }
            }
        }
    }
    // kalo gaada karakter
    public void attackHp(int posPlayer) {
        // pilih mana player mana enemy
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
        int att = player.getAttackAtPos(posPlayer);
        enemy.substractHp(att);
    }

    // attack umum
    public void attack(int posPlayer, int posEnemy) {
        // pilih mana player mana enemy
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
        if (player.getCharacterAtPos(posPlayer) != null) {
            // kalo ada karakternya maka serang kartunya
            if (enemy.getCharacterAtPos(posEnemy) != null) {
                this.attackCharacter(posPlayer, posEnemy);
            } else {
                // kalo gaada karakternya dan gaada karakter di lawan serang hp lawan
                if (enemy.isCharacterFieldEmpty()) {
                    this.attackHp(posPlayer);
                }
                // kalo gaada ga ngapa ngapain
            }
        }
        // kalo pencet player di field yg kosong ga ngapa ngapain
    }


}