package edu.utsa.cs3443.marvels_domodomo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class DomoDomoApplication extends MainScreenController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
