package com.avatarduel.controller;

import com.avatarduel.AvatarDuel;
import com.avatarduel.cards.Card;
import com.avatarduel.cards.CardType;
import com.avatarduel.cards.Element;
import com.avatarduel.cards.LandCard;
import com.avatarduel.cards.characters.CharacterCard;
import com.avatarduel.cards.skills.SkillCard;
import com.avatarduel.exceptions.InvalidFieldIndexException;
import com.avatarduel.gamemanager.Command;
import com.avatarduel.gamemanager.Player;
import com.avatarduel.gamemanager.phase.BattlePhase;
import com.avatarduel.gamemanager.phase.DrawPhase;
import com.avatarduel.gamemanager.phase.MainPhase;
import com.avatarduel.gamemanager.phase.Phase;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

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
    @FXML
    private HBox player2emptyskillfield;
    @FXML
    private BattleLogController battleLogController;

    private StringProperty str = new SimpleStringProperty("");

    FXMLLoader cardloader;
    CardController cardController;
    ImageView card;

    Phase phase = AvatarDuel.gameManager.getPhase();
    Player player = AvatarDuel.gameManager.getPlayer();

    int count = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        updateScreen();
        GUIState.hoveredProperty().addListener((k, oldValue, newValue) -> showCard());
        GUIState.state.addListener((k, oldValue, newValue) -> {
            if(newValue.intValue() == 2){
                setFieldDisable(true);
            }
        });

        GUIState.fieldLocationProperty().addListener((k, oldValue, newValue) -> determineGUIState());

        str.addListener((k, oldValue, newValue) -> System.out.println(newValue));
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

    private void setFieldDisable(boolean isFieldDisabled){
        player2skillfield.setDisable(isFieldDisabled);
        player2charfield.setDisable(isFieldDisabled);
        player1skillfield.setDisable(isFieldDisabled);
        player1charfield.setDisable(isFieldDisabled);
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
        for(Card c: AvatarDuel.gameManager.getPlayer().getCardsInHand()){
            //if(AvatarDuel.gameManager.turn == 1){
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
            //} else{
            //    imgname = "BlankCard.png";
            //    type = "BlankCard";
            //}

            renderhand(player1hand.getChildren(),imgname, type);
        }

        for(Card c: AvatarDuel.gameManager.getEnemy().getCardsInHand()){
            imgname = "BlankCard.png";
            type = "BlankCard";
            /*if(AvatarDuel.gameManager.turn == 2){
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
            }*/

            renderhand(player2hand.getChildren(), imgname, type);
        }
    }

   /* public void renderfield() throws Exception{
        String imgname ="";
        String type = "";
        ObservableList player1skillfieldChildren = player1skillfield.getChildren();
        ObservableList player1charfieldChildren = player1charfield.getChildren();
        ObservableList player2charfieldChildren = player2charfield.getChildren();
        ObservableList player2skillfieldChildren = player2skillfield.getChildren();

        for(Card c: AvatarDuel.gameManager.getPlayer().getField().getSkillInColumn(1)){
            //if(AvatarDuel.gameManager.turn == 1){
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
            //} else{
            //    imgname = "BlankCard.png";
            //    type = "BlankCard";
            //}

            renderhand(player1hand.getChildren(),imgname, type);
        }

        for(Card c: AvatarDuel.gameManager.getPlayer().getCardsInHand()){
            //if(AvatarDuel.gameManager.turn == 1){
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
            //} else{
            //    imgname = "BlankCard.png";
            //    type = "BlankCard";
            //}

            renderhand(player1hand.getChildren(),imgname, type);
        }

        for(Card c: AvatarDuel.gameManager.getEnemy().getCardsInHand()){
            imgname = "BlankCard.png";
            type = "BlankCard";
            *//*if(AvatarDuel.gameManager.turn == 2){
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
            }*//*

            renderhand(player2hand.getChildren(), imgname, type);
        }
    }*/

    public void renderhand(ObservableList handchildren, String imagename, String type) throws Exception{
        cardloader = new FXMLLoader(getClass().getClassLoader().getResource("FXML/card.fxml"));
        card = cardloader.load();
        cardController = cardloader.getController();
        cardController.sourceProperty().addListener((k, oldValue, newValue) -> determineGUIState());
        cardController.targetProperty().addListener((k, oldValue, newValue) -> {
            System.out.println("Target Chosen");
            determineGUIState();});
        cardController.setCardImage(imagename);
        cardController.setContextMenuItem(AvatarDuel.gameManager.getPhase(), "hand", type);
        System.out.println("masuk sini");

        handchildren.add(card);

    }

    private void determineGUIState(){
        if(GUIState.command.equals("Attack") || GUIState.command.equals("Skill") || GUIState.command.equals("Defense")){
            System.out.println("Skill");
            System.out.println(GUIState.getState());

            if(GUIState.getState() == 0){
                GUIState.setState(1);
            } else if(GUIState.getState() == 1){
                GUIState.setState(2);
            } else if(GUIState.getState() == 2){
                GUIState.setState(0);
                setFieldDisable(false);
            }
        } else if(GUIState.command.equals("Summon")){
            if(GUIState.getState() == 0){
                GUIState.setState(2);
            } else if(GUIState.getState() == 2){
                GUIState.setState(0);
                setFieldDisable(false);
            }
        } else{
            GUIState.setState(0);
        }

        if(GUIState.getState() == 0){
            processCommand();
        }
    }

    private void processCommand(){
        switch(GUIState.command){
            case "Summon":
                summonCommand(GUIState.source, GUIState.targetLocation, GUIState.target);
                break;
            case "Attack":
                attackCommand(GUIState.source, GUIState.targetLocation, GUIState.target);
                break;
            case "Skill":
                skillCommand(GUIState.source, GUIState.targetLocation, GUIState.target);
                break;
            case "Position":
                positionCommand(GUIState.source);
                break;
        }

    }

    private void summonCommand(int posInHand, int posInField, int fieldLocation){
        System.out.println("Summon card: " + posInHand);
        System.out.println(posInHand);

        if(fieldLocation != 2){
            battleLogController.addText("Can't Summon there");
        } else{
            try{
                AvatarDuel.gameManager.getPhase().process(Command.SUMMONATTACK, posInHand, posInField, 0, false);
            } catch (Exception e){
                battleLogController.addText("Gagal Summon");
                battleLogController.addText(e.getMessage());
            }
        }

        /*Node card = player1hand.getChildren().get(index);
        player1hand.getChildren().remove(index);
        player1charfield.getChildren().add(card);*/


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
        AvatarDuel.gameManager.getPhase().nextPhase();
        AvatarDuel.gameManager.getPhase().phaseInfo();
        updateScreen();
    }

    private void renderPhaseIndicator(){
        Label prevLabel, currLabel;
        if(AvatarDuel.gameManager.getPhase() instanceof DrawPhase){
            prevLabel = (Label) phaseindicator.getChildren().get(3);
            currLabel = (Label) phaseindicator.getChildren().get(0);
        } else if(AvatarDuel.gameManager.getPhase() instanceof MainPhase){
            prevLabel = (Label) phaseindicator.getChildren().get(0);
            currLabel = (Label) phaseindicator.getChildren().get(1);
        } else if(AvatarDuel.gameManager.getPhase() instanceof BattlePhase){
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
        AvatarDuel.gameManager.getPhase().phaseInfo();
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
                    c = AvatarDuel.gameManager.getPlayer().getCardsInHand().get(hovered);
                    cardInfoController.setInfo(c);
                    break;
                case 2:
                    locationString = "Player 1's skill field";
                    try{
                        c = AvatarDuel.gameManager.getPlayer().getField().getSkillInColumn(hovered);
                        cardInfoController.setInfo(c);
                    } catch(InvalidFieldIndexException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    locationString = "Player 1's character field";
                    try{
                        c = AvatarDuel.gameManager.getPlayer().getField().getCharacterInColumn(hovered);
                        cardInfoController.setInfo(c);
                    } catch(InvalidFieldIndexException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    locationString = "Player 2's character field";
                    try{
                        c = AvatarDuel.gameManager.getEnemy().getField().getCharacterInColumn(hovered);
                        cardInfoController.setInfo(c);
                    } catch(InvalidFieldIndexException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 5:
                    locationString = "Player 2's skill field";
                    try{
                        c = AvatarDuel.gameManager.getEnemy().getField().getSkillInColumn(hovered);
                        cardInfoController.setInfo(c);
                    } catch(InvalidFieldIndexException e){
                        System.out.println(e.getMessage());
                    }
                    break;
                /*case 6:
                    locationString = "Player 2's hand";
                    c = AvatarDuel.gameManager.getEnemy().getCardsInHand().get(hovered);
                    cardInfoController.setInfo(c);
                    break;*/
            }
        } else{
            cardInfoController.setBlankInfo();
        }


        System.out.println("Card " + (hovered + 1) + " on " + locationString + " hovered");


    }
}
