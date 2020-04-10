package com.avatarduel.cards.skills;

import com.avatarduel.cards.Element;

public class PowerUpSkill extends SkillCard{
    public PowerUpSkill(int id, String name, String description, Element element, String imagePath, int power){
        super(id, name, description, element, imagePath, power, Skill.POWER);
    }
}