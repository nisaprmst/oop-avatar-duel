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

public class BattleLogController implements Initializable {
    @FXML
    private TextFlow textSpace;
    private int count;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        GUIState.stateProperty().addListener((k, oldValue, newValue) -> addStateLog());
        count = 0;
    }

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
