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

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.print("Attack:"); System.out.println(this.atkPoint);
        System.out.print("Defense:"); System.out.println(this.defPoint);
    }
}