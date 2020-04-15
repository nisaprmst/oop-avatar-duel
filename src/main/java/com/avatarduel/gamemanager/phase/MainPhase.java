package com.avatarduel.gamemanager.phase;

import com.avatarduel.exceptions.InvalidFieldIndexException;
import com.avatarduel.exceptions.NotEnoughPowerException;
import com.avatarduel.gamemanager.GameManager;
import com.avatarduel.gamemanager.Player;
import com.avatarduel.gamemanager.Command;
import com.avatarduel.gamemanager.Field;
import com.avatarduel.cards.characters.*;
import com.avatarduel.cards.characters.CharacterCard;
import com.avatarduel.cards.skills.*;
import com.avatarduel.cards.*;

public class MainPhase extends Phase {
    // ctor
    public MainPhase(GameManager game) {
        super(game, PhaseType.MAIN);
    }
    public void nextPhase() {
        game.changePhase(new BattlePhase(game));
    }
    public void phaseInfo() {
        System.out.println("Starting main phase");
    }
    public void process(Command command, int posInHand, int posInField, int target, boolean isOnPlayer) {
        if (command == Command.SUMMONATTACK) {
            try {
                this.setCharacterCard(posInHand, posInField, Position.ATTACK);
            } catch (NotEnoughPowerException e) {
                System.out.println(e.getMessage());
            }
        } else if (command == Command.SUMMONDEFENSE) {
            try {
                this.setCharacterCard(posInHand, posInField, Position.DEFENSE);
            } catch (NotEnoughPowerException e) {
                System.out.println(e.getMessage());
            }
        } else if (command == Command.CHANGEPOSITION) {
            this.changePositionCharacter(posInField);
        } else if (command == Command.PLACESKILL) {
            try {
                this.setSkillCard(posInHand, posInField, target, isOnPlayer);
            } catch (NotEnoughPowerException e) {
                System.out.println(e.getMessage());
            }
        } else if (command == Command.REMOVESKILL) {
            this.removeSkillCard(posInField);
        } else {
            
        }
    }

