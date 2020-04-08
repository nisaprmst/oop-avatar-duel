package com.avatarduel.controller;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class CardController {
    @FXML
    private ImageView card;

    public ImageView setCardImage(Image img){
        card = new ImageView(img);
        card.setRotate(90);
        return card;
    }

    public void printCard(Image img){
        System.out.println(img.impl_getUrl());
    }
}
