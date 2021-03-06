package com.avatarduel.gamemanager.phase;

import com.avatarduel.exceptions.AlreadyPlacedCardException;
import com.avatarduel.exceptions.InvalidFieldIndexException;
import com.avatarduel.exceptions.NoCardInFieldException;
import com.avatarduel.exceptions.NotEnoughPowerException;
import com.avatarduel.gamemanager.GameManager;
import com.avatarduel.gamemanager.Player;
import com.avatarduel.gamemanager.Command;
import com.avatarduel.gamemanager.Field;
import com.avatarduel.cards.characters.*;
import com.avatarduel.cards.characters.CharacterCard;
import com.avatarduel.cards.skills.*;
import com.avatarduel.cards.*;

import java.io.IOException;
import java.net.URISyntaxException;

/** A class that fetch the GameManager data to process MainPhase of the game. */
public class MainPhase extends Phase {
    private boolean hasSummonedLandCard;
    public MainPhase(GameManager game) {
        super(game, PhaseType.MAIN);
        this.hasSummonedLandCard = false;
    }

    @Override
    public void nextPhase() {
        game.changePhase(new BattlePhase(game));
    }

    @Override
    public void phaseInfo() {
        System.out.println("Starting main phase");
    }

    @Override
    public void process(Command command, int posInHand, int posInField, int target, boolean isOnPlayer) throws Exception {
        if (command == Command.SUMMONATTACK) {
            setCharacterCardInHandToField(posInHand, posInField, Position.ATTACK);
        } else if (command == Command.SUMMONDEFENSE) {
            this.setCharacterCardInHandToField(posInHand, posInField, Position.DEFENSE);
        } else if (command == Command.SUMMONLAND) {
            this.setLandCard(posInHand);
        }else if (command == Command.CHANGEPOSITION) {
            this.changePositionCharacter(posInField);
        } else if (command == Command.PLACESKILL) {
            this.setSkillCard(posInHand, posInField, target, isOnPlayer);
        } else if (command == Command.REMOVESKILL) {
            this.removeSkillCard(posInField);
        } else if (command == Command.REMOVEHAND){
            this.removeHand(posInHand);
        }
    }
    // meletakkan kartu karakter ke field
    private void setCharacterCardInHandToField(int posInHand, int posInField, Position pos) throws NotEnoughPowerException {
        // pilih mana player mana enemy
        Player player, enemy;
        player = game.getPlayer();
        Field field;
        field = player.getField();
        CharacterCard character;
        Card removed;
        removed = player.getCardsInHand().get(posInHand);
        if(removed.getCardType() == CardType.CHARACTER){
            character = (CharacterCard) removed;
            if(!player.isPowerEnough(character)){
                System.out.println("Kena sini");
                throw new NotEnoughPowerException(character.getElement());
            }
            try {
                removed = player.removeFromHand(posInHand);
                character = (CharacterCard) removed;
                field.placeCharacterInColumn(character,posInField);
            } catch (InvalidFieldIndexException | AlreadyPlacedCardException e) {
                System.out.println(e.getMessage());
            }
            character.setJustSummoned(true);
            character.setHasAttacked(false);
            character.setPosition(pos);
            player.usePower(character);
        }
    }

