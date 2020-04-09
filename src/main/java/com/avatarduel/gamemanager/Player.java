package com.avatarduel.gamemanager;

import com.avatarduel.cards.*;
import com.avatarduel.cards.characters.CharacterCard;
import com.avatarduel.cards.characters.Position;
import com.avatarduel.cards.skills.AuraSkill;
import com.avatarduel.cards.skills.DestroySkill;
import com.avatarduel.exceptions.EmptyDeckException;
import com.avatarduel.exceptions.InvalidFieldIndexException;
import com.avatarduel.cards.skills.SkillCard;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

public class Player {
    // atribut
    private int hp;
    private Deck deck;
    private ArrayList<Card> cardsInHand;
    private Field field;
    private Map<Element, Integer> currPower; // untuk menyimpan nilai power saat main
    private Map<Element, Integer> power; // untuk menyimpan nilai maksimal power

    // ctor 
    public Player() {
        this.hp = 0;
        this.deck = new Deck();
        this.cardsInHand = new ArrayList<>();
        this.field = new Field();
        this.power = new HashMap<>();
        this.currPower = new HashMap<>();
        for (Element el: Element.values()) {
            this.power.put(el, 0);
            this.currPower.put(el, 0);
        }
        try {
            CardLoader cl = new CardLoader();
            cl.loadLandCardsFromFile("../card/data/land.csv");
            cl.loadCharacterCardsFromFile("../card/data/character.csv");
            cl.loadAuraSkillFromFile("../card/data/skill_aura.csv");

            deck.loadDeck(cl.getLoadedCards());
            System.out.println(deck.size());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        for (int i = 0; i < 7; i++) {
            try {
                this.cardsInHand.add(this.deck.drawCard());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    // getter
    public int getHp() {
        return this.hp;
    }
    public Deck getDeck() {
        return this.deck;
    }
    public ArrayList<Card> getCardsInHand() {
        return this.cardsInHand;
    }
    public Field getField() {
        return this.field;
    }
    public Map<Element, Integer> getPower() {
        return this.power;
    }
    public int getAttackAtPos(int pos) throws InvalidFieldIndexException {
        return this.field.getCharacterInColumn(pos).getAttack();
    }
    public int getDefenseAtPos(int pos) throws InvalidFieldIndexException {
        return this.field.getCharacterInColumn(pos).getDefense();
    }
    public Position getPositionAtPos(int pos) throws InvalidFieldIndexException {
        return this.field.getCharacterInColumn(pos).getPosition();
    }
    public CharacterCard getCharacterAtPos(int pos) throws InvalidFieldIndexException {
        return this.field.getCharacterInColumn(pos);
    }
    public SkillCard getSkillAtPos(int pos) throws InvalidFieldIndexException {
        return this.field.getSkillInColumn(pos);
    }
    public boolean getIsPowerUpAtPos(int pos) throws InvalidFieldIndexException {
        return this.field.getCharacterInColumn(pos).getIsPowerUp();
    }
    // setter
    public void setHp(int hp) {
        this.hp = hp;
    }
    public void setDeck(Deck deck) {
        this.deck = deck;
    }
    public void setCardsInHand(ArrayList<Card> cards) {
        this.cardsInHand = cards;
    }
    public void setField(Field field) {
        this.field = field;
    }
    public void setPower(Map<Element, Integer> power) {
        this.power = power;
    }

    // method
    public void substractHp(int val) {
        this.hp -= val;
    }
    public void draw() {
        Card card = this.deck.drawCard();
        this.cardsInHand.add(card);
    }
    public void addPower(LandCard land) {
        this.power.replace(land.getElement(), this.power.get(land.getElement()) + 1);
    }
    public void resetPower() {
        for (Element el: Element.values()) {
            this.currPower.replace(el, this.power.get(el));
        }
    }
    public boolean isPowerEnough(CharacterCard character) {
        return this.currPower.get(character.getElement()) >= character.getPower();
    }
    public boolean isPowerEnough(SkillCard skill) {
        return this.currPower.get(skill.getElement()) >= skill.getPower();
    }
    public void usePower(CharacterCard character) {
        if (isPowerEnough(character)) {
            this.currPower.replace(character.getElement(), this.currPower.get(character.getElement())-character.getPower());
        }
    }
    public void usePower(SkillCard skill) {
        if (isPowerEnough(skill)) {
            this.currPower.replace(skill.getElement(), this.currPower.get(skill.getElement())-skill.getPower());
        }
    }
    public Card removeFromHand(int idxCard) {
        return this.deck.remove(idxCard);
    }
    public void removeCharacter(int idxCard) {
        this.field.removeCharacter(idxCard);
    }
    public void removeSkill(int idxCard) {
        this.field.removeSkill(idxCard);
    }
    public boolean canAttack(int position) throws InvalidFieldIndexException {
        CharacterCard card;
        card = this.field.getCharacterInColumn(position);
        boolean ret = card.getJustSummoned();
        return !ret;
    }
    public boolean canChangePos(int position) throws InvalidFieldIndexException {
        CharacterCard card;
        card = this.field.getCharacterInColumn(position);
        boolean ret = card.getHasAttacked();
        return !ret;
    }
    public boolean isCharacterFieldEmpty() {
        return this.field.isCharacterEmpty();
    }
}