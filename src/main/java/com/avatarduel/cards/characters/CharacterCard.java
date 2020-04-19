package com.avatarduel.cards.characters;

import java.util.ArrayList;

import com.avatarduel.cards.Card;
import com.avatarduel.cards.CardType;
import com.avatarduel.cards.Element;
import com.avatarduel.cards.skills.SkillCard;

/**
 * This class inherit Card class to representing additional CharacterCard data
 * and several method for CharacterCard.
 */
public class CharacterCard extends Card {
    private int attack;
    private int defense;
    private int power;
    private boolean hasAttacked; /* If is set true, the CharacterCard has already attack */
    private Position position; /* Can be either ATTACK or DEFENSE */
    private boolean justSummoned; /* If is set true, the CharacterCard is just summoned to the field and can't attack */
    private boolean isPowerUp; /* If is set true, the CharacterCard is equipped with power up skill */
    private ArrayList<Integer> skillLinkedAtPlayer;
    private ArrayList<Integer> skillLinkedAtEnemy;
    /**
     * Parameterized constructor. The fifth first argument are the same with the parent.
     * @param attack The integer value of character attack point.
     * @param defense The integer value of character defense point.
     * @param power The integer value of character power use point.
     */
    public CharacterCard(int id, String name, String description, Element element, String imagePath,
                         int attack, int defense, int power) {
        super(id, name, description, element, imagePath, CardType.CHARACTER);
        this.attack = attack;
        this.defense = defense;
        this.power = power;
        this.position = Position.ATTACK;
        this.hasAttacked = false;
        this.justSummoned = false;
        this.isPowerUp = false;
        this.skillLinkedAtPlayer = new ArrayList<>();
        this.skillLinkedAtEnemy = new ArrayList<>();
    }

    public CharacterCard() { super(-1); }

    public CharacterCard(int id) {
        super(id);
    }

    public int getAttack() { return this.attack; }
    public int getDefense() { return this.defense; }
    public int getPower() { return this.power; }
    public boolean getHasAttacked() { return this.hasAttacked; }
    public Position getPosition() { return this.position; }
    public boolean getJustSummoned() { return this.justSummoned; }
    public boolean getIsPowerUp() { return this.isPowerUp; }
    public ArrayList<Integer> getSkillLinkedAtEnemy() { return skillLinkedAtEnemy; }
    public ArrayList<Integer> getSkillLinkedAtPlayer() { return skillLinkedAtPlayer; }

    public void setHasAttacked(boolean param) { this.hasAttacked = param; }
    public void setPosition(Position pos) { this.position = pos; }
    public void setAtkPoint(int att) { this.attack = att; }
    public void setDefPoint(int def) { this.defense = def; }
    public void setJustSummoned(boolean param) { this.justSummoned = param; }
    public void setIsPowerUp(boolean param) { this.isPowerUp = param; }
    public void setSkillLinkedAtEnemy(ArrayList<Integer> skillLinkedAtEnemy) {
        this.skillLinkedAtEnemy = skillLinkedAtEnemy;
    }
    public void setSkillLinkedAtPlayer(ArrayList<Integer> skillLinkedAtPlayer) {
        this.skillLinkedAtPlayer = skillLinkedAtPlayer;
    }

    public void addSkill(int index, boolean isOnPlayer) {
        if (isOnPlayer) {
            this.skillLinkedAtPlayer.add(index);
        } else {
            this.skillLinkedAtEnemy.add(index);
        }
    }
    public void removeSkill(int index, boolean isOnPlayer) {
        if (isOnPlayer) {
            this.skillLinkedAtPlayer.remove(index);
        } else {
            this.skillLinkedAtEnemy.remove(index);
        }
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.print("Attack:"); System.out.println(this.attack);
        System.out.print("Defense:"); System.out.println(this.defense);
        System.out.print("Power:"); System.out.println(this.power);
    }
}
