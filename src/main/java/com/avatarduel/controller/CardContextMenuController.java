package com.avatarduel.controller;

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
    private MenuItem delete;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        summon = new MenuItem("Summon");
        defense = new MenuItem("Defense");
        summon.setOnAction(e -> System.out.println("summon"));
    }

    public void setMenuItems(String phase){
        cardcontextmenu.getItems().addAll(summon, defense);

        //ystem.out.println(cardcontextmenu.getItems());

    }


}
