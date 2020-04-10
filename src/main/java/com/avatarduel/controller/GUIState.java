package com.avatarduel.controller;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class GUIState {

    public static int state = 0;
    // State 1 -> Waiting For Target Input
    // State 0 -> Not Waiting For Target Input

    public static String command = "";
    public static int source, target = 999;
    public static int location = 999;
    // Location 1 -> player 1's hand
    // Location 2 -> player 1's skillfield
    // Location 3 -> player 1's charfield
    // Location 4 -> player 2's charfield
    // Location 5 -> player 2's skillfield
    // Location 6 -> player 2's hand

    private static IntegerProperty hovered = new SimpleIntegerProperty(999);
    private static IntegerProperty hovLocation = new SimpleIntegerProperty(999);

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
