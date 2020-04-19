package com.avatarduel.controller;

import com.avatarduel.cards.Card;
import com.avatarduel.cards.CardType;
import com.avatarduel.cards.Element;
import com.avatarduel.cards.LandCard;
import com.avatarduel.cards.characters.CharacterCard;
import com.avatarduel.cards.skills.AuraSkill;
import com.avatarduel.cards.skills.PowerUpSkill;
import com.avatarduel.cards.skills.Skill;
import com.avatarduel.cards.skills.SkillCard;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;
import javafx.scene.text.TextFlow;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * CardInfoController is a Controller for CardInfo
 * <p>
 *     It holds all information about a card including:
 *     1. Image
 *     2. Name
 *     3. Type and Element
 *     4. Status (Power, Attack, Defense) if available
 * </p>
 */
public class CardInfoController implements Initializable {
    @FXML
    ImageView cardImageContainer;
    @FXML
    Label cardName;
    @FXML
    TextFlow cardStatus;
    @FXML
    Label cardDescription;


    /**
     * Initialize CardInfoController by setting blank info
     * @param location location URL
     * @param resources resource bundle
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setBlankInfo();
    }

    /**
     * Set the info for a specific card
     * @param c Card to render the info
     */
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
        setName(c);
        setCardStatus(c);
        setDescription(c);
    }

    /**
     * Set blank info
     */
    public void setBlankInfo(){
        String imagePath = "BlankCard.png";
        setcardImage(imagePath);
        clearInfo();
    }

    /**
     * Clear the current info for replace purpose
     */
    private void clearInfo(){
        cardName.setText("");
        cardDescription.setText("");
        cardStatus.getChildren().clear();
    }

    /**
     * Set card image to specific image
     * @param imagepath The path to the corresponding card image
     */
    private void setcardImage(String imagepath){
        String path = getClass().getResource("../card/image/" + imagepath).toString();
        Image img = new Image(path, 250, 350, false, true);
        cardImageContainer.setImage(img);
    }


    /**
     * Apply a certain style to the label
     * @param label FXML label component to be styled
     */
    private void setLabelStyle(Label label){
        label.setMinHeight(22);
        label.setMaxHeight(22);
        label.setMaxWidth(280);
        label.setMinWidth(280);
        label.setFont(cardDescription.getFont());
    }


    /**
     * Determine the type of certain card
     * @param c Card to be determined type
     * @return String of the Card Type
     */
    private String determineType(Card c){
        if(c.getCardType() == CardType.CHARACTER){
            return "Character";
        } else if(c.getCardType() == CardType.SKILL){
            SkillCard skill = (SkillCard) c;
            if(skill.getSkillType() == Skill.AURA){
                return "Aura Skill";
            } else if(skill.getSkillType() == Skill.POWER){
                return "Power Up Skill";
            } else{
                return "Destroy Skill";
            }
        } else{
            return "Land";
        }
    }

    /**
     * Determine the element of certain card
     * @param c Card to be determined element
     * @return String of the Card Element
     */
    private String determineElement(Card c){
        if(c.getElement() == Element.AIR){
            return "Air";
        } else if(c.getElement() == Element.WATER){
            return "Water";
        } else if(c.getElement() == Element.FIRE){
            return "Fire";
        } else if(c.getElement() == Element.EARTH) {
            return "Earth";
        } else{
            return "Energybending";
        }
    }

    /**
     * Set card status to a card's status
     * @param c Card to be shown the status
     */
    private void setCardStatus(Card c){
        String type = determineType(c);
        String element = determineElement(c);
        Label detailType = new Label(type + "/" + element);
        setLabelStyle(detailType);

        this.cardStatus.getChildren().add(detailType);
        if(c.getCardType() == CardType.CHARACTER){
            CharacterCard character = (CharacterCard) c;
            Label power = new Label("Power : " + character.getPower());
            Label attack = new Label("Attack : " + character.getAttack());
            Label defense = new Label("Defense : " + character.getDefense());
            setLabelStyle(power);
            setLabelStyle(attack);
            setLabelStyle(defense);
            this.cardStatus.getChildren().addAll(power, attack, defense);
        } else if(c.getCardType() == CardType.SKILL){
            SkillCard skill = (SkillCard) c;
            Label power = new Label("Power : " + skill.getPower());
            setLabelStyle(power);
            this.cardStatus.getChildren().addAll(power);
            if(skill.getSkillType() == Skill.AURA){
                AuraSkill auraSkill = (AuraSkill) skill;
                Label attack = new Label("Attack : " + auraSkill.getAtkPoint());
                Label defense = new Label("Defense : " + auraSkill.getDefPoint());
                setLabelStyle(attack);
                setLabelStyle(defense);
                this.cardStatus.getChildren().addAll(attack, defense);
            }
        }

    }

    /**
     * Set card name to a card's name
     * @param c Card to be shown the name
     */
    private void setName(Card c){
        cardName.setText(c.getName());
    }

    /**
     * Set card description to a card's description
     * @param c Card to be shown the description
     */
    private void setDescription(Card c){
        cardDescription.setText(c.getDescription());
    }
}
