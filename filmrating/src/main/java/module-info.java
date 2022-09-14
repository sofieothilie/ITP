module app {
    requires javafx.controls;
    requires javafx.fxml;

    opens filmrating to javafx.graphics, javafx.fxml;
}
