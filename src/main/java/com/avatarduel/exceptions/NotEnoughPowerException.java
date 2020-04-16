package com.avatarduel.exceptions;

import com.avatarduel.cards.Element;

public class NotEnoughPowerException  extends Exception {
    Element element;
    public NotEnoughPowerException(Element element) {
        super();
        this.element = element;
    }

    public String toString() {
        return "The power of element " + this.element + " is not enough";
    }
}