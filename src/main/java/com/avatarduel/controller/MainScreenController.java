package com.avatarduel.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MainScreenController implements Initializable {
    @FXML
    private Label labelone;
    @FXML
    private VBox leftvbox;
    @FXML
    private HBox player1hand;
    @FXML
    private HBox player2hand;

    FXMLLoader cardloader;
    CardController cardController;
    ImageView card;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void changeText(String text){
        labelone.setText(text);
    }

    public void printText(){
        System.out.println(labelone.getText());
    }

    public void addnewlabel(String text){
        Label label = new Label(text);
        leftvbox.getChildren().add(label);
    }

    public void deletelabel(int index){
        leftvbox.getChildren().remove(index);
        Button button = new Button("click di remove");
        button.setOnAction(e -> deletehand());
        leftvbox.getChildren().add(button);
    }

    public void addplayer1hand(String imagename) throws Exception{
        cardloader = new FXMLLoader(getClass().getClassLoader().getResource("FXML/card.fxml"));
        String loaderpath = getClass().getClassLoader().getResource("FXML/card.fxml").toString();
        System.out.println(loaderpath);

        card = cardloader.load();

        cardController = cardloader.getController();
        String path = getClass().getClassLoader().getResource("com/avatarduel/card/image/character/" + imagename).toString();
        Image img = new Image(path, 90, 90, true, true);
        System.out.println(path);
        card = cardController.setCardImage(img);

        player1hand.getChildren().add(card);

    }

    public void deletehand(){;
        player1hand.getChildren().remove(player1hand.getChildren().size()-1);
    }
}
