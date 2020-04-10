package com.avatarduel.cards;

/** Card is abstract class to representing card data in general. */
abstract public class Card {
    private final int id;
    private final String name;
    private final String description;
    private final Element element;
    private final String imagePath;
    private final CardType cardType;

    public Card() {
        id = -1;
        name = "";
        description = "";
        element = Element.AIR;
        imagePath = "";
        cardType = CardType.CHARACTER;
    }

    /**
     * Constructor
     *
     * @param id card id
     * @param name card name
     * @param description card description
     * @param element card element
     * @param imagePath card image path
     * @param cardType card type
     */
    public Card(int id, String name, String description, Element element, String imagePath, CardType cardType) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.element = element;
        this.imagePath = imagePath;
        this.cardType = cardType;
    }

    public int getId() { return this.id; }
    public String getName() { return this.name; }
    public String getDescription() { return  this.description; }
    public Element getElement() { return element; }
    public String getImagePath() { return imagePath; }
    public CardType getCardType() { return cardType; }

    /** Print all properties in Card to the console. */
    public void printInfo() {
        System.out.print("ID:"); System.out.println(this.id);
        System.out.print("Name:"); System.out.println(this.name);
        System.out.print("Description:"); System.out.println(this.description);
        System.out.print("Element:"); System.out.println(this.element);
        System.out.print("Image Path:"); System.out.println(this.imagePath);
    }
}
