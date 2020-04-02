package com.avatarduel.cards;

abstract public class Card {
    private int id;
    private String name;
    String description;

    public Card(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
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
}
