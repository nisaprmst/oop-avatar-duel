package com.avatarduel.controller;

import com.avatarduel.cards.Card;
import com.avatarduel.cards.LandCard;
import com.avatarduel.cards.characters.CharacterCard;
import com.avatarduel.cards.skills.SkillCard;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class CardInfoController implements Initializable {
    @FXML
    ImageView cardImageContainer;
    @FXML
    Label cardName;
    @FXML
    Label cardPower;
    @FXML
    Label cardAttack;
    @FXML
    Label cardDefense;
    @FXML
    Label cardDescription;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setBlankInfo();
    }

    public void setInfo(Card c){
        String imagePath = "";
        if(c instanceof CharacterCard){
            imagePath = "character/" + c.getImagePath();
        } else if(c instanceof SkillCard){
            imagePath = "skill/" + c.getImagePath();
        } else if(c instanceof LandCard){
            imagePath = "land/" + c.getImagePath();
        }
        setcardImage(imagePath);
    }

    public void setBlankInfo(){
        String imagePath = "BlankCard.png";
        setcardImage(imagePath);
    }

    public void setcardImage(String imagepath){
        System.out.println(imagepath);
        String path = getClass().getResource("../card/image/" + imagepath).toString();
        Image img = new Image(path, 250, 350, false, true);
        cardImageContainer.setImage(img);
    }

    public void setName(Card c){
        cardName.setText(c.getName());
    }

    public void setPower(Card c){
        cardName.setText(c.getName());
    }

    public void setAttack(Card c){
        cardName.setText("NOT AZULA HAHAHAHAHA");
    }

    public void setDefense(Card c){
        cardName.setText("NOT AZULA HAHAHAHAHA");
    }

    public void setDescription(Card c){
        cardName.setText("NOT AZULA HAHAHAHAHA");
    }
}
