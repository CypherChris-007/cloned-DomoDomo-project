package edu.utsa.cs3443.marvels_domodomo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


/**
 * @author Giovanni De Leon
 * TO-DO CONTROLLER.
 * Handles the logic for the To-Do list screen.
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
    private CheckBox taskOne;
    @FXML
    private CheckBox taskTwo;
    @FXML
    private CheckBox taskThree;

    TaskManager taskManager;

    public void initialize() {
        ArrayList<Task> tasks = TaskManager.getInstance().getTasks();
        System.out.println("Loaded tasks: " + tasks.size()); // check this in console
        taskDisplay();
    }

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

    // TO-DO METHODS
    protected void taskDisplay() {
        ArrayList<Task> tasks = TaskManager.getInstance().getTasks();
        CheckBox[] boxes = {taskOne, taskTwo, taskThree};

        for (int i = 0; i < boxes.length; i++) {
            if (i < tasks.size()) {
                Task task = tasks.get(i);
                boxes[i].setText(task.getTaskName() + " +" + task.getPoints());
                boxes[i].setSelected(task.isTaskIsCompleted());
            } else {
                boxes[i].setOpacity(0); // clear unused slots
            }
        }
    }

    @FXML
    protected void onBoxClick(ActionEvent pEvent) throws IOException{
        ArrayList<Task> tasks = TaskManager.getInstance().getTasks();
        if(taskOne.isSelected()){
            Task task = tasks.get(0);
            TaskManager.getInstance().removeTask(task);
        } else if (taskTwo.isSelected()) {
            Task task = tasks.get(1);
            TaskManager.getInstance().removeTask(task);
        }else if(taskThree.isSelected()){
            Task task = tasks.get(2);
            TaskManager.getInstance().removeTask(task);
        }
    }

    @FXML
    protected void petHeartIndicator(){

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
