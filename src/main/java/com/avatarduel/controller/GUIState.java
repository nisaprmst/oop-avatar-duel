package com.avatarduel.controller;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;


/**
 * GUIState is a public class managing the GUI State of the program.
 * <p>
 *     It holds all the information of what command is currently processed,
 *     the source, target, and more information
 * </p>
 */
public class GUIState {


    /**
     *  An Integer Property to be listened about GUIState
     */
    public static IntegerProperty state = new SimpleIntegerProperty(0);
    // State 2 -> Waiting for Field Input
    // State 1 -> Waiting For Target Input
    // State 0 -> Not Waiting For Target Input

    public static String command = "";
    public static int source, target = 999;
    public static int sourceLocation, targetLocation = 999;
    // Location 1 -> player 1's hand
    // Location 2 -> player 1's skillfield
    // Location 3 -> player 1's charfield
    // Location 4 -> player 2's charfield
    // Location 5 -> player 2's skillfield
    // Location 6 -> player 2's hand

    /**
     * An Integer Property to be listened about the field index
     */
    private static IntegerProperty fieldIndex = new SimpleIntegerProperty(999);

    /**
     *  An Integer Property to be listened about the field location
     */
    private static IntegerProperty fieldLocation = new SimpleIntegerProperty(999);
    // Location 1 -> player 1's skillfield
    // Location 2 -> player 1's charfield
    // Location 3 -> player 2's charfield
    // Location 4 -> player 2's skillfield


    /**
     * Set the PropertyVariables to the initialized value
     */
    public static void resetVariables(){
        setFieldLocation(999);
        setFieldIndex(999);
        setHovLocation(999);
        setHovered(999);
    }


    public static int getFieldIndex() {
        return fieldIndex.get();
    }

    public static IntegerProperty fieldIndexProperty() {
        return fieldIndex;
    }

    public static void setFieldIndex(int fieldIndex) {
        GUIState.fieldIndex.set(fieldIndex);
    }

    public static int getFieldLocation() {
        return fieldLocation.get();
    }

    public static IntegerProperty fieldLocationProperty() {
        return fieldLocation;
    }

    public static void setFieldLocation(int fieldLocation) {
        GUIState.fieldLocation.set(fieldLocation);
    }

    private static IntegerProperty hovered = new SimpleIntegerProperty(999);
    private static IntegerProperty hovLocation = new SimpleIntegerProperty(999);

    public static int getState() {
        return state.get();
    }

    public static IntegerProperty stateProperty() {
        return state;
    }

    public static void setState(int state) {
        GUIState.state.set(state);
    }

    public static int getHovered() {
        return hovered.get();
    }

    public static IntegerProperty hoveredProperty() {
        return hovered;
    }

    public static void setHovered(int hov) {
        hovered.set(hov);
    }

    public static int getHovLocation() {
        return hovLocation.get();
    }

    public static IntegerProperty hovLocationProperty() {
        return hovLocation;
    }

    public static void setHovLocation(int hovloc) {
        hovLocation.set(hovloc);
    }
}
