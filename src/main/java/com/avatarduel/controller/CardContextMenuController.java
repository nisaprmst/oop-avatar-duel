package com.avatarduel.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;

import java.net.URL;
import java.util.ResourceBundle;

public class CardContextMenuController implements Initializable {
    @FXML
    private ContextMenu cardcontextmenu;

    private MenuItem summon;
    private MenuItem defense;
    private MenuItem attack;
    private MenuItem skilluse;
    private MenuItem changeposition;


    private StringProperty command = new SimpleStringProperty("");

    public String getCommand() {
        return command.get();
    }

    public StringProperty commandProperty() {
        return command;
    }

    public void setCommand(String command) {
        this.command.set(command);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources){
        summon = new MenuItem("Summon");
        defense = new MenuItem("Defense");
        attack = new MenuItem("Attack");
        skilluse = new MenuItem("Use");
        summon.setOnAction(e -> setStateCommand("Summon"));
        defense.setOnAction(e -> setStateCommand("Defense"));
        attack.setOnAction(e -> setStateCommand("Attack"));
        skilluse.setOnAction(e -> setStateCommand("Skill"));
    }

    private void setStateCommand(String commandString){
        GUIState.command = commandString;
        setCommand(commandString);
    }

    public void setMenuItems(int phase, String location, String type){
        System.out.println(phase);
        System.out.println(location);
        if(!type.equals("BlankCard")){
            switch (phase){
                case 1:
                    break;
                case 2:
                case 4:
                    if(location.equals("hand")){
                        if(type.equals("CharacterCard")){
                            cardcontextmenu.getItems().addAll(summon, defense);
                        } else{
                            cardcontextmenu.getItems().addAll(skilluse);
                        }
                    } else if(location.equals("field")){
                        cardcontextmenu.getItems().addAll(attack);
                    }
                    break;
                case 3:
                    if(location.equals("field")){
                        cardcontextmenu.getItems().addAll(attack);
                    }
                    break;
                case 5:
                    break;
            }
        }

    }




}
