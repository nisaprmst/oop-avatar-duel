package com.avatarduel.util;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ConfirmBox {
    /** Gonna be used for Confirmation Boxes
     */
    static boolean answer;

    public ConfirmBox() {
    }

    public static boolean display(String title, String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(250.0D);
        Label label = new Label();
        label.setText(message);
        Button yesButton = new Button("OK");
        yesButton.setOnAction((e) -> {
            answer = true;
            window.close();
        });/*
        noButton.setOnAction((e) -> {
            answer = false;
            window.close();
        });*/
        VBox layout = new VBox(10.0D);
        layout.getChildren().addAll(new Node[]{label, yesButton});
        layout.setAlignment(Pos.CENTER);
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
        return answer;
    }
}
