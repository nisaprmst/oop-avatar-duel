package com.avatarduel.cards.skills;

import com.avatarduel.cards.Card;
import com.avatarduel.cards.*;

public class SkillCard extends Card{
    private int power;

    public SkillCard(int id, String name, String description, Element element, String imagePath, int power){
        super(id, name, description, element, imagePath);
        this.power = power;
    }

    public int getPower(){
        return power;
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.print("Power: "); System.out.println(this.power);
    }
}