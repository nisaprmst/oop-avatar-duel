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
  private static final String LAND_CSV_FILE_PATH = "card/data/land.csv";
  public Player player1;
  public Player player2;
  public static GameManager gameManager;

  public void loadCards() throws IOException, URISyntaxException {
    File landCSVFile = new File(getClass().getResource(LAND_CSV_FILE_PATH).toURI());
    CSVReader landReader = new CSVReader(landCSVFile, "\t");
    landReader.setSkipHeader(true);
    List<String[]> landRows = landReader.read();
    for (String[] row : landRows) {
      LandCard l = new LandCard(Integer.parseInt(row[0]), row[1], row[3], Element.valueOf(row[2]), row[4]);
    }
  }

  @Override
  public void start(Stage stage) throws Exception{
    LandCard fire = new LandCard(1, "Fire", "aflkanffa", Element.FIRE, "asu.png");
    LandCard earth = new LandCard(1, "Fire", "aflkanffa", Element.EARTH, "asu.png");
    LandCard air = new LandCard(1, "Fire", "aflkanffa", Element.AIR, "asu.png");
    LandCard water = new LandCard(1, "Fire", "aflkanffa", Element.WATER, "asu.png");

    /*player1 = new Player();
    player2 = new Player();
    for(int i = 0; i < 20; i++){
      player1.addPower(fire);
      player1.addPower(earth);
      player1.addPower(air);
      player1.addPower(water);
      player2.addPower(fire);
      player2.addPower(earth);
      player2.addPower(air);
      player2.addPower(water);
    }
    player1.resetPower();
    player2.resetPower();
    player1.setNama("Azula");
    player2.setNama("Jaina Proodmore");
    player1.setHp(80);
    player2.setHp(80);

    gameManager = GameManager.getGameManager();
    gameManager.setPlayer(player1);
    gameManager.setEnemy(player2);
    GameManager.getGameManager().setPlayer(player1);
    GameManager.getGameManager().setEnemy(player2);
    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("FXML/MainScreen.fxml"));
    StackPane root = loader.load();
    MainScreenController controller = loader.getController();*/

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

    /*controller.isGameProperty().addListener((k, oldValue, newValue) -> {
      if(!newValue.booleanValue()){
        ConfirmBox.display("Game end", "Thanks for playing!");
        stage.close();
      }
    });*/
    Scene scene = new Scene(root, 1280 , 720);
    stage.setTitle("Avatar Duel");
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch();
  }
}