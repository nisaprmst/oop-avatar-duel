package com.avatarduel.controller;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;


/**
 * FieldSpaceController is a Controller for empty space in the field.
 * <p>
 *     It is mainly used for player to choose where they want to put the card.
 *     The space will only be clickable in a certain GUI State.
 * </p>
 */
public class FieldSpaceController implements Initializable {
    @FXML
    private Rectangle rect;


    /**
     * Initialize FieldSpaceController by adding EventHandler and setting cursor
     * @param location location URL
     * @param resources resource bundle
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        rect.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                determineLocation();
            }
        });
        rect.setCursor(Cursor.HAND);
    }

    /**
     * Determine the location of current space.
     * fieldLocationProperty in GUIState will be set to which row the space is in.
     * fieldIndexProperty in GUIState will be be set to which index of the space relative to the row.
     */
    private void determineLocation(){
        String id = this.rect.getParent().getId();
        GUIState.setFieldIndex(Integer.parseInt(id.substring(id.length()-1, id.length())));

        if(id.contains("player1")){
            if(id.contains("skillfield")){
                GUIState.setFieldLocation(1);
            } else if(id.contains("charfield")){
                GUIState.setFieldLocation(2);
            }
        } else if(id.contains("player2")){
            if(id.contains("skillfield")){
                GUIState.setFieldLocation(4);
            } else if(id.contains("charfield")){
                GUIState.setFieldLocation(3);
            }
        }
    }



}
