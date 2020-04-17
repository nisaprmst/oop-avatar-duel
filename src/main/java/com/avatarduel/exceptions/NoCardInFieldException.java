package com.avatarduel.exceptions;

public class NoCardInFieldException extends Exception {
    private int index;

    public NoCardInFieldException(int index) {
        super();
        this.index = index;
    }

    public String toString() {
        return "There is no Card in index number " + this.index + " in field";
    }
}
