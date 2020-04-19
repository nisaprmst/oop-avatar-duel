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

public class FieldSpaceController implements Initializable {
    @FXML
    private Rectangle rect;

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
