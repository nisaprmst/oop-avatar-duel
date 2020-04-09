package com.avatarduel.gamemanager;

import com.avatarduel.cards.*;
import com.avatarduel.cards.characters.CharacterCard;
import com.avatarduel.cards.characters.Position;

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
        for (int i = 0; i < 7; i++) {
            this.cardsInHand.add(this.deck.drawCard());
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
    public int getAttackAtPos(int pos) {
        return this.field.getCharacterRow(pos).getAttack();
    }
    public int getDefenseAtPos(int pos) {
        return this.field.getCharacterRow(pos).getDefense();
    }
    public Position getPositionAtPos(int pos) {
        return this.field.getCharacterRow(pos).getPosition();
    }
    public CharacterCard getCharacterAtPos(int pos) {
        return this.field.getCharacterRow(pos);
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
    public boolean isPowerEnoughAura(AuraSkill aura){
        return this.currPower.get(aura.getElement()) >= aura.getPower();
    }
    public boolean isPowerEnoughDestroy(DestroySkill dest){
        return this.currPower.get(dest.getElement()) >= dest.getPower();
    }
    public void usePower(CharacterCard character) {
        if (isPowerEnough(character)) {
            this.currPower.replace(character.getElement(), this.currPower.get(character.getElement())-character.getPower());
        }
    }
    public void usePowerAura(AuraSkill aura){
        if(isPowerEnoughAura(aura)){
            this.currPower.replace(aura.getElement(), this.currPower.get(aura.getElement())-aura.getPower())
        }
    }
    public void usePowerDestroy(DestroySkill dest){
        if(isPowerEnoughDestroy(dest)){
            this.currPower.replace(dest.getElement(), this.currPower.get(dest.getElement())-dest.getPower())
        }
    }
    public void removeCharacter(int idxCard) {
        this.field.removeCharacter(idxCard);
    }
    public void removeSkill(int idxCard) {
        this.field.removeSkill(idxCard);
    }
    public boolean canAttack(int position) {
        CharacterCard card;
        card = this.field.getCharacterRow(position);
        boolean ret = card.getJustSummoned();
        return !ret;
    }
    public boolean canChangePos(int position) {
        CharacterCard card;
        card = this.field.getCharacterRow(position);
        boolean ret = card.getHasAttacked();
        return !ret;
    }
    public boolean isCharacterFieldEmpty() {
        return this.field.isCharacterEmpty();
    }
}