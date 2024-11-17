module telas {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.desktop;

    exports inicializa;
    opens inicializa to javafx.fxml;
    exports viewcontroller;
    opens viewcontroller to javafx.fxml;
}