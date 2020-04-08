package com.avatarduel.cards;

/**
 * Card is abstract class to representing card data in general.
 */
abstract public class Card {
    private int id;
    private String name;
    private String description;
    private Element element;
    private String imagePath;

    /**
     * Constructor
     *
     * @param id card id
     * @param name card name
     * @param description card description
     * @param element card element
     * @param imagePath card image path
     */
    public Card(int id, String name, String description, Element element, String imagePath) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.element = element;
        this.imagePath = imagePath;
    }

    public int getId() { return this.id; }
    public String getName() { return this.name; }
    public String getDescription() { return  this.description; }
    public Element getElement() { return element; }
    public String getImagePath() { return imagePath; }

    /**
     * Print all properties in Card to the console
     */
    public void printInfo() {
        System.out.print("ID:"); System.out.println(this.id);
        System.out.print("Name:"); System.out.println(this.name);
        System.out.print("Description:"); System.out.println(this.description);
        System.out.print("Element:"); System.out.println(this.element);
        System.out.print("Image Path:"); System.out.println(this.imagePath);
    }
}
