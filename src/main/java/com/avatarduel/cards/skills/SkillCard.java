package com.avatarduel.cards.skills;

import com.avatarduel.cards.Card;
import com.avatarduel.cards.characters.CharacterCard;
import com.avatarduel.cards.*;

public class SkillCard extends Card{
    private int power;
    private Skill skillType;
    private CharacterCard characterLinked;

    public SkillCard(int id, String name, String description, Element element, String imagePath, int power, Skill skillType){
        super(id, name, description, element, imagePath, CardType.SKILL);
        this.power = power;
        this.skillType = skillType;
        this.characterLinked = null;
    }

    public SkillCard() {
        super(-1);
    }

    public int getPower(){
        return power;
    }
    public Skill getSkillType() {
        return skillType;
    }
    public CharacterCard getCharacterLinked() {
        return characterLinked;
    }

    public void setPower(int power) {
        this.power = power;
    }
    public void setCharacterLinked(CharacterCard characterLinked) {
        this.characterLinked = characterLinked;
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.print("Power: "); System.out.println(this.power);
    }
}