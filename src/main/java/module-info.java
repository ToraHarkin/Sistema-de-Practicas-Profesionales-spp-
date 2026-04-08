module spp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens spp.ui.controller to javafx.fxml;
    
    exports spp.ui;
}