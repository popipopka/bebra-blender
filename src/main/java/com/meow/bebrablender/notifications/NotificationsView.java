package com.meow.bebrablender.notifications;

import com.meow.bebrablender.BebraApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class NotificationsView {
    private static String message;

    public static String getMessage() {
        return message;
    }

    public static void setMessage(String message) {
        NotificationsView.message = message;
    }
    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void showMessage() {
        stage = new Stage();
        AnchorPane viewport = null;
        try {
            viewport = FXMLLoader.load(Objects.requireNonNull(BebraApplication.class.getResource("notifications.fxml")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scene scene = new Scene(viewport);
        stage.setMinWidth(800);
        stage.setMinHeight(300);
        viewport.prefWidthProperty().bind(scene.widthProperty());
        viewport.prefHeightProperty().bind(scene.heightProperty());

        stage.setTitle("BebraMessage");
        stage.setScene(scene);
        stage.show();
    }
}
