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
    private static String currentThemeS1 = "-fx-base: #D3D3D3;";

    private static String currentThemeS2 = "-fx-background-color: #FFFFF0;-fx-font: 25 arial;";
    private static String currentThemeS3 = "-fx-control-inner-background: #FFFFF0;-fx-font: 25 arial;";

    public static String getMessage() {
        return message;
    }

    public static String getCurrentThemeS1() {
        return currentThemeS1;
    }

    public static String getCurrentThemeS2() {
        return currentThemeS2;
    }

    public static String getCurrentThemeS3() {
        return currentThemeS3;
    }

    public static void setCurrentThemeS1(String currentThemeS1) {
        NotificationsView.currentThemeS1 = currentThemeS1;
    }

    public static void setCurrentThemeS2(String currentThemeS2) {
        NotificationsView.currentThemeS2 = currentThemeS2;
    }

    public static void setCurrentThemeS3(String currentThemeS3) {
        NotificationsView.currentThemeS3 = currentThemeS3;
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
