package com.avatarduel.gamemanager.phase;

import com.avatarduel.exceptions.InvalidFieldIndexException;
import com.avatarduel.gamemanager.*;
import com.avatarduel.cards.characters.*;
import com.avatarduel.exceptions.InvalidFieldIndexException;

public class BattlePhase extends Phase {
    // ctor
    public BattlePhase(GameManager game) {
        super(game, PhaseType.BATTLE);
    }
    public void nextPhase() {
        game.changePhase(new DrawPhase(game));
        game.changeTurn();
        game.getPlayer().getField().resetHasAttacked();
        game.getEnemy().getField().resetHasAttacked();
        game.getPlayer().resetPower();
        game.getEnemy().resetPower();
        game.getPlayer().getField().resetJustSummoned();
        game.getEnemy().getField().resetJustSummoned();
    }
    public void phaseInfo() {
        System.out.println("Starting battle phase");
    }

    // untuk kalo ada karakter
    public void attackCharacter(int posPlayer, int posEnemy) {
        // pilih mana player mana enemy
        Player player, enemy;
        player = game.getPlayer();
        enemy = game.getEnemy();
        // kalo ga baru disummon brrti bisa attack
        if (player.canAttack(posPlayer)) {
            // itung selisih attack
            int att;
            if (enemy.getPositionAtPos(posEnemy) == Position.ATTACK) {
                att = player.getAttackAtPos(posPlayer) - enemy.getAttackAtPos(posEnemy);
            } else {
                att = player.getAttackAtPos(posPlayer) - enemy.getDefenseAtPos(posEnemy);
            }
            if (att > 0) {
                enemy.removeCharacter(posEnemy);
                // kalo attack lebih besar dan posisi enemy bukan bertahan maka HP enemy berkurang
                if (enemy.getPositionAtPos(posEnemy) == Position.ATTACK || player.getIsPowerUpAtPos(posPlayer)) {
                    enemy.substractHp(att);
                }
                CharacterCard character = player.getCharacterAtPos(posPlayer);
                character.setHasAttacked(true);
            }
        }
    }
    // kalo gaada karakter
    public void attackHp(int posPlayer) {
        // pilih mana player mana enemy
        Player player, enemy;
        player = game.getPlayer();
        enemy = game.getEnemy();
        if (player.canAttack(posPlayer)) {
            int att = player.getAttackAtPos(posPlayer);
            CharacterCard character = player.getCharacterAtPos(posPlayer);
            character.setHasAttacked(true);
            enemy.substractHp(att);
        }
    }

    // attack umum
    public void process(Command command, int posInHand, int posInField, int target, boolean isOnPlayer) {

        // pilih mana player mana enemy
        Player player, enemy;
        player = game.getPlayer();
        enemy = game.getEnemy();
        if (player.getCharacterAtPos(posInField) != null) {
            // kalo ada karakternya maka serang kartunya
            if (enemy.getCharacterAtPos(target) != null) {
                this.attackCharacter(posInField, target);
            } else {
                // kalo gaada karakternya dan gaada karakter di lawan serang hp lawan
                if (enemy.isCharacterFieldEmpty()) {
                    this.attackHp(posInField);
                }
                // kalo gaada ga ngapa ngapain
            }
        }
        // kalo pencet player di field yg kosong ga ngapa ngapain
    }


}