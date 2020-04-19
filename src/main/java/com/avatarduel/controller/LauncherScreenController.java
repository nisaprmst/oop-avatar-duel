package com.avatarduel.controller;

import com.avatarduel.gamemanager.GameManager;
import com.avatarduel.gamemanager.Player;
import com.avatarduel.util.ConfirmBox;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


public class LauncherScreenController {
    @FXML
    private TextField player1Name;
    @FXML
    private TextField player1HP;
    @FXML
    private TextField player1Character;
    @FXML
    private TextField player1Land;
    @FXML
    private TextField player1Destroy;
    @FXML
    private TextField player1Powerup;
    @FXML
    private TextField player1Aura;
    @FXML
    private TextField player2Name;
    @FXML
    private TextField player2HP;
    @FXML
    private TextField player2Character;
    @FXML
    private TextField player2Land;
    @FXML
    private TextField player2Destroy;
    @FXML
    private TextField player2Powerup;
    @FXML
    private TextField player2Aura;
    @FXML
    private Button loadButton;
    BooleanProperty isGameReady = new SimpleBooleanProperty(false);
    Player player1;
    Player player2;

    public boolean isIsGameReady() {
        return isGameReady.get();
    }

    public BooleanProperty isGameReadyProperty() {
        return isGameReady;
    }

    public void setIsGameReady(boolean isGameReady) {
        this.isGameReady.set(isGameReady);
    }

    @FXML
    private void onLoadButtonClicked(){
        try{
            player1 = new Player(player1Character.getText(), player1Land.getText(), player1Destroy.getText(), player1Powerup.getText(), player1Aura.getText());
            player1.setNama(player1Name.getText());
            player1.setHp(Integer.parseInt(player1HP.getText()));
            player2 = new Player(player2Character.getText(), player2Land.getText(), player2Destroy.getText(), player2Powerup.getText(), player2Aura.getText());
            player2.setNama(player2Name.getText());
            player2.setHp(Integer.parseInt(player2HP.getText()));
            GameManager gameManager = GameManager.getGameManager();
            gameManager.setPlayer(player1);
            gameManager.setEnemy(player2);
            readyConfirm();
        } catch (Exception e){
            System.out.println(e);
            ConfirmBox.display("Exception", "File can't be loaded, please check input");
        }

    }

    private void readyConfirm(){
        setIsGameReady(ConfirmBox.display("Success", "File is loaded!\n Player 1 please click below when ready"));
        System.out.println(isGameReadyProperty().getValue());
    }


}
