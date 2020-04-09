package com.avatarduel.cards;

public class LandCard extends Card {
    public LandCard(int id, String name, String description, Element element, String imagePath) {
        super(id, name, description, element, imagePath, CardType.LAND);
    }
}
