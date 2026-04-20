package edu.utsa.cs3443.marvels_domodomo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * @author Giovanni De Leon
 * TO-DO CONTROLLER.
 * Handles the
 *
 * */

public class MainScreenController {
    @FXML
    private Button editButton;
    @FXML
    private Button optionsButton;
    @FXML
    private Button petButton;
    @FXML
    private Button toDoButton;

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
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(fxml)
        );
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) editButton.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


}