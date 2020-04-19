package com.avatarduel.gamemanager.phase;

import com.avatarduel.exceptions.NoCardInFieldException;
import com.avatarduel.gamemanager.*;
import com.avatarduel.cards.characters.*;
import com.avatarduel.exceptions.InvalidFieldIndexException;

/** A class that fetch the GameManager data to process the battle phase of the game. */
public class BattlePhase extends Phase {
    // ctor
    public BattlePhase(GameManager game) {
        super(game, PhaseType.BATTLE);
    }

    @Override
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

    @Override
    public void phaseInfo() {
        System.out.println("Starting battle phase");
    }

    // untuk kalo ada karakter
    public void attackCharacter(int posPlayer, int posEnemy) throws InvalidFieldIndexException, NoCardInFieldException {
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
                // kalo attack lebih besar dan posisi enemy bukan bertahan maka HP enemy berkurang
                if (enemy.getPositionAtPos(posEnemy) == Position.ATTACK || player.getIsPowerUpAtPos(posPlayer)) {
                    enemy.substractHp(att);
                } 
                // setelah diserang, kartu karakter lawan mati dan seluruh skill dihapus
                CharacterCard characterEnemy = enemy.getCharacterAtPos(posEnemy);
                for (int idxCard : characterEnemy.getSkillLinkedAtEnemy()) {
                    player.removeSkill(idxCard);
                }
                for (int idxCard : characterEnemy.getSkillLinkedAtPlayer()) {
                    enemy.removeSkill(idxCard);
                }
                enemy.removeCharacter(posEnemy);
                CharacterCard characterPlayer = player.getCharacterAtPos(posPlayer);
                characterPlayer.setHasAttacked(true); // satu karakter hanya bisa menyerang 1x tiap turn
            }
        }
    }
    // kalo gaada karakter
    public void attackHp(int posPlayer) throws InvalidFieldIndexException, NoCardInFieldException {
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
    /*public void process(Command command, int posInHand, int posInField, int target, boolean isOnPlayer) throws Exception {

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
    }*/

    public void process(Command command, int posInHand, int posInField, int target, boolean isOnPlayer) throws Exception {

        // pilih mana player mana enemy
        Player player, enemy;
        player = game.getPlayer();
        enemy = game.getEnemy();
        // kalo gaada karakternya dan gaada karakter di lawan serang hp lawan
        if (enemy.isCharacterFieldEmpty()) {
            this.attackHp(posInField);
        } else if (player.getCharacterAtPos(posInField) != null) {
            // kalo ada karakternya maka serang kartunya
            if (enemy.getCharacterAtPos(target) != null) {
                this.attackCharacter(posInField, target);
            } else {

                // kalo gaada ga ngapa ngapain
            }
        }
        // kalo pencet player di field yg kosong ga ngapa ngapain
    }


}