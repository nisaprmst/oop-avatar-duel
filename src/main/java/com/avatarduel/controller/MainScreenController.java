package com.avatarduel.controller;

import com.avatarduel.cards.*;
import com.avatarduel.cards.characters.*;
import com.avatarduel.cards.skills.*;
import com.avatarduel.exceptions.*;
import com.avatarduel.gamemanager.*;
import com.avatarduel.gamemanager.phase.*;
import com.avatarduel.util.ConfirmBox;
import javafx.beans.property.SimpleBooleanProperty;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * MainScreenController is a Controller for MainScreen
 * <p>
 *     Main Screen holds the controlling ability of the components inside including:
 *     1. Hand
 *     2. Field
 *     3. Card Info
 *     4. Player Status
 *     5. Battle Log and Phase Indicator
 * </p>
 */
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
    private FXMLLoader cardloader;
    private CardController cardController;
    private ImageView card;


    /**
     * A BooleanProperty to be listened indicating if the game is still running
     */
    private SimpleBooleanProperty isGame;


    /**
     * Initialize the MainScreenController by setting listeners, variables and render the screen for the first time
     * @param location location URL
     * @param resources resource bundle
     */
    @Override
    public void initialize(URL location, ResourceBundle resources){
        isGame = new SimpleBooleanProperty(true);
        battleLogController.addText("WELCOME TO AVATAR DUEL!");
        initializeListeners();
        drawCommand();
        updateGameCondition();
    }

    /**
     * Initialize the listeners needed to be initialized in the beginning
     */
    private void initializeListeners(){
        GUIState.hoveredProperty().addListener((k, oldValue, newValue) -> showCard());
        GUIState.state.addListener((k, oldValue, newValue) -> {
            if(newValue.intValue() == 2){
                setFieldDisable(true);
            }
        });
        GUIState.fieldLocationProperty().addListener((k, oldValue, newValue) -> {
            if(newValue.intValue() != 999){
                determineGUIState();
            }
        });
    }


    /**
     * Update game condition by checking win state and rendering the screen
     */
    private void updateGameCondition(){
        int win;
        Player player, enemy;

        player = GameManager.getGameManager().getPlayer();
        enemy = GameManager.getGameManager().getEnemy();
        boolean game = (player.getHp() > 0 && enemy.getHp() > 0 && player.getDeck().size() > 0 && enemy.getDeck().size() > 0);
        updateScreen();

        if(!game){
            win = determineWin(player, enemy);
            String winner, loser;
            if(win == 1){
                winner = player.getNama();
                loser = enemy.getNama();
            } else{
                loser = player.getNama();
                winner = enemy.getNama();

            }
            ConfirmBox.display("Win", winner + " win against " + loser);
            setIsGame(false);
        }
    }


    /**
     * Determine which player wins the game
     * @param player The current PLayer
     * @param enemy The current Enemy
     * @return
     */
    private int determineWin(Player player, Player enemy){
        if(player.getHp() <= 0 || player.getDeck().size() <= 0){
            return 2;
        } else{
            return 1;
        }
    }

    /**
     * Render Game Screen by the process
     * 1. Clearing field and hand
     * 2. Render field and hand
     * 3. Render Phase Indicator
     * 4. Render Players' status
     */
    private void updateScreen(){
        clearfield();
        clearhand();
        try{
            renderplayerhand();
            renderplayerfield();
        } catch (Exception e){
        }
        renderPhaseIndicator();
        enemyStatusController.setStatus(GameManager.getGameManager().getEnemy());
        playerStatusController.setStatus(GameManager.getGameManager().getPlayer());

    }

    /**
     * Clear the field rows from the card images
     */
    private void clearfield(){
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

    /**
     * Set fields' for card disabled attribute
     * @param isFieldDisabled
     */
    private void setFieldDisable(boolean isFieldDisabled){
        player2skillfield.setDisable(isFieldDisabled);
        player2charfield.setDisable(isFieldDisabled);
        player1skillfield.setDisable(isFieldDisabled);
        player1charfield.setDisable(isFieldDisabled);
    }

    /**
     * Clear player's hand from the card images
     */
    private void clearhand(){
        player1hand.getChildren().clear();
        player2hand.getChildren().clear();
    }


    /**
     * Render player's hand by iterating through the hand array, render accordingly, and also set contextMenuItem
     * @throws Exception If there is any error from loading the image or accessing the arrays
     */
    private void renderplayerhand() throws Exception{
        String imgname ="";
        String type = "";
        ObservableList player1handchildren = player1hand.getChildren();
        ObservableList player2handchildren = player2hand.getChildren();
        for(Card c: GameManager.getGameManager().getPlayer().getCardsInHand()){
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

        for(Card c: GameManager.getGameManager().getEnemy().getCardsInHand()){
            imgname = "BlankCard.png";
            type = "BlankCard";
            renderhand(player2hand.getChildren(), imgname, type);
        }
    }

    /**
     * Render players' field by iterating through the field array, render accordingly, and also set contextMenuItem
     * @throws Exception If there is any error from loading the image or accessing the arrays
     */
    private void renderplayerfield() throws Exception{
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
                Card c = GameManager.getGameManager().getPlayer().getField().getSkillInColumn(i);
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
                CharacterCard c = GameManager.getGameManager().getPlayer().getField().getCharacterInColumn(i);
                imgname = "character/" + c.getImagePath();
                type = "CharacterCard";
                position =  c.getPosition();
                renderfield((StackPane)player1charfieldChildren.get(i), imgname, type, position, c.getJustSummoned(), c.getHasAttacked());
            } catch(NoCardInFieldException e){

            }
        }

        // Render Enemy Character Field
        for(int i = 0; i < 6; i++) { // 6 is the max space
            Position position = Position.ATTACK;
            try{
                CharacterCard c = GameManager.getGameManager().getEnemy().getField().getCharacterInColumn(i);
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
                Card c = GameManager.getGameManager().getEnemy().getField().getSkillInColumn(i);
                imgname = "skill/" + c.getImagePath();
                type = "SkillCard";
                renderfield((StackPane)player2skillfieldChildren.get(i), imgname, type, position, false, false);
            } catch(NoCardInFieldException e){
            }
        }
    }

    /**
     * Render each card in hand individually and setting its context menu
     * @param handchildren The container of the image
     * @param imagename The imagename
     * @param type The card type
     * @throws Exception if there is any error from loading the FXML, image or accessing array
     */
    private void renderhand(ObservableList handchildren, String imagename, String type) throws Exception{
        cardloader = new FXMLLoader(getClass().getClassLoader().getResource("FXML/card.fxml"));
        card = cardloader.load();
        cardController = cardloader.getController();
        cardController.sourceProperty().addListener((k, oldValue, newValue) -> {
            determineGUIState();});
        cardController.targetProperty().addListener((k, oldValue, newValue) -> {
            determineGUIState();});
        cardController.setCardImage(imagename, "hand", type, Position.ATTACK);
        cardController.setContextMenuItem(GameManager.getGameManager().getPhase(), "hand", type, false, false, Position.DEFENSE);

        handchildren.add(card);

    }

    /**
     * Render each card in hand individually and setting its context menu
     * @param fieldchildren The container of the image
     * @param imagename The imagename
     * @param type The card type
     * @param position The card's position
     * @param hasJustSummoned Whether this card is just summoned
     * @param hasAttacked Whether this card has just attacked
     * @throws Exception if there is any error from loading the FXML, image or accessing array
     */
    private void renderfield(StackPane fieldchildren, String imagename, String type, Position position, boolean hasJustSummoned, boolean hasAttacked) throws Exception{
        cardloader = new FXMLLoader(getClass().getClassLoader().getResource("FXML/card.fxml"));
        card = cardloader.load();
        cardController = cardloader.getController();
        cardController.sourceProperty().addListener((k, oldValue, newValue) -> {
            determineGUIState();});
        cardController.targetProperty().addListener((k, oldValue, newValue) -> {
            determineGUIState();});
        cardController.setCardImage(imagename, "field", type, position);
        cardController.setContextMenuItem(GameManager.getGameManager().getPhase(), "field", type, hasJustSummoned, hasAttacked, position);

        fieldchildren.setNodeOrientation(NodeOrientation.INHERIT);
        fieldchildren.getChildren().add(card);

    }

    /**
     *  Determine GUI State according to command in GUIState and current State.
     *  This state is used for mapping interactions
     */
    private void determineGUIState(){
        if(GUIState.command.equals("Attack") && GUIState.getState() == 0 && GameManager.getGameManager().getEnemy().isCharacterFieldEmpty()){
            battleLogController.addText("Begin attack processing...");
            battleLogController.addText("Direct attack...");
        } else if (GUIState.command.equals("Attack")) {
            if (GUIState.getState() == 0) {
                battleLogController.addText("Begin attack processing...");
                battleLogController.addText("Choose whom to attack!");
                GUIState.setState(1);
            } else if (GUIState.getState() == 1) {
                battleLogController.addText("Attack initiated!");
                GUIState.setState(0);
            }
        }  else if (GUIState.command.equals("Skill")) {
            if (GUIState.getState() == 0) {
                battleLogController.addText("Begin skill processing...");
                battleLogController.addText("Choose whom to attach the skill!");
                GUIState.setState(1);
            } else if (GUIState.getState() == 1) {
                battleLogController.addText("Choose where to put the skill!");
                GUIState.setState(2);
            } else if (GUIState.getState() == 2) {
                battleLogController.addText("Skill initiated!");
                GUIState.setState(0);
                setFieldDisable(false);
            }
        } else if (GUIState.command.equals("Summon") || GUIState.command.equals("Defense")) {
            if (GUIState.getState() == 0) {
                battleLogController.addText("Begin summon processing...");
                battleLogController.addText("Choose where to put the card!");
                GUIState.setState(2);
            } else if (GUIState.getState() == 2) {
                battleLogController.addText("Summon initiated!");
                GUIState.setState(0);
                setFieldDisable(false);
            }
        } else if (GUIState.command.equals("Land")) {
            battleLogController.addText("Land card chosen!");
            GUIState.setState(0);
        } else if (GUIState.command.equals("Change Position")) {
            battleLogController.addText("Change Position initiated!");
            GUIState.setState(0);
        } else if (GUIState.command.equals("Remove Skill")) {
            battleLogController.addText("Remove Skill initiated!");
            GUIState.setState(0);
        }else if (GUIState.command.equals("Remove Hand")) {
            battleLogController.addText("Remove Hand initiated!");
            GUIState.setState(0);
        }

        if (GUIState.getState() == 0) {
            processCommand();
        }

    }


    /**
     * Process commands when the input state is already done
     */
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
                positionCommand(GUIState.source);
                break;
            case "Remove Skill":
                removeCommand(GUIState.source);
                break;
            case "Remove Hand":
                removeHandCommand(GUIState.source);
                break;
        }
        updateGameCondition();
        GUIState.resetVariables();

    }


    /**
     * Process the Draw Command
     */
    private void drawCommand(){
        try{
            GameManager.getGameManager().getPhase().process(Command.PLACESKILL, 0, 0, 0, true);
        }catch (Exception e){
        }
    }

    /**
     * Process the Remove Skill Command
     * @param posInField Skill's position on skill field
     */
    private void removeCommand(int posInField){
        try{
            GameManager.getGameManager().getPhase().process(Command.REMOVESKILL, 0, posInField, 0, true);

        }catch (Exception e){
        }
    }

    /**
     * Process the Remove Hand Command
     * @param posInHand Card's position on hand
     */
    private void removeHandCommand(int posInHand){
        try{
            GameManager.getGameManager().getPhase().process(Command.REMOVEHAND, posInHand, 0, 0, true);

        }catch (Exception e){
        }
    }

    /**
     * Process the Summon command
     * @param posInHand  Card's position on hand
     * @param posInField Card's to be put position on field
     * @param fieldLocation Which field the card is put
     * @param summonPosition In what position will the card be summoned
     */
    private void summonCommand(int posInHand, int posInField, int fieldLocation, Position summonPosition){
        Command command;

        if(summonPosition == Position.ATTACK){
            command = Command.SUMMONATTACK;
        } else{
            command = Command.SUMMONDEFENSE;
        }
        if(fieldLocation != 2){
            battleLogController.addText("Can't Summon there!");
        } else{
            try{
                GameManager.getGameManager().getPhase().process(command, posInHand, posInField, 0, true);
                battleLogController.addText("Card summoned in " + summonPosition.toString() + " position!");
            } catch (Exception e){
                battleLogController.addText("Summon failed!");
            }
        }
    }


    /**
     * Process the Summon Land Command
     * @param index Card's position on hand
     */
    private void landCommand(int index){
        try{
            String element = GameManager.getGameManager().getPlayer().getCardsInHand().get(index).getElement().toString().toLowerCase();
            GameManager.getGameManager().getPhase().process(Command.SUMMONLAND, index, 0, 0, true);
            battleLogController.addText("Land card of element " + element + " used!");
        } catch (Exception e){
            battleLogController.addText("You have used your land card this turn!");
        }
    }


    /**
     * Process the Change Position Command
     * @param index Card's position in character field
     */
    private void positionCommand(int index){
        try{
            GameManager.getGameManager().getPhase().process(Command.CHANGEPOSITION, 0, index, 0, true);
            String position = GameManager.getGameManager().getPlayer().getCharacterAtPos(index).toString().toLowerCase();
            battleLogController.addText("Card changed to " + position + " position!");
        } catch (Exception e){
            battleLogController.addText("Change position failed!");
        }
    }


    /**
     * Process the Attack command
     * @param source The location of attacker
     * @param target The location of attacked
     * @param targetLocation The row of where the attacked is
     */
    private void attackCommand(int source, int target, int targetLocation){
        if(GameManager.getGameManager().getEnemy().isCharacterFieldEmpty()){
            try{
                GameManager.getGameManager().getPhase().process(Command.ATTACKENEMY, 0, source, 0, false);
                battleLogController.addText("Direct attack launched!");
            } catch (Exception e){
                battleLogController.addText("Attack failed!");
            }
        } else{
            try{
                GameManager.getGameManager().getPhase().process(Command.ATTACKENEMY, 0, source, target, false);
                battleLogController.addText("Attack launched!");
            } catch (Exception e){
                battleLogController.addText("Attack failed!");
            }
        }

    }

    /**
     * Process the Skill Command
     * @param posInHand Card's position on hand
     * @param target Target's position on field
     * @param targetLocation Target's row location
     * @param fieldIndex In which index will the card be put
     * @param fieldLocation In which row will the card be put
     */
    private void skillCommand(int posInHand, int target, int targetLocation, int fieldIndex, int fieldLocation){
        boolean isOnPlayer = targetLocation == 3;

        // Check if target location is in character field
        if(targetLocation != 3 && targetLocation != 4) {
            battleLogController.addText("Can't use skill there!");
            return;
        }

        // Check if field location (where the skill will be put) is skill field
        if(fieldLocation != 1){
            battleLogController.addText("Skill has to be put in your skill field!");
            return;
        }

        // All the prequisite is right
        try{
            String skilltype = ((SkillCard) GameManager.getGameManager().getPlayer().getCardsInHand().get(posInHand)).getSkillType().toString().toLowerCase();
            GameManager.getGameManager().getPhase().process(Command.PLACESKILL, posInHand, fieldIndex, target, isOnPlayer);
            battleLogController.addText("Skill of type " + skilltype + " launched!");
        } catch(Exception e){
            battleLogController.addText("Skill launch failed!");
        }

    }

    /**
     * Handle Next Phase Button Click
     */
    @FXML
    private void onNextPhaseButtonClick(){
        if(GUIState.getState() == 0){
            GameManager.getGameManager().getPhase().nextPhase();
            if(GameManager.getGameManager().getPhase().getType() == PhaseType.DRAW){
                try{
                    GameManager.getGameManager().getPhase().process(Command.SUMMONLAND, 0, 0, 0, true);
                } catch (EmptyDeckException e){
                    updateGameCondition();
                } catch (Exception e){
                }
                ConfirmBox.display("Change Turn", "Player " + GameManager.getGameManager().getPlayer().getNama() + " please click below when ready");

            }
            updateGameCondition();
            battleLogController.addText("Entering " + GameManager.getGameManager().getPhase().getType().toString().toLowerCase() + " phase");

        } else{
            battleLogController.addText("Please finish your action first!");
        }



    }


    /**
     * Handle Cancel Button Click
     */
    @FXML
    private void onCancelButtonClick(){
        if(GUIState.getState() != 0){
            GUIState.setState(0);
            GUIState.resetVariables();
            battleLogController.addText("Action Canceled");
            updateGameCondition();
        }
    }


    /**
     * Render phase indicator according the the current phase
     */
    private void renderPhaseIndicator(){
        Label prevLabel, currLabel;
        if(GameManager.getGameManager().getPhase().getType() == PhaseType.DRAW){
            prevLabel = (Label) phaseindicator.getChildren().get(2);
            currLabel = (Label) phaseindicator.getChildren().get(0);
        } else if(GameManager.getGameManager().getPhase().getType() == PhaseType.MAIN){
            prevLabel = (Label) phaseindicator.getChildren().get(0);
            currLabel = (Label) phaseindicator.getChildren().get(1);
        } else if(GameManager.getGameManager().getPhase().getType() == PhaseType.BATTLE){
            prevLabel = (Label) phaseindicator.getChildren().get(1);
            currLabel = (Label) phaseindicator.getChildren().get(2);
        } else{ //End Phase
            prevLabel = (Label) phaseindicator.getChildren().get(2);
            currLabel = (Label) phaseindicator.getChildren().get(3);
        }
        prevLabel.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        currLabel.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, CornerRadii.EMPTY, Insets.EMPTY)));
    }


    /**
     * Show card information
     */
    private void showCard(){
        Card c;
        int location = GUIState.getHovLocation();
        int hovered = GUIState.getHovered();
        if(hovered != -999){
            switch(location){
                case 1:
                    c = GameManager.getGameManager().getPlayer().getCardsInHand().get(hovered);
                    cardInfoController.setInfo(c);
                    break;
                case 2:
                    try{
                        c = GameManager.getGameManager().getPlayer().getField().getSkillInColumn(hovered);
                        cardInfoController.setInfo(c);
                    } catch(Exception e){
                    }
                    break;
                case 3:
                    try{
                        c = GameManager.getGameManager().getPlayer().getField().getCharacterInColumn(hovered);
                        cardInfoController.setInfo(c);
                    } catch(Exception e){
                    }
                    break;
                case 4:
                    try{
                        c = GameManager.getGameManager().getEnemy().getField().getCharacterInColumn(hovered);
                        cardInfoController.setInfo(c);
                    } catch(Exception e){
                    }
                    break;
                case 5:
                    try{
                        c = GameManager.getGameManager().getEnemy().getField().getSkillInColumn(hovered);
                        cardInfoController.setInfo(c);
                    } catch(Exception e){
                    }
                    break;
            }
        } else{
            cardInfoController.setBlankInfo();
        }

    }

    public boolean isIsGame() {
        return isGame.get();
    }

    public SimpleBooleanProperty isGameProperty() {
        return isGame;
    }

    public void setIsGame(boolean isGame) {
        this.isGame.set(isGame);
    }
}
