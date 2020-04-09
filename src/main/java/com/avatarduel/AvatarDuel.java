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
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import com.avatarduel.cards.Element;
import com.avatarduel.cards.LandCard;
import com.avatarduel.util.CSVReader;

import javax.crypto.KeyAgreement;

public class AvatarDuel extends Application {
  private static final String LAND_CSV_FILE_PATH = "card/data/land.csv";
  public static Player player1;
  public static Player player2;

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



    ArrayList<Card> player1hand = new ArrayList<Card>();
    ArrayList<Card> player2hand = new ArrayList<Card>();
    player1hand.add(card1);
    player1hand.add(card2);
    player1hand.add(card3);
    player2hand.add(card4);
    player2hand.add(card5);
    player2hand.add(card6);

    player1 = new Player();
    player2 = new Player();
    // player1.setCardsInHand(player1hand);
    // player2.setCardsInHand(player2hand);

    // FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("FXML/MainScreen.fxml"));
    // BorderPane root = loader.load();
    // MainScreenController controller = loader.getController();
    StackPane root = new StackPane();




      //GameManager gameManager = new GameManager();

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