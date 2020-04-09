package com.avatarduel.controller;

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

}
