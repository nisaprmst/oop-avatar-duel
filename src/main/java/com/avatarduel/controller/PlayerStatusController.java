package com.avatarduel.controller;

import com.avatarduel.cards.Element;
import com.avatarduel.gamemanager.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class PlayerStatusController implements Initializable {
    @FXML
    private Label playerName;
    @FXML
    private Label playerHP;
    @FXML
    private Label firePowerCount;
    @FXML
    private Label waterPowerCount;
    @FXML
    private Label airPowerCount;
    @FXML
    private Label earthPowerCount;
    @FXML
    private Label energyPowerCount;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Initialized");

    }

    public void setStatus(Player p){
        playerName.setText(p.getNama());
        playerHP.setText("" + p.getHp() + " HP");
        firePowerCount.setText(p.getCurrPower().get(Element.FIRE) + "/" + p.getPower().get(Element.FIRE));
        waterPowerCount.setText(p.getCurrPower().get(Element.WATER) + "/" + p.getPower().get(Element.WATER));
        airPowerCount.setText(p.getCurrPower().get(Element.AIR) + "/" + p.getPower().get(Element.AIR));
        earthPowerCount.setText(p.getCurrPower().get(Element.EARTH) + "/" + p.getPower().get(Element.EARTH));
        energyPowerCount.setText(p.getCurrPower().get(Element.ENERGYBINDING) + "/" + p.getPower().get(Element.ENERGYBINDING));
    }
}
