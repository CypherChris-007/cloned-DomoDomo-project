package edu.utsa.cs3443.marvels_domodomo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class OptionsController {

    // Add this at the top with your other variables
    private static ColorAdjust sharedEffect = new ColorAdjust();

    // Add this getter method so other controllers can "grab" the color
    public static ColorAdjust getSharedEffect() {
        return sharedEffect;
    }


    private static String sharedSpritePath = "/images/BGDomo/catt.png";
    public static String getSharedSpritePath() { return sharedSpritePath; }



    @FXML
    private Button editButton;
    @FXML
    private Button optionsButton;
    @FXML
    private Button petButton;
    @FXML
    private Button toDoButton;

    // Added for background change
    @FXML private ImageView backgroundImage; // ← new


    //Added for sprite change
    @FXML private ImageView spriteImage; // ← new
    @FXML private MenuButton colorMenuButton; // ← new (add fx:id="colorMenuButton" in FXML)
    @FXML private MenuButton spriteMenuButton; // ← new (add fx:id="colorMenuButton" in FXML)


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
        if (backgroundImage != null) {
            backgroundImage.setEffect(sharedEffect);
        }
        // Wire up menu items to background color changes
        for (MenuItem item : colorMenuButton.getItems()) {
            item.setOnAction(e -> applyColorScheme(item.getText()));
        }


        // Wire sprite menu items
        if (spriteMenuButton != null) {
            for (MenuItem item : spriteMenuButton.getItems()) {
                item.setOnAction(e -> applySpriteScheme(item.getText()));
            }
        }
    }

    private void applyColorScheme(String color) {

        switch (color) {
            case "Red" -> {
                sharedEffect.setHue(-1.0);       // shift green → red
                sharedEffect.setSaturation(0.3);
            }
            case "Blue" -> {
                sharedEffect.setHue(0.5);        // shift green → blue
                sharedEffect.setSaturation(0.2);
            }
            default -> sharedEffect.setHue(0.0); // no change (original green)
        }

        backgroundImage.setEffect(sharedEffect);
    }



    private void applySpriteScheme(String spriteName) {
        switch (spriteName) {
            case "Orange" -> sharedSpritePath = "/images/BGDomo/orangeBoy.png";
            case "Rowdy"  -> sharedSpritePath = "/images/BGDomo/rowdypixel.png";
            default       -> sharedSpritePath = "/images/BGDomo/catt.png";
        }
        spriteImage.setImage(new Image(getClass().getResourceAsStream(sharedSpritePath)));
    }



    private void switchScene(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) optionsButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
