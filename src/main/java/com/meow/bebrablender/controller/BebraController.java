package com.meow.bebrablender.controller;

import com.meow.bebrablender.math.vectors.Vector3d;
import com.meow.bebrablender.model.Model;
import com.meow.bebrablender.notifications.NotificationsView;
import com.meow.bebrablender.objreader.ObjReader;
import com.meow.bebrablender.objwriter.ObjWriter;
import com.meow.bebrablender.render_engine.Camera;
import com.meow.bebrablender.render_engine.ModelNormalizer;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class BebraController {
    final private float TRANSLATION = 0.5F;
    @FXML
    private Button fileButton;
    @FXML
    private Button camerasButton;
    @FXML
    private Button modelsButton;
    @FXML
    private Button scaleButton;
    @FXML
    private Button rotateButton;
    @FXML
    private Button translateButton;
    @FXML
    private Button saveModelButton;
    @FXML
    private Button deleteVertexButton;
    @FXML
    private Button deletePolygonButton;
    @FXML
    private Button selectColorButton;
    @FXML
    private Button addCameraButton;
    @FXML
    private Button nextCameraButton;
    @FXML
    private Button deleteCameraButton;
    @FXML
    private Button whiteThemeButton;
    @FXML
    private Button darkThemeButton;
    @FXML
    private Button redThemeButton;
    @FXML
    private Button openModelButton;
    @FXML
    private Button applyScaleButton;
    @FXML
    private Button applyRotateButton;
    @FXML
    private Button applyTranslateButton;
    @FXML
    private Button backButton;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField fieldXTF;
    @FXML
    private TextField fieldYTF;
    @FXML
    private TextField fieldZTF;
    @FXML
    private TextField xTF;
    @FXML
    private TextField yTF;
    @FXML
    private TextField zTF;
    @FXML
    private ColorPicker colorPicker;
    private ListView<String> modelListView;
    private ListView<String> cameraListView;
    @FXML
    private Label label;
    private String currentThemeS1 = new String();
    private String currentThemeS2 = new String();
    private String whiteThemeS1 = "-fx-base: #D3D3D3;";
    private String whiteThemeS2 = "-fx-background-color: #FFFFF0;-fx-font: 25 arial;";
    private String darkThemeS1 = "-fx-base: #808080;";
    private String darkThemeS2 = "-fx-background-color: #696969;-fx-font: 25 arial;";
    private String redThemeS1 = "-fx-base: #A52A2A;";
    private String redThemeS2 = "-fx-background-color: #800000;-fx-font: 25 arial;";


    @FXML
    private Canvas canvas;

    private Model mesh = null;

    private Camera camera = new Camera(
            new Vector3d(0, 0, 100),
            new Vector3d(0, 0, 0),
            1.0F, 1, 0.01F, 100);

    private Timeline timeline;

    @FXML
    private void initialize() {
        anchorPane.setStyle("-fx-base: #D3D3D3;");
        buttonControl();

        timeline = new Timeline();
        timeline.setCycleCount(Animation.INDEFINITE);

        KeyFrame frame = new KeyFrame(Duration.millis(15), event -> {
            double width = canvas.getWidth();
            double height = canvas.getHeight();

            canvas.getGraphicsContext2D().clearRect(0, 0, width, height);
//            camera.setAspectRatio((float) (width / height));

            if (mesh != null) {
//                RenderEngine.render(canvas.getGraphicsContext2D(), camera, mesh, (int) width, (int) height);
            }
        });

        timeline.getKeyFrames().add(frame);
        timeline.play();
    }

    private void hideApplyXYZ() {
        applyScaleButton.setVisible(false);
        applyRotateButton.setVisible(false);
        applyTranslateButton.setVisible(false);
        backButton.setVisible(false);
        fieldXTF.setVisible(false);
        fieldYTF.setVisible(false);
        fieldZTF.setVisible(false);
        xTF.setVisible(false);
        yTF.setVisible(false);
        zTF.setVisible(false);
    }

    private void backModelSettings() {
        hideApplyXYZ();
        colorPicker.setVisible(false);
        scaleButton.setVisible(true);
        rotateButton.setVisible(true);
        translateButton.setVisible(true);
        saveModelButton.setVisible(true);
        deleteVertexButton.setVisible(true);
        deletePolygonButton.setVisible(true);
        selectColorButton.setVisible(true);
    }

    private void auxiliaryForFieldsXYZ() {
        backButton.setVisible(true);
        fieldXTF.setVisible(true);
        fieldYTF.setVisible(true);
        fieldZTF.setVisible(true);
        xTF.setVisible(true);
        yTF.setVisible(true);
        zTF.setVisible(true);

        hideSettingsModel();
    }

    private void hideSettingsModel() {
        scaleButton.setVisible(false);
        rotateButton.setVisible(false);
        translateButton.setVisible(false);
        saveModelButton.setVisible(false);
        deleteVertexButton.setVisible(false);
        deletePolygonButton.setVisible(false);
        selectColorButton.setVisible(false);
    }

    private void changeTheme() {
        anchorPane.setStyle(currentThemeS1);
        fileButton.setStyle(currentThemeS2);
        camerasButton.setStyle(currentThemeS2);
        ;
        modelsButton.setStyle(currentThemeS2);
        scaleButton.setStyle(currentThemeS2);
        rotateButton.setStyle(currentThemeS2);
        translateButton.setStyle(currentThemeS2);
        saveModelButton.setStyle(currentThemeS2);
        deleteVertexButton.setStyle(currentThemeS2);
        deletePolygonButton.setStyle(currentThemeS2);
        selectColorButton.setStyle(currentThemeS2);
        addCameraButton.setStyle(currentThemeS2);
        nextCameraButton.setStyle(currentThemeS2);
        deleteCameraButton.setStyle(currentThemeS2);

        openModelButton.setStyle(currentThemeS2);
        applyScaleButton.setStyle(currentThemeS2);
        applyRotateButton.setStyle(currentThemeS2);
        applyTranslateButton.setStyle(currentThemeS2);
        backButton.setStyle(currentThemeS2);
        NotificationsView.setCurrentThemeS1(currentThemeS1);
        NotificationsView.setCurrentThemeS2(currentThemeS2);

    }

    public void changeColor(ActionEvent event) {
        Color color = colorPicker.getValue();
    }

    private void buttonControl() {
        final boolean[] cameraSetting = {false};
        final boolean[] modelSetting = {false};
        final boolean[] fileSetting = {false};
        FileChooser fileChooser = new FileChooser();
        // создаем список объектов
        ObservableList<String> langs = FXCollections.observableArrayList("Java", "JavaScript", "C#", "Python");
        modelListView = new ListView<>(langs);
        modelListView.setMinHeight(320);
        modelListView.setMaxHeight(320);
        modelListView.setMinWidth(300);
        modelListView.setLayoutX(1250);
        modelListView.setLayoutY(60);
        // получаем модель выбора элементов
        MultipleSelectionModel<String> langsSelectionModel = modelListView.getSelectionModel();
        // устанавливаем слушатель для отслеживания изменений
        langsSelectionModel.selectedItemProperty().addListener(new ChangeListener<String>() {

            public void changed(ObservableValue<? extends String> changed, String oldValue, String newValue) {

                label.setText("Selected: " + newValue);
            }
        });
        modelListView.setVisible(false);
        anchorPane.getChildren().add(modelListView);

        ObservableList<String> langs1 = FXCollections.observableArrayList("Camera bebra", "Camera 2");
        cameraListView = new ListView<>(langs1);
        cameraListView.setMinHeight(320);
        cameraListView.setMaxHeight(320);
        cameraListView.setMinWidth(300);
        cameraListView.setLayoutX(0);
        cameraListView.setLayoutY(120);
        // получаем модель выбора элементов
        MultipleSelectionModel<String> langsSelectionModel1 = cameraListView.getSelectionModel();
        // устанавливаем слушатель для отслеживания изменений
        langsSelectionModel1.selectedItemProperty().addListener(new ChangeListener<String>() {

            public void changed(ObservableValue<? extends String> changed, String oldValue, String newValue) {

            }
        });
        cameraListView.setVisible(false);
        anchorPane.getChildren().add(cameraListView);

        colorPicker.setStyle(currentThemeS2);
        colorPicker.setMinWidth(300);
        colorPicker.setMinHeight(60);
        colorPicker.setLayoutX(1250);
        colorPicker.setLayoutY(540);
        colorPicker.setVisible(false);

        fieldXTF.setText("");
        fieldXTF.setStyle("-fx-background-color: #FFFFF0;-fx-font: 25 arial;");
        fieldXTF.setMinHeight(60);
        fieldXTF.setMaxWidth(150);
        fieldXTF.setLayoutX(1299);
        fieldXTF.setLayoutY(390);
        fieldXTF.setVisible(false);

        fieldYTF.setText("");
        fieldYTF.setStyle("-fx-background-color: #FFFFF0;-fx-font: 25 arial;");
        fieldYTF.setMinHeight(60);
        fieldYTF.setMaxWidth(150);
        fieldYTF.setLayoutX(1299);
        fieldYTF.setLayoutY(490);
        fieldYTF.setVisible(false);

        fieldZTF.setText("");
        fieldZTF.setStyle("-fx-background-color: #FFFFF0;-fx-font: 25 arial;");
        fieldZTF.setMinHeight(60);
        fieldZTF.setMaxWidth(150);
        fieldZTF.setLayoutX(1299);
        fieldZTF.setLayoutY(590);
        fieldZTF.setVisible(false);

        xTF.setText("X: ");
        xTF.setStyle("-fx-background-color: #FFFFF0;-fx-font: 25 arial;");
        xTF.setMinHeight(60);
        xTF.setMaxWidth(51);
        xTF.setLayoutX(1250);
        xTF.setLayoutY(390);
        xTF.setVisible(false);
        xTF.setEditable(false);

        yTF.setText("Y: ");
        yTF.setStyle("-fx-background-color: #FFFFF0;-fx-font: 25 arial;");
        yTF.setMinHeight(60);
        yTF.setMaxWidth(51);
        yTF.setLayoutX(1250);
        yTF.setLayoutY(490);
        yTF.setVisible(false);
        yTF.setEditable(false);

        zTF.setText("Z: ");
        zTF.setStyle("-fx-background-color: #FFFFF0;-fx-font: 25 arial;");
        zTF.setMinHeight(60);
        zTF.setMaxWidth(51);
        zTF.setLayoutX(1250);
        zTF.setLayoutY(590);
        zTF.setVisible(false);
        zTF.setEditable(false);

        applyScaleButton.setText("Apply");
        applyScaleButton.setStyle("-fx-font: 25 arial; -fx-base: #FFFFF0;");
        applyScaleButton.setMinWidth(300);
        applyScaleButton.setMinHeight(60);
        applyScaleButton.setLayoutX(1250);
        applyScaleButton.setLayoutY(660);
        applyScaleButton.setDisable(false);
        applyScaleButton.setVisible(false);
        applyScaleButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });

        applyRotateButton.setText("Apply");
        applyRotateButton.setStyle("-fx-font: 25 arial; -fx-base: #FFFFF0;");
        applyRotateButton.setMinWidth(300);
        applyRotateButton.setMinHeight(60);
        applyRotateButton.setLayoutX(1250);
        applyRotateButton.setLayoutY(660);
        applyRotateButton.setDisable(false);
        applyRotateButton.setVisible(false);
        applyRotateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });

        applyTranslateButton.setText("Apply");
        applyTranslateButton.setStyle("-fx-font: 25 arial; -fx-base: #FFFFF0;");
        applyTranslateButton.setMinWidth(300);
        applyTranslateButton.setMinHeight(60);
        applyTranslateButton.setLayoutX(1250);
        applyTranslateButton.setLayoutY(660);
        applyTranslateButton.setDisable(false);
        applyTranslateButton.setVisible(false);
        applyTranslateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });

        backButton.setText("Back");
        backButton.setStyle("-fx-font: 25 arial; -fx-base: #FFFFF0;");
        backButton.setMinWidth(300);
        backButton.setMinHeight(60);
        backButton.setLayoutX(1250);
        backButton.setLayoutY(740);
        backButton.setDisable(false);
        backButton.setVisible(false);
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                backModelSettings();
            }
        });

        fileButton.setText("File");
        fileButton.setStyle("-fx-font: 25 arial; -fx-base: #FFFFF0;");
        fileButton.setMinWidth(300);
        fileButton.setMinHeight(60);
        fileButton.setLayoutX(0);
        fileButton.setLayoutY(0);
        fileButton.setDisable(false);
        fileButton.setVisible(true);
        fileButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (fileSetting[0]) {
                    whiteThemeButton.setVisible(false);
                    darkThemeButton.setVisible(false);
                    redThemeButton.setVisible(false);
                    openModelButton.setVisible(false);
                    fileSetting[0] = false;
                } else {
                    whiteThemeButton.setVisible(true);
                    darkThemeButton.setVisible(true);
                    redThemeButton.setVisible(true);
                    openModelButton.setVisible(true);
                    fileSetting[0] = true;
                }
            }
        });
        openModelButton.setText("Open model");
        openModelButton.setStyle("-fx-font: 25 arial; -fx-base: #FFFFF0;");
        openModelButton.setMinWidth(300);
        openModelButton.setMinHeight(60);
        openModelButton.setLayoutX(300);
        openModelButton.setLayoutY(0);
        openModelButton.setDisable(false);
        openModelButton.setVisible(false);
        openModelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage stage = new Stage();
                File selectedFile = fileChooser.showOpenDialog(stage);
            }
        });
        whiteThemeButton.setText("W");
        whiteThemeButton.setStyle("-fx-font: 25 arial; -fx-base: #FFFFF0;");
        whiteThemeButton.setMinWidth(100);
        whiteThemeButton.setMinHeight(60);
        whiteThemeButton.setLayoutX(600);
        whiteThemeButton.setLayoutY(0);
        whiteThemeButton.setDisable(false);
        whiteThemeButton.setVisible(false);
        whiteThemeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                currentThemeS1 = whiteThemeS1;
                currentThemeS2 = whiteThemeS2;
                changeTheme();
            }
        });
        darkThemeButton.setText("D");
        darkThemeButton.setStyle("-fx-font: 25 arial; -fx-base: #A9A9A9;");
        darkThemeButton.setMinWidth(100);
        darkThemeButton.setMinHeight(60);
        darkThemeButton.setLayoutX(700);
        darkThemeButton.setLayoutY(0);
        darkThemeButton.setDisable(false);
        darkThemeButton.setVisible(false);
        darkThemeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                currentThemeS1 = darkThemeS1;
                currentThemeS2 = darkThemeS2;
                changeTheme();
            }
        });
        redThemeButton.setText("R");
        redThemeButton.setStyle("-fx-font: 25 arial; -fx-base: #A52A2A;");
        redThemeButton.setMinWidth(100);
        redThemeButton.setMinHeight(60);
        redThemeButton.setLayoutX(800);
        redThemeButton.setLayoutY(0);
        redThemeButton.setDisable(false);
        redThemeButton.setVisible(false);
        redThemeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                currentThemeS1 = redThemeS1;
                currentThemeS2 = redThemeS2;
                changeTheme();
            }
        });

        camerasButton.setText("Cameras");
        camerasButton.setStyle("-fx-font: 25 arial; -fx-base: #FFFFF0;");
        camerasButton.setMinWidth(300);
        camerasButton.setMinHeight(60);
        camerasButton.setLayoutX(0);
        camerasButton.setLayoutY(60);
        camerasButton.setDisable(false);
        camerasButton.setVisible(true);
        camerasButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (cameraSetting[0]) {
                    addCameraButton.setVisible(false);
                    nextCameraButton.setVisible(false);
                    deleteCameraButton.setVisible(false);
                    cameraListView.setVisible(false);
                    cameraSetting[0] = false;
                } else {
                    addCameraButton.setVisible(true);
                    nextCameraButton.setVisible(true);
                    deleteCameraButton.setVisible(true);
                    cameraListView.setVisible(true);
                    cameraSetting[0] = true;
                }
            }
        });
        addCameraButton.setText("Add camera");
        addCameraButton.setStyle("-fx-font: 25 arial; -fx-base: #FFFFF0;");
        addCameraButton.setMinWidth(300);
        addCameraButton.setMinHeight(60);
        addCameraButton.setLayoutX(0);
        addCameraButton.setLayoutY(440);
        addCameraButton.setDisable(false);
        addCameraButton.setVisible(false);
        addCameraButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });
        nextCameraButton.setText("Next camera");
        nextCameraButton.setStyle("-fx-font: 25 arial; -fx-base: #FFFFF0;");
        nextCameraButton.setMinWidth(300);
        nextCameraButton.setMinHeight(60);
        nextCameraButton.setLayoutX(0);
        nextCameraButton.setLayoutY(500);
        nextCameraButton.setDisable(false);
        nextCameraButton.setVisible(false);
        nextCameraButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });
        deleteCameraButton.setText("Delete camera");
        deleteCameraButton.setStyle("-fx-font: 25 arial; -fx-base: #FFFFF0;");
        deleteCameraButton.setMinWidth(300);
        deleteCameraButton.setMinHeight(60);
        deleteCameraButton.setLayoutX(0);
        deleteCameraButton.setLayoutY(560);
        deleteCameraButton.setDisable(true);
        deleteCameraButton.setVisible(false);
        deleteCameraButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

            }
        });
        modelsButton.setText("Models");
        modelsButton.setStyle("-fx-font: 25 arial; -fx-base: #FFFFF0;");
        modelsButton.setMinWidth(300);
        modelsButton.setMinHeight(60);
        modelsButton.setLayoutX(1250);
        modelsButton.setLayoutY(0);
        modelsButton.setDisable(false);
        modelsButton.setVisible(true);
        modelsButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if (modelSetting[0]) {
                    scaleButton.setVisible(false);
                    translateButton.setVisible(false);
                    rotateButton.setVisible(false);
                    saveModelButton.setVisible(false);
                    deleteVertexButton.setVisible(false);
                    deletePolygonButton.setVisible(false);
                    selectColorButton.setVisible(false);
                    modelListView.setVisible(false);
                    modelSetting[0] = false;
                    hideApplyXYZ();
                } else {
                    scaleButton.setVisible(true);
                    translateButton.setVisible(true);
                    rotateButton.setVisible(true);
                    saveModelButton.setVisible(true);
                    deleteVertexButton.setVisible(true);
                    deletePolygonButton.setVisible(true);
                    selectColorButton.setVisible(true);
                    modelListView.setVisible(true);
                    modelSetting[0] = true;
                }
                langs.add("bebra");
            }
        });

        scaleButton.setText("Scale");
        scaleButton.setStyle("-fx-font: 25 arial; -fx-base: #FFFFF0;");
        scaleButton.setMinWidth(300);
        scaleButton.setMinHeight(60);
        scaleButton.setLayoutX(1250);
        scaleButton.setLayoutY(380);
        scaleButton.setDisable(false);
        scaleButton.setVisible(false);
        scaleButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                auxiliaryForFieldsXYZ();
                applyScaleButton.setVisible(true);
            }
        });
        rotateButton.setText("Rotate");
        rotateButton.setStyle("-fx-font: 25 arial; -fx-base: #FFFFF0;");
        rotateButton.setMinWidth(300);
        rotateButton.setMinHeight(60);
        rotateButton.setLayoutX(1250);
        rotateButton.setLayoutY(440);
        rotateButton.setDisable(true);
        rotateButton.setVisible(false);
        rotateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                auxiliaryForFieldsXYZ();
                applyRotateButton.setVisible(true);
            }
        });
        translateButton.setText("Translate");
        translateButton.setStyle("-fx-font: 25 arial; -fx-base: #FFFFF0;");
        translateButton.setMinWidth(300);
        translateButton.setMinHeight(60);
        translateButton.setLayoutX(1250);
        translateButton.setLayoutY(500);
        translateButton.setDisable(true);
        translateButton.setVisible(false);
        translateButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                auxiliaryForFieldsXYZ();
                applyTranslateButton.setVisible(true);
            }
        });
        saveModelButton.setText("Save model");
        saveModelButton.setStyle("-fx-font: 25 arial; -fx-base: #FFFFF0;");
        saveModelButton.setMinWidth(300);
        saveModelButton.setMinHeight(60);
        saveModelButton.setLayoutX(1250);
        saveModelButton.setLayoutY(560);
        saveModelButton.setDisable(true);
        saveModelButton.setVisible(false);
        saveModelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                String file = "New file.obj";
                ObjWriter objWriter = new ObjWriter();
                objWriter.writeModelToObjFile(file, mesh);
            }
        });
        deleteVertexButton.setText("Delete vertex");
        deleteVertexButton.setStyle("-fx-font: 25 arial; -fx-base: #FFFFF0;");
        deleteVertexButton.setMinWidth(300);
        deleteVertexButton.setMinHeight(60);
        deleteVertexButton.setLayoutX(1250);
        deleteVertexButton.setLayoutY(620);
        deleteVertexButton.setDisable(false);
        deleteVertexButton.setVisible(false);
        deleteVertexButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                NotificationsView.setMessage("Error: The developers are invalid." + "\n" + "They didn't have time to do it." + "\n" + "Wait for the next updates");
                NotificationsView.showMessage();
            }
        });
        deletePolygonButton.setText("Delete polygon");
        deletePolygonButton.setStyle("-fx-font: 25 arial; -fx-base: #FFFFF0;");
        deletePolygonButton.setMinWidth(300);
        deletePolygonButton.setMinHeight(60);
        deletePolygonButton.setLayoutX(1250);
        deletePolygonButton.setLayoutY(680);
        deletePolygonButton.setDisable(true);
        deletePolygonButton.setVisible(false);
        deletePolygonButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            }
        });
        selectColorButton.setText("Color model");
        selectColorButton.setStyle("-fx-font: 25 arial; -fx-base: #FFFFF0;");
        selectColorButton.setMinWidth(300);
        selectColorButton.setMinHeight(60);
        selectColorButton.setLayoutX(1250);
        selectColorButton.setLayoutY(740);
        selectColorButton.setDisable(false);
        selectColorButton.setVisible(false);
        selectColorButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                hideSettingsModel();
                colorPicker.setVisible(true);
                backButton.setVisible(true);
            }
        });
    }

    @FXML
    private void onOpenModelMenuItemClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Model (*.obj)", "*.obj"));
        fileChooser.setTitle("Load Model");

        File file = fileChooser.showOpenDialog((Stage) canvas.getScene().getWindow());
        if (file == null) {
            return;
        }

        Path fileName = Path.of(file.getAbsolutePath());

        try {
            ObjReader objReader = new ObjReader(fileName);
            Model initialModel = objReader.read();
            ModelNormalizer.
                    triangulateAndRecalculateModelNormals(
                            initialModel.getVertices(),
                            initialModel.getPolygons(),
                            initialModel.getNormals()
                    );
            mesh = initialModel;
            // todo: обработка ошибок
        } catch (IOException exception) {

        }
    }

    @FXML
    public void handleCameraForward(ActionEvent actionEvent) {
        camera.movePosition(new Vector3d(0, 0, -TRANSLATION));
    }

    @FXML
    public void handleCameraBackward(ActionEvent actionEvent) {
        camera.movePosition(new Vector3d(0, 0, TRANSLATION));
    }

    @FXML
    public void handleCameraLeft(ActionEvent actionEvent) {
        camera.movePosition(new Vector3d(TRANSLATION, 0, 0));
    }

    @FXML
    public void handleCameraRight(ActionEvent actionEvent) {
        camera.movePosition(new Vector3d(-TRANSLATION, 0, 0));
    }

    @FXML
    public void handleCameraUp(ActionEvent actionEvent) {
        camera.movePosition(new Vector3d(0, TRANSLATION, 0));
    }

    @FXML
    public void handleCameraDown(ActionEvent actionEvent) {
        camera.movePosition(new Vector3d(0, -TRANSLATION, 0));
    }
}