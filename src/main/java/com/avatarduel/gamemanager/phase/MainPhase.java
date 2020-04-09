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
    private int count;
    public MainPhase(GameManager game, int count) {
        super(game);
        this.count = count;
    }
    public void nextPhase() {
        if (this.count == 1) {
            game.changePhase(new BattlePhase(game));
        } else if (this.count == 2) {
            game.changePhase(new DrawPhase(game));
            game.changeTurn();
        }
    }
    public void phaseInfo() {
        System.out.println("Starting main phase");
    }
    public void process() {
        //
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
        if(removed instanceof CharacterCard){
            character = (CharacterCard) removed;
            if(player.isPowerEnough(character)){
                field.placeCharacter(character,posInField);
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
        if(removed instanceof SkillCard){
            skill = (SkillCard) removed;
            if(player.isPowerEnough(skill)){
                field.placeSkill(skill,posInField);
                player.usePower(skill);
                if (skill instanceof AuraSkill) {
                    AuraSkill aura = (AuraSkill) skill;
                    this.addAuratoCharacter(aura, target);
                } else if (skill instanceof DestroySkill) {
                    DestroySkill destroy = (DestroySkill) skill;
                    this.destroyEnemyCharacter(destroy, target);
                    // setelah menghancurkan karakter lawan, kartu destroy card hancur
                    player.removeSkill(posInField);
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
        if(character.getPosition() == Position.ATTACK){
            character.setPosition(Position.DEFENSE);
        } else {
            character.setPosition(Position.ATTACK);
        }
    }
}