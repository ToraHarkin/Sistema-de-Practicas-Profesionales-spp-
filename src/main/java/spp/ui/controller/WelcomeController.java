package spp.ui.controller;

import java.io.IOException;
import javafx.fxml.FXML;
import spp.ui.App;
import spp.ui.exception.ViewLoadException;


public class WelcomeController {  
    @FXML
    private void handleStartSetUp() {
        try {
            App.setRoot("RegisterAdmin");
        } catch (IOException e) {
            throw new ViewLoadException("RegisterAdmin", e);
        }
    }
}
