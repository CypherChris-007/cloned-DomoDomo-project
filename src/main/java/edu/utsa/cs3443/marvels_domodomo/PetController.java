/**
 * @author: Bidisha
 *
 * @purpose: PetController handles the Pet screen.
 * Reads in dialogue.txt and randomizes a line in the file
 * to display a motivational message to the user upon Pet button's nav bar click.
 */
package edu.utsa.cs3443.marvels_domodomo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PetController {
    private List<String> dialogueLines = new ArrayList<>();
    private Random random = new Random();

//    @FXML private Label motivationLabel;

    // Nav buttons — wire these in Pet-screen.fxml the same way other screens do
    @FXML private Label statusLabel; // changes the text upon petButton click
    @FXML private Button optionsButton;
    @FXML private Button toDoButton;
    @FXML private Button editButton;
    @FXML private Button petButton;
    // Added for background change
    @FXML private ImageView backgroundImage;
    @FXML private ImageView spriteImage; // ← new


    /**
     * Called automatically by JavaFX after the FXML is loaded.
     * Calculates completion % and sets the motivational message.
     */
    @FXML
    public void initialize() {

        loadDialogue(); // Fills the list from dialogue.txt
        updateLabel();  // Picks a random line immediately in dialogue.txt
        // test if fx:id is valid
        if (backgroundImage == null) System.out.println("ERROR: backgroundImage is null!");
        if (spriteImage == null) System.out.println("ERROR: spriteImage is null!");



        // Added for background change, getter call
        if (backgroundImage != null) {
            backgroundImage.setEffect(OptionsController.getSharedEffect());
        }
        // Added for sprite change, getter call from resources directory
        if (spriteImage != null) {
            spriteImage.setImage(new Image(getClass().getResourceAsStream(OptionsController.getSharedSpritePath())));
        }






//        double completionPct = TaskManager.getInstance().getCompletionPercent();
//        double remaining = 100.0 - completionPct;
//
//        String message;
//
//        if (TaskManager.getInstance().getTasks().isEmpty()) {
//            message = "No tasks yet — add some in the Edit tab!";
//        } else if (completionPct == 100.0) {
//            message = "You did it! All goals complete today! 🎉";
//        } else {
//            // Matches the Figma format which goes likee "You are 77.3% away from achieving all your goals today — don't give up!"
//            message = String.format(
//                    "You are %.1f%% away from achieving all your goals today — don't give up!",
//                    remaining
//            );
//        }
//
//        motivationLabel.setText(message);
    }

    // ── Tab Navigation ────────────────────────────────────────────
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
        updateLabel();

        switchScene("Pet-screen.fxml");
    }



    // Will load a dialogue from the dialogue.txt file
    private void loadDialogue() {
        File file = new File("data/dialogue.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            dialogueLines.clear(); // Prevents duplicates if refreshed multiple times
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    dialogueLines.add(line);
                }
            }

        } catch (Exception e) {
            // prints the exact path the app is looking at if it fails
            System.out.println("Error: Could not find file at " + file.getAbsolutePath());
            statusLabel.setText("Keep going! You're doing great.");
        }
    }

    // Will update the view's text at random within dialogue.txt upon call(click)
    private void updateLabel() {
        if (dialogueLines != null && !dialogueLines.isEmpty()) {
            int randomIndex = random.nextInt(dialogueLines.size());
            String randomLine = dialogueLines.get(randomIndex);

            statusLabel.setText(randomLine);
        } else {
            // Fallback text if the file was empty or didn't load
            statusLabel.setText("You're doing great! Keep it up!");
        }
    }

    private void switchScene(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) petButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


}