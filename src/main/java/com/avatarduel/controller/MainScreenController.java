package com.avatarduel.controller;

import com.avatarduel.AvatarDuel;
import com.avatarduel.cards.Card;
import com.avatarduel.cards.CardType;
import com.avatarduel.cards.Element;
import com.avatarduel.cards.LandCard;
import com.avatarduel.cards.characters.CharacterCard;
import com.avatarduel.cards.characters.Position;
import com.avatarduel.cards.skills.SkillCard;
import com.avatarduel.exceptions.InvalidFieldIndexException;
import com.avatarduel.exceptions.NoCardInFieldException;
import com.avatarduel.gamemanager.Command;
import com.avatarduel.gamemanager.Player;
import com.avatarduel.gamemanager.phase.*;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
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
    @FXML
    private PlayerStatusController enemyStatusController;
    @FXML
    private PlayerStatusController playerStatusController;

    private StringProperty str = new SimpleStringProperty("");

    FXMLLoader cardloader;
    CardController cardController;
    ImageView card;

    Phase phase = AvatarDuel.gameManager.getPhase();
    Player player = AvatarDuel.gameManager.getPlayer();

    int count = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources){
        GUIState.hoveredProperty().addListener((k, oldValue, newValue) -> showCard());
        GUIState.state.addListener((k, oldValue, newValue) -> {
            if(newValue.intValue() == 2){
                battleLogController.addText("Field disabled");
                setFieldDisable(true);
            }
        });

        GUIState.fieldLocationProperty().addListener((k, oldValue, newValue) -> {
            battleLogController.addText("Location is: " + newValue.intValue());
            if(newValue.intValue() != 999){
                determineGUIState();
            }
        });

        drawCommand();
        updateScreen();

    }
    public void updateScreen(){
        clearfield();
        clearhand();
        try{
            renderplayerhand();
            renderplayerfield();
        } catch (Exception e){
            System.out.print(e);
        }
        renderPhaseIndicator();
        enemyStatusController.setStatus(AvatarDuel.gameManager.getEnemy());
        playerStatusController.setStatus(AvatarDuel.gameManager.getPlayer());

    }

    public void clearfield(){
        for (Node node: player1charfield.getChildren()) {
            ((StackPane) node).getChildren().clear();
        }
        for (Node node: player2charfield.getChildren()) {
            ((StackPane) node).getChildren().clear();
        }
        for (Node node: player1skillfield.getChildren()) {
            ((StackPane) node).getChildren().clear();
        }
        for (Node node: player2skillfield.getChildren()) {
            ((StackPane) node).getChildren().clear();
        }
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

            renderhand(player1hand.getChildren(),imgname, type);
        }

        for(Card c: AvatarDuel.gameManager.getEnemy().getCardsInHand()){
            imgname = "BlankCard.png";
            type = "BlankCard";

            renderhand(player2hand.getChildren(), imgname, type);
        }
    }

    public void renderplayerfield() throws Exception{
        String imgname ="";
        String type = "";
        ObservableList player1skillfieldChildren = player1skillfield.getChildren();
        ObservableList player1charfieldChildren = player1charfield.getChildren();
        ObservableList player2charfieldChildren = player2charfield.getChildren();
        ObservableList player2skillfieldChildren = player2skillfield.getChildren();
        // Render Player Skill Field
        for(int i = 0; i < 6; i++) { // 6 is the max space
            Position position = Position.ATTACK;
            try{
                Card c = AvatarDuel.gameManager.getPlayer().getField().getSkillInColumn(i);
                imgname = "skill/" + c.getImagePath();
                type = "SkillCard";
                renderfield((StackPane) player1skillfieldChildren.get(i), imgname, type, position, false, false);
            } catch(NoCardInFieldException e){

            }
        }

        // Render Player Character Field
        for(int i = 0; i < 6; i++) { // 6 is the max space
            Position position = Position.ATTACK;
            try{
                CharacterCard c = AvatarDuel.gameManager.getPlayer().getField().getCharacterInColumn(i);
                imgname = "character/" + c.getImagePath();
                type = "CharacterCard";
                position = ( c).getPosition();
                renderfield((StackPane)player1charfieldChildren.get(i), imgname, type, position, c.getJustSummoned(), c.getHasAttacked());
            } catch(NoCardInFieldException e){

            }
        }

        // Render Enemy Character Field
        for(int i = 0; i < 6; i++) { // 6 is the max space
            Position position = Position.ATTACK;
            try{
                CharacterCard c = AvatarDuel.gameManager.getEnemy().getField().getCharacterInColumn(i);
                imgname = "character/" + c.getImagePath();
                type = "CharacterCard";
                position = (c).getPosition();
                renderfield((StackPane)player2charfieldChildren.get(i), imgname, type, position, c.getJustSummoned(), c.getHasAttacked());
            } catch(NoCardInFieldException e){
            }
        }

        // Render Enemy Skill Field
        for(int i = 0; i < 6; i++) { // 6 is the max space
            Position position = Position.ATTACK;
            try{
                Card c = AvatarDuel.gameManager.getEnemy().getField().getSkillInColumn(i);
                imgname = "skill/" + c.getImagePath();
                type = "SkillCard";
                renderfield((StackPane)player2skillfieldChildren.get(i), imgname, type, position, false, false);
            } catch(NoCardInFieldException e){
            }
        }
    }

    public void renderhand(ObservableList handchildren, String imagename, String type) throws Exception{
        cardloader = new FXMLLoader(getClass().getClassLoader().getResource("FXML/card.fxml"));
        card = cardloader.load();
        cardController = cardloader.getController();
        cardController.sourceProperty().addListener((k, oldValue, newValue) -> {
            battleLogController.addText("Source is: " + newValue.intValue());
            determineGUIState();});
        cardController.targetProperty().addListener((k, oldValue, newValue) -> {
            battleLogController.addText("Target is: " + newValue.intValue());
            determineGUIState();});
        cardController.setCardImage(imagename, "hand", type, Position.ATTACK);
        cardController.setContextMenuItem(AvatarDuel.gameManager.getPhase(), "hand", type, false, false);

        handchildren.add(card);

    }

    public void renderfield(StackPane fieldchildren, String imagename, String type, Position position, boolean hasJustSummoned, boolean hasAttacked) throws Exception{
        cardloader = new FXMLLoader(getClass().getClassLoader().getResource("FXML/card.fxml"));
        card = cardloader.load();
        cardController = cardloader.getController();
        cardController.sourceProperty().addListener((k, oldValue, newValue) -> {
            battleLogController.addText("Source is: " + newValue.intValue());
            determineGUIState();});
        cardController.targetProperty().addListener((k, oldValue, newValue) -> {
            battleLogController.addText("Target is: " + newValue.intValue());
            determineGUIState();});
        cardController.setCardImage(imagename, "field", type, position);
        cardController.setContextMenuItem(AvatarDuel.gameManager.getPhase(), "field", type, hasJustSummoned, hasAttacked);

        fieldchildren.setNodeOrientation(NodeOrientation.INHERIT);
        fieldchildren.getChildren().add(card);

    }

    private void determineGUIState(){
        if(GUIState.command.equals("Attack") && GUIState.getState() == 0 && AvatarDuel.gameManager.getEnemy().isCharacterFieldEmpty()){
            battleLogController.addText("Can attack direct");
        } else if (GUIState.command.equals("Attack")) {
            if (GUIState.getState() == 0) {
                GUIState.setState(1);
            } else if (GUIState.getState() == 1) {
                GUIState.setState(0);
            }
        }  else if (GUIState.command.equals("Skill")) {
            if (GUIState.getState() == 0) {
                GUIState.setState(1);
            } else if (GUIState.getState() == 1) {
                GUIState.setState(2);
            } else if (GUIState.getState() == 2) {
                GUIState.setState(0);
                setFieldDisable(false);
            }
        } else if (GUIState.command.equals("Summon") || GUIState.command.equals("Defense")) {
            if (GUIState.getState() == 0) {
                GUIState.setState(2);
            } else if (GUIState.getState() == 2) {
                battleLogController.addText("Keluar state 2 summon");
                GUIState.setState(0);
                setFieldDisable(false);
            }
        } else if (GUIState.command.equals("Land") || GUIState.command.equals("Change Position") || GUIState.command.equals("Remove")) {
            GUIState.setState(0);
        }

        if (GUIState.getState() == 0) {
            processCommand();
        }

    }

    private void processCommand(){
        switch(GUIState.command){
            case "Summon":
                summonCommand(GUIState.source, GUIState.fieldIndexProperty().getValue(), GUIState.fieldLocationProperty().getValue(), Position.ATTACK);
                break;
            case "Defense":
                summonCommand(GUIState.source, GUIState.fieldIndexProperty().getValue(), GUIState.fieldLocationProperty().getValue(), Position.DEFENSE);
                break;
            case "Land":
                landCommand(GUIState.source);
                break;
            case "Attack":
                attackCommand(GUIState.source, GUIState.target, GUIState.targetLocation);
                break;
            case "Skill":
                skillCommand(GUIState.source, GUIState.target, GUIState.targetLocation, GUIState.fieldIndexProperty().getValue(), GUIState.fieldLocationProperty().getValue());
                break;
            case "Change Position":
                positionCommand(GUIState.source, GUIState.sourceLocation);
                break;
            case "Remove":
                //removeCommand(GUIState.source, GUIState.sourceLocation);
                break;
        }
        updateScreen();
        GUIState.resetVariables();

    }

    private void drawCommand(){
        try{
            AvatarDuel.gameManager.getPhase().process(Command.PLACESKILL, 0, 0, 0, true);
        }catch (Exception e){
            battleLogController.addText(e.toString());
        }
    }

    private void summonCommand(int posInHand, int posInField, int fieldLocation, Position summonPosition){
        Command command;
        System.out.println("Summon card: " + posInHand);
        System.out.println(posInHand);

        System.out.println("FIELD LOCATION : " + fieldLocation);

        if(summonPosition == Position.ATTACK){
            command = Command.SUMMONATTACK;
        } else{
            command = Command.SUMMONDEFENSE;
        }

        if(fieldLocation != 2){
            battleLogController.addText("Can't Summon there");
        } else{
            try{
                System.out.println(AvatarDuel.gameManager.getPlayer().getCurrPower());
                AvatarDuel.gameManager.getPhase().process(command, posInHand, posInField, 0, true);
            } catch (Exception e){
                battleLogController.addText("Gagal Summon");
                System.out.println(e);
            }
        }

        /*Node card = player1hand.getChildren().get(index);
        player1hand.getChildren().remove(index);
        player1charfield.getChildren().add(card);*/


    }

    private void landCommand(int index){
        try{
            AvatarDuel.gameManager.getPhase().process(Command.SUMMONLAND, index, 0, 0, true);
        } catch (Exception e){
            battleLogController.addText("Gagal Summon Land");
        }
    }

    private void positionCommand(int index, int location){
        try{
            AvatarDuel.gameManager.getPhase().process(Command.CHANGEPOSITION, 0, index, 0, true);
        } catch (Exception e){
            battleLogController.addText("Gagal change position");
        }
    }

    private void attackCommand(int source, int target, int targetLocation){
        battleLogController.addText("Character: " + source);
        battleLogController.addText("tried to attack: " + target);

        if(AvatarDuel.gameManager.getEnemy().isCharacterFieldEmpty()){
            try{
                AvatarDuel.gameManager.getPhase().process(Command.ATTACKENEMY, 0, source, 0, false);
            } catch (Exception e){
                battleLogController.addText("Fail Attack kosong");
            }
        } else{
            try{
                AvatarDuel.gameManager.getPhase().process(Command.ATTACKENEMY, 0, source, target, false);
            } catch (Exception e){
                battleLogController.addText(e.toString());
            }
        }

    }

    private void skillCommand(int posInHand, int target, int targetLocation, int fieldIndex, int fieldLocation){

        /*Node card = player1hand.getChildren().get(source);
        player1hand.getChildren().remove(source);

        player1skillfield.getChildren().add(card);*/

        // Check if the target is player or not
        boolean isOnPlayer = targetLocation == 3;

        // Check if target location is in character field
        if(targetLocation != 3 && targetLocation != 4) {
            battleLogController.addText("Can't use skill there");
            return;
        }

        // Check if field location (where the skill will be put) is skill field
        if(fieldLocation != 1){
            return;
        }

        // If the card is used for player, then the skill field has to be player's skill field
        if(targetLocation == 3 && fieldLocation != 1){
            battleLogController.addText("Wrong Position");
            return;
        }

        // If the card is used for enemy, then the skill field has to be enemy's skill field
        if(targetLocation == 4 && fieldLocation != 1){
            battleLogController.addText("Wrong Position");
            return;
        }

        // All the prequisite is right
        battleLogController.addText("Skill card: " + posInHand);
        battleLogController.addText(" used for character card: " + target);
        battleLogController.addText(" in: " + targetLocation);
        battleLogController.addText("Put on " + fieldLocation);
        battleLogController.addText(" on index " + fieldIndex);
        battleLogController.addText(" isUsedonPlayer? " + isOnPlayer);

        //process(Command command, int posInHand, int posInField, int target, boolean isOnPlayer)
        try{
            AvatarDuel.gameManager.getPhase().process(Command.PLACESKILL, posInHand, fieldIndex, target, isOnPlayer);
        } catch(Exception e){
            battleLogController.addText(e.toString());
        }

    }

    @FXML
    private void onNextPhaseButtonClick(){
        AvatarDuel.gameManager.getPhase().nextPhase();
        if(AvatarDuel.gameManager.getPhase().getType() == PhaseType.DRAW){
            try{
                AvatarDuel.gameManager.getPhase().process(Command.SUMMONLAND, 0, 0, 0, true);
            } catch (Exception e){
                battleLogController.addText("Cant draw");
            }
        }

        AvatarDuel.gameManager.getPhase().phaseInfo();
        updateScreen();
    }

    @FXML
    private void onCancelButtonClick(){
        GUIState.setState(0);
        GUIState.resetVariables();
        battleLogController.addText("Action Canceled");
        updateScreen();
    }

    private void renderPhaseIndicator(){
        Label prevLabel, currLabel;
        if(AvatarDuel.gameManager.getPhase().getType() == PhaseType.DRAW){
            prevLabel = (Label) phaseindicator.getChildren().get(2);
            currLabel = (Label) phaseindicator.getChildren().get(0);
        } else if(AvatarDuel.gameManager.getPhase().getType() == PhaseType.MAIN){
            prevLabel = (Label) phaseindicator.getChildren().get(0);
            currLabel = (Label) phaseindicator.getChildren().get(1);
        } else if(AvatarDuel.gameManager.getPhase().getType() == PhaseType.BATTLE){
            prevLabel = (Label) phaseindicator.getChildren().get(1);
            currLabel = (Label) phaseindicator.getChildren().get(2);
        } else{ //End Phase
            prevLabel = (Label) phaseindicator.getChildren().get(2);
            currLabel = (Label) phaseindicator.getChildren().get(3);
        }
        prevLabel.setBackground(new Background(new BackgroundFill(Color.YELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
        currLabel.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
        battleLogController.addText("draw Phase");
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
                    } catch(Exception e){
                        battleLogController.addText(e.getMessage());
                    }
                    break;
                case 3:
                    locationString = "Player 1's character field";
                    try{
                        c = AvatarDuel.gameManager.getPlayer().getField().getCharacterInColumn(hovered);
                        cardInfoController.setInfo(c);
                    } catch(Exception e){
                        battleLogController.addText(e.getMessage());
                    }
                    break;
                case 4:
                    locationString = "Player 2's character field";
                    try{
                        c = AvatarDuel.gameManager.getEnemy().getField().getCharacterInColumn(hovered);
                        cardInfoController.setInfo(c);
                    } catch(Exception e){
                        battleLogController.addText(e.getMessage());
                    }
                    break;
                case 5:
                    locationString = "Player 2's skill field";
                    try{
                        c = AvatarDuel.gameManager.getEnemy().getField().getSkillInColumn(hovered);
                        cardInfoController.setInfo(c);
                    } catch(Exception e){
                        battleLogController.addText(e.getMessage());
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


        //System.out.println("Card " + (hovered + 1) + " on " + locationString + " hovered");


    }

    private void test(){

    }
}
