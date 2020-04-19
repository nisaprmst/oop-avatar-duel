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
import com.avatarduel.controller.MainScreenController;
import com.avatarduel.gamemanager.*;
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
    CharacterCard card1 = new CharacterCard(1, "Aang", "The Last Airbender", Element.AIR, "Aang.png", 10, 10, 3);
    CharacterCard card2 = new CharacterCard(1, "Aang", "The Last Airbender", Element.AIR, "Aang.png", 10, 10, 3);
    AuraSkill card3 = new AuraSkill(2, "Air Funnel", "Fooshhhh", Element.AIR, "Air Funnel.png", 10, 10, 10);

    CharacterCard card4 = new CharacterCard(1, "Appa", "The Last Air Bison", Element.AIR, "Appa.png", 11, 10, 3);
    CharacterCard card5 = new CharacterCard(1, "Appa", "The Last Air Bison", Element.AIR, "Appa.png", 11, 10, 3);
    AuraSkill card6 = new AuraSkill(2, "Air Scooter", "WOOHOOOO", Element.AIR, "Air Scooter.png", 10, 10, 10);

    ArrayList<Card> player1hand = new ArrayList<>();
    ArrayList<Card> player2hand = new ArrayList<>();
    player1hand.add(card1);
    player1hand.add(card2);
    player1hand.add(card3);
    player2hand.add(card4);
    player2hand.add(card5);
    player2hand.add(card6);

    LandCard fire = new LandCard(1, "Fire", "aflkanffa", Element.FIRE, "asu.png");
    LandCard earth = new LandCard(1, "Fire", "aflkanffa", Element.EARTH, "asu.png");
    LandCard air = new LandCard(1, "Fire", "aflkanffa", Element.AIR, "asu.png");
    LandCard water = new LandCard(1, "Fire", "aflkanffa", Element.WATER, "asu.png");

    player1 = new Player();
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
    //System.out.println(player1.getCurrPower());
    // player1.setCardsInHand(player1hand);
    // player2.setCardsInHand(player2hand);

    gameManager = GameManager.getGameManager();
    gameManager.setPlayer(player1);
    gameManager.setEnemy(player2);
    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("FXML/MainScreen.fxml"));
    BorderPane root = loader.load();
    MainScreenController controller = loader.getController();
    //StackPane root = new StackPane();

      //GameManager gameManager = new GameManager();

    Rectangle rect = new Rectangle(126, 126);
    Rectangle rect2 = new Rectangle(126, 126);
    for(int i = 0; i < 6; i++){
      Pane pane = new Pane();
      pane.setMinSize(126,126);
      pane.setMaxSize(126,126);
      root.getChildren().add(pane);
    }

    HBox hbox = new HBox();
    hbox.setMinSize(792, 126);
    hbox.setMaxSize(792, 126);
    for(int i = 0; i < 6; i++){
      Pane pane = new Pane();
      pane.setMinSize(126,126);
      pane.setMaxSize(126,126);
      hbox.getChildren().add(pane);
    }
    Pane tilePaneIndex0 = (Pane) hbox.getChildren().get(0);
    tilePaneIndex0.getChildren().add(rect);
    Pane tilePaneIndex2 = (Pane) hbox.getChildren().get(2);
    tilePaneIndex2.getChildren().add(rect2);

    System.out.println(hbox.getChildren());
    Scene scene = new Scene(root, 1280 , 720);

    stage.setTitle("Avatar Duel");
    stage.setScene(scene);
    stage.show();


    /*controller.addplayer1hand("Aang.png");
    controller.addplayer1hand("Afiko.png");
    controller.addplayer1hand("Appa.png");*/
  }

  public static void main(String[] args) {
    launch();
  }
}