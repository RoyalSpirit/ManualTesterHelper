module demo.app {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;

    exports demo;
    exports demo.ui.controls;

    opens demo.ui to javafx.fxml;
    opens demo.ui.components to javafx.fxml;
}