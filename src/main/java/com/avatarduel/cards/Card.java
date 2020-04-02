package com.avatarduel.cards;

abstract public class Card {
    private int id;
    private String name;
    String description;
    private Element element;
    private String imagePath;

    public Card() {
        this.id = -1;
        this.name = "";
        this.description = "";
        this.element = Element.AIR;
    }

    public Card(int id, String name, String description, Element element) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.element = element;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return  this.description;
    }

    public Element getElement() {
        return element;
    }
}
