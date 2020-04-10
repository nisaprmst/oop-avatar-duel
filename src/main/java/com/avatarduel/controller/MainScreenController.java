package com.avatarduel.controller;

import com.avatarduel.AvatarDuel;
import com.avatarduel.cards.Card;
import com.avatarduel.cards.LandCard;
import com.avatarduel.cards.characters.CharacterCard;
import com.avatarduel.cards.skills.SkillCard;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

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
    @FXML
    private HBox player2skillfield;
    @FXML
    private HBox player1skillfield;
    @FXML
    private HBox player2charfield;
    @FXML
    private HBox player1charfield;
    @FXML
    private Button nextphaseButton;
    @FXML
    private TilePane phaseindicator;

    FXMLLoader cardloader;
    CardController cardController;
    ImageView card;

    int phase = 1;
    int player = 1;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        updateScreen();
    }

    public void updateScreen(){
        clearfield();
        clearhand();
        try{
            renderplayerhand();
        } catch (Exception e){
            System.out.print(e.getMessage());
        }
        //fillfield()
    }

    public void clearfield(){
        player1charfield.getChildren().clear();
        player2charfield.getChildren().clear();
        player1skillfield.getChildren().clear();
        player1skillfield.getChildren().clear();
    }

    public void clearhand(){
        player1hand.getChildren().clear();
        player2hand.getChildren().clear();
    }

    public void renderplayerhand() throws Exception{
        String imgname ="";
        String type = "";
        ObservableList player1handchildren = player1hand.getChildren();
        ObservableList player2handchildren = player2hand.getChildren();
        for(Card c: AvatarDuel.gameManager.player1.getCardsInHand()){
            if(player == 1){
                if(c instanceof CharacterCard){
                    imgname = "character/" + c.getImagePath();
                    type = "CharacterCard";
                } else if(c instanceof SkillCard){
                    imgname = "skill/" + c.getImagePath();
                    type = "SkillCard";
                } else if(c instanceof LandCard){
                    imgname = "land/" + c.getImagePath();
                    type = "LandCard";
                }
            } else{
                imgname = "BlankCard.png";
                type = "BlankCard";
            }

            renderhand(player1hand.getChildren(),imgname, type);
        }

        for(Card c: AvatarDuel.gameManager.player2.getCardsInHand()){
            if(player == 2){
                if(c instanceof CharacterCard){
                    imgname = "character/" + c.getImagePath();
                    type = "CharacterCard";
                } else if(c instanceof SkillCard){
                    imgname = "skill/" + c.getImagePath();
                    type = "SkillCard";
                } else if(c instanceof LandCard){
                    imgname = "land/" + c.getImagePath();
                    type = "LandCard";
                }
            } else{
                imgname = "BlankCard.png";
                type = "BlankCard";
            }

            renderhand(player2hand.getChildren(), imgname, type);
        }
    }

    public void renderhand(ObservableList handchildren, String imagename, String type) throws Exception{
        cardloader = new FXMLLoader(getClass().getClassLoader().getResource("FXML/card.fxml"));
        card = cardloader.load();
        cardController = cardloader.getController();
        cardController.sourceProperty().addListener((k, oldValue, newValue) -> determineGUIState());
        cardController.targetProperty().addListener((k, oldValue, newValue) -> processCommand());
        cardController.setCardImage(imagename);
        cardController.setContextMenuItem(phase, "hand", type);
        System.out.println("masuk sini");

        handchildren.add(card);

    }

    private void determineGUIState(){
        if(GUIState.command.equals("Attack") || GUIState.command.equals("Skill")){
            GUIState.state = 1;
        } else{
            GUIState.state = 0;
        }

        if(GUIState.state == 0){
            processCommand();
        }
    }

    private void processCommand(){
        switch(GUIState.command){
            case "Summon":
                summonCommand(GUIState.source);
                break;
            case "Attack":
                attackCommand(GUIState.source, GUIState.location, GUIState.target);
                break;
            case "Skill":
                skillCommand(GUIState.source, GUIState.location, GUIState.target);
                break;
            case "Position":
                positionCommand(GUIState.source);
                break;
        }

    }

    private void summonCommand(int index){
        System.out.println("Summon card: " + index);
        System.out.println(index);
        Node card = player1hand.getChildren().get(index);
        player1hand.getChildren().remove(index);

        player1charfield.getChildren().add(card);
    }

    private void positionCommand(int index){

    }

    private void attackCommand(int source, int targetlocation, int target){

    }

    private void skillCommand(int source, int targetlocation, int target){
        System.out.print("Skill card: " + source);
        System.out.print(" used for character card: " + target);
        System.out.println(" in: " + targetlocation);
        Node card = player1hand.getChildren().get(source);
        player1hand.getChildren().remove(source);

        player1skillfield.getChildren().add(card);
    }

    public void addplayer1hand(String imagename) throws Exception{
        cardloader = new FXMLLoader(getClass().getClassLoader().getResource("FXML/card.fxml"));
        card = cardloader.load();
        cardController = cardloader.getController();
        cardController.setCardImage(imagename);
       // cardController.setContextMenuItem();

    }

    public void deletehand(){;
        player1hand.getChildren().remove(player1hand.getChildren().size()-1);
    }

    public void player1summon(){
        card = (ImageView) player1hand.getChildren().remove(player1hand.getChildren().size()-1);
        player1charfield.getChildren().add(card);
    }

    public void onNextPhaseButtonClick(){
        /*Label label = (Label)phaseindicator.getChildren().get(phase-1);
        label.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        System.out.println(phaseindicator.getChildren());
        if(phase + 1 > 5){
            System.out.println("entered draw phase");
            phase = 1;
            if(player == 1){
                player = 2;
            } else{
                player = 1;
            }
        } else{
            phase = phase + 1;
        }

        label = (Label)phaseindicator.getChildren().get(phase-1);
        label.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        printPhase();*/

        AvatarDuel.gameManager.phase.nextPhase();
        AvatarDuel.gameManager.phase.phaseInfo();
        updateScreen();

    }

    public void printPhase(){
        String phaseString = "Entered ";
        switch(phase){
            case 1:
                phaseString += "Draw Phase";
                break;
            case 2:
                phaseString += "Main Phase 1";
                break;
            case 3:
                phaseString += "Battle Phase";
                break;
            case 4:
                phaseString += "Main Phase 2";
                break;
            case 5:
                phaseString += "End Phase";
                break;
        }
        System.out.println(phaseString);
    }
}