    /**
     * This method place SkillCard in given index in hand
     * to the player field in given index in player skill field
     *
     * @param posInHand the index of the skill card in hand
     * @param posInField the index of skill card in field
     * @param target the index of character card want to attach
     * @param isOnPlayer booelan to decide whether the target index is on to player card or enemy card
     * @throws NotEnoughPowerException throws when player trying to use skill card when their power is not enough
     */
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
        removed = player.getCardsInHand().get(posInHand);
        if(removed.getCardType() == CardType.SKILL){
            skill = (SkillCard) removed;
            if (!player.isPowerEnough(skill)) {
                throw new NotEnoughPowerException(skill.getElement());
            }
            try {
                removed = player.removeFromHand(posInHand);
                skill = (SkillCard) removed;
                field.placeSkillInColumn(skill,posInField);
                player.usePower(skill);
                if (isOnPlayer) {
                    character = player.getCharacterAtPos(target);
                } else {
                    character = enemy.getCharacterAtPos(target);
                }
                skill.setCharacterLinked(character);
                if (skill.getSkillType() == Skill.AURA) {
                    character.addSkill(posInField, isOnPlayer);
                    AuraSkill aura = (AuraSkill) skill;
                    this.addAuratoCharacter(aura, target, isOnPlayer);
                } else if (skill.getSkillType() == Skill.DESTROY) {
                    DestroySkill destroy = (DestroySkill) skill;
                    this.destroyEnemyCharacter(destroy, target, isOnPlayer);
                    // setelah menghancurkan karakter lawan, kartu destroy card hancur
                    player.removeSkill(posInField);
                } else if (skill.getSkillType() == Skill.POWER) {
                    character.addSkill(posInField, isOnPlayer);
                    PowerUpSkill power = (PowerUpSkill) skill;
                    this.addPowerUptoCharacter(power, target, isOnPlayer);
                }
            } catch (InvalidFieldIndexException | NoCardInFieldException | AlreadyPlacedCardException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // memilih land card pada deck
    public void setLandCard(int posInHand){
        // pilih mana player mana enemy
        Player player, enemy;
        player = game.getPlayer();
        // satu main phase hanya boleh satu kali memanggil land card
        if (!this.hasSummonedLandCard) {
            LandCard land = (LandCard) player.removeFromHand(posInHand);
            player.addPower(land);
            this.hasSummonedLandCard = true;
        }
    }

    public void addAuratoCharacter (AuraSkill skill, int characterpos, boolean isOnPlayer) throws InvalidFieldIndexException, NoCardInFieldException {
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
        int att = character.getAttack() + skill.getAtkPoint();
        character.setAtkPoint(att);
        //perubahan defense karena aura skill pada karakter yang dipilih
        int def = character.getDefense() + skill.getDefPoint();
        character.setDefPoint(def);
    }

    public void destroyEnemyCharacter(DestroySkill skill, int pos, boolean isOnPlayer) throws NoCardInFieldException, InvalidFieldIndexException {
        // pilih mana player mana enemy
        Player player, enemy;
        player = game.getPlayer();
        enemy = game.getEnemy();
        // menghancurkan kartu karakter
        if (isOnPlayer) {
            // setelah diserang, kartu karakter lawan mati dan seluruh skill dihapus
            CharacterCard characterEnemy = player.getCharacterAtPos(pos);
            for (int idxCard : characterEnemy.getSkillLinkedAtEnemy()) {
                enemy.removeSkill(idxCard);
            }
            for (int idxCard : characterEnemy.getSkillLinkedAtPlayer()) {
                player.removeSkill(idxCard);
            }
            player.removeCharacter(pos);
        } else {
            // setelah diserang, kartu karakter lawan mati dan seluruh skill dihapus
            CharacterCard characterEnemy = enemy.getCharacterAtPos(pos);
            for (int idxCard : characterEnemy.getSkillLinkedAtEnemy()) {
                player.removeSkill(idxCard);
            }
            for (int idxCard : characterEnemy.getSkillLinkedAtPlayer()) {
                enemy.removeSkill(idxCard);
            }
            enemy.removeCharacter(pos);
        }
    }
    public void addPowerUptoCharacter (PowerUpSkill power, int characterpos, boolean isOnPlayer) throws InvalidFieldIndexException, NoCardInFieldException {
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
        // cek elementnya
        //perubahan power up karena skill pada karakter yang dipilih
        character.setIsPowerUp(true);
    }
    // mengubah posisi pada kartu karakter
    public void changePositionCharacter(int posInField) throws InvalidFieldIndexException, NoCardInFieldException {
        // pilih mana player mana enemy
        Player player, enemy;
        player = game.getPlayer();
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
    public void removeSkillCard(int posInField) throws InvalidFieldIndexException, NoCardInFieldException {
        Player player,enemy;
        player = game.getPlayer();
        CharacterCard characterLink;
        SkillCard skill = player.getSkillAtPos(posInField);
        characterLink = skill.getCharacterLinked();
        Position characterpos = characterLink.getPosition();

        // remove skill card
        player.removeSkill(posInField);

        if(skill.getSkillType() == Skill.AURA){
            AuraSkill aura = (AuraSkill) skill;
            //perubahan attack
            int att = characterLink.getAttack() - aura.getAtkPoint();
            characterLink.setAtkPoint(att);
            //perubahan defense
            int def = characterLink.getDefense() - aura.getDefPoint();
            characterLink.setDefPoint(def);
        }
    }

    public void removeHand(int posInHand) throws InvalidFieldIndexException, NoCardInFieldException {
        Player player;
        player = game.getPlayer();
        player.removeFromHand(posInHand);
    }
}