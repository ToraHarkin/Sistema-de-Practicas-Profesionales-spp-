module spp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.commons.dbcp2;

    opens spp.ui.controller to javafx.fxml;
    
    exports spp.ui;
}