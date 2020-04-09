package com.avatarduel.gamemanager.phase;

import com.avatarduel.gamemanager.GameManager;
import com.avatarduel.gamemanager.Player;
import com.avatarduel.gamemanager.Field;
import com.avatarduel.cards.characters.*;
import com.avatarduel.cards.characters.CharacterCard;
import com.avatarduel.cards.skills.*;
import com.avatarduel.cards.*;

public class MainPhase extends Phase {
    // ctor
    public MainPhase(GameManager game) {
        super(game);
    }
    public void nextPhase() {
        game.changePhase(new DrawPhase(game));
    }
    public void phaseInfo() {
        System.out.println("Starting main phase");
    }

    // meletakkan kartu karakter ke field
    public void setCharacterCard(int posInHand, int posInField){
        // pilih mana player mana enemy
        Player player, enemy;
        enemy = new Player();
        player = new Player();
        if (game.turn == 1) {
            player = game.player1;
            enemy = game.player2;
        } else {
            player = game.player2;
            enemy = game.player1;
        }
        Field field;
        field = player.getField();
        CharacterCard character;
        Card removed;
        removed = player.removeFromHand(posInHand);
        if(removed.getCardType() == CardType.CHARACTER){
            character = (CharacterCard) removed;
            if(player.isPowerEnough(character)){
                field.placeCharacterInColumn(character,posInField);
                character.setJustSummoned(true);
                character.setHasAttacked(false);
                player.usePower(character);
            }
        }
    }
    // meletakkan kartu karakter ke field
    public void setSkillCard(int posInHand, int posInField, int target){
        // pilih mana player mana enemy
        Player player, enemy;
        if (game.turn == 1) {
            player = game.player1;
            enemy = game.player2;
        } else {
            player = game.player2;
            enemy = game.player1;
        }
        Field field;
        field = player.getField();
        SkillCard skill;
        Card removed;
        removed = player.removeFromHand(posInHand);
        if(removed.getCardType() == CardType.SKILL){
            skill = (SkillCard) removed;
            if(player.isPowerEnough(skill)){
                field.placeSkillInColumn(skill,posInField);
                player.usePower(skill);
                if (skill.getSkillType() == Skill.AURA) {
                    AuraSkill aura = (AuraSkill) skill;
                    this.addAuratoCharacter(aura, target);
                } else if (skill.getSkillType() == Skill.DESTROY) {
                    DestroySkill destroy = (DestroySkill) skill;
                    this.destroyEnemyCharacter(destroy, target);
                    // setelah menghancurkan karakter lawan, kartu destroy card hancur
                    player.removeSkill(posInField);
                } else if (skill.getSkillType() == Skill.POWER) {
                    PowerSkill power = (PowerSkill) skill;
                    this.addPowerUptoCharacter(power, target);
                }
            }
        }
    }
    // memilih land card pada deck
    public void setLandCard(int posInHand){
        // pilih mana player mana enemy
        Player player, enemy;
        if (game.turn == 1) {
            player = game.player1;
            enemy = game.player2;
        } else {
            player = game.player2;
            enemy = game.player1;
        }
        LandCard land = (LandCard) player.removeFromHand(posInHand);
        player.addPower(land);
    }

    public void addAuratoCharacter (AuraSkill skill, int characterpos){
        // pilih mana player mana enemy
        Player player, enemy;
        if (game.turn == 1) {
            player = game.player1;
            enemy = game.player2;
        } else {
            player = game.player2;
            enemy = game.player1;
        }
        CharacterCard character = player.getCharacterAtPos(characterpos);
        //perubahan attack karena aura skill pada karakter yang dipilih
        int att = player.getAttackAtPos(characterpos) + skill.getAtkPoint();
        character.setAtkPoint(att);
        //perubahan defense karena aura skill pada karakter yang dipilih
        int def = player.getDefenseAtPos(characterpos) + skill.getDefPoint();
        character.setDefPoint(def);
    }

    public void destroyEnemyCharacter(DestroySkill skill, int enemypos){
        // pilih mana player mana enemy
        Player player, enemy;
        if (game.turn == 1) {
            player = game.player1;
            enemy = game.player2;
        } else {
            player = game.player2;
            enemy = game.player1;
        }
        // menghancurkan kartu karakter lawan
        enemy.removeCharacter(enemypos);
    }
    public void addPowerUptoCharacter (PowerSkill power, int characterpos) {
        // pilih mana player mana enemy
        Player player, enemy;
        if (game.turn == 1) {
            player = game.player1;
            enemy = game.player2;
        } else {
            player = game.player2;
            enemy = game.player1;
        }
        CharacterCard character = player.getCharacterAtPos(characterpos);
        //perubahan power up karena skill pada karakter yang dipilih
        character.setIsPowerUp(true);
    }
    // mengubah posisi pada kartu karakter
    public void changePositionCharacter(int idxField){
        // pilih mana player mana enemy
        Player player, enemy;
        enemy = new Player();
        player = new Player();
        if (game.turn == 1) {
            player = game.player1;
            enemy = game.player2;
        } else {
            player = game.player2;
            enemy = game.player1;
        }
        CharacterCard character;
        character = player.getCharacterAtPos(idxField);
        // kalau tidak baru menyerang pada main phase 1
        if (!character.getHasAttacked()) {
            if(character.getPosition() == Position.ATTACK){
                character.setPosition(Position.DEFENSE);
            } else {
                character.setPosition(Position.ATTACK);
            }
        }
    }
}