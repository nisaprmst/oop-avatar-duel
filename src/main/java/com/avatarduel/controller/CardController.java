package com.avatarduel.controller;

import com.avatarduel.cards.characters.Position;
import com.avatarduel.gamemanager.phase.Phase;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class CardController implements Initializable {
    @FXML
    private ImageView card;

    private IntegerProperty source  = new SimpleIntegerProperty(999);

    private IntegerProperty target  = new SimpleIntegerProperty(999);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public int getSource() {
        return source.get();
    }

    public IntegerProperty sourceProperty() {
        return source;
    }

    public void setSource(int source) {
        this.source.set(source);
    }

    public int getTarget() {
        return target.get();
    }

    public IntegerProperty targetProperty() {
        return target;
    }

    public void setTarget(int target) {
        this.target.set(target);
    }

    public void setCardImage(String imagename, String location, String type, Position position){
        int requestedWidth, requestedHeight;
        String path = getClass().getResource("../card/image/" + imagename).toString();
        if(location == "hand"){
            requestedWidth = 90;
            requestedHeight = 126;
        } else{
            requestedWidth = 71;
            requestedHeight = 100;
        }
        Image img = new Image(path, requestedWidth, requestedHeight, false, true);
        card.setImage(img);
        card.setCursor(Cursor.HAND);

        if(position == Position.DEFENSE){
            card.setRotate(90);
        }

        card.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
                //System.out.println("entered");
                Parent parent = card.getParent();
                int location = determineLocation(parent);
                int i;
                if(location != 1 && location != 6){
                    Parent grandParent = parent.getParent();
                    for(i = 0; i < grandParent.getChildrenUnmodifiable().size(); i++){
                        if(parent.equals(grandParent.getChildrenUnmodifiable().get(i))){
                            break;
                        }
                    }
                } else{
                    for(i = 0; i < parent.getChildrenUnmodifiable().size(); i++){
                        if(card.equals(parent.getChildrenUnmodifiable().get(i))){
                            break;
                        }
                    }
                }



                //System.out.println(i);
                GUIState.setHovLocation(determineLocation(parent));
                GUIState.setHovered(i);

                card.setTranslateY(card.getTranslateY() - 10);

            }
        });

        card.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                //System.out.println("exited");
                card.setTranslateY(card.getTranslateY() + 10);
                GUIState.setHovered(-999);
            }
        });
    }

    public void setContextMenuItem(Phase phase, String location, String type, boolean hasJustSummoned, boolean hasAttacked, Position position) throws Exception{
        FXMLLoader contextMenuLoader = new FXMLLoader(getClass().getClassLoader().getResource("FXML/CardContextMenu.fxml"));
        ContextMenu contextMenu = contextMenuLoader.load();
        CardContextMenuController contextMenuController = contextMenuLoader.getController();
        contextMenuController.commandProperty().addListener((k, oldValue, newValue) -> setCommandSource());

        contextMenuController.setMenuItems(phase, location, type, hasJustSummoned, hasAttacked, position);

        card.addEventHandler(ContextMenuEvent.CONTEXT_MENU_REQUESTED, event -> {
            if(GUIState.getState() == 0){
                contextMenu.show(card, event.getScreenX(), event.getScreenY());
                event.consume();
            }
        });
        card.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            contextMenu.hide();
            if(GUIState.getState() == 1){
                setCommandTarget();
            }
        });

    }

    private void setCommandSource(){
        int i;
        Parent parent = card.getParent();
        String id = parent.getId();
        if(id == null){
            Parent grandParent = parent.getParent();
            for(i = 0; i < grandParent.getChildrenUnmodifiable().size(); i++){
                if(parent.equals(grandParent.getChildrenUnmodifiable().get(i))){
                    break;
                }
            }
        } else{
            for(i = 0; i < parent.getChildrenUnmodifiable().size(); i++){
                if(card.equals(parent.getChildrenUnmodifiable().get(i))){
                    break;
                }
            }
        }
        //System.out.println("Source is: " + i);
        GUIState.source = i;
        setSourceLocation(parent);
        //System.out.println("Source location is: " + GUIState.sourceLocation);
        setSource(i);
    }

    private void setCommandTarget(){
        Parent parent = card.getParent().getParent();
        int i;
        for(i = 0; i < parent.getChildrenUnmodifiable().size(); i++){
            if((card.getParent()).equals(parent.getChildrenUnmodifiable().get(i))){
                break;
            }
        }
        //System.out.println("Target is: " + i);
        GUIState.target = i;
        setTargetLocation(parent);
        //System.out.println("location is: " + GUIState.targetLocation);

        setTarget(i);
    }

    private int determineLocation(Parent parent){
        String id = parent.getId();
        if(id == null){
            id = parent.getParent().getId();
        }
        System.out.println(id);
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
    }

    private void setSourceLocation(Parent parent){
        int location = determineLocation(parent);

        GUIState.sourceLocation = location;

    }

    private void setTargetLocation(Parent parent){
        int location = determineLocation(parent);

        GUIState.targetLocation = location;

    }

}
