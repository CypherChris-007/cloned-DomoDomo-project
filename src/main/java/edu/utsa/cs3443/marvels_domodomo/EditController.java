package edu.utsa.cs3443.marvels_domodomo;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;//identify index

public class EditController {

    @FXML private Button EditID;
    @FXML private Button OptionsID;
    @FXML private Button PetID;
    @FXML private Button ToDoID;
    @FXML private Button addID;
    @FXML private Button editID;
    @FXML private Button removeID;
    @FXML private TextArea textArea;

    private static final String FILE_PATH = "data/tasks.txt";

    @FXML
    public void initialize() {

        // ── rest of initialize ──
        try {
            File dataFolder = new File("data");
            if (!dataFolder.exists()) dataFolder.mkdir();
            File taskFile = new File(FILE_PATH);
            if (!taskFile.exists()) taskFile.createNewFile();
        } catch (Exception e) {
            System.out.println("Error creating file: " + e.getMessage());
        }

        loadFromFile();
    }


    // ── File Read ─────────────────────────────────────────────────
//
//    private void loadFromFile() {
//        try {
//            String content = new String(
//                    Files.readAllBytes(Paths.get(FILE_PATH))
//            );
//            if (content.trim().isEmpty()) {
//                textArea.setText("TYPE HERE...");
//            } else {
//                textArea.setText(content);
//            }
//        } catch (Exception e) {
//            textArea.setText("TYPE HERE...");
//            System.out.println("Load failed: " + e.getMessage());
//        }
//        textArea.setEditable(false);
//    }
private void loadFromFile()
    {
        ArrayList<Task> tasks = TaskManager.getInstance().getTasks();
    if(tasks.isEmpty())
    {
        textArea.setText("Type Here . . . ");
    }
    else{
        StringBuilder display = new StringBuilder();
        for (Task task : tasks) //enhanced for-loop
        {
            display.append(task.getTaskName()).append("\n"); // only show task name
        }
        textArea.setText(display.toString().trim());
    }
        textArea.setEditable(false);

    }

    // ── File Write ────────────────────────────────────────────────

    private void saveToFile() {
        try {
            Files.write(
                    Paths.get(FILE_PATH),
                    textArea.getText().getBytes()
            );
            System.out.println("Saved to " + FILE_PATH);
        } catch (Exception e) {
            System.out.println("Save failed: " + e.getMessage());
        }
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
        switchScene("Pet-screen.fxml");
    }

    // ── Add / Edit / Remove ───────────────────────────────────────

@FXML
protected void onAddClick() {
    if (addID.getText().equals("Add")) {
        // Unlock and clear for new entry
        textArea.setEditable(true);
        textArea.clear();
        textArea.requestFocus();
        addID.setText("Save");
    } else {
        // Get what the user typed (just the task name e.g. "Code")
        String taskName = textArea.getText().trim();

        if (!taskName.isEmpty() && !taskName.equals("TYPE HERE...")) {
            // Create task with default 1 point and false completion
            Task newTask = new Task(taskName, 1, false);
            TaskManager.getInstance().addTask(newTask); // saves to file automatically
        }

        // Refresh display to show task names only
        loadFromFile();
        textArea.setEditable(false);
        addID.setText("Add");
        showMessage(addID, "Saved!");
    }
}

@FXML
protected void onEditClick2() {
    if (editID.getText().equals("Edit")) {
        // Unlock existing text for editing
        textArea.setEditable(true);
        textArea.requestFocus();
        editID.setText("Save");
    } else {
        // Get the edited lines from the text area
        String[] lines = textArea.getText().trim().split("\n");
        ArrayList<Task> currentTasks = TaskManager.getInstance().getTasks();

        for (int i = 0; i < lines.length && i < currentTasks.size(); i++) {
            String newName = lines[i].trim();
            if (!newName.isEmpty()) {
                // Keep same points and completion, just update the name
                Task oldTask = currentTasks.get(i);
                Task updatedTask = new Task(newName, oldTask.getPoints(), oldTask.isTaskIsCompleted());
                TaskManager.getInstance().editTask(oldTask, updatedTask);
            }
        }

        loadFromFile();
        textArea.setEditable(false);
        editID.setText("Edit");
        showMessage(editID, "Edited!");
    }
}


    @FXML
    protected void onRemoveClick() {
        // Get the task names currently shown in the text area
        String[] lines = textArea.getText().trim().split("\n");
        ArrayList<Task> currentTasks = TaskManager.getInstance().getTasks();

        // Find and remove each task by name match
        for (String line : lines) {
            String name = line.trim();
            for (Task task : new ArrayList<>(currentTasks)) {
                if (task.getTaskName().equals(name)) {
                    TaskManager.getInstance().removeTask(task);
                    break;
                }
            }
        }

        loadFromFile();
        textArea.setEditable(false);
        textArea.setText("TYPE HERE...");
        addID.setText("Add");
        editID.setText("Edit");
        showMessage(removeID, "Removed!");
    }

    // ── Message Helper ────────────────────────────────────────────

    private void showMessage(Button button, String message) {
        String original = button.getText();
        button.setText(message);
        PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
        pause.setOnFinished(e -> button.setText(original));
        pause.play();
    }

    // ── Scene Switch ──────────────────────────────────────────────

    private void switchScene(String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource(fxml)
        );
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) EditID.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}