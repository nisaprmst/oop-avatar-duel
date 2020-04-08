package com.avatarduel;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import com.avatarduel.controller.MainScreenController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import com.avatarduel.cards.Element;
import com.avatarduel.cards.LandCard;
import com.avatarduel.util.CSVReader;

public class AvatarDuel extends Application {
  private static final String LAND_CSV_FILE_PATH = "card/data/land.csv";

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
    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("FXML/MainScreen.fxml"));
    BorderPane root = loader.load();
    MainScreenController controller = loader.getController();

    Scene scene = new Scene(root, 1280 , 720);

    stage.setTitle("Avatar Duel");
    stage.setScene(scene);
    stage.show();

    controller.addnewlabel("2nd label");
    controller.addnewlabel("3rd label");

    controller.deletelabel(1);

    controller.addplayer1hand("Aang.png");
    controller.addplayer1hand("Afiko.png");
    controller.addplayer1hand("Appa.png");
  }

  public static void main(String[] args) {
    launch();
  }
}