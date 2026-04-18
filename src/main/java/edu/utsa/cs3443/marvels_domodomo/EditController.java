package edu.utsa.cs3443.marvels_domodomo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class EditController {

    @FXML
    private Button EditID;
    @FXML
    private Button OptionsID;
    @FXML
    private Button PetID;
    @FXML
    private Button ToDoID;
    @FXML
    private Button addID;
    @FXML
    private Button editID;
    @FXML
    private Button removeID;
    @FXML
    private TextArea textArea;

    private boolean isAdding = false;
    private boolean isEditing = false;
    private String savedText = "";



    @FXML
    public void initialize() {
        textArea.setEditable(false);
        textArea.setText("TYPE HERE...");
    }

    // ── Tab Navigation ──────────────────────────────────────────

    @FXML
    protected void onOptionsClick() throws Exception {
        switchScene("Options-screen.fxml");
    }

    @FXML
    protected void onToDoClick() throws Exception {
        switchScene("ToDo-screen.fxml");
    }

    @FXML
    protected void onEditClick() throws Exception {
        switchScene("Edit-screen.fxml");
    }

    @FXML
    protected void onPetClick() throws Exception {
        switchScene("Pet-screen.fxml");
    }

    // ── Add / Edit / Remove ─────────────────────────────────────

    @FXML
    protected void onAddClick() {
        if (!isAdding) {
            // Unlock and clear for new entry
            textArea.setEditable(true);
            textArea.clear();
            textArea.requestFocus();
            addID.setText("Save");
            isAdding = true;
        } else {
            // Save entry
            savedText = textArea.getText();
            textArea.setEditable(false);
            addID.setText("Add");
            isAdding = false;
            System.out.println("Saved: " + savedText);
        }
    }

    @FXML
    protected void onEditClick2() {
        if (!isEditing) {
            // Unlock existing text for editing
            textArea.setEditable(true);
            textArea.requestFocus();
            editID.setText("Save");
            isEditing = true;
        } else {
            // Save edits
            savedText = textArea.getText();
            textArea.setEditable(false);
            editID.setText("Edit");
            isEditing = false;
            System.out.println("Edited: " + savedText);
        }
    }

    @FXML
    protected void onRemoveClick() {
        savedText = "";
        textArea.clear();
        textArea.setEditable(false);
        textArea.setText("TYPE HERE...");
        addID.setText("Add");
        editID.setText("Edit");
        isAdding = false;
        isEditing = false;
    }

    // ── Helper ──────────────────────────────────────────────────

    private void switchScene(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(fxml)
        );
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) EditID.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    // ── Pop-up ──────────────────────────────────────────────────


}