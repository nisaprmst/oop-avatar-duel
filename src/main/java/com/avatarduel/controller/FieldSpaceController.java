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
        System.out.println(Integer.parseInt(id.substring(id.length()-1, id.length())));

        if(id.contains("player1")){
            System.out.println("PLAYER 1");
            if(id.contains("skillfield")){
                System.out.println("SKILLFIELD");
                GUIState.setFieldLocation(1);
            } else if(id.contains("charfield")){
                System.out.println("CHARFIELD");
                GUIState.setFieldLocation(2);
            }
        } else if(id.contains("player2")){
            System.out.println("PLAYER 2");
            if(id.contains("skillfield")){
                System.out.println("SKILLFIELD");
                GUIState.setFieldLocation(4);
            } else if(id.contains("charfield")){
                System.out.println("CHARFIELD");
                GUIState.setFieldLocation(3);
            }
        }
    }

   /* private void setCommandTarget(){
        Parent parent = card.getParent();
        int i;
        for(i = 0; i < parent.getChildrenUnmodifiable().size(); i++){
            if(card.equals(parent.getChildrenUnmodifiable().get(i))){
                break;
            }
        }
        System.out.println("Target is: " + i);
        GUIState.target = i;
        setTargetLocation(parent);
        System.out.println("location is: " + GUIState.targetLocation);

        GUIState.setState(0);
        setTarget(i);
    }

    private int determineLocation(Parent parent){
        String id = parent.getId();
        int location = 999;

        switch (id){
            case "player1hand":
                location = 1;
                break;
            case "player1skillfield":
                location = 2;
                break;
            case "player1charfield":
                location = 3;
                break;
            case "player2charfield":
                location = 4;
                break;
            case "player2skillfield":
                location = 5;
                break;
            case "player2hand":
                location = 6;
                break;
        }

        return location;
    }*/

}
