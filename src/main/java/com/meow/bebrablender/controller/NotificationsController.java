package com.meow.bebrablender.controller;

import com.meow.bebrablender.notifications.NotificationsView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

public class NotificationsController {
    @FXML
    Button notifButton;
    @FXML
    TextArea notifArea;
    @FXML
    AnchorPane notifAPane;


    @FXML
    private void initialize(){
        notifAPane.setStyle(NotificationsView.getCurrentThemeS1());

        notifArea.setText(NotificationsView.getMessage());
        notifArea.setStyle(NotificationsView.getCurrentThemeS3());
        notifArea.setMaxHeight(180);
        notifArea.setMinWidth(600);
        notifArea.setLayoutX(90);
        notifArea.setLayoutY(10);
        notifArea.setVisible(true);
        notifArea.setEditable(false);

        notifButton.setText("OK");
        notifButton.setStyle(NotificationsView.getCurrentThemeS2());
        notifButton.setLayoutX(365);
        notifButton.setLayoutY(200);
        notifButton.setDisable(false);
        notifButton.setVisible(true);
        notifButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                NotificationsView.getStage().close();
            }
        });
    }
}
