module com.meow.bebrablender {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.meow.bebrablender to javafx.fxml;
    exports com.meow.bebrablender;
}