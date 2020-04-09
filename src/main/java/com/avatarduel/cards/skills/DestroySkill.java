package com.avatarduel.cards.skills;

import com.avatarduel.cards.Element;

public class DestroySkill extends SkillCard{
    public DestroySkill(int id, String name, String description, Element element, String imagePath, int power){
        super(id, name, description, element, imagePath, power, Skill.DESTROY);
    }
}