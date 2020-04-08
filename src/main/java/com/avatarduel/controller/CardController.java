package com.avatarduel.controller;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

public class CardController {
    @FXML
    private ImageView card;

    public void setCardImage(Image img){
        card.setImage(img);
        card.setRotate(90);
        card.setOnMouseEntered(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                System.out.println("entered");
                Parent parent = card.getParent();

                int i;
                for(i = 0; i < parent.getChildrenUnmodifiable().size(); i++){
                    if(card.equals(parent.getChildrenUnmodifiable().get(i))){
                        break;
                    }
                }

                System.out.println(i);

                card.setTranslateY(card.getTranslateY() - 10);

            }
        });

        card.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                System.out.println("exited");
                card.setTranslateY(card.getTranslateY() + 10);
            }
        });
    }

    public void setContextMenuItem() throws Exception{
        FXMLLoader contextMenuLoader = new FXMLLoader(getClass().getClassLoader().getResource("FXML/CardContextMenu.fxml"));
        ContextMenu contextMenu = contextMenuLoader.load();
        //contextMenu.getItems().add(new MenuItem("Load"));
        CardContextMenuController contextMenuController = contextMenuLoader.getController();

        contextMenuController.setMenuItems( "Main 1");

        //System.out.println(contextMenu.getItems());

        /*card.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown()) {
                    contextMenu.show(card, event.getScreenX(), event.getScreenY());
                }
            }
        });*/

        //card.setOnContextMenuRequested(e ->contextMenu.show(card, e.getScreenX(), e.getScreenY()));

        card.addEventHandler(ContextMenuEvent.CONTEXT_MENU_REQUESTED, event -> {
            contextMenu.show(card, event.getScreenX(), event.getScreenY());
            event.consume();
        });
        card.addEventHandler(MouseEvent.MOUSE_PRESSED, event -> {
            contextMenu.hide();
        });

    }

}
