package com.avatarduel.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.TextFlow;

import java.net.URL;
import java.security.Guard;
import java.util.ResourceBundle;


/**
 * BattlelogController is a Controller for BattleLog FXML file
 * <p>
 *     BattleLog will be filled with the game condition and
 *     what player has to do from time to time.
 *     This component will be the player's only guide.
 * </p>
 */
public class BattleLogController implements Initializable {
    @FXML
    private TextFlow textSpace;
    /**
     * Variable indicating how many entry lines has been shown.
     * It is further used to alternate entry background colors.
     */
    private int count;

    /**
     * Initialize BattleLogController by adding listener and setting count to 0
     * @param location location URL
     * @param resources resource bundle
     */
    @Override
    public void initialize(URL location, ResourceBundle resources){
        GUIState.stateProperty().addListener((k, oldValue, newValue) -> addStateLog());
        count = 0;
    }


    /**
     * Adding a new entry to the BattleLog involving game states.
     */
    private void addStateLog(){
        int state = GUIState.getState();
        if(state == 1){
            addText("Beginning " + GUIState.command + " processing!");
            addText("Choose which card this card will affect!");
        } if(state == 2){
            if(GUIState.command.equals("Summon") || GUIState.command.equals("Defense")){
                addText("Beginning " + GUIState.command + " processing!");
            }
            addText("Choose where to put this card!");
        }
    }

    /**
     * Adding a new general entry to the BattleLog
     * @param text the log entry string
     */
    public void addText(String text){
        try{
            FXMLLoader battleLogEntryLoader = new FXMLLoader(getClass().getClassLoader().getResource("FXML/BattleLogEntry.fxml"));
            Label battleLogEntryLabel = battleLogEntryLoader.load();
            battleLogEntryLabel.setText(text);
            if(count % 2 == 0){
                battleLogEntryLabel.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
            } else{
                battleLogEntryLabel.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
            }
            textSpace.getChildren().add(battleLogEntryLabel);
            count++;
        } catch(Exception e) {

        }
    }
}
