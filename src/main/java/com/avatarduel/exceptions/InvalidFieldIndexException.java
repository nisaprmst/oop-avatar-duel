package com.avatarduel.exceptions;

public class InvalidFieldIndexException extends Exception{
    private int index;

    public InvalidFieldIndexException(int index) {
        super();
        this.index = index;
    }

    public String toString() {
        return this.index + " is invalid index access";
    }
}
