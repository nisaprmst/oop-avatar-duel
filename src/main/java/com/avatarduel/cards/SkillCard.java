package com.avatarduel.cards;

import com.avatarduel.cards.Card;

public class SkillCard extends Card{
    private int power;

    public SkillCard(int id, String name, String description, String imagePath, int power){
        super(id, name, description, imagePath);
        this.power = power;
    }

    public getPower(){
        return power;
    }
}