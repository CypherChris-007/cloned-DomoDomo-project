package edu.utsa.cs3443.marvels_domodomo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class OptionsController {
    @FXML
    private Button editButton;
    @FXML
    private Button optionsButton;
    @FXML
    private Button petButton;
    @FXML
    private Button toDoButton;
    @FXML private ImageView backgroundImage; // ← new
    @FXML private MenuButton colorMenuButton; // ← new (add fx:id="colorMenuButton" in FXML)

    // TOP TAB BUTTONS
    @FXML
    protected void onOptionsClick() throws Exception {
        switchScene("Options-screen.fxml");
    }

    @FXML
    protected void onToDoClick() throws Exception {
        switchScene("Main-screen.fxml");
    }

    @FXML
    protected void onEditClick() throws Exception {
        switchScene("Edit-screen.fxml");
    }

    @FXML
    protected void onPetClick() throws Exception {
        switchScene("Pet-screen.fxml");
    }

    @FXML
    public void initialize() {
        // Safety check — will print to console if something is still null
        if (backgroundImage == null) System.out.println("ERROR: backgroundImage is null!");
        if (colorMenuButton == null)  System.out.println("ERROR: colorMenuButton is null!");

        // Wire up menu items to color changes
        for (MenuItem item : colorMenuButton.getItems()) {
            item.setOnAction(e -> applyColorScheme(item.getText()));
        }
    }

    private void applyColorScheme(String color) {
        ColorAdjust colorAdjust = new ColorAdjust();

        switch (color) {
            case "Red" -> {
                colorAdjust.setHue(-1.0);       // shift green → red
                colorAdjust.setSaturation(0.3);
            }
            case "Blue" -> {
                colorAdjust.setHue(0.5);        // shift green → blue
                colorAdjust.setSaturation(0.2);
            }
            default -> colorAdjust.setHue(0.0); // no change (original green)
        }

        backgroundImage.setEffect(colorAdjust);
    }

    private void switchScene(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) optionsButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
