package com.avatarduel.controller;

import com.avatarduel.AvatarDuel;
import com.avatarduel.cards.Card;
import com.avatarduel.cards.CardType;
import com.avatarduel.cards.Element;
import com.avatarduel.cards.LandCard;
import com.avatarduel.cards.characters.CharacterCard;
import com.avatarduel.cards.skills.SkillCard;
import com.avatarduel.exceptions.InvalidFieldIndexException;
import com.avatarduel.gamemanager.phase.BattlePhase;
import com.avatarduel.gamemanager.phase.DrawPhase;
import com.avatarduel.gamemanager.phase.MainPhase;
import com.avatarduel.gamemanager.phase.Phase;
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
    private CardInfoController cardInfoController;
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

    Phase phase = AvatarDuel.gameManager.phase;
    int player = AvatarDuel.gameManager.turn;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        updateScreen();
        GUIState.hoveredProperty().addListener((k, oldValue, newValue) -> showCard());
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
        renderPhaseIndicator();

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
            if(AvatarDuel.gameManager.turn == 1){
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
            if(AvatarDuel.gameManager.turn == 2){
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
        cardController.setContextMenuItem(AvatarDuel.gameManager.phase, "hand", type);
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

    @FXML
    private void onNextPhaseButtonClick(){
        AvatarDuel.gameManager.phase.nextPhase();
        AvatarDuel.gameManager.phase.phaseInfo();
        updateScreen();
    }

    private void renderPhaseIndicator(){
        Label prevLabel, currLabel;
        if(AvatarDuel.gameManager.phase instanceof DrawPhase){
            prevLabel = (Label) phaseindicator.getChildren().get(3);
            currLabel = (Label) phaseindicator.getChildren().get(0);
        } else if(AvatarDuel.gameManager.phase instanceof MainPhase){
            prevLabel = (Label) phaseindicator.getChildren().get(0);
            currLabel = (Label) phaseindicator.getChildren().get(1);
        } else if(AvatarDuel.gameManager.phase instanceof BattlePhase){
            prevLabel = (Label) phaseindicator.getChildren().get(1);
            currLabel = (Label) phaseindicator.getChildren().get(2);
        } else{ //End Phase
            prevLabel = (Label) phaseindicator.getChildren().get(2);
            currLabel = (Label) phaseindicator.getChildren().get(3);
        }
        prevLabel.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        currLabel.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
    }

    private void printPhase(){
        AvatarDuel.gameManager.phase.phaseInfo();
    }

    private void showCard(){
        Card c;
        int location = GUIState.getHovLocation();
        int hovered = GUIState.getHovered();

        String locationString = "";

        if(hovered != -999){
            switch(location){
                case 1:
                    locationString = "Player 1's hand";
                    c = AvatarDuel.gameManager.player1.getCardsInHand().get(hovered);
                    cardInfoController.setInfo(c);
                    break;
                case 2:
                    locationString = "Player 1's skill field";
                    try{
                        c = AvatarDuel.gameManager.player1.getField().getSkillInColumn(hovered);
                        cardInfoController.setInfo(c);
                    } catch(InvalidFieldIndexException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    locationString = "Player 1's character field";
                    try{
                        c = AvatarDuel.gameManager.player1.getField().getCharacterInColumn(hovered);
                        cardInfoController.setInfo(c);
                    } catch(InvalidFieldIndexException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    locationString = "Player 2's character field";
                    try{
                        c = AvatarDuel.gameManager.player2.getField().getCharacterInColumn(hovered);
                        cardInfoController.setInfo(c);
                    } catch(InvalidFieldIndexException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    locationString = "Player 2's skill field";
                    try{
                        c = AvatarDuel.gameManager.player2.getField().getSkillInColumn(hovered);
                        cardInfoController.setInfo(c);
                    } catch(InvalidFieldIndexException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 6:
                    locationString = "Player 2's hand";
                    c = AvatarDuel.gameManager.player2.getCardsInHand().get(hovered);
                    cardInfoController.setInfo(c);
                    break;
            }
        } else{
            cardInfoController.setBlankInfo();
        }


        System.out.println("Card " + (hovered + 1) + " on " + locationString + " hovered");


    }
}