    // meletakkan kartu karakter ke field
    public void setCharacterCard(int posInHand, int posInField, Position pos) throws NotEnoughPowerException {
        // pilih mana player mana enemy
        Player player, enemy;
        player = game.getPlayer();
        enemy = game.getEnemy();
        Field field;
        field = player.getField();
        CharacterCard character;
        Card removed;
        removed = player.removeFromHand(posInHand);
        if(removed.getCardType() == CardType.CHARACTER){
            character = (CharacterCard) removed;
            if(!player.isPowerEnough(character)){
                throw new NotEnoughPowerException(character.getElement());
            }
            try {
                field.placeCharacterInColumn(character,posInField);
            } catch (InvalidFieldIndexException e) {
                System.out.println(e.getMessage());
            }
            character.setJustSummoned(true);
            character.setHasAttacked(false);
            character.setPosition(pos);
            player.usePower(character);
        }
    }
    // meletakkan kartu karakter ke field
    public void setSkillCard(int posInHand, int posInField, int target, boolean isOnPlayer) throws NotEnoughPowerException {
        // pilih mana player mana enemy
        Player player, enemy;
        player = game.getPlayer();
        enemy = game.getEnemy();
        Field field;
        field = player.getField();
        SkillCard skill;
        CharacterCard character;
        Card removed;
        removed = player.removeFromHand(posInHand);
        if(removed.getCardType() == CardType.SKILL){
            skill = (SkillCard) removed;
            if(!player.isPowerEnough(skill)){
                throw new NotEnoughPowerException(skill.getElement());
            }
            try {
                field.placeSkillInColumn(skill,posInField);
                player.usePower(skill);
                if (isOnPlayer) {
                    character = player.getCharacterAtPos(target);
                } else {
                    character = enemy.getCharacterAtPos(target);
                }
                skill.setCharacterLinked(character);
                if (skill.getSkillType() == Skill.AURA) {
                    character.addSkill(skill);
                    AuraSkill aura = (AuraSkill) skill;
                    this.addAuratoCharacter(aura, target, isOnPlayer);
                } else if (skill.getSkillType() == Skill.DESTROY) {
                    DestroySkill destroy = (DestroySkill) skill;
                    this.destroyEnemyCharacter(destroy, target, isOnPlayer);
                    // setelah menghancurkan karakter lawan, kartu destroy card hancur
                    player.removeSkill(posInField);
                } else if (skill.getSkillType() == Skill.POWER) {
                    character.addSkill(skill);
                    PowerUpSkill power = (PowerUpSkill) skill;
                    this.addPowerUptoCharacter(power, target);
                }
            } catch (InvalidFieldIndexException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    // memilih land card pada deck
    public void setLandCard(int posInHand){
        // pilih mana player mana enemy
        Player player, enemy;
        player = game.getPlayer();
        enemy = game.getEnemy();
        LandCard land = (LandCard) player.removeFromHand(posInHand);
        player.addPower(land);
    }

    public void addAuratoCharacter (AuraSkill skill, int characterpos, boolean isOnPlayer) {
        // pilih mana player mana enemy
        Player player, enemy;
        player = game.getPlayer();
        enemy = game.getEnemy();
        CharacterCard character;
        if (isOnPlayer) {
            character = player.getCharacterAtPos(characterpos);
        } else {
            character = enemy.getCharacterAtPos(characterpos);
        }
        //perubahan attack karena aura skill pada karakter yang dipilih
        int att = player.getAttackAtPos(characterpos) + skill.getAtkPoint();
        character.setAtkPoint(att);
        //perubahan defense karena aura skill pada karakter yang dipilih
        int def = player.getDefenseAtPos(characterpos) + skill.getDefPoint();
        character.setDefPoint(def);
    }

    public void destroyEnemyCharacter(DestroySkill skill, int pos, boolean isOnPlayer){
        // pilih mana player mana enemy
        Player player, enemy;
        player = game.getPlayer();
        enemy = game.getEnemy();
        // menghancurkan kartu karakter
        if (isOnPlayer) {
            player.removeCharacter(pos);
        } else {
            enemy.removeCharacter(pos);
        }
    }
    public void addPowerUptoCharacter (PowerUpSkill power, int characterpos) {
        // pilih mana player mana enemy
        Player player, enemy;
        player = game.getPlayer();
        enemy = game.getEnemy();
        CharacterCard character = player.getCharacterAtPos(characterpos);
        // cek elementnya
        //perubahan power up karena skill pada karakter yang dipilih
        character.setIsPowerUp(true);
    }
    // mengubah posisi pada kartu karakter
    public void changePositionCharacter(int posInField) {
        // pilih mana player mana enemy
        Player player, enemy;
        player = game.getPlayer();
        enemy = game.getEnemy();
        CharacterCard character;
        character = player.getCharacterAtPos(posInField);
        // kalau tidak baru menyerang pada main phase 1
        if (!character.getHasAttacked()) {
            if(character.getPosition() == Position.ATTACK){
                character.setPosition(Position.DEFENSE);
            } else {
                character.setPosition(Position.ATTACK);
            }
        }
    }
    // menghapus kartu skill tertentu
    public void removeSkillCard(int posInField){
        Player player,enemy;
        player = game.getPlayer();
        enemy = game.getEnemy();
        CharacterCard characterLink;
        SkillCard skill = player.getSkillAtPos(posInField);
        characterLink = skill.getCharacterLinked();
        Position characterpos = characterLink.getPosition();
        AuraSkill aura = (AuraSkill) skill;

        // remove skill card
        player.removeSkill(posInField);
        
        //perubahan attack
        int att = characterLink.getAttack() - aura.getAtkPoint();
        characterLink.setAtkPoint(att);
        //perubahan defense
        int def = characterLink.getDefense() - aura.getDefPoint();
        characterLink.setDefPoint(def);
    }
}