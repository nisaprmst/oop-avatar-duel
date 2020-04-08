package com.avatarduel.gamemanager.phase;

import com.avatarduel.cards.LandCard;
import com.avatarduel.cards.characters.CharacterCard;
import com.avatarduel.cards.characters.Position;
import com.avatarduel.cards.skills.AuraSkill;
import com.avatarduel.cards.skills.DestroySkill;
import com.avatarduel.gamemanager.Field;
import com.avatarduel.gamemanager.GameManager;
import com.avatarduel.gamemanager.Player;

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
        }
    }
    public void phaseInfo() {
        System.out.println("Starting main phase");
    }
    public void process() {
        //
    }
    // meletakkan kartu karakter ke field
    public void setCharacterCard(CharacterCard character, int position){
        Player player;
        player = new Player();
        Field field;
        field = new Field();

		if(player.isPowerEnough(character)){
            if(field.isCharacterEmpty()){
                field.placeCharacter(character,position);
                character.setJustSummoned(true);
                character.setHasAttacked(false);
            }
        }
    }
    // memilih land card pada deck
    public void setLandCard(LandCard land){
        Player player;
        player = new Player();

        player.addPower(land);
    }
    // memilih aura skill pada deck
//    public void setAuraSkill(AuraSkill skill, int position, CharacterCard character, int characterpos){
//        Player player;
//        player = new Player();
//
//        Field field;
//        field = new Field();
//        if(field.isSkillEmpty()){
//            if(player.isPowerEnough()){
//                field.placeSkill(skill, position);
//                //perubahan attack karena aura skill pada karakter yang dipilih
//                int att = player.getAttackAtPos(characterpos) + skill.getAtkPoint();
//                character.setAtkPoint(att);
//                //perubahan defense karena aura skill pada karakter yang dipilih
//                int def = player.getDefenseAtPos(characterpos) + skill.getDefPoint();
//                character.setDefPoint(def);
//            }
//        }
//    }
    // memilih destroy kill pada deck
//    public void setDestroySkill(DestroySkill skill, int position, int enemypos){
//        Player enemy, player;
//        enemy = new Player();
//        player = new Player();
//
//        Field fieldplayer, fieldenemy;
//        fieldenemy = new Field();
//        fieldplayer = new Field();
//        if(fieldplayer.isSkillEmpty()){
//            if(player.isPowerEnough()){
//                // menghancurkan kartu karakter lawan
//                enemy.removeCharacter(enemypos);
//                // setelah menghancurkan karakter lawan, kartu destroy card hancur
//                player.removeSkill(position);
//            }
//        }
//    }
    // mengubah posisi pada kartu karakter
    public void setPositionCharacter(CharacterCard character){
        if(character.getPosition() == Position.ATTACK){
            character.setPosition(Position.DEFENSE);
        } else {
            character.setPosition(Position.ATTACK);
        }
    }
}