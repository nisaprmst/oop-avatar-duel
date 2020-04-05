package com.avatarduel.cards.skills;

public class AuraSkill extends SkillCard{
    private int atkPoint;
    private int defPoint;

    public AuraSkill(int id, String name, String description, String imagePath, int power, int atkPoint, int defPoint){
        super(id, name, description, imagePath, power);
        this.atkPoint = atkPoint;
        this.defPoint = defPoint;
    }

    public int getAtkPoint(){
        return atkPoint;
    }

    public int getDefPoint(){
        return defPoint;
    }
}