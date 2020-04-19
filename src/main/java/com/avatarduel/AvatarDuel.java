package com.avatarduel;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import com.avatarduel.cards.Card;
import com.avatarduel.cards.characters.CharacterCard;
import com.avatarduel.cards.skills.AuraSkill;
import com.avatarduel.cards.skills.SkillCard;
import com.avatarduel.controller.LauncherScreenController;
import com.avatarduel.controller.MainScreenController;
import com.avatarduel.gamemanager.*;
import com.avatarduel.util.ConfirmBox;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import com.avatarduel.cards.Element;
import com.avatarduel.cards.LandCard;
import com.avatarduel.util.CSVReader;

import javax.crypto.KeyAgreement;

public class AvatarDuel extends Application {
  @Override
  public void start(Stage stage) throws Exception{

    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("FXML/LauncherScreen.fxml"));
    VBox launcherRoot = loader.load();
    LauncherScreenController launcherController = loader.getController();

    launcherController.isGameReadyProperty().addListener((k, oldValue, newValue) -> {
      if(newValue.booleanValue()){
        try{
          startGame(stage);
        } catch (Exception e){
          System.out.println("Start game failed");
        }
      }
    });

    Scene scene = new Scene(launcherRoot, 600 , 400);

    stage.setTitle("Avatar Duel");
    stage.setScene(scene);
    stage.show();

  }

  private void startGame(Stage stage) throws Exception{
    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("FXML/MainScreen.fxml"));
    StackPane root = loader.load();
    MainScreenController controller = loader.getController();

    controller.isGameProperty().addListener((k, oldValue, newValue) -> {
      if(!newValue.booleanValue()){
        ConfirmBox.display("Game end", "Thanks for playing!");
        stage.close();
      }
    });
    Scene scene = new Scene(root, 1280 , 720);
    stage.setTitle("Avatar Duel");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }
}