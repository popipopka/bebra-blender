module com.meow.bebrablender {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.meow.bebrablender to javafx.fxml;
    exports com.meow.bebrablender;
    exports com.meow.bebrablender.controller;
    opens com.meow.bebrablender.controller to javafx.fxml;
}