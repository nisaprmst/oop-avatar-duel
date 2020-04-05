package com.avatarduel.cards;

abstract public class Card {
    private int id;
    private String name;
    private String description;
    private Element element;
    private String imagePath;

    public Card() {
        this.id = -1;
        this.name = "";
        this.description = "";
        this.element = Element.AIR;
        this.imagePath = "";
    }

    public Card(int id, String name, String description, Element element, String imagePath) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.element = element;
        this.imagePath = imagePath;
    }

    public Card(int id, String name, String description, String imagePath) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imagePath = imagePath;
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

    public Element getElement() { return element; }

    public String getImagePath() { return imagePath; }

    public void printInfo() {
        System.out.print("ID:"); System.out.println(this.id);
        System.out.print("Name:"); System.out.println(this.name);
        System.out.print("Description:"); System.out.println(this.description);
        System.out.print("Element:"); System.out.println(this.element);
        System.out.print("Image Path:"); System.out.println(this.imagePath);
    }
}
