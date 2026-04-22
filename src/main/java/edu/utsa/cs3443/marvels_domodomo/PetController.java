/**
 * @author: Bidisha
 *
 * @purpose: PetController handles the Pet screen.
 * Reads task completion % from the shared TaskManager singleton
 * and displays a motivational message to the user.
 */
package edu.utsa.cs3443.marvels_domodomo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PetController {

//    @FXML private Label motivationLabel;

    // Nav buttons — wire these in Pet-screen.fxml the same way other screens do
    @FXML private Button optionsButton;
    @FXML private Button toDoButton;
    @FXML private Button editButton;
    @FXML private Button petButton;

    /**
     * Called automatically by JavaFX after the FXML is loaded.
     * Calculates completion % and sets the motivational message.
     */
//    @FXML
//    public void initialize() {
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
//    }

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
        switchScene("Pet-screen.fxml");
    }

    private void switchScene(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) petButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


}