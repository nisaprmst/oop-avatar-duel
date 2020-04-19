package com.avatarduel.controller;

import com.avatarduel.cards.characters.Position;
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

/**
 * CardContextMenuController is a Controller for card's ContextMenu
 * <p>
 *     It holds all the possible menu for the card including its interaction with the MainScreen
 * </p>
 */
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
    private MenuItem removehand;

    /**
     * An String Property to be listened for command type
     */
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


    /**
     * Initializes CardContextMenuController by initializing MenuItems
     * @param location location URL
     * @param resources resource bundle
     */
    @Override
    public void initialize(URL location, ResourceBundle resources){
        summon = new MenuItem("Summon Attack");
        defense = new MenuItem("Summon Defense");
        attack = new MenuItem("Attack");
        skilluse = new MenuItem("Use Skill");
        landuse = new MenuItem("Use Land");
        changeposition = new MenuItem("Change Position");
        remove = new MenuItem("Remove");
        removehand = new MenuItem("Remove");
        summon.setOnAction(e -> setStateCommand("Summon"));
        defense.setOnAction(e -> setStateCommand("Defense"));
        attack.setOnAction(e -> setStateCommand("Attack"));
        skilluse.setOnAction(e -> setStateCommand("Skill"));
        landuse.setOnAction(e -> setStateCommand("Land"));
        changeposition.setOnAction(e -> setStateCommand("Change Position"));
        remove.setOnAction(e -> setStateCommand("Remove Skill"));
        removehand.setOnAction(e -> setStateCommand("Remove Hand"));
    }


    /**
     * Setting the command in GUIState
     * @param commandString The command type
     */
    private void setStateCommand(String commandString){
        GUIState.command = commandString;
        setCommand(commandString);
    }

    /**
     * Set Menu Items of the card according to several parameters
     * @param phase The current phase
     * @param location The location of the card (field or hand)
     * @param type The type of the card
     * @param hasJustSummoned Whether this card has just been summoned
     * @param hasAttacked Whether this card has just attacked
     * @param position The position of this card (attack or defense)
     */
    public void setMenuItems(Phase phase, String location, String type, boolean hasJustSummoned, boolean hasAttacked, Position position){
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
                        cardcontextmenu.getItems().addAll(removehand);
                    } else if(location.equals("field")){
                        if(type.equals("CharacterCard")){
                            cardcontextmenu.getItems().addAll(changeposition);
                        } else if(type.equals("SkillCard")){
                            cardcontextmenu.getItems().addAll(remove);
                        }
                    }
                    break;
                case 3: // Battle Phase
                    if(location.equals("field")){
                        if(type.equals("CharacterCard")){
                            if(!hasJustSummoned && !hasAttacked && position == Position.ATTACK){
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

    /**
     * Determine a phase with number
     * @param phase Current Phase
     * @return Integer identification of phase
     */
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
