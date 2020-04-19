package com.avatarduel.controller;

import com.avatarduel.gamemanager.GameManager;
import com.avatarduel.gamemanager.Player;
import com.avatarduel.util.ConfirmBox;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;


/**
 * LauncherScreenController is a Controller for the game launcher
 * <p>
 *     The launcher itself is initiated after running the game.
 *     Giving the opportunity for players to costumize name, hp, and deck.
 * </p>
 */
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

    /**
     * A BooleanProperty signaling is the game ready to start.
     */
    BooleanProperty isGameReady = new SimpleBooleanProperty(false);

    public boolean isIsGameReady() {
        return isGameReady.get();
    }

    public BooleanProperty isGameReadyProperty() {
        return isGameReady;
    }

    public void setIsGameReady(boolean isGameReady) {
        this.isGameReady.set(isGameReady);
    }


    /**
     * Initiates player status and file loading from the input parameters.
     * <p>
     *     The method will catch for any exception from the input and ask the player to input again if caught.
     *     If succeed, it will set the Player attribute of GameManager, indicating it is ready to start.
     * </p>
     */
    @FXML
    private void onLoadButtonClicked(){
        Player player1;
        Player player2;
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

    /**
     * Setting isGameReady Property to true
     */
    private void readyConfirm(){
        setIsGameReady(ConfirmBox.display("Success", "File is loaded!\n Player 1 please click below when ready"));
        System.out.println(isGameReadyProperty().getValue());
    }


}
