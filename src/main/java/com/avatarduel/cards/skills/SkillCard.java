package com.avatarduel.cards.skills;

import com.avatarduel.cards.Card;
import com.avatarduel.cards.*;

public class SkillCard extends Card{
    private int power;
    private Skill skillType;
    private int idxCharacterLinked;

    public SkillCard(int id, String name, String description, Element element, String imagePath, int power, Skill skillType){
        super(id, name, description, element, imagePath, CardType.SKILL);
        this.power = power;
        this.skillType = skillType;
        this.idxCharacterLinked = -1;
    }

    public SkillCard() {
        super();
    }

    public int getPower(){
        return power;
    }
    public Skill getSkillType() {
        return skillType;
    }
    /**
     * @return the idxCharacterLinked
     */
    public int getIdxCharacterLinked() {
        return idxCharacterLinked;
    }

    /**
     * @param idxCharacterLinked the idxCharacterLinked to set
     */
    public void setIdxCharacterLinked(int idxCharacterLinked) {
        this.idxCharacterLinked = idxCharacterLinked;
    }

    /**
     * @param power the power to set
     */
    public void setPower(int power) {
        this.power = power;
    }

    /**
     * @param skillType the skillType to set
     */
    public void setSkillType(Skill skillType) {
        this.skillType = skillType;
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.print("Power: "); System.out.println(this.power);
    }
}