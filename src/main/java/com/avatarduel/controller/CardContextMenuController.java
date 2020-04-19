package com.avatarduel.controller;

import com.avatarduel.gamemanager.phase.BattlePhase;
import com.avatarduel.gamemanager.phase.DrawPhase;
import com.avatarduel.gamemanager.phase.MainPhase;
import com.avatarduel.gamemanager.phase.Phase;
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
    private MenuItem landuse;
    private MenuItem changeposition;
    private MenuItem remove;


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
        summon = new MenuItem("Summon Attack");
        defense = new MenuItem("Summon Defense");
        attack = new MenuItem("Attack");
        skilluse = new MenuItem("Use Skill");
        landuse = new MenuItem("Use Land");
        changeposition = new MenuItem("Change Position");
        remove = new MenuItem("Remove");
        summon.setOnAction(e -> setStateCommand("Summon"));
        defense.setOnAction(e -> setStateCommand("Defense"));
        attack.setOnAction(e -> setStateCommand("Attack"));
        skilluse.setOnAction(e -> setStateCommand("Skill"));
        landuse.setOnAction(e -> setStateCommand("Land"));
        changeposition.setOnAction(e -> setStateCommand("Change Position"));
        remove.setOnAction(e -> setStateCommand("Remove"));
    }

    private void setStateCommand(String commandString){
        GUIState.command = commandString;
        setCommand(commandString);
    }

    public void setMenuItems(Phase phase, String location, String type, boolean hasJustSummoned, boolean hasAttacked){
        int phaseInt = determinePhase(phase);
        cardcontextmenu.getItems().clear();
        if(!type.equals("BlankCard")){
            switch (phaseInt){
                case 1:  // Draw Phase
                    break;
                case 2:     // Main Phase
                    if(location.equals("hand")){
                        if(type.equals("CharacterCard")){
                            cardcontextmenu.getItems().addAll(summon, defense);
                        } else if(type.equals("LandCard")){
                            cardcontextmenu.getItems().addAll(landuse);
                        } else if(type.equals("SkillCard")){
                            cardcontextmenu.getItems().addAll(skilluse);
                        }
                        cardcontextmenu.getItems().addAll(remove);
                    } else if(location.equals("field")){
                        if(type.equals("CharacterCard")){
                            cardcontextmenu.getItems().addAll(changeposition);
                        }
                        cardcontextmenu.getItems().addAll(remove);
                    }
                    break;
                case 3: // Battle Phase
                    if(location.equals("field")){
                        if(type.equals("CharacterCard")){
                            if(!hasJustSummoned && !hasAttacked){
                                cardcontextmenu.getItems().addAll(attack);
                            }
                        }

                    }
                    break;
                case 4:
                    break;
            }
        }

    }

    private int determinePhase(Phase phase){
        if(phase instanceof DrawPhase){
            return 1;
        } else if(phase instanceof MainPhase){
            return 2;
        } else if(phase instanceof BattlePhase){
            return 3;
        } else{
            return 4;
        }
    }




}
