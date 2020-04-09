package com.avatarduel.gamemanager.phase;

import com.avatarduel.exceptions.InvalidFieldIndexException;
import com.avatarduel.gamemanager.*;
import com.avatarduel.cards.characters.*;
import com.avatarduel.exceptions.InvalidFieldIndexException;

public class BattlePhase extends Phase {
    // ctor
    public BattlePhase(GameManager game) {
        super(game);
    }
    public void nextPhase() {
        game.changePhase(new MainPhase(game));
        game.changeTurn();
        game.player1.getField().resetHasAttacked();
        game.player2.getField().resetHasAttacked();
        game.player1.resetPower();
        game.player2.resetPower();
        game.player1.getField().resetJustSummoned();
        game.player2.getField().resetJustSummoned();
    }
    public void phaseInfo() {
        System.out.println("Starting battle phase");
    }

    public void process(Command command, int posInHand, int posInField, int target, boolean isOnPlayer) {
        
    }
    // untuk kalo ada karakter
    public void attackCharacter(int posPlayer, int posEnemy) throws InvalidFieldIndexException {
        // pilih mana player mana enemy
        Player player, enemy;
        if (game.turn == 1) {
            player = game.player1;
            enemy = game.player2;
        } else {
            player = game.player2;
            enemy = game.player1;
        }
        // kalo ga baru disummon brrti bisa attack
        if (player.canAttack(posPlayer)) {
            // itung selisih attack
            int att;
            if (enemy.getPositionAtPos(posEnemy) == Position.ATTACK) {
                att = player.getAttackAtPos(posPlayer) - enemy.getAttackAtPos(posEnemy);
            } else {
                att = player.getAttackAtPos(posPlayer) - enemy.getDefenseAtPos(posEnemy);
            }
            if (att >= 0) {
                enemy.removeCharacter(posEnemy);
                // kalo attack lebih besar dan posisi enemy bukan bertahan maka HP enemy berkurang
                if (att > 0) {
                    if (enemy.getPositionAtPos(posEnemy) == Position.ATTACK || player.getIsPowerUpAtPos(posPlayer)) {
                        enemy.substractHp(att);
                    }
                }
                CharacterCard character = player.getCharacterAtPos(posPlayer);
                character.setHasAttacked(true);
            }
        }
    }
    // kalo gaada karakter
    public void attackHp(int posPlayer) throws InvalidFieldIndexException {
        // pilih mana player mana enemy
        Player player, enemy;
        if (game.turn == 1) {
            player = game.player1;
            enemy = game.player2;
        } else {
            player = game.player2;
            enemy = game.player1;
        }
        if (player.canAttack(posPlayer)) {
            int att = player.getAttackAtPos(posPlayer);
            CharacterCard character = player.getCharacterAtPos(posPlayer);
            character.setHasAttacked(true);
            enemy.substractHp(att);
        }
    }

    // attack umum
    public void attack(int posPlayer, int posEnemy) throws InvalidFieldIndexException {
        // pilih mana player mana enemy
        Player player, enemy;
        if (game.turn == 1) {
            player = game.player1;
            enemy = game.player2;
        } else {
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